package com.db.exercise2.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PingPongServiceTest {

    @Mock
    private Logger logger;

    @Captor
    private ArgumentCaptor<String> logLineCaptor;

    @Captor
    private ArgumentCaptor<Long> logLineTimestampCaptor;

    @Test
    public void testLoggerCalls() {
        // Give
        long durationMs = 1_000;
        PingPongService pingPongService = new PingPongService(durationMs, logger);

        // When
        pingPongService.process();
        verify(logger, atLeastOnce()).info(logLineCaptor.capture(), anyLong());

        // Then
        checkPingPongLogLines(logLineCaptor.getAllValues());
    }

    @Test
    public void testLoggerCallsTimeInterval() {
        // Give
        long durationMs = 5_000;
        PingPongService pingPongService = new PingPongService(durationMs, logger);

        // When
        pingPongService.process();
        verify(logger, atLeastOnce()).info(anyString(), logLineTimestampCaptor.capture());

        // Then
        List<Long> allValues = logLineTimestampCaptor.getAllValues();
        long actualDuration = allValues.get(allValues.size() - 1) - allValues.get(0);
        // Make sure the time is within the accepted range (with an error of 1%)
        assertTrue(actualDuration >= durationMs && actualDuration <= durationMs * 1.01);
    }

    /**
     * Method that checks if there's an alternation of ping and pong across all the logger calls
     * @param logLines
     */
    private void checkPingPongLogLines(List<String> logLines) {
        boolean isPing = true;
        for(String logLine : logLines) {
            assertEquals(isPing ? "ping" : "pong", logLine);
            isPing =! isPing;
        }
    }

}
