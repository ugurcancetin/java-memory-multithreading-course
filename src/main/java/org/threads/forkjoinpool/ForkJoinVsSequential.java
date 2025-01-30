package org.threads.forkjoinpool;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinVsSequential {

  public static void main(String[] args) {
    // Büyük bir sayı dizisi oluştur (örneğin, 100 öğe)
    int size = 100;
    int[] numbers = new int[size];
    for (int i = 0; i < size; i++) {
      numbers[i] = i + 1; // 1'den 100'e kadar sayılar
    }

    // ForkJoinPool oluştur (varsayılan olarak mevcut çekirdek sayısı kadar)
    ForkJoinPool pool = new ForkJoinPool();

    // SleepTask görevini oluştur
    SleepTask task = new SleepTask(numbers, 0, numbers.length);

    // Görevi başlat ve süreyi ölç
    long startTime = System.currentTimeMillis();
    pool.invoke(task);
    long endTime = System.currentTimeMillis();

    System.out.println("ForkJoin Toplam Süre: " + (endTime - startTime) + " ms");

    // ForkJoinPool'u kapat
    pool.shutdown();
  }
}
