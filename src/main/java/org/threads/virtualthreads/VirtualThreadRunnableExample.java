package org.threads.virtualthreads;

public class VirtualThreadRunnableExample {

  public static void main(String[] args) {
    Thread.startVirtualThread(() -> System.out.println("Hello from virtual thread: " + Thread.currentThread()));

    // Sleep main thread to allow virtual thread to complete
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
