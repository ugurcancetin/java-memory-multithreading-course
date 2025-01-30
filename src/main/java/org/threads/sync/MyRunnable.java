package org.threads.sync;

public class MyRunnable implements Runnable {

  private Counter counter;

  public MyRunnable(Counter counter) {
    this.counter = counter;
  }

  @Override
  public void run() {
    // Her thread sayacı 5 kez artıracak
    for (int i = 0; i < 5; i++) {
      counter.increment();
      try {
        Thread.sleep(100); // 100ms bekle
      } catch (InterruptedException e) {
        System.out.println("Thread interrupted.");
      }
    }
  }
}
