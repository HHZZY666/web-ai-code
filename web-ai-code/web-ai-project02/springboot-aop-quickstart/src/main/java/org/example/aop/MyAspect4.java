package org.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class MyAspect4 {
    //前置通知
    @Before("execution(* org.example.service.impl.*.*(..))")
    public void before(){
        log.info("MyAspect4 -> before ...");
    }

    //后置通知
    @After("execution(* org.example.service.impl..*(..))")
    public void after(){
        log.info("MyAspect4 -> after ...");
    }
}
