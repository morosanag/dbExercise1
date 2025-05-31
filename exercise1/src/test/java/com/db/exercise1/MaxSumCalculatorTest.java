package com.db.exercise1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaxSumCalculatorTest {

    @Test
    public void testValidCases() {
        // Given
        MaxSumCalculator maxSumCalculator = new MaxSumCalculator(new InputValidator());

        // When/Then
        assertEquals(140, maxSumCalculator.compute(new Integer[]{130, 191, 200, 10}));
        assertEquals(600, maxSumCalculator.compute(new Integer[]{405, 45, 300, 300}));
        assertEquals(-1, maxSumCalculator.compute(new Integer[]{50, 222, 49, 52, 25}));
        assertEquals(9918, maxSumCalculator.compute(new Integer[]{30, 909, 3190, 99, 3990, 9009}));
        assertEquals(60, maxSumCalculator.compute(new Integer[]{30, 21, 30}));
        assertEquals(12, maxSumCalculator.compute(new Integer[]{1, 2, 11}));
    }

}
