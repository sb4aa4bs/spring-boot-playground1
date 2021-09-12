package com.apeiron.abs2.springbootplayground1.model;

import lombok.Data;

@Data
public class Response {

    int score;
    String scoreDescription;
    String correlationId;


    public Response(int score, String scoreDescription, String correlationId) {
        this.score = score;
        this.scoreDescription = scoreDescription;
        this.correlationId = correlationId;
    }

    public Response() {
    }
}
