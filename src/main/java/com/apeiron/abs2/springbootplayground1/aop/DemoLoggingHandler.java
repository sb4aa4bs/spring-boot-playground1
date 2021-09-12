package com.apeiron.abs2.springbootplayground1.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class DemoLoggingHandler {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController * || @org.springframework.stereotype" +
            ".Service *)")
    public void restController(){}

    @Pointcut("execution(* *.*(..))")
    protected void allMethod(){}

    // logPublicOperations

    // logAnyFunctionWithinResource

    // logAllOperation

    // Request Logging

    // Response Logging

    // Controller Performance Logging
    // @Around("execution(* com.apeiron.abs2.springbootplayground1.controller.DemoController.*(..))")
    @Around("restController() && allMethod()")
    public Object logAroundControllerPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object response = joinPoint.proceed();
        log.info(joinPoint.getSignature().getDeclaringTypeName() +"." +joinPoint.getSignature().getName() +"() got executed in " + (System.currentTimeMillis()-startTime) +" milliseconds");
        return response;
    }

    // Service Impl Performance Logging
    @Around("execution(* com.apeiron.abs2.springbootplayground1.service.impl.DemoServiceImpl.score(..))") // Service
    // Impl method is not logging the time taken to execute, currently this method is not working
    public Object logAroundServiceImplPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object response = joinPoint.proceed();
        log.info(joinPoint.getSignature().getDeclaringTypeName() +"." +joinPoint.getSignature().getName() +"() got executed in " + (System.currentTimeMillis()-startTime) +" milliseconds");
        return response;
    }


    // Controller Exception Logging
   //  @AfterThrowing(value = "execution(* com.apeiron.abs2.springbootplayground1.controller.DemoController.*(..))", throwing="exception")
    @AfterThrowing(pointcut = "restController() && allMethod()", throwing = "exception")
    public void logAroundControllerException(JoinPoint joinPoint, Throwable exception) {
        log.info(joinPoint.getSignature().getDeclaringTypeName() +"." +joinPoint.getSignature().getName() +"() " +
                "Exception thrown " +exception.getMessage());
    }

    // Request Logging

    // Response Logging


}