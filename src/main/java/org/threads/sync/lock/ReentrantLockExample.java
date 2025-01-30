package org.threads.sync.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

  private final ReentrantLock lock = new ReentrantLock();
  private int count = 0;

  public void increment() {
    lock.lock();
    try {
      count++;
    } finally {
      lock.unlock(); // Kilidi mutlaka serbest bırak
    }
  }

  public int getCount() {
    return count;
  }

  public static void main(String[] args) throws InterruptedException {
    ReentrantLockExample example = new ReentrantLockExample();
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        example.increment();
      }
    });
    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        example.increment();
      }
    });
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    System.out.println("Toplam Sayı: " + example.getCount()); // Beklenen: 2000
  }
}

