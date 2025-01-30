package org.threads.lambda;

public class Main {

  public static void main(String[] args) {
    // Lambda ifadesi ile Runnable oluşturma
    Runnable runnable = () -> {
      for (int i = 1; i <= 5; i++) {
        System.out.println("Lambda Runnable: " + i);
        try {
          Thread.sleep(500); // 500ms bekle
        } catch (InterruptedException e) {
          System.out.println("Lambda Runnable interrupted.");
        }
      }
    };

    Thread thread = new Thread(runnable);
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
  }
}
