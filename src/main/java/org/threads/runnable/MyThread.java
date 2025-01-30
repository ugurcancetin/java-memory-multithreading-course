package org.threads.runnable;

// MyThread.java
public class MyThread extends Thread {
  @Override
  public void run() {
    // Thread'in yapacağı işler
    for (int i = 1; i <= 5; i++) {
      System.out.println("MyThread: " + i);
      try {
        Thread.sleep(500); // 500ms bekle
      } catch (InterruptedException e) {
        System.out.println("MyThread interrupted.");
      }
    }
  }
}
