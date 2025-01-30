package org.threads.sync;

public class Counter {

  private int count = 0;

  // Synchronized metod
  public synchronized void increment() {
    count++;
    System.out.println(Thread.currentThread().getName() + " incremented count to " + count);
  }

  public int getCount() {
    return count;
  }
}
