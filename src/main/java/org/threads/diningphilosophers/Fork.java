package org.threads.diningphilosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
  private final Lock lock = new ReentrantLock();

  public void pickUp() {
    System.out.println("Fork was picked up.");
    lock.lock();
  }

  public void putDown() {
    System.out.println("Fork was put down.");
    lock.unlock();
  }
}
