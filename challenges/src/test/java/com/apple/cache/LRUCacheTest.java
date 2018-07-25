package com.apple.cache;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LRUCacheTest {

  private Cache cache;

  @Before
  public void setUp() {
    cache = LRUCache.getInstance(2);
  }

  @After
  public void tearDown() {
    cache = null;
  }

  @Test
  public void testSingleInstance() {
    LRUCache c = LRUCache.getInstance();

    assertSame(c, cache);
    assertSame(LRUCache.getInstance(), cache);
  }

  @Test
  public void testCacheTypeSafety() {
    Key<Integer> key = KeyImpl.getInstance("Komma", Integer.class);
    cache.put(key, 22);
    cache.put(KeyImpl.getInstance("Komma", String.class), "Number");
    //cache.put();
    assertEquals(new Integer(22), cache.get(key));
    assertEquals("Number", cache.get(KeyImpl.getInstance("Komma", String.class)));
  }

  @Test
  public void testLRU() {
    Key<Integer> one = KeyImpl.getInstance("one", Integer.class);
    Key<String> two = KeyImpl.getInstance("two", String.class);
    Key<Integer> three = KeyImpl.getInstance("three", Integer.class);
    Key<String> four = KeyImpl.getInstance("four", String.class);

    cache.put(one, 1);
    cache.put(two, "two");
    assertEquals(new Integer(1), cache.get(one));
    cache.put(three, 3);
    assertNull(cache.get(two));
    cache.put(four, "four");
    assertNull(cache.get(one));
    assertEquals(new Integer(3), cache.get(three));
    assertEquals("four", cache.get(four));
  }
}