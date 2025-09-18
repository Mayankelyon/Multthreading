package com.mul;

public class AlternateNumberPrinter {
	private int num = 1;
	private final int MAX = 10;
	private boolean oddTurn = true;

	public synchronized void printOdd() {
		while (num <= MAX) {
			while (!oddTurn) {
				try {
					wait();
				} catch (InterruptedException e) {
				}
			}
			System.out.println("Odd: " + num++);
			oddTurn = false;
			notifyAll();
		}
	}

	public synchronized void printEven() {
		while (num <= MAX) {
			while (oddTurn) {
				try {
					wait();
				} catch (InterruptedException e) {
				}
			}
			System.out.println("Even: " + num++);
			oddTurn = true;
			notifyAll();
		}
	}

	public static void main(String[] args) {
		AlternateNumberPrinter printer = new AlternateNumberPrinter();
		new Thread(printer::printOdd).start();
		new Thread(printer::printEven).start();
	}
}
