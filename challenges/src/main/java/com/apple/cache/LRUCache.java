package com.apple.cache;

import java.util.Hashtable;
import java.util.Map;

/**
 * Least Recently Used cache implementation with following properties.
 * - Guarantee that there is a single instance of the cache
 * - Guarantee the type safety of the elements stored in the cache
 * - Guarantee the thread safety of the cache
 *
 * @author Ram Komma
 */
public class LRUCache implements Cache {

  // A singleton instance of this cache.
  private static LRUCache instance;

  // Head and tail of a double linked list to track recently used elements of key.
  private ListNode head;
  private ListNode tail;

  // maximum capacity of cache.
  private int capacity;

  private Map<Key, ListNode> map;

  /**
   * A private constructor. Access to constructor restricted to control instantiation.
   * @param capacity, capacity of the cache.
   */
  private LRUCache(int capacity) {

    head = new ListNode<>(KeyImpl.getInstance("Head", String.class), "head");
    tail = new ListNode<>(KeyImpl.getInstance("Tail", String.class), "tail");

    head.next = tail;
    tail.previous = head;
    map = new Hashtable<>(capacity);
    this.capacity = capacity;
  }

  /**
   * An instanciator, to create an instance of cache. Creates an instance first time,
   * returns the same instance every time. Creates a cache with a default capacity of 10.
   *
   * Double checked locking for instance creation is established, to keep be thread safe.
   * @return returns an instance of {@link LRUCache}.
   */
  public static LRUCache getInstance() {
    return getInstance(10);
  }

  /**
   * An instanciator, to create an instance of cache with a given capacity. If the cache already exists,
   * to changes to the existing capacity are done.
   *
   * @param capacity capacity of the cache.
   * @return returns an instance of {@link LRUCache}.
   */
  public static LRUCache getInstance(int capacity) {
    if (instance == null) {
      synchronized (LRUCache.class) {
        if (instance == null) {
          instance = new LRUCache(capacity);
        }
      }
    }
    return instance;
  }

  @Override
  public <V> V get(Key<V> key) {
    if (map.containsKey(key)) {
      updateCacheEntry(key);
      ListNode<V> cacheObj = map.get(key);
      return cacheObj.value;
    }
    return null;
  }

  @Override
  public <V> void put(Key<V> key, V value) {
    if (map.containsKey(key)) {
      updateCacheEntry(key);
      map.get(key).value = value;
    } else if (map.size() == capacity) {
      ListNode nodeToDelete = head.next;
      head.next = nodeToDelete.next;
      nodeToDelete.next.previous = head;
      map.remove(nodeToDelete.key);
    }
    ListNode<V> newNode = new ListNode<>(key, value);
    newNode.previous = tail.previous;
    newNode.previous.next = newNode;
    tail.previous = newNode;
    newNode.next = tail;
    map.put(key, newNode);
  }

  /**
   * A private method to track recent usage data.
   *
   * @param key key who is recently used.
   * @param <V> type of value.
   */
  private synchronized <V> void updateCacheEntry(Key<V> key) {
    ListNode node = map.get(key);
    node.next.previous = node.previous;
    node.previous.next = node.next;

    node.previous = tail.previous;
    tail.previous.next = node;
    tail.previous = node;
    node.next = tail;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return getInstance();
  }

  /**
   * A private class representing, a node structure of linkedList.
   */
  private class ListNode<CacheVal> {
    Key<CacheVal> key;
    CacheVal value;
    ListNode previous;
    ListNode next;

    ListNode(Key<CacheVal> key, CacheVal value) {
      this.key = key;
      this.value = value;
    }
  }
}
