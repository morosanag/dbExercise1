package com.db.exercise2.task;

import com.db.exercise2.service.PingPongService;

import java.util.TimerTask;

public class StopPingPongTask extends TimerTask {

    private final PingPongService pingPongService;

    public StopPingPongTask(PingPongService pingPongService) {
        this.pingPongService = pingPongService;
    }

    @Override
    public void run() {
        pingPongService.setEnd(true);
    }

}
