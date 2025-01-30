package org.threads.structured;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

public class ShutdownOnFailure {

  public static void main(String[] args) {
    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
      // İki görev ekle
      var task1 = scope.fork(() -> {
        System.out.println("Task 1 started.");
        Thread.sleep(1000);
        System.out.println("Task 1 completed.");
        return "Result 1";
      });

      var task2 = scope.fork(() -> {
        System.out.println("Task 2 started.");
        Thread.sleep(2000);
        System.out.println("Task 2 completed.");
        return "Result 2";
      });

      // Tüm görevlerin tamamlanmasını bekle
      scope.join(); // Bekler
      scope.throwIfFailed(); // Hata var mı kontrol et

      // Sonuçları al
      String result1 = task1.get();
      String result2 = task2.get();

      System.out.println("Results: " + result1 + ", " + result2);
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }
}
