package com.mul;

// Problem Statement
//5 philosophers sit around a round table.
//Each has 1 plate of food and needs 2 chopsticks to eat.
//Between each philosopher, there’s 1 chopstick (so 5 in total).
//They alternate between thinking and eating.
//Challenge: Prevent deadlock (where all philosophers pick one chopstick and wait forever).
//Naive (Deadlock-prone) Solution
//Each philosopher tries to pick left chopstick first, then right.
//This can cause deadlock if all 5 pick left at the same time.
//Deadlock-free Solution (Odd/Even strategy)
//Philosophers with even index pick left then right.
//Philosophers with odd index pick right then left.
//This prevents circular waiting.
import java.util.concurrent.locks.ReentrantLock;

class Chopstick {
	private final ReentrantLock lock = new ReentrantLock();

	public void pickUp() {
		lock.lock();
	}

	public void putDown() {
		lock.unlock();
	}
}

class Philosopher implements Runnable {
	private final int id;
	private final Chopstick left;
	private final Chopstick right;

	public Philosopher(int id, Chopstick left, Chopstick right) {
		this.id = id;
		this.left = left;
		this.right = right;
	}

	private void think() throws InterruptedException {
		System.out.println("Philosopher " + id + " is thinking...");
		Thread.sleep((long) (Math.random() * 1000));
	}

	private void eat() throws InterruptedException {
		System.out.println("Philosopher " + id + " is eating...");
		Thread.sleep((long) (Math.random() * 1000));
	}

	@Override
	public void run() {
		try {
			while (true) {
				think();

				if (id % 2 == 0) { // even philosophers
					left.pickUp();
					right.pickUp();
				} else { // odd philosophers
					right.pickUp();
					left.pickUp();
				}

				eat();

				left.putDown();
				right.putDown();
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}

public class DiningPhilosophers {
	public static void main(String[] args) {
		int N = 5;
		Chopstick[] chopsticks = new Chopstick[N];
		for (int i = 0; i < N; i++)
			chopsticks[i] = new Chopstick();

		Thread[] philosophers = new Thread[N];
		for (int i = 0; i < N; i++) {
			Chopstick left = chopsticks[i];
			Chopstick right = chopsticks[(i + 1) % N];
			philosophers[i] = new Thread(new Philosopher(i, left, right));
			philosophers[i].start();
		}
	}
}
