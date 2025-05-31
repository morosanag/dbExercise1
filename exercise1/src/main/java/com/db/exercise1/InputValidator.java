package com.db.exercise1;

import java.util.Arrays;

public class InputValidator {

    public void validateInput(Integer[] values) {
        if (values == null) {
            throw new RuntimeException("[Invalid input] Null input");
        }

        if (Arrays.stream(values).anyMatch(x -> x < 0)) {
            throw new RuntimeException("[Invalid input] Array contains negative numbers");
        }
    }

}
