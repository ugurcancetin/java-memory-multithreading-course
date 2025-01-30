package org.memoryleaks.innerclass;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MemoryLeak {

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executor = Executors.newFixedThreadPool(2);

    // --- Yanlış Yaklaşım: LeakyTask Kullanımı ---
    OuterClass outer1 = new OuterClass("Veri1");
    WeakReference<OuterClass> weakRef1 = new WeakReference<>(outer1);
    OuterClass.LeakyTask leakyTask = outer1.new LeakyTask();
    executor.submit(leakyTask);

    // --- Doğru Yaklaşım: NonLeakyTask Kullanımı ---
    OuterClass outer2 = new OuterClass("Veri2");
    WeakReference<OuterClass> weakRef2 = new WeakReference<>(outer2);
    OuterClass.NonLeakyTask nonLeakyTask = new OuterClass.NonLeakyTask(outer2);
    executor.submit(nonLeakyTask);

    // Güçlü referansları kaldır
    outer1 = null;
    outer2 = null;

    // Bellek sızıntısını tetiklemek için GC'yi çağır
    System.out.println("GC çağrılıyor...");
    System.gc();

    // Bir süre bekle (örneğin, 1 saniye)
    Thread.sleep(1_000);

    // WeakReference'ları kontrol et
    System.out.println(
        "\nLeakyTask için OuterClass nesnesi GC tarafından toplanmış mı? " + (weakRef1.get()
            == null));
    System.out.println(
        "NonLeakyTask için OuterClass nesnesi GC tarafından toplanmış mı? " + (weakRef2.get()
            == null));

    // Executor'u kapat
    executor.shutdown();

    // Daha fazla bekleyelim ki GC finalize metodlarını çalıştırabilsin
    Thread.sleep(11_000); // LeakyTask ve NonLeakyTask tamamlanacak

    System.out.println("\nProgram sonlanıyor...");
  }

  // Dış Sınıf: OuterClass
  static class OuterClass {

    private String data;

    public OuterClass(String data) {
      this.data = data;
    }

    // Yanlış Yaklaşım: İç Sınıf (Non-Static Inner Class)
    class LeakyTask implements Runnable {

      @Override
      public void run() {
        try {
          // Uzun süreli bir görev simülasyonu (10 saniye)
          Thread.sleep(10_000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
        System.out.println("[LeakyTask] Görev tamamlandı: " + data);
      }

      @Override
      protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("[LeakyTask] finalize() çağrıldı.");
      }
    }

    // Doğru Yaklaşım: Statik İç Sınıf (Static Inner Class) ve WeakReference
    public static class NonLeakyTask implements Runnable {

      private WeakReference<OuterClass> outerRef;

      public NonLeakyTask(OuterClass outer) {
        // Dış sınıfa zayıf referans tutuyoruz
        this.outerRef = new WeakReference<>(outer);
      }

      @Override
      public void run() {
        try {
          // Uzun süreli bir görev simülasyonu (10 saniye)
          Thread.sleep(10_000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }

        OuterClass outer = outerRef.get();
        if (outer != null) {
          System.out.println("[NonLeakyTask] Görev tamamlandı: " + outer.data);
        } else {
          System.out.println("[NonLeakyTask] Görev tamamlandı ancak OuterClass toplanmış.");
        }
      }

      @Override
      protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("[NonLeakyTask] finalize() çağrıldı.");
      }
    }

    @Override
    protected void finalize() throws Throwable {
      super.finalize();
      System.out.println("[OuterClass] finalize() çağrıldı.");
    }
  }



}
