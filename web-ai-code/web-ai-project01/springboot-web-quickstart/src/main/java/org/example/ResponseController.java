package org.example;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ResponseController {

    /**
     * 方法一：HttpServletResponse 设置响应数据
     */
    @RequestMapping("/response")
    public void response(HttpServletResponse response) throws IOException {
        //1.设置响应状态码
        response.setStatus(401);

        //2.设置响应头
        response.setHeader("name", "zhangsan");

        //3.设置响应体
        response.getWriter().write("<h1>hello response</h1>");
    }

    /**
    * 方法二：ResponseEntity 封装响应数据 - Spring 中提供的方式
    */
    @RequestMapping("/response2")
    public ResponseEntity<String> response2() {
        return ResponseEntity.status(401)
                .header("name", "lisi")
                .body("<h1>hello response</h1>");
    }

}
