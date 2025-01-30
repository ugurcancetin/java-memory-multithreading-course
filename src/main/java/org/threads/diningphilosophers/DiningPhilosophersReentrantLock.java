package org.threads.diningphilosophers;

public class DiningPhilosophersReentrantLock {

  public static void main(String[] args) {
    // Filozofları tutacak bir dizi oluşturuyoruz
    final Philosopher[] philosophers = new Philosopher[5];
    // Çatalları tutacak bir dizi oluşturuyoruz
    final Fork[] forks = new Fork[5];

    // Her çatal için bir Fork nesnesi oluşturuyoruz
    for (int i = 0; i < forks.length; i++) {
      forks[i] = new Fork();
    }

    // Her filozof için bir Philosopher nesnesi oluşturuyoruz
    for (int i = 0; i < philosophers.length; i++) {
      // Sol çatalın indeksini belirliyoruz
      final int leftFork = i;
      // Sağ çatalın indeksini belirliyoruz (döngüsel olarak)
      final int rightFork = (i + 1) % forks.length;

      // Son filozof için çatalları ters sırayla alıyoruz
      if (i == philosophers.length - 1) {
        philosophers[i] = new Philosopher(forks[rightFork], forks[leftFork]);
      } else {
        philosophers[i] = new Philosopher(forks[leftFork], forks[rightFork]);
      }

      // Her filozof için bir Thread oluşturup başlatıyoruz
      Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
      t.start();
    }
  }
}
