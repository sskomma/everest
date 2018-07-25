package com.apple.cache;

/**
 * Interface of key, used in {@link Cache}.
 * Type information of Value being associated with key, is saved to ensure type safety.
 *
 * @param <V> type of value being associated with key.
 *
 * @author Ram Komma
 */
interface Key<V> {
  /**
   * Method to give the actual key data.
   * @return the key.
   */
  Object getKey();

  /**
   * Type information of value being associated with this key.
   * @return {@link Class} of value
   */
  Class<V> valueType();
}
