package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.User;

import java.util.List;

@Mapper //应用程序在运行时，会自动为该接口创建一个实现类对象（代理对象），并且会将该实现类对象自动注入到IOC容器中 - bean
public interface UserMapper {

    //查询所有用户
    //@Select("select id, username, password, name, age from user")
    public List<User> findAll();

    @Delete("delete from user where id = #{id}")
    public void deleteById(Integer id);

    @Insert("insert into user(username, password, name, age) values(#{username}, #{password}, #{name}, #{age})")
    public void insert(User user);

    @Update("update user set username = #{username},password = #{password},name = #{name},age = #{age} where id = #{id}")
    public void update(User user);

    @Select("select id, username, password, name, age from user where username = #{username} and password = #{password}")
    public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
