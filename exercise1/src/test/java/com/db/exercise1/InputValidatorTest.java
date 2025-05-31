package com.db.exercise1;

import org.junit.Test;

import static org.junit.Assert.*;

public class InputValidatorTest {

    @Test
    public void testValidationPass() {
        // Given
        InputValidator inputValidator = new InputValidator();
        Exception expectedException = null;

        // When
        try {
            inputValidator.validateInput(new Integer[]{130, 191, 200, 10});
        } catch (Exception ex) {
            expectedException = ex;
        }

        // Then
        assertNull(expectedException);
    }

    @Test
    public void testInvalidCases() {
        checkInvalidCase("[Invalid input] Null input", null);
        checkInvalidCase("[Invalid input] Array contains negative numbers", new Integer[]{130, -1});
    }

    private void checkInvalidCase(String exceptedErrorMessage, Integer[] values) {
        // Given
        MaxSumCalculator maxSumCalculator = new MaxSumCalculator(new InputValidator());
        RuntimeException expectedException = null;
        // When
        try {
            maxSumCalculator.compute(values);
        } catch (RuntimeException ex) {
            expectedException = ex;
        }
        // Then
        assertNotNull(expectedException);
        assertEquals(exceptedErrorMessage, expectedException.getMessage());
    }

}
