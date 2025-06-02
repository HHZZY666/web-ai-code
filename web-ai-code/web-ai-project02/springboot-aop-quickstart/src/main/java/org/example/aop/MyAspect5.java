package org.example.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspect5 {

    @Before("@annotation(org.example.anno.LogOperation) ")
    public  void before() {
        System.out.println("MyAspect5.before()");
    }
}
