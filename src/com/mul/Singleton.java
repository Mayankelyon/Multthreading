package com.mul;

public class Singleton {
	private static volatile Singleton instance;

	private Singleton() {
	} // private constructor

	public static Singleton getInstance() {
		if (instance == null) { // First check (no locking)
			synchronized (Singleton.class) {
				if (instance == null) { // Second check (with lock)
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}
