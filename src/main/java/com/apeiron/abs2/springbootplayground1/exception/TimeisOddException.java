package com.apeiron.abs2.springbootplayground1.exception;

import lombok.Data;

@Data
public class TimeisOddException extends RuntimeException {


    public TimeisOddException(String message) {
        super(message);
    }
}
