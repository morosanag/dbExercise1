package com.db.exercise2;

import com.db.exercise2.service.PingPongService;
import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args) {
        PingPongService runner = new PingPongService(5_000, LoggerFactory.getLogger(PingPongService.class));
        runner.process();
    }

}
