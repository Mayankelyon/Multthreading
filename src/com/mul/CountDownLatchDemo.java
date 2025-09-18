package com.mul;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        Runnable service = () -> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " started");
            } catch (InterruptedException e) {}
            latch.countDown(); // reduce counter
        };

        new Thread(service, "DB Service").start();
        new Thread(service, "Cache Service").start();
        new Thread(service, "API Service").start();

        latch.await(); // main thread waits
        System.out.println("All services are up. System starting...");
    }
}
