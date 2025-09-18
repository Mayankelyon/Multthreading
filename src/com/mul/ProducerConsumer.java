package com.mul;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
	private final Queue<Integer> queue = new LinkedList<>();
	private final int LIMIT = 5;

	public synchronized void produce() throws InterruptedException {
		int value = 0;
		while (true) {
			while (queue.size() == LIMIT) {
				wait(); // wait if queue is full
			}
			queue.add(value);
			System.out.println("Produced: " + value);
			value++;
			notifyAll(); // notify consumers
			Thread.sleep(500);
		}
	}

	public synchronized void consume() throws InterruptedException {
		while (true) {
			while (queue.isEmpty()) {
				wait(); // wait if queue is empty
			}
			int val = queue.poll();
			System.out.println("Consumed: " + val);
			notifyAll(); // notify producer
			Thread.sleep(1000);
		}
	}

	public static void main(String[] args) {
		ProducerConsumer pc = new ProducerConsumer();
		Thread producer = new Thread(() -> {
			try {
				pc.produce();
			} catch (InterruptedException e) {
			}
		});
		Thread consumer = new Thread(() -> {
			try {
				pc.consume();
			} catch (InterruptedException e) {
			}
		});

		producer.start();
		consumer.start();
	}
}
