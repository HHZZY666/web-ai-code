package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    /**
     * 新增员工基本信息
     * @param emp
     */
    @Options( useGeneratedKeys = true,keyProperty = "id")//获取到生成的主键值 --主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 批量删除员工信息
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id查询员工信息及员工工作经历信息
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 修改员工信息
     * @param emp
     */
    void updateById(Emp emp);

    /**
     * 统计员工职位人数
     * @return
     */
    @MapKey("pos")
    List<Map<String ,Object>> countEmpJobData();

    /**
     * 统计员工性别人数
     * @return
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    /**
     * 根据用户名和密码查询员工信息
     * @param emp
     * @return
     */
    @Select("select id, username, name from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}















