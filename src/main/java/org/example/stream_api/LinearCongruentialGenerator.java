package org.example.stream_api;

import java.util.stream.Stream;

public class LinearCongruentialGenerator {
    public Stream<Long> generateRandomStream(long seed, long a, long c, long m) {
        return Stream.iterate(seed, x -> (a * x + c) % m);
    }
}
