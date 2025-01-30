package org.threads.sync;

public class Main {

  public static void main(String[] args) {
    Counter counter = new Counter();

    // İki thread oluştur
    Thread thread1 = new Thread(new MyRunnable(counter), "Thread 1");
    Thread thread2 = new Thread(new MyRunnable(counter), "Thread 2");

    thread1.start();
    thread2.start();

    // Thread'lerin bitmesini bekle
    try {
      thread1.join();
      thread2.join();
    } catch (InterruptedException e) {
      System.out.println("Main thread interrupted.");
    }

    System.out.println("Final count: " + counter.getCount());
  }
}
