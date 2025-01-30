package org.jit;


// Run with -XX:+UnlockDiagnosticVMOptions -XX:+PrintCompilation -XX:+PrintCodeCache
public class PrimeNumbers {
    private static final int UPPER_LIMIT = 1_000_0000; // Test için ihtiyaca göre ayarlayın

    public static void main(String[] args) {
      System.out.println("Eğer " + UPPER_LIMIT + " sayısına kadar olan asal sayıları bulmaya başlıyoruz.");

      long startTime = System.nanoTime();
      boolean[] sieve = sieveOfEratosthenes(UPPER_LIMIT);
      long endTime = System.nanoTime();

      int primeCount = countPrimes(sieve);
      System.out.println("Bulunan asal sayılar: " + primeCount);
      System.out.printf("Geçen süre: %.3f saniye%n", (endTime - startTime) / 1e9);
    }

    /**
     * Eratosthenes Eleği algoritmasını kullanarak n sayısına kadar tüm asal sayıları bulur.
     * Bu yöntem, JIT optimizasyonlarını tetiklemek için birden çok kez çağrılacak şekilde tasarlanmıştır.
     *
     * @param n Asal sayı arama için üst sınır.
     * @return Asal sayıları temsil eden boolean dizisi.
     */
    public static boolean[] sieveOfEratosthenes(int n) {
      boolean[] sieve = new boolean[n + 1];
      // Tüm girdileri true olarak başlatın. sieve[i] değeri sonunda i'nin asal olup olmadığını gösterecek.
      for (int i = 2; i <= n; i++) {
        sieve[i] = true;
      }

      int sqrtN = (int) Math.sqrt(n);
      for (int p = 2; p <= sqrtN; p++) {
        if (sieve[p]) {
          // p'nin katlarını asal olmayan olarak işaretle
          for (int multiple = p * p; multiple <= n; multiple += p) {
            sieve[multiple] = false;
          }
        }
      }

      return sieve;
    }

    /**
     * Eleği kullanarak bulunan asal sayıları sayar.
     *
     * @param sieve Asal sayıları temsil eden boolean dizisi.
     * @return Asal sayıların sayısı.
     */
    public static int countPrimes(boolean[] sieve) {
      int count = 0;
      for (boolean isPrime : sieve) {
        if (isPrime) count++;
      }
      return count;
    }

}
