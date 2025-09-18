package com.mul;

import java.util.concurrent.*;

public class CompletableFutureChain {
	public static void main(String[] args) throws Exception {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			sleep(500);
			return "Step 1";
		}).thenCompose(result1 -> CompletableFuture.supplyAsync(() -> {
			sleep(500);
			return result1 + " -> Step 2";
		})).thenCompose(result2 -> CompletableFuture.supplyAsync(() -> {
			sleep(500);
			return result2 + " -> Step 3";
		}));

		System.out.println(future.get());
	}

	private static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}
	}
}
