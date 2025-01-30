package org.threads.sync.deadlock;

public class DeadlockDemo {

  private final Object Lock1 = new Object();
  private final Object Lock2 = new Object();

  public void method1() {
    synchronized (Lock1) {
      System.out.println("Method1 Lock1 alındı.");
      synchronized (Lock2) {
        System.out.println("Method1 Lock2 alındı.");
      }
    }
  }

  public void method2() {
    synchronized (Lock2) {
      System.out.println("Method2 Lock2 alındı.");
      synchronized (Lock1) {
        System.out.println("Method2 Lock1 alındı.");
      }
    }
  }

  public static void main(String[] args) {
    DeadlockDemo demo = new DeadlockDemo();
    Thread t1 = new Thread(demo::method1);
    Thread t2 = new Thread(demo::method2);
    t1.start();
    t2.start();
  }
}

