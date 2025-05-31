package com.db.exercise2.task;

import com.db.exercise2.service.PingPongService;

public class PingPongTask extends Thread {

    private final boolean isPing;
    private final PingPongService pingPongService;

    public PingPongTask(boolean isPing, PingPongService pingPongService) {
        this.isPing = isPing;
        this.pingPongService = pingPongService;
    }

    /**
     * Lock-free implementation for the ping-pong logs checking the volatile variables
     * end - to see if we still need to print ping-pong
     * turn - to check if the current thread needs to print either ping (if isPing = true)
     * or pong (if isPing = false)
     */
    @Override
    public void run() {
        while (!pingPongService.end) {
            if (pingPongService.turn == isPing) {
                pingPongService.getLogger().info(isPing ? "ping" : "pong", System.currentTimeMillis());
                pingPongService.turn = !isPing;
            }
        }
    }

}
