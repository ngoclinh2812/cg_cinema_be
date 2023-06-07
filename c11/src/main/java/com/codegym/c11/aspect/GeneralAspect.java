package com.codegym.c11.aspect;

import com.codegym.c11.model.dto.ITheaterDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class GeneralAspect {

    @Before("execution(public * com.codegym.c11.service.sf.theater.TheaterService.*(..))")
    public void before(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println(String.format("[CMS] Before method: %s.%s%s", className, method, args));
    }

    @AfterReturning(pointcut = "execution(public * com.codegym.c11.service.sf.theater.TheaterService.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println(String.format("[CMS] Return succesfully: %s.%s%s", className, method, args));
        System.out.println("[CMS] Result: " + result);
    }

    @AfterThrowing(pointcut = "execution(public * com.codegym.c11.service.sf.theater.TheaterService.*(..))", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println(String.format("[CMS] Exception occurred: %s.%s%s: %s", className, method, args, e.getMessage()));
    }

}
