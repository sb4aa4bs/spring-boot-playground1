package com.apeiron.abs2.springbootplayground1.controller;


import com.apeiron.abs2.springbootplayground1.exception.NegativeScoreException;
import com.apeiron.abs2.springbootplayground1.model.ScoreResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/v1/playground1")
@Slf4j
public class DemoController {

    @GetMapping("/score")
    public ResponseEntity<ScoreResponse> score(@RequestHeader Map<String, String> httpHeaders){
        ResponseEntity entity=null;
        int score=new Random().nextInt();
        log.info("Request Headers after Applying the HeaderUpdateFilter : {}", httpHeaders.toString());
        log.info("Score : {}", score );
        ScoreResponse scoreResponse = new ScoreResponse(score, "AcceptableScore", httpHeaders.get(
                "correlationId"));
        if(score < 1) {
            throw new NegativeScoreException("NegativeScoreException : Score Generated is Negative");
        }
        // return 200 OK in case if the generated Score is a positive integer
        return new ResponseEntity<ScoreResponse>(scoreResponse, HttpStatus.OK);
    }
}
