package org.memoryleaks.caching;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheExample {

  private static class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int MAX_ENTRIES;

    public LRUCache(int maxEntries) {
      super(maxEntries, 0.75f, true); // true for access-order
      this.MAX_ENTRIES = maxEntries;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
      return size() > MAX_ENTRIES;
    }
  }

  private Map<String, String> cache;

  public LRUCacheExample(int maxEntries) {
    cache = new LRUCache<>(maxEntries);
  }

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
