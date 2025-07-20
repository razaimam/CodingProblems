package org.example;

import java.util.LinkedHashMap;
import java.util.Map;

public class CacheLRU<K, V> {
    private final int capacity;
    private final Map<K, V> map;

    public CacheLRU(int capacity) {
        this.capacity = capacity;
        this.map = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > CacheLRU.this.capacity;
            }
        };
    }

    public V get(K key) {
        return map.get(key);
    }

    public void put(K key, V value) {
        map.put(key, value);
    }

    public int size() {
        return map.size();
    }

    public void clear() {
        map.clear();
    }

    public static void main(String[] args) {
        CacheLRU<Integer, String> cache = new CacheLRU<>(3);

        // Test case 1: Basic put and get
        cache.put(1, "One");
        cache.put(2, "Two");
        System.out.println(cache.get(1)); // Output: One
        System.out.println(cache.size()); // Output: 2

        // Test case 2: Eviction policy
        cache.put(3, "Three");
        cache.put(4, "Four"); // This should evict key 2 as it is the least recently used
        System.out.println(cache.get(1)); // Output: 1
        System.out.println(cache.get(2)); // Output: null (evicted)
        System.out.println(cache.size()); // Output: 3

        // Test case 3: Access order
        cache.get(4); // Access key 4 to make it most recently used
        cache.put(5, "Five"); // This should evict key 3
        System.out.println(cache.get(3)); // Output: null (evicted)
    }
}
