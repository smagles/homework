package org.example.stream_api;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StringSorter {
    public List<String> getDescendingUpperCaseSortedStrings(List<String> list) {
        return list.stream()
                .map(s -> s.toUpperCase())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

    }
}
