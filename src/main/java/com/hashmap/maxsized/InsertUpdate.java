package com.hashmap.maxsized;

import java.util.LinkedHashMap;
import java.util.Map;

// README
// In the MaxSizedHashMap, the element at position 0 is the
// head of the queue (eldest). The element at the
// position (size-1) is the tail of the queue (most recent).
//
// i.e. considering the following hashmap (with maxSize = 5):
// (head of the queue) --> |1|2|3|4|5| <-- (tail of the queue)
//
// Element 1 is the eldest and will be removed
// in case of putting a new element.
//
// Element 5 is the most recent.
//
// IMPORTANT:
// The operations UPDATE and PUT over an element will move it to the tail of the queue.
// Accessing an element (GET method) does not change its position inside the HashMap.
//
// Replacing an element will move it to the tail of the queue.
//    -> replacing operation can be performed by explicitly calling the replace method or
//    -> by calling the put method passing an existing key as a param.
// Putting an element will move it to the tail of the queue.

public class InsertUpdate<K, V> extends LinkedHashMap<K, V> {
    private final int maxSize;
    private K mostRecent;

    public InsertUpdate(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxSize;
    }

    /**
     * Putting a new key-value in the cache updates the mostRecent element.
     *
     * @param key   - key
     * @param value - value
     * @return the previous value associated with key, or null if there was no mapping for key.
     */
    @Override
    public V put(K key, V value) {
        super.remove(key);
        mostRecent = (K) key;
        return super.put(key, value);
    }

    /**
     * Replacing a value in the cache updates the mostRecent element.
     *
     * @param key   - key
     * @param value - value
     * @return the previous value associated with key, or null if there was no mapping for key.
     */
    @Override
    public V replace(K key, V value) {
        mostRecent =  super.containsKey(key)?key:mostRecent;
        if(mostRecent == key) {
            super.remove(key);
            return super.put(key, value);
        }
        return null;
    }

    /**
     * Reading the most recent element from the cache.
     *
     * @return the most recent value from the cache.
     * Remember that the most recent accessed
     * element considers the operations: put or replace.
     */
    public V getMostRecent() {
        return super.get(mostRecent);
    }
}
