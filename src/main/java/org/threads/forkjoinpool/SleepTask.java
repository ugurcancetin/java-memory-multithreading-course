package org.threads.forkjoinpool;

import java.util.concurrent.RecursiveAction;

public class SleepTask extends RecursiveAction {

  private static final int THRESHOLD = 10; // Görev bölme eşiği
  private final int[] numbers;
  private final int start;
  private final int end;

  public SleepTask(int[] numbers, int start, int end) {
    this.numbers = numbers;
    this.start = start;
    this.end = end;
  }

  @Override
  protected void compute() {
    int length = end - start;
    if (length <= THRESHOLD) {
      // Görev küçükse, doğrudan işlem yap
      for (int i = start; i < end; i++) {
        try {
          System.out.println(
              "Thread " + Thread.currentThread().getName() + " işliyor: " + numbers[i]);
          Thread.sleep(1000); // 1 saniye uyuma
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    } else {
      // Görev büyükse, iki alt göreve böl
      int mid = start + length / 2;
      SleepTask leftTask = new SleepTask(numbers, start, mid);
      SleepTask rightTask = new SleepTask(numbers, mid, end);

      // Alt görevleri paralel olarak başlat
      leftTask.fork();
      rightTask.fork();

      // Alt görevlerin tamamlanmasını bekle
      leftTask.join();
      rightTask.join();
    }
  }
}

