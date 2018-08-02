package com.apple.cache;

/**
 * A cache interface.
 *
 * @author Ram Komma
 */
public interface Cache {

  /**
   * Associates the specified value with the specified key in this map.
   * If the map previously contained a mapping for the key, the old
   * value is replaced.
   *
   * @param key key with which the specified value is to be associated
   * @param value value to be associated with the specified key
   * @param <V> type of value being associated with
   */
   <V> void put(Key<V> key, V value);

  /**
   * Returns the value to which the specified key is mapped,
   * or {@code null} if this map contains no mapping for the key.
   *
   * @param key the object to search for
   * @param <V> type of value.
   * @return Value object of type V, null if no mapping exists.
   */
   <V> V get(Key<V> key);
}
