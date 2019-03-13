package com.algorithms.design;

/**
 * Design a HashMap without using any built-in hash table libraries.
 *
 * To be specific, your design should include these functions:
 *
 * put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
 * get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 * remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.
 *
 * Example:
 *
 * HashMapWithArray hashMap = new HashMapWithArray();
 * hashMap.put(1, 1);
 * hashMap.put(2, 2);
 * hashMap.get(1);            // returns 1
 * hashMap.get(3);            // returns -1 (not found)
 * hashMap.put(2, 1);          // update the existing value
 * hashMap.get(2);            // returns 1
 * hashMap.remove(2);          // remove the mapping for 2
 * hashMap.get(2);            // returns -1 (not found)
 *
 * Note:
 *
 * All keys and values will be in the range of [0, 1000000].
 * The number of operations will be in the range of [1, 10000].
 * Please do not use the built-in HashMap library.
 *
 * https://leetcode.com/problems/design-hashmap/description/
 */
public class HashMapWithArray {

  private static final float THRESHOLD = 0.65f;
  private int[] keys;
  private int[] values;
  private int capacity;
  private int size;
  private int maxCollisions = 0;

  /** Initialize your data structure here. */
  public HashMapWithArray() {
    this(16);
  }

  private HashMapWithArray(int capacity) {
    keys = new int[capacity];
    values = new int[capacity];
    for (int i = 0; i < capacity; i++) {
      keys[i] = -1;
      values[i] = -1;
    }
    this.capacity = capacity;
    this.size = 0;
  }

 /**
   * value will always be non-negative.
   */
  public void put(int key, int value) {
    System.out.printf("Inserting : Key(%d), value(%d)\n", key, value);
    ensureCapacity(false);
    int index = hash(key, capacity);

    int doubleHash = reHash(key);
    int collisionCounter = 1;
    while (keys[index] != key && keys[index] != -1) {
      index = (index + collisionCounter * doubleHash) % capacity;
      collisionCounter++;
      if(collisionCounter > capacity) {
        ensureCapacity(true);
        put(key, value);
        return;
      }
    }
    maxCollisions = Math.max(maxCollisions, collisionCounter);
    if (keys[index] != key) {
      size++;
    }
    keys[index] = key;
    values[index] = value;
  }

  /**
   * Returns the value to which the specified key is mapped,
   * or -1 if this map contains no mapping for the key
   */
  public int get(int key) {
    int index = findIndexOfKey(key);
    if (index != -1) {
      System.out.printf("Getting   : Key(%d), Found value (%d) \n", key, values[index]);
      return values[index];
    }
    System.out.printf("Getting   : Key(%d), No value found\n", key);
    return -1;
  }

  /** Removes the mapping of the specified value key
   *  if this map contains a mapping for the key
   */
  public void remove(int key) {
    int index = findIndexOfKey(key);
    if (index != -1) {
      keys[index] = -1;
      values[index] = -1;
      size--;
      System.out.printf("Removing  : %d\n", key);
      return;
    }
    System.out.printf("Removing  : %d, No value found\n", key);
  }

  /**
   * Returns the index of the given key.
   * @param key key to be found
   * @return index of the given key.
   */
  private int findIndexOfKey(int key) {
    int index = hash(key, capacity);
    int doubleHash = reHash(key);
    for (int i = 0; i <= maxCollisions; i++) {
      index = (index + i * doubleHash) % (capacity -1);
      if (keys[index] == key) {
        return index;
      }
    }
    return -1;
  }

  /**
   * Compute hash for the given key.
   * @param key to find the hash of.
   * @return hash value.
   */
  private static int hash(int key, int capacity ) {
    return (key ^ (key >> 16)) & (capacity - 1);
  }

  /**
   * Compute double hash for the given key.
   * A collision resolution mechanism.
   * @param key to find double hash for.
   * @return double hash.
   */
  private int reHash(int key) {
    return (key ^ (key >> 16));
  }

  private void ensureCapacity(boolean force) {
    float filledUpPercent = (float) size / capacity;
    if (filledUpPercent >= THRESHOLD || force) {
      int newCapacity = capacity * 2;
      int[] newKeys = new int[newCapacity];
      int[] newValues = new int[newCapacity];
      for (int i = 0; i < newCapacity; i++) {
        newKeys[i] = -1;
        newValues[i] = -1;
      }
      int newCollisionCounter = 0;
      for (int i = 0; i < capacity; i++) {
        newCollisionCounter = 0;
        int key = keys[i];
        int value = values[i];
        if (key != -1) {
          int index = hash(key, newCapacity);
          int doubleHash = reHash(key);
          while (newKeys[index] != key && newKeys[index] != -1) {
            index = (index + newCollisionCounter * doubleHash) % newCapacity;
            newCollisionCounter++;
          }
          newCollisionCounter = Math.max(maxCollisions, newCollisionCounter);
          newKeys[index] = key;
          newValues[index] = value;
        }
      }

      keys = newKeys;
      values = newValues;
      capacity = newCapacity;
      maxCollisions = newCollisionCounter;
      System.out.println("Capacity Doubled to : " + newCapacity);
    }
  }

  public static void main(String[] args) {
    HashMapWithArray map = new HashMapWithArray();
    map.put(4,30);map.put(41,24);map.remove(12);map.get(4);map.put(82,59);map.put(92,22);map.put(34,41);
    map.remove(98);map.put(59,59);map.put(20,35);map.put(24,74);map.put(95,10);map.remove(59);map.put(66,1);
    map.get(20);map.put(20,46);map.remove(59);map.remove(2);map.put(28,13);map.put(45,16);map.get(41);map.put(12,8);
    map.remove(24);map.put(27,61);map.put(43,57);map.put(72,24);map.remove(20);map.put(95,75);map.remove(20);
    map.put(64,8);map.put(36,61);map.put(95,53);map.put(76,34);map.remove(24);map.put(55,68);map.remove(21);map.put(75,71);
    map.put(43,36);map.put(50,3);map.put(4,97);map.put(44,57);map.put(20,1);map.put(4,66);map.put(8,71);map.get(14);map.put(34,41);
    map.put(75,64);map.put(98,47);map.get(35);map.put(12,45);map.put(93,69);map.put(93,10);map.put(64,66);map.put(72,99);
    map.get(53);map.put(50,97);map.put(48,65);map.remove(80);map.put(46,13);map.put(60,15);map.put(42,3);map.put(29,18);map.put(95,70);
    map.put(0,80);map.put(6,15);map.remove(73);map.put(26,45);map.get(58);map.get(46);map.put(60,91);map.put(13,32);map.get(75);
    map.put(99,67);map.put(85,95);map.put(82,37);map.put(7,62);map.put(1,97);map.remove(66);map.remove(13);map.put(63,35);
    map.get(65);map.put(25,12);map.put(38,69);map.put(44,3);map.remove(6);map.put(86,62);map.put(76,8);map.put(22,79);
    map.put(55,86);map.put(37,79);map.put(63,26);map.put(51,23);map.put(48,50);map.put(77,72);map.remove(65);
    map.put(14,18);map.put(63,90);map.remove(37);map.put(88,6);map.remove(61);
  }
  /**
   * Outstanding problems:
   * ReHash generates same set of after indexes to be used, and if
   * they are all filled up we are stuck in infinite loop.
   */
}