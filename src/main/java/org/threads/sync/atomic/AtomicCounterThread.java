package org.threads.sync.atomic;


public class AtomicCounterThread extends Thread {

  private AtomicCounter counter;

  public AtomicCounterThread(AtomicCounter counter) {
    this.counter = counter;
  }

  public void run() {
    for (int i = 0; i < 1000; i++) {
      counter.increment();
    }
  }
}
