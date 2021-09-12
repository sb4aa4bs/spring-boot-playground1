package com.apeiron.abs2.springbootplayground1;


import com.apeiron.abs2.springbootplayground1.exception.NegativeScoreException;
import com.apeiron.abs2.springbootplayground1.model.ScoreResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/v1/playground1")
@Slf4j
public class DemoController {

    @GetMapping("/score")
    public ResponseEntity<ScoreResponse> score(){
        ResponseEntity entity=null;
        ScoreResponse pingResponse = new ScoreResponse(0, "ping success");
        int score=new Random().nextInt();
        log.info("Score : {}", score );
        pingResponse.setScore(score);
        pingResponse.setScoreDescription("AcceptableScore");
        if(score < 1) {
            pingResponse.setScore(score);
            pingResponse.setScoreDescription("NegativeScore");
            throw new NegativeScoreException("NegativeScoreException : Score Generated is Negative");
        }
        // return 200 OK in case if the generated Score is a positive integer
        return new ResponseEntity<ScoreResponse>(pingResponse, HttpStatus.OK);
    }
}
