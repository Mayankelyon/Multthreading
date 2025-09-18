package com.mul;

import java.util.concurrent.*;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        int N = 3;
        CyclicBarrier barrier = new CyclicBarrier(N, () -> {
            System.out.println("All players ready. Game starts!");
        });

        Runnable player = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " ready");
                barrier.await(); // wait for others
            } catch (Exception e) {}
        };

        for (int i = 1; i <= N; i++) {
            new Thread(player, "Player-" + i).start();
        }
    }
}
