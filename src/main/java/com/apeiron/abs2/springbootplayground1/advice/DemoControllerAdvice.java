package com.apeiron.abs2.springbootplayground1.advice;

import com.apeiron.abs2.springbootplayground1.exception.NegativeScoreException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DemoControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NegativeScoreException.class})
    public ResponseEntity<Object> handleBadRequest(RuntimeException re, WebRequest wr){
       return handleExceptionInternal(re, re.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, wr);
    }

}
