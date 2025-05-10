package com.itheima;

import org.junit.jupiter.api.Test;

public class UserServiceTest {

    @Test
    public void testGetaAge(){
        UserService userService = new UserService();
        Integer age = userService.getAge("100000200010011011");
        System.out.println(age);
    }
}
