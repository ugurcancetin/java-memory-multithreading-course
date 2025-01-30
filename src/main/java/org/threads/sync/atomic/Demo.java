package org.threads.sync.atomic;

public class Demo {

  public static void main(String[] args) throws InterruptedException {
    AtomicCounter counter = new AtomicCounter();
    Thread t1 = new AtomicCounterThread(counter);
    Thread t2 = new AtomicCounterThread(counter);
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    System.out.println("Toplam Sayı: " + counter.getCount()); // Beklenen: 2000
  }
}
