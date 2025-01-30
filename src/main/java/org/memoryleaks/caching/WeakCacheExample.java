package org.memoryleaks.caching;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakCacheExample {

  private Map<String, String> cache = new WeakHashMap<>();

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
    return "Value for " + key;
  }

  public int getCacheSize() {
    return cache.size();
  }
}
