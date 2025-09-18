package com.mul;

public class PrintNumbers {
    private int num = 1;          // start number
    private final int MAX = 10;   // end number
    private int turn = 1;         // track whose turn (1,2,3)

    public synchronized void print(int threadId) {
        while (num <= MAX) {
            while (turn != threadId && num <= MAX) {
                try {
                    wait(); // not this thread's turn
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            if (num <= MAX) {
                System.out.println("Thread-" + threadId + " -> " + num);
                num++;
                turn = (turn % 3) + 1; // cycle 1→2→3
                notifyAll(); // wake up other threads
            }
        }
    }

    public static void main(String[] args) {
        PrintNumbers printer = new PrintNumbers();

        Runnable r1 = () -> printer.print(1);
        Runnable r2 = () -> printer.print(2);
        Runnable r3 = () -> printer.print(3);

        new Thread(r1, "Thread-1").start();
        new Thread(r2, "Thread-2").start();
        new Thread(r3, "Thread-3").start();
    }
}
