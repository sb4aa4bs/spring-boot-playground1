package com.apeiron.abs2.springbootplayground1.model;

import lombok.Data;

@Data
public class Response {

    int score;
    String scoreDescription;


    public Response(int score, String scoreDescription) {
        this.score = score;
        this.scoreDescription = scoreDescription;
    }

    public Response() {
    }
}
