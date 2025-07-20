package org.example;


import java.util.HashMap;
import java.util.Map;

public class TTLCache<K, V> {
    private final long ttlMillis;
    private final Map<K, CacheEntry<V>> map;

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

    public TTLCache(long ttlMillis) {
        this.ttlMillis = ttlMillis;
        this.map = new HashMap<>();
    }

    public synchronized V get(K key) {
        CacheEntry<V> entry = map.get(key);
        if (entry == null || entry.isExpired()) {
            map.remove(key);
            return null;
        }
        return entry.value;
    }

    public synchronized void put(K key, V value) {
        cleanUpExpiredEntries();
        map.put(key, new CacheEntry<>(value, ttlMillis));
    }

    public synchronized int size() {
        cleanUpExpiredEntries();
        return map.size();
    }

    public synchronized void clear() {
        map.clear();
    }

    private void cleanUpExpiredEntries() {
        map.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }

    public static void main(String[] args) throws InterruptedException {
        TTLCache<Integer, String> cache = new TTLCache<>(3000);

        // Test case 1: Basic put and get within TTL
        cache.put(1, "One");
        System.out.println("Get key 1: " + cache.get(1)); // Output: One
        Thread.sleep(1000);
        cache.put(2, "Two");
        System.out.println("Get key 2: " + cache.get(2)); // Output: Two
        System.out.println("Size: " + cache.size()); // Output: 2

        // Test case 2: Entry expiration after TTL
        Thread.sleep(2500); // Total wait: 3500ms > 3000ms TTL
        System.out.println("Get key 1 after TTL: " + cache.get(1)); // Output: null
        System.out.println("Get key 2 after TTL: " + cache.get(2)); // Output: null
        System.out.println("Size after expiration: " + cache.size()); // Output: 0

        // Test case 3: Add new entry after expiration
        cache.put(3, "Three");
        System.out.println("Get key 3: " + cache.get(3)); // Output: Three
        System.out.println("Size: " + cache.size()); // Output: 1

        // Test case 4: Clear cache
        cache.clear();
        System.out.println("Size after clear: " + cache.size()); // Output: 0
    }
}
