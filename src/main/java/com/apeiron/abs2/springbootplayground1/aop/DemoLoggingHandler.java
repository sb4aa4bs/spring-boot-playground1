package com.apeiron.abs2.springbootplayground1.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    @Pointcut("execution(public * *(..))")
    protected void loggingPublicOperation() {
    }

    // logAnyFunctionWithinResource
    @Pointcut("execution(* *.*(..))")
    protected void loggingAllOperation() {
    }

    // logAllOperation
    @Pointcut("within(com.apeiron.abs2.springbootplayground1..*)")
    private void logAnyFunctionWithinResource() {
    }

    // Controller Performance Logging
    // @Around("execution(* com.apeiron.abs2.springbootplayground1.controller.DemoController.*(..))")
    @Around("restController() && allMethod()")
    public Object logAroundControllerPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
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

    // Response Logging
    @AfterReturning(pointcut = "restController() && allMethod()", returning="response")
    //@AfterReturning(pointcut = "execution(* com.apeiron.abs2.springbootplayground1.service.impl.DemoServiceImpl.score())", returning="response")
    public void logResponse(JoinPoint joinPoint, Object response){
        log.info(joinPoint.getSignature().getDeclaringTypeName() +"." +joinPoint.getSignature().getName() +" Http " +
                "Response Payload : " +response.toString());

    }

    // Request Logging is not working
    @Before("restController() && allMethod() && args(..,request)")
    public void logRequest(JoinPoint joinPoint, HttpServletRequest request){
        log.info("logRequest");
        try {
            log.info(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + " Http " +
                    "Request Payload : " + extractRequest(request.getReader()));
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        log.info(joinPoint.getSignature().getDeclaringTypeName() +"." +joinPoint.getSignature().getName() +" Http " +
                "Request Headers " +extractHeader(request));
    }

    // Service Impl Performance Logging, Impl method is not logging the time taken to execute, currently this method is not working
    @Around("execution(* com.apeiron.abs2.springbootplayground1.service.impl.DemoServiceImpl.score(..))") // Service
    public Object logAroundServiceImplPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object response = joinPoint.proceed();
        log.info(joinPoint.getSignature().getDeclaringTypeName() +"." +joinPoint.getSignature().getName() +"() got executed in " + (System.currentTimeMillis()-startTime) +" milliseconds");
        return response;
    }

    // utilities goes below

    public static String extractRequest(BufferedReader reader){
        String line;
        StringBuilder response = new StringBuilder();
        try {
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        return response.toString();
    }

    // not completely implemented
    public Map<String, String> extractHeader(HttpServletRequest request){
        log.info("extractHeader");
        Map<String, String> headers = new HashMap<String, String>();
        return headers;
    }
}