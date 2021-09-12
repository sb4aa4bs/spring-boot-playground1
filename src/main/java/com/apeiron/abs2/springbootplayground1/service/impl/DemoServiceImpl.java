package com.apeiron.abs2.springbootplayground1.service.impl;

import com.apeiron.abs2.springbootplayground1.exception.NegativeScoreException;
import com.apeiron.abs2.springbootplayground1.model.ScoreResponse;
import com.apeiron.abs2.springbootplayground1.service.DemoService;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class DemoServiceImpl implements DemoService {
    @Override
    public ScoreResponse score(String correlationId) {
        int score=new Random().nextInt();
        log.info("Score : {}", score );
        ScoreResponse scoreResponse = new ScoreResponse(score, "AcceptableScore", correlationId);
        if(score < 1) {
            throw new NegativeScoreException("NegativeScoreException : Score Generated is Negative");
        }
        return scoreResponse;
    }
}
