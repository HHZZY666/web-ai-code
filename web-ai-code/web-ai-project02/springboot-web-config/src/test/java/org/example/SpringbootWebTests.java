package org.example;

import cn.hutool.core.io.FileUtil;
import com.example.TokenParser;
import com.google.gson.Gson;
import org.example.pojo.Result;
import org.example.utils.AliyunOSSOperator;
import org.example.utils.AliyunOSSProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.awt.*;
import java.io.File;


@SpringBootTest
class SpringbootWebTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @Autowired
    private Gson gson;

    @Test
    public void testJson(){
        System.out.println(gson.toJson(Result.success("Hello Gson")));
    }

    @Test
    public void testScope() {
        for (int i = 0; i < 1000; i++){
            Object deptController = applicationContext.getBean("deptController");
            System.out.println(deptController);
        }
    }

    @Test
    public void testUpload() throws Exception {
        File file = new File("C:\\Users\\昭阳\\Desktop\\Snipaste_2025-06-02_19-19-14.png");
        String url = aliyunOSSOperator.upload(FileUtil.readBytes(file),"1.jpg");
        System.out.println(url);

    }

    @Autowired
    private TokenParser tokenParser;

    @Test
    public void testTokenParser(){
        tokenParser.parse();
    }

}
