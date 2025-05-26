package org.example.service;

import org.example.pojo.Emp;
import org.example.pojo.PageResult;

public interface EmpService {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageResult<Emp> page(Integer page, Integer pageSize);
}
