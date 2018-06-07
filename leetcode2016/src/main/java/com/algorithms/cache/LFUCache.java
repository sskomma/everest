package com.algorithms.cache;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Least Frequently used cache.
 *
 * LFU Strategy: Track number of times each element is used in a cache.
 */
public class LFUCache {
/*  private Map<Integer, Storage> cacheStore;
  private int capacity = 0;
  private PriorityQueue<Storage> heap;

  public void LFUCache(int capacity) {
    this.capacity = capacity;
    cacheStore = new HashMap<>(capacity);
    heap = new PriorityQueue<>(capacity, Comparator.comparingInt(a -> a.getFrequency()));
  }

  public void put(int key, int value) {
    if (cacheStore.containsKey(key)) {
      heap.
    } else {
      if (cacheStore.size() == capacity) {
        //TODO: Remove LFU
      } else {
        Storage storage = new Storage(value);
        cacheStore.put(key, storage);
        heap.add(storage);
      }
    }
  }

  class Storage {
    int value;
    int frequency;

    public Storage(int value) {
      this.value = value;
      frequency++;
    }

    public int getValue() {
      return value;
    }

    public void setValue(int value) {
      this.value = value;
    }

    public int getFrequency() {
      return frequency;
    }

    public void setFrequency(int frequency) {
      this.frequency = frequency;
    }
  }*/
}
