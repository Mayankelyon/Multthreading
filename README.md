# 🚀 Java Multithreading Examples

This project demonstrates **core multithreading concepts in Java** through hands-on examples.  
It covers both **classic interview problems** and **modern concurrency utilities** from `java.util.concurrent`.

---

## 📌 Features & Examples

### 🔹 Basics
- Thread creation (`Runnable` and `Thread`).
- Race condition demo & need for synchronization.
- Synchronized counter (thread-safe increment).

### 🔹 Classic Problems
- Producer–Consumer (using `wait/notify` and `BlockingQueue`).
- Dining Philosophers (deadlock-free solution).
- Odd–Even Printer (two threads printing alternately).
- Round-Robin Printer (M threads printing 1–N in sequence).
- FizzBuzz with 4 threads.

### 🔹 Concurrency Utilities
- **CountDownLatch** → Wait for multiple services before starting system.
- **CyclicBarrier** → Multiple threads wait for each other to reach a point.
- **ExecutorService** → Manage a pool of threads.
- **Parallel Array Sum** → Divide-and-conquer using thread pool.
- **Thread-Safe Singleton** (volatile + double-checked locking).

### 🔹 Asynchronous Programming
- **CompletableFuture** → Async tasks with callbacks.
- **thenCompose() chaining** → Execute dependent async tasks.

