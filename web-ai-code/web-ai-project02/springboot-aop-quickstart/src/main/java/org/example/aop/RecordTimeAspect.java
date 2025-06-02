package org.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect
public class RecordTimeAspect {

    @Around( "execution(* org.example.service.impl.*.*(..))")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        //1.记录开始时间
        long begin = System.currentTimeMillis();

        //2.执行目标方法
        Object result = pjp.proceed();

        //3.记录结束时间
        long end = System.currentTimeMillis();
        log.info("方法 {} 耗时：{}ms",pjp.getSignature(), end-begin);
        return result;

    }
}
