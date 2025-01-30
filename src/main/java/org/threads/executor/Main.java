package org.threads.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

  public static void main(String[] args) {
    // 3 thread'li bir thread havuzu oluştur
    try (ExecutorService executor = Executors.newFixedThreadPool(3)) {

      // 10 görev oluştur ve havuza gönder
      for (int i = 1; i <= 10; i++) {
        executor.submit(new MyTask("Task " + i));
      }

      // Executor'u kapat
      executor.shutdown();
    }
  }
}
