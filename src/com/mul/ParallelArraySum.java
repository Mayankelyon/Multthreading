package com.mul;

import java.util.concurrent.*;

public class ParallelArraySum {
	public static void main(String[] args) throws Exception {
		int[] arr = new int[1000];
		for (int i = 0; i < arr.length; i++)
			arr[i] = i + 1;

		int numThreads = 4;
		ExecutorService executor = Executors.newFixedThreadPool(numThreads);
		int chunk = arr.length / numThreads;

		Future<Integer>[] results = new Future[numThreads];

		for (int i = 0; i < numThreads; i++) {
			int start = i * chunk;
			int end = (i == numThreads - 1) ? arr.length : (i + 1) * chunk;
			results[i] = executor.submit(() -> {
				int sum = 0;
				for (int j = start; j < end; j++)
					sum += arr[j];
				return sum;
			});
		}

		int total = 0;
		for (Future<Integer> f : results)
			total += f.get();

		executor.shutdown();
		System.out.println("Total Sum = " + total);
	}
}
