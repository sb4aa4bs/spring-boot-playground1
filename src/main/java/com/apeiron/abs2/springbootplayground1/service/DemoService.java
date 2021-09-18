package com.apeiron.abs2.springbootplayground1.service;

import com.apeiron.abs2.springbootplayground1.model.Echo;
import com.apeiron.abs2.springbootplayground1.model.ScoreResponse;

public interface DemoService {

    ScoreResponse score(String correlationId);

    Echo greetHelloX(Echo echo1);

}
