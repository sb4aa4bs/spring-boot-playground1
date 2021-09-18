package com.apeiron.abs2.springbootplayground1.service.impl;

import com.apeiron.abs2.springbootplayground1.exception.NegativeScoreException;
import com.apeiron.abs2.springbootplayground1.exception.TimeisOddException;
import com.apeiron.abs2.springbootplayground1.model.Echo;
import com.apeiron.abs2.springbootplayground1.model.ScoreResponse;
import com.apeiron.abs2.springbootplayground1.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public ScoreResponse score(String correlationId) {
        int score=new Random().nextInt();
        log.info("Generated Score : {}", score );
        ScoreResponse scoreResponse = new ScoreResponse(score, "AcceptableScore", correlationId);
        if(score < 1) {
            throw new NegativeScoreException("NegativeScoreException : Score Generated is Negative");
        }
        return scoreResponse;
    }

    @Override
    public Echo greetHelloX(Echo echo1) {
        Echo echo = new Echo();
        echo.setX("Hello " + echo1.getX());
        if (System.currentTimeMillis() % 2 == 0)
            return echo;
        else
            throw new TimeisOddException("TimeisOddException: Current Time is not divisible by 2");
    }
}
