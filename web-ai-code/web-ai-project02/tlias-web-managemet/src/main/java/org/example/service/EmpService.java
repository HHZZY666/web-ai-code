package org.example.service;

import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public interface EmpService {
    /**
     * 分页查询
     * @return
     */
    //PageResult<Emp> page(Integer page, Integer pageSize,String name, Integer gender, LocalDate begin, LocalDate end);
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工信息
     * @param emp
     */
    void save(Emp emp);
}
