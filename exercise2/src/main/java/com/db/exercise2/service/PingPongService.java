package com.db.exercise2.service;

import com.db.exercise2.task.PingPongTask;
import com.db.exercise2.task.StopPingPongTask;
import lombok.Data;
import org.slf4j.Logger;

import java.util.Timer;

@Data
public class PingPongService {

    private final long durationMs;
    private final Logger logger;
    private final PingPongTask task1;
    private final PingPongTask task2;
    // Variable that indicates if the 2 tasks are still active
    public volatile boolean end;
    // This variable indicates which task (ping or pong) will write
    public volatile boolean turn;

    public PingPongService(long durationMs, Logger logger) {
        this.durationMs = durationMs;
        this.end = false;
        this.turn = true;
        this.logger = logger;
        this.task1 = new PingPongTask(true, this);
        this.task2 = new PingPongTask(false, this);
    }

    public void process() {
        Timer timer = new Timer("StopPingPong");
        // This task will toggle the value of end variable, switching off the 2 tasks
        StopPingPongTask stopPingPongTask = new StopPingPongTask(this);

        this.task1.start();
        this.task2.start();

        timer.schedule(stopPingPongTask, durationMs);

        try {
            this.task1.join();
            this.task2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
