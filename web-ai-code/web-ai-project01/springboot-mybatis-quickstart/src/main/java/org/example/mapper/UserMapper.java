package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.User;

import java.util.List;

@Mapper //应用程序在运行时，会自动为该接口创建一个实现类对象（代理对象），并且会将该实现类对象自动注入到IOC容器中 - bean
public interface UserMapper {

    //查询所有用户
    @Select("select id, username, password, name, age from user")
    public List<User> findAll();


}
