package org.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.JwtUtils;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        //1.获取请求路径
         String requestURI = request.getRequestURI();

        //2.判断请求路径是否为登录接口,如果是，放行
         if (requestURI.contains("/login")){
             log.info( "登录请求");
              filterChain.doFilter(request,response);
               return;
         }

        //3.获取请求头中的 token
        String token = request.getHeader("token");

        //4.判断token是否为空，如果为空，返回未登录信息（401）
         if (token == null || token.isEmpty()){
             log.info("令牌为空，响应401");
              response.setStatus(401);
              return;
         }

        //5.如果有token，校验token,如果校验失败， 返回未登录信息（401）
        try{
            JwtUtils.parseToken(token);
        }catch ( Exception e){
            log.info("令牌非法，响应401");
            response.setStatus(401);
            return;
        }

        //6.校验成功，放行
         log.info( "令牌合法，放行");
         filterChain.doFilter(request,response);
    }

}

