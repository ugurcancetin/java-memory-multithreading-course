package org.memoryleaks.eventlistener;

import java.lang.ref.WeakReference;

public class Demo {

  public static void main(String[] args) throws InterruptedException {
    // EventProducer oluştur
    EventProducer producer = new EventProducer();

    // LeakyListener ve SafeListener oluştur
    LeakyListener leaky = new LeakyListener(producer);
    SafeListener safe = new SafeListener(producer);

    // WeakReference'lar oluştur
    WeakReference<LeakyListener> weakLeaky = new WeakReference<>(leaky);
    WeakReference<SafeListener> weakSafe = new WeakReference<>(safe);

    // Birkaç tane "event" tetikle
    producer.fireEvent("Birinci Olay");
    producer.fireEvent("İkinci Olay");

    // LeakyListener ve SafeListener'a olan güçlü referansları kaldır
    safe.stopListening();
    leaky = null;
    safe = null;

    // Sistem'e GC'yi çağır
    System.out.println("\nGC'yi çağırıyoruz...");
    System.gc();

    // Biraz bekleyelim ki GC çalışsın
    Thread.sleep(2000);

    // WeakReference'ları kontrol et
    System.out.println("\nLeakyListener'ın GC tarafından toplanıp toplanmadığı kontrol ediliyor:");
    if (weakLeaky.get() != null) {
      System.out.println("LeakyListener henüz GC tarafından toplanmadı.");
    } else {
      System.out.println("LeakyListener GC tarafından toplandı.");
    }

    System.out.println("\nSafeListener'ın GC tarafından toplanıp toplanmadığı kontrol ediliyor:");
    if (weakSafe.get() != null) {
      System.out.println("SafeListener henüz GC tarafından toplanmadı.");
    } else {
      System.out.println("SafeListener GC tarafından toplandı.");
    }

    System.out.println("\nProgram sonlanıyor...");
  }
}
