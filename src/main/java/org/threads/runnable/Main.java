package org.threads.runnable;

public class Main {

  public static void main(String[] args) {

    int availableProcessors = Runtime.getRuntime().availableProcessors();
    System.out.println("Kullanılabilir CPU Çekirdek Sayısı: " + availableProcessors);


    MyThread thread = new MyThread();
    thread.start(); // Yeni thread başlatılır

    // Ana thread'in yapacağı işler
    for (int i = 1; i <= 5; i++) {
      System.out.println("Main Thread: " + i);
      try {
        Thread.sleep(500); // 500ms bekle
      } catch (InterruptedException e) {
        System.out.println("Main thread interrupted.");
      }
    }

    // MyRunnable Example
    Runnable runnable = new MyRunnable();
    Thread threadWithRunnable = new Thread(runnable);
    threadWithRunnable.start(); // Yeni thread başlatılır

    // Ana thread'in yapacağı işler
    for (int i = 1; i <= 5; i++) {
      System.out.println("Main Thread: " + i);
      try {
        threadWithRunnable.sleep(500); // 500ms bekle
      } catch (InterruptedException e) {
        System.out.println("Main thread interrupted.");
      }
    }
  }
}
