package com.codegym.c11.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class AccountAspect {

    private Logger logger = LoggerFactory.getLogger(AccountAspect.class);

    @Before("execution(public * com.codegym.c11.service.sf.account.AccountServiceImpl.*(..))")
    public void before(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info(String.format("[CMS] Before method: %s.%s%s", className, method, args));
    }

    @AfterReturning(pointcut = "execution(public * com.codegym.c11.service.sf.account.AccountServiceImpl.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info(String.format("[CMS] Return succesfully: %s.%s%s", className, method, args), result);
    }

    @AfterThrowing(pointcut = "execution(public * com.codegym.c11.service.sf.account.AccountServiceImpl.*(..))", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info(String.format("[CMS] Exception occurred: %s.%s%s: %s", className, method, args, e.getMessage()));
    }

}
