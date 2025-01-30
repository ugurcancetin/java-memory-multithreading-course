package org.memoryleaks.caching;

public class Main {

  public static void main(String[] args) {
    System.out.println("=== Yanlış Önbellekleme (HashMap) ===");
    WrongCacheExample wrongCache = new WrongCacheExample();

    for (int i = 0; i < 100000; i++) {
      wrongCache.getData("Key" + i);
      if (i % 20000 == 0 && i != 0) {
        System.out.println(
            "Added " + i + " entries, Current Cache Size: " + wrongCache.getCacheSize());
      }
    }
    System.gc();
    System.out.println("Final Cache Size: " + wrongCache.getCacheSize());

    System.out.println("\n=== Doğru Önbellekleme (WeakHashMap) ===");
    WeakCacheExample weakCache = new WeakCacheExample();
    for (int i = 0; i < 100000; i++) {
      String key = new String(
          "Key" + i); // Yeni String nesnesi oluşturmak, zayıf referansın test edilmesi için
      weakCache.getData(key);
      if (i % 20000 == 0 && i != 0) {
        System.out.println(
            "Added " + i + " entries, Current Cache Size: " + weakCache.getCacheSize());
      }
    }

    // Tahmin edilebilir şekilde bazı anahtarların GC tarafından temizlenmesini sağlamak
    System.gc();
    try {
      Thread.sleep(1000); // GC'nin çalışması için beklemek
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("After GC, Cache Size: " + weakCache.getCacheSize());

    System.out.println("\n=== Doğru Önbellekleme (LRU Cache) ===");
    int maxEntries = 1000;
    LRUCacheExample lruCache = new LRUCacheExample(maxEntries);
    for (int i = 0; i < 10000; i++) {
      lruCache.getData("Key" + i);
      if (i % 2000 == 0 && i != 0) {
        System.out.println(
            "Added " + i + " entries, Current Cache Size: " + lruCache.getCacheSize());
      }
    }
    System.out.println(
        "Final Cache Size (should not exceed " + maxEntries + "): " + lruCache.getCacheSize());
  }
}
