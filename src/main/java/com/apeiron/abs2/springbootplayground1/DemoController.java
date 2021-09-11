package com.apeiron.abs2.springbootplayground1;


import com.apeiron.abs2.springbootplayground1.exception.NegativeScoreException;
import com.apeiron.abs2.springbootplayground1.model.ScoreResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/v1/playground1")
public class DemoController {

    @GetMapping("/score")
    public ResponseEntity<ScoreResponse> score(){
        ResponseEntity entity=null;
        ScoreResponse pingResponse = new ScoreResponse(0, "ping success");
        int score=new Random().nextInt();
        pingResponse.setScore(score);
        pingResponse.setScoreDescription("AcceptableScore");
        if(score < 1) {
            pingResponse.setScore(score);
            pingResponse.setScoreDescription("NegativeScore");
            throw new NegativeScoreException();
        }
        //using ResponseEntity
        return new ResponseEntity<ScoreResponse>(pingResponse, HttpStatus.OK);
    }
}
