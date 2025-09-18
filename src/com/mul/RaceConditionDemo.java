package com.mul;

public class RaceConditionDemo {
    private int counter = 0;

    public void increment() {
        counter++; // not thread-safe
    }

    public static void main(String[] args) throws InterruptedException {
        RaceConditionDemo demo = new RaceConditionDemo();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) demo.increment();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) demo.increment();
        });

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Final Counter: " + demo.counter);
    }
}
