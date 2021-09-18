package com.apeiron.abs2.springbootplayground1.controller;


import com.apeiron.abs2.springbootplayground1.model.Echo;
import com.apeiron.abs2.springbootplayground1.model.ScoreResponse;
import com.apeiron.abs2.springbootplayground1.service.impl.DemoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/playground1")
@Slf4j
public class DemoController {

    @GetMapping("/score")
    public ResponseEntity<ScoreResponse> score(@RequestHeader Map<String, String> httpHeaders) {
        ResponseEntity entity = null;
        log.info("Request Headers after Applying the HeaderUpdateFilter : {}", httpHeaders.toString());
        // return 200 OK in case if the generated Score is a positive integer
        return new ResponseEntity<ScoreResponse>(new DemoServiceImpl().score(httpHeaders.get("correlationId")), HttpStatus.OK);
    }

    @PostMapping(value = "/echo")
    public ResponseEntity<Echo> greetHelloX(@RequestBody Echo echo1) {
        return new ResponseEntity<Echo>(new DemoServiceImpl().greetHelloX(echo1), HttpStatus.OK);
    }


}
