package com.hashmap.maxsize;

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
// All the operations (GET, UPDATE, PUT) over an element will move it to the tail of the queue.
//
// Accessing an element (get method) will move it to the tail of the queue.
// Replacing an element will move it to the tail of the queue.
//    -> replacing operation can be performed by explicitly calling the replace method or
//    -> by calling the put method passing an existing key as a param.
// Putting an element will move it to the tail of the queue.

public class AllOperations<K, V> extends LinkedHashMap<K, V> {
    private final int maxSize;
    private K mostRecent;

    public AllOperations(int maxSize) {
        //The put, replace or get methods will move the respective
        //element to the end of the list if the "accessOrder" param is defined equals true.

        //Defining the "initialCapacity" as maxSize+2 and the "loadFactor" as 1f
        //means the HashMap will never have its capacity increased.
        super(maxSize+2, 1f, true);
        this.maxSize = maxSize;
    }

    /**
     * @param eldest
     * @return returns true if this map should remove its eldest entry.
     */
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
            return super.replace(key, value);
        }
        return null;
    }

    /**
     * Reading a value from the cache also updates the mostRecent value.
     *
     * @param key - key
     * @return the value associated with the key.
     */
    @Override
    public V get(Object key) {
        mostRecent = (K) key;
        return super.get(key);
    }

    /**
     * Reading the most recent element from the cache.
     *
     * @return the most recent value from the cache.
     * Remember that the most recent accessed
     * element considers the operations: get, put or replace.
     */
    public V getMostRecent() {
        return super.get(mostRecent);
    }
}
