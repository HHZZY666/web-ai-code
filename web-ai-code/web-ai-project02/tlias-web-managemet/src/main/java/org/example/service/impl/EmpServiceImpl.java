package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.EmpExprMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.*;
import org.example.service.EmpLogService;
import org.example.service.EmpService;
import org.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper  empExprMapper;

    @Autowired
    private EmpLogService empLogService;
    /**
     * 原始分页查询操作
     * @param page
     * @param pageSize
     * @return
     */
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        //1.调用mapper查询总记录数
//        Long total = empMapper.count();
//
//        //2.调用mapper查询结果列表
//        Integer start = (page-1) * pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//
//        //3.封装结果 PageResult
//        return new PageResult<Emp>(total,  rows);

    /**
     * PageHelper分页查询操作
     * @return
     */
    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        //1.设置分页参数
        PageHelper.startPage(page, pageSize);

        //2.执行查询
        List<Emp> empList = empMapper.list(name,gender,begin,end);

        //3.解析结果并封装
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }*/
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        //2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);

        //3.解析结果并封装
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    @Transactional(rollbackFor = {Exception.class}) //事务管理注解 -- 默认出现运行时异常（RuntimeException），回滚事务
    @Override
    public void save(Emp emp) throws Exception {
        try {
            //1.保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //2.保存员工工作经历信息
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)){
                //遍历集合，为empId赋值
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工：" + emp);
            empLogService.insertLog(empLog);
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //1.批量删除员工基本信息
        empMapper.deleteByIds(ids);

        //2.批量删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1.根据id修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2.根据id修改员工工作经历信息
        //2.1 先删除
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        //2.2 再添加
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public LoginInfo login(Emp emp) {
        //1.调用mapper接口，根据用户名和密码查询工作信息
        Emp e = empMapper.selectByUsernameAndPassword(emp);

        //2.判断是否存在该员工，如果存在，组装登录成功信息
        if (e != null){
            log.info("登录成功,员工信息：{}",e);
            //生成JWT令牌
            Map <String, Object> cliams = new HashMap<>();
            cliams.put("id", e.getId());
            cliams.put("username", e.getUsername());
            String jwt = JwtUtils.generateToken(cliams);
            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt);
        }

        //3.不存在，返回null
        return null;
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }
}
















