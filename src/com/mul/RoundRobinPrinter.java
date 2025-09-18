package com.mul;

public class RoundRobinPrinter {
	private final int N;
	private final int M;
	private int num = 1;
	private int turn = 1;

	public RoundRobinPrinter(int N, int M) {
		this.N = N;
		this.M = M;
	}

	public synchronized void print(int threadId) {
		while (num <= N) {
			while (turn != threadId && num <= N) {
				try {
					wait();
				} catch (InterruptedException e) {
				}
			}
			if (num <= N) {
				System.out.println("Thread-" + threadId + " -> " + num++);
				turn = (turn % M) + 1; // cycle threads
				notifyAll();
			}
		}
	}

	public static void main(String[] args) {
		int N = 10, M = 3;
		RoundRobinPrinter printer = new RoundRobinPrinter(N, M);

		for (int i = 1; i <= M; i++) {
			int id = i;
			new Thread(() -> printer.print(id)).start();
		}
	}
}
