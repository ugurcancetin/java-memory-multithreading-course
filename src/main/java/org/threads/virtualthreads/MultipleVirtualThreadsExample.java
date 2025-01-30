package org.threads.virtualthreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultipleVirtualThreadsExample {

  public static void main(String[] args) {
    int numberOfTasks = 1000000;

    try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
      for (int i = 1; i <= numberOfTasks; i++) {
        final int taskId = i;
        executor.submit(() -> {
          System.out.println("Task " + taskId + " started in " + Thread.currentThread());
          // Simulate some work
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Task " + taskId + " interrupted");
          }
          System.out.println("Task " + taskId + " completed");
        });
      }
    }
    // All virtual threads are completed and executor is closed here
  }
}
