package com.mul;

public class SynchronizedDemo {
    private int counter = 0;

    public synchronized void increment() {
        counter++;
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedDemo demo = new SynchronizedDemo();

        Thread t1 = new Thread(() -> { for (int i = 0; i < 1000; i++) demo.increment(); });
        Thread t2 = new Thread(() -> { for (int i = 0; i < 1000; i++) demo.increment(); });

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Final Counter: " + demo.counter); // Always 2000
    }
}
