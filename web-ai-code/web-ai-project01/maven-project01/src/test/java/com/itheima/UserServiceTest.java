package com.itheima;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    @Test
    public void testGetaAge(){
        UserService userService = new UserService();
        Integer age = userService.getAge("100000200010011011");
        System.out.println(age);
    }

    @Test
    public void testGetGenderWithAssert(){
        UserService userService = new UserService();
        String gender = userService.getGender("100000200110011011");
        Assertions.assertEquals("男", gender,"性别获取逻辑有问题");
    }
}
