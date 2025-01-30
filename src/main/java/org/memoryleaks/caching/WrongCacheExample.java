package org.memoryleaks.caching;

import java.util.HashMap;
import java.util.Map;

public class WrongCacheExample {

  private Map<String, String> cache = new HashMap<>();

  public String getData(String key) {
    if (cache.containsKey(key)) {
      return cache.get(key);
    } else {
      String value = loadDataFromSource(key);
      cache.put(key, value);
      return value;
    }
  }

  private String loadDataFromSource(String key) {
    // Veri kaynağından veri yükleme işlemi
    return "Value for " + key;
  }

  public int getCacheSize() {
    return cache.size();
  }
}
