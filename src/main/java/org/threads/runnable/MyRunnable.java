package org.threads.runnable;

public class MyRunnable implements Runnable {

  @Override
  public void run() {
    // Thread'in yapacağı işler
    for (int i = 1; i <= 5; i++) {
      System.out.println("MyRunnable: " + i);
      try {
        Thread.sleep(500); // 500ms bekle
      } catch (InterruptedException e) {
        System.out.println("MyRunnable interrupted.");
      }
    }
  }
}
