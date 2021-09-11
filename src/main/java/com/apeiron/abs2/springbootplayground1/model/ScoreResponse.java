package com.apeiron.abs2.springbootplayground1.model;

import lombok.Data;

@Data
public class ScoreResponse extends Response{

    public ScoreResponse(int errorCode, String errorDescription) {
        super(errorCode, errorDescription);
    }
}
