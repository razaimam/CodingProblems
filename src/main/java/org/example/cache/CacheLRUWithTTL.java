package org.example.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class CacheLRUWithTTL<K, V> {
    private final int capacity;
    private final long ttlMillis;
    private final Map<K, CacheEntry<V>> map;

    public CacheLRUWithTTL(int capacity, long ttlMillis) {
        this.capacity = capacity;
        this.ttlMillis = ttlMillis;
        this.map = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, CacheEntry<V>> eldest) {
                return size() > CacheLRUWithTTL.this.capacity;
            }
        };
    }


    public synchronized V getValue(K key) {
        cleanUpExpiredEntries();
        CacheEntry<V> entry = map.get(key);
        if (entry == null || entry.isExpired()) {
            map.remove(key);
            return null;
        }
        return entry.value;
    }

    public synchronized void putValue(K key, V value) {
        cleanUpExpiredEntries();
        map.put(key, new CacheEntry<>(value, ttlMillis));
    }

    public synchronized int size() {
        return map.size();
    }

    public synchronized void clear() {
        map.clear();
    }

    private void cleanUpExpiredEntries() {
        map.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }

    static class CacheEntry<V> {
        V value;
        long expiryTime;

        CacheEntry(V value, long ttlMillis) {
            this.value = value;
            this.expiryTime = System.currentTimeMillis() + ttlMillis;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CacheLRUWithTTL<Integer, String> cache = new CacheLRUWithTTL<>(2, 3000);

        cache.putValue(1, "One");
        Thread.sleep(1000);
        cache.putValue(2, "Two");

        System.out.println(cache.getValue(1)); // "One"
        Thread.sleep(3000);
        System.out.println(cache.getValue(1)); // null
        cache.putValue(3, "Three");

        System.out.println(cache.getValue(2)); // null
        System.out.println(cache.getValue(3)); // "Three"
    }
}