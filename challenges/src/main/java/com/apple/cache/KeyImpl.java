package com.apple.cache;

import com.google.common.base.Objects;

/**
 * An implementation of {@link Key}.
 * @param <V> type information of value being associated with the key.
 *
 * @author Ram Komma
 */
public class KeyImpl<V> implements Key<V> {

  private Object key;
  private Class<V> valType;

  private KeyImpl(Object key, Class<V> valueType) {
    this.key = key;
    this.valType = valueType;
  }

  /**
   * An instanciator, to create an instance of Key.
   * @param key the key to save value in cache.
   * @param valueType Clazz of value being associated.
   * @param <V> value type.
   * @return {@link Key} an instance of Key.
   */
  public static <V> Key<V> getInstance(Object key, Class<V> valueType) {
    return new KeyImpl<>(key, valueType);
  }

  @Override
  public Object getKey() {
    return key;
  }

  @Override
  public Class<V> valueType() {
    return valType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof KeyImpl)) {
      return false;
    }
    KeyImpl<?> key1 = (KeyImpl<?>) o;
    return Objects.equal(getKey(), key1.getKey()) &&
        Objects.equal(valType, key1.valType);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getKey(), valType);
  }
}
