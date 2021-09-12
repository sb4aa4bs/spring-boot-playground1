package com.apeiron.abs2.springbootplayground1.exception;

public class NegativeScoreException extends RuntimeException {

    public NegativeScoreException(String message) {
        super(message);
    }
}
