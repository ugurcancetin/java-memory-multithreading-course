package org.profiling;

public class ProfilingDemo {

  public static void main(String[] args) {
    // Başka 4 normal iş parçacığı başlat
    for (int i = 1; i <= 4; i++) {
      Thread normalThread = new Thread(new NormalTask(), "Normal-Thread-" + i);
      normalThread.start();
    }

    // Problemli iş parçacığını başlat
    Thread problematicThread = new Thread(new ProblematicTask(), "Problematic-Thread");
    problematicThread.start();
  }
}

// Normal görev: Basit matematiksel işlemler yapar
class NormalTask implements Runnable {

  @Override
  public void run() {
    while (true) {
      double result = Math.sqrt(Math.random()) * Math.random();
      // Kısa bir bekleme ile CPU kullanımını hafiflet
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break;
      }
    }
  }
}

// Problemli görev: Büyük bir dizi üzerinde verimsiz bir Bubble Sort uygular
class ProblematicTask implements Runnable {

  @Override
  public void run() {
    while (true) {
      int size = 10000;
      int[] numbers = generateRandomNumbers(size);
      sortNumbers(numbers);
      // Sıralama tamamlandıktan sonra hemen tekrar başlar
    }
  }

  private int[] generateRandomNumbers(int size) {
    int[] nums = new int[size];
    for (int i = 0; i < size; i++) {
      nums[i] = (int) (Math.random() * size);
    }
    return nums;
  }

  private void sortNumbers(int[] numbers) {
    // Verimsiz bir Bubble Sort algoritması
    for (int i = 0; i < numbers.length - 1; i++) {
      for (int j = 0; j < numbers.length - i - 1; j++) {
        if (numbers[j] > numbers[j + 1]) {
          // Değiş tokuş
          int temp = numbers[j];
          numbers[j] = numbers[j + 1];
          numbers[j + 1] = temp;
        }
      }
    }
  }
}
