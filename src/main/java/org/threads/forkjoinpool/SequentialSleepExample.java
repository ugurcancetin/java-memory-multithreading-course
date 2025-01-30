package org.threads.forkjoinpool;

public class SequentialSleepExample {

  public static void main(String[] args) {
    // Aynı büyük sayı dizisini oluştur
    int size = 100;
    int[] numbers = new int[size];
    for (int i = 0; i < size; i++) {
      numbers[i] = i + 1; // 1'den 100'e kadar sayılar
    }

    // Sıralı (Sequential) toplam işlemi
    long startTime = System.currentTimeMillis();
    for (int num : numbers) {
      try {
        System.out.println("Thread " + Thread.currentThread().getName() + " işliyor: " + num);
        Thread.sleep(1000); // 1 saniye uyuma
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    long endTime = System.currentTimeMillis();

    System.out.println("Sequential Toplam Süre: " + (endTime - startTime) + " ms");
  }
}
