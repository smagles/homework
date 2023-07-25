package org.example.stream_api;

import java.util.Arrays;
import java.util.stream.Collectors;


public class ArrayUtils {
    public String getSortedNumbersAsString(String[] array) {
        return Arrays.stream(array)
                .flatMap(s -> Arrays.stream(s.split(",\\s*")))
                .map(s -> Integer.parseInt(s))
                .sorted()
                .map(s -> String.valueOf(s))
                .collect(Collectors.joining(", "));
    }
}
