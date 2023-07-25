package org.example.stream_api;

import java.util.List;
import java.util.stream.Collectors;

public class NamesWithOddIndexes {
    public String getNamesWithOddIndexes(List<String> names) {
        return names.stream()
                .filter(name -> names.indexOf(name) % 2 == 0)
                .map(name -> (names.indexOf(name) + 1) + ". " + name)
                .collect(Collectors.joining(", "));
    }
}
