package org.example.service.impl;

import org.example.dao.UserDao;
import org.example.pojo.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Primary
@Component
public class UserServiceImpl2 implements UserService {

    @Autowired//应用程序运行时自动查询该类型bean对象并赋值给该成员变量
    private UserDao userDao;

    @Override
    public List<User> findAll() {


        //调用Dao，获取数据
        List<String> lines =  userDao.findAll();

        //2.解析用户信息，将用户信息封装成User对象 -> list集合
        List<User> userList = lines.stream().map(line -> {
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];
            String name = parts[3];
            Integer age = Integer.parseInt(parts[4]);
            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new User(id + 200, username, password, name, age, updateTime);
        }).toList();

        return userList;
    }
}
