package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {

    //原始分页查询实现
    /**
     * 查询总信息数
     */
//    @Select("select count(*) from emp left join dept d on emp.dept_id = d.id")
//    public Long count();

    /**
     * 分页查询
     */
//    @Select("Select e.* , d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by update_time desc limit #{start} , #{pageSize};")
//    public List<Emp> list(Integer start, Integer pageSize);

    //@Select("Select e.* , d.name deptName from emp e left join dept d on e.dept_id = d.id order by update_time desc")
    //public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);
    public List<Emp> list(EmpQueryParam empQueryParam);
}
