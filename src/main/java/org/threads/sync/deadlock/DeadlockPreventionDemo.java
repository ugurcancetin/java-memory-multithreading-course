package org.threads.sync.deadlock;

public class DeadlockPreventionDemo {

  private final Object Lock1 = new Object();
  private final Object Lock2 = new Object();

  // Her iki metot da kilitleri aynı sırayla alır
  public void method1() {
    synchronized (Lock1) {
      System.out.println("Method1 Lock1 alındı.");
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      synchronized (Lock2) {
        System.out.println("Method1 Lock2 alındı.");
      }
    }
  }

  public void method2() {
    synchronized (Lock1) { // Önce Lock1'i alıyoruz
      System.out.println("Method2 Lock1 alındı.");
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      synchronized (Lock2) {
        System.out.println("Method2 Lock2 alındı.");
      }
    }
  }

  public static void main(String[] args) {
    DeadlockPreventionDemo demo = new DeadlockPreventionDemo();
    Thread t1 = new Thread(demo::method1);
    Thread t2 = new Thread(demo::method2);
    t1.start();
    t2.start();
  }
}

