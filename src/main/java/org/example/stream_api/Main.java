package org.example.stream_api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.example.stream_api.StreamZipper.zip;


public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ivan", "Peter", "John", "Mary", "Anastasia");
        NamesWithOddIndexes n = new NamesWithOddIndexes();
        String result1 = n.getNamesWithOddIndexes(names);
        System.out.println(result1);

        StringSorter sorter = new StringSorter();
        List<String> result2 = sorter.getDescendingUpperCaseSortedStrings(names);
        System.out.println(result2);

        String[] array = {"1, 2, 0", "4, 5"};
        ArrayUtils arrayUtils = new ArrayUtils();
        String result3 = arrayUtils.getSortedNumbersAsString(array);
        System.out.println(result3);

        long a = 25214903917L;
        long c = 11L;
        long m = (long) Math.pow(2, 48);
        long seed = 2L;

        LinearCongruentialGenerator g = new LinearCongruentialGenerator();
        Stream<Long> random = g.generateRandomStream(seed, a, c, m);
        random.limit(5).forEach(System.out::println);

        Stream<Integer> firstStream = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> secondStream = Stream.of(6, 7, 8, 9);
        StreamZipper<Integer> zip = new StreamZipper<>();
        Stream<Integer> result = zip(firstStream, secondStream);
        result.forEach(System.out::println);
    }

}