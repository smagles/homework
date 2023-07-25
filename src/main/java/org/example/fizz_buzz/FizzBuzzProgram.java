package org.example.fizz_buzz;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzzProgram {
    public static void main(String[] args) {

        FizzBuzzProgram fizzBuzz = new FizzBuzzProgram(15);

        Thread threadA = new Thread(() -> {
            try {
                fizzBuzz.fizz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                fizzBuzz.buzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadD = new Thread(() -> {
            try {
                fizzBuzz.number();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
            threadD.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    private int n;
    private BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private AtomicInteger current = new AtomicInteger(1);

    public FizzBuzzProgram(int n) {
        this.n = n;
    }

    public void fizz() throws InterruptedException {
        while (current.get() <= n) {
            if (current.get() % 3 == 0 && current.get() % 5 != 0) {
                queue.put("fizz");
                current.incrementAndGet();
            }
        }

    }

    public void buzz() throws InterruptedException {
        while (current.get() <= n) {
            if (current.get() % 5 == 0 && current.get() % 3 != 0) {
                queue.put("buzz");
                current.incrementAndGet();
            }
        }

    }

    public void fizzbuzz() throws InterruptedException {
        while (current.get() <= n) {
            if (current.get() % 5 == 0 && current.get() % 3 == 0) {
                queue.put("fizzbuzz");
                current.incrementAndGet();
            }
        }

    }

    public void number() throws InterruptedException {
        while (current.get() <= n) {
            if (current.get() % 5 != 0 && current.get() % 3 != 0) {
                queue.put(Integer.toString(current.get()));
                current.incrementAndGet();
            }
        }
        while (!queue.isEmpty()) {
            System.out.print(queue.take());
            if (!queue.isEmpty() || current.get() <= n) {
                System.out.print(", ");
            } else {
                System.out.print(".");

            }
        }


    }
}
