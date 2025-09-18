package com.mul;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintSequence {
	private int state = 0; // 0 -> A, 1 -> B, 2 -> C
	private final Lock lock = new ReentrantLock();
	private final Condition condA = lock.newCondition();
	private final Condition condB = lock.newCondition();
	private final Condition condC = lock.newCondition();
	private final int n;

	public PrintSequence(int n) {
		this.n = n;
	}

	public void printA() {
		for (int i = 0; i < n; i++) {
			lock.lock();
			try {
				while (state % 3 != 0) {
					condA.await();
				}
				System.out.print("A");
				state++;
				condB.signal(); // signal B
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} finally {
				lock.unlock();
			}
		}
	}

	public void printB() {
		for (int i = 0; i < n; i++) {
			lock.lock();
			try {
				while (state % 3 != 1) {
					condB.await();
				}
				System.out.print("B");
				state++;
				condC.signal(); // signal C
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} finally {
				lock.unlock();
			}
		}
	}

	public void printC() {
		for (int i = 0; i < n; i++) {
			lock.lock();
			try {
				while (state % 3 != 2) {
					condC.await();
				}
				System.out.print("C");
				state++;
				condA.signal(); // signal A
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} finally {
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) {
		PrintSequence sequence = new PrintSequence(5); // print ABC 5 times

		Thread t1 = new Thread(sequence::printA);
		Thread t2 = new Thread(sequence::printB);
		Thread t3 = new Thread(sequence::printC);

		t1.start();
		t2.start();
		t3.start();
	}
}
