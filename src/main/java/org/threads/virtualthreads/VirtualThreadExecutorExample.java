package org.threads.virtualthreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadExecutorExample {

  public static void main(String[] args) {
    try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
      for (int i = 1; i <= 5; i++) {
        final int taskId = i;
        executor.submit(() -> {
          System.out.println("Task " + taskId + " running in " + Thread.currentThread());
          // Simulate work
          try {
            Thread.sleep(500);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Task " + taskId + " interrupted");
          }
        });
      }
    }
    // Executor is automatically closed here
  }
}
