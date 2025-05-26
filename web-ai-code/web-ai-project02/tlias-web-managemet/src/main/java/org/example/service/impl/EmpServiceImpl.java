package org.example.service.impl;

import org.example.mapper.EmpMapper;
import org.example.pojo.Emp;
import org.example.pojo.PageResult;
import org.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //1.调用mapper查询总记录数
        Long total = empMapper.count();

        //2.调用mapper查询结果列表
        Integer start = (page-1) * pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);

        //3.封装结果 PageResult
        return new PageResult<Emp>(total,  rows);
    }
}
