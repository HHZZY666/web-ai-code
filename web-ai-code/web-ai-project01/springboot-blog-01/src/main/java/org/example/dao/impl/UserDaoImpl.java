package org.example.dao.impl;

import cn.hutool.core.io.IoUtil;
import org.example.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public List<String> findAll() {
        //1.加载并读取user.txt文件，获取用户信息
        //InputStream in = new FileInputStream(new File("D:/code/web-ai-code/web-ai-project01/springboot-web-01/src/main/resources/user.txt"));
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());
        return lines;
    }
}
