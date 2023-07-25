package org.example.fizz_buzz;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeTracker {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleAtFixedRate(new TimerRunnable(), 0, 1, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(new MessageTask(), 5, 5, TimeUnit.SECONDS);
    }

    static class TimerRunnable implements Runnable {
        private long startTime = System.currentTimeMillis();

        @Override
        public void run() {
            long currentTime = System.currentTimeMillis();
            long elapsedSeconds = (currentTime - startTime) / 1000;
            System.out.println(elapsedSeconds + " seconds");
        }
    }

    static class MessageTask implements Runnable {
        @Override
        public void run() {
            System.out.println("5 seconds have passed");
        }
    }
}
