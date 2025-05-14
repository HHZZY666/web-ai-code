package org.example;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    @RequestMapping("/request")
    public String request(HttpServletRequest request){
        //获取请求方式
        String method = request.getMethod();
        System.out.println(
                "method: " + method
        );

        //获取请求url地址
        String url = request.getRequestURL().toString();
        System.out.println(
                "url: " + url
        );

        //获取uri地址
        String uri = request.getRequestURI();
        System.out.println(
                "uri: " + uri
        );

        //获取请求参数-name,age
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        System.out.println(
                "name: " + name + " age: " + age
        );

        //获取请求协议
        String protocol = request.getProtocol();
        System.out.println(
                "protocol: " + protocol
        );

        //获取请求头
        String header = request.getHeader("User-Agent");
        System.out.println(
                "header: " + header
        );

        return "OK";
    }
}
