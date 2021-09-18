package com.apeiron.abs2.springbootplayground1.model;

import lombok.Data;

@Data
public class ScoreResponse extends Response{

    public ScoreResponse(int score, String scoreDescription, String correlationId) {
        super(score, scoreDescription, correlationId);
    }
}


//import lombok.Data;
//
//@Data
//public class ScoreResponse{
//    int score;
//    String scoreDescription;
//    String correlationId;
//
//
//    public ScoreResponse(int score, String scoreDescription, String correlationId) {
//        this.score = score;
//        this.scoreDescription = scoreDescription;
//        this.correlationId = correlationId;
//    }
//
//    public ScoreResponse() {
//    }
//}