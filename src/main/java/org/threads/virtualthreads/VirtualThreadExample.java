package org.threads.virtualthreads;

public class VirtualThreadExample {

  public static void main(String[] args) {
    Thread virtualThread = Thread.ofVirtual()
        .name("virtual-thread-1")
        .unstarted(() -> System.out.println("Running in " + Thread.currentThread().getName()));

    virtualThread.start();

    // Wait for the virtual thread to finish
    try {
      virtualThread.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      System.err.println("Virtual thread interrupted");
    }

    Thread.ofVirtual()
        .name("virtual-thread-2")
        .start(() -> System.out.println("Running in " + Thread.currentThread().getName()));
  }
}
