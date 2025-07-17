package org.example;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheWithTTL <K, V> {
    private final int capacity;
    private final long ttlMillis;
    private final Map<K, CacheEntry<V>> map;
    protected final Object lock = new Object();
    static class CacheEntry<V>{
        V value;
        long expiryTime;

        CacheEntry(V value, long ttlMills){
            this.value = value;
            this.expiryTime = System.currentTimeMillis() + ttlMills;
        }

        boolean isExpired(){
            return System.currentTimeMillis() > expiryTime;
        }
    }

    public LRUCacheWithTTL(int capacity, long ttlMillis){
        this.capacity = capacity;
        this.ttlMillis = ttlMillis;
        this.map = new LinkedHashMap<>(capacity, 0.75f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, CacheEntry<V>> eldest) {
                return size() > LRUCacheWithTTL.this.capacity;
            }
        };
    }


    public synchronized V getValue(K key){
        synchronized (lock){
        CacheEntry<V> entry = map.get(key);
        if(entry == null){
            return null;
        }
        if (entry.isExpired()){
            map.remove(key);
            return  null;
        }
        return entry.value;
        }
    }

    public synchronized void putValue(K key, V value){
        synchronized (lock) {
            cleanUpExpiredEntries();
            map.put(key, new CacheEntry<>(value, ttlMillis));
        }
    }
    public int size(){
        synchronized (lock){
            return map.size();
        }
    }

    public void clear(){
        synchronized (lock){
            map.clear();
        }
    }

    private void cleanUpExpiredEntries(){
        Iterator<Map.Entry<K, CacheEntry<V>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            if(iterator.next().getValue().isExpired()){
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LRUCacheWithTTL<Integer, String> cache = new LRUCacheWithTTL<>(2, 3000); // TTL = 3s

        cache.putValue(1, "One");
        Thread.sleep(1000);
        cache.putValue(2, "Two");

        System.out.println(cache.getValue(1)); // "One"
        Thread.sleep(3000); // wait for 1 to expire
        System.out.println(cache.getValue(1)); // null (expired)
        cache.putValue(3, "Three"); // evicts 2 (LRU policy)

        System.out.println(cache.getValue(2)); // null (evicted)
        System.out.println(cache.getValue(3)); // "Three"
    }
}
