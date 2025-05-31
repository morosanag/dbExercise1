package com.db.exercise1;

import java.util.*;

public class MaxSumCalculator {

    private final InputValidator inputValidator;

    public MaxSumCalculator(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    /**
     * This method will take an input array of integers as parameter
     * perform checks on the input (throw an exception if needed - Runtime)
     * otherwise returns the expected solution
     *
     * @param values
     * @return
     */
    public int compute(Integer[] values) {
        inputValidator.validateInput(values);

        // Build a map that contains all the numbers having the same first and last digit
        // group under the same key
        Map<String, List<Integer>> integerListMap = buildMap(values);

        return integerListMap
                .values()
                .stream()
                .map(this::sumList)
                .max(Comparator.naturalOrder())
                .orElse(-1);
    }

    /**
     * This method generates a string containing the first and last digit of a number.
     * In this way we can group the numbers together to see which pair has the highest sum
     *
     * @param x
     * @return
     */
    private String getFirstAndLastDigits(Integer x) {
        String strX = x.toString();
        return "" + strX.charAt(0) + strX.charAt(strX.length() - 1);
    }

    private Map<String, List<Integer>> buildMap(Integer[] values) {
        // The maximum keyset size is 100 so we can indicate this to the map
        // to reduce unnecessary allocations
        Map<String, List<Integer>> map = new HashMap<>(100);
        for (int value : values) {
            String key = getFirstAndLastDigits(value);
            if (map.containsKey(key)) {
                List<Integer> list = map.get(key);
                if (list.size() > 1) {
                    Integer first = list.get(0);
                    Integer second = list.get(1);
                    if (value > list.get(0) || value > list.get(1)) {
                        list.set(first < second ? 0 : 1, value);
                    }
                } else {
                    list.add(value);
                }
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(value);
                map.put(key, list);
            }
        }
        return map;
    }

    private int sumList(List<Integer> values) {
        return values.size() < 2 ? -1 : values.stream().mapToInt(x -> x).sum();
    }

}
