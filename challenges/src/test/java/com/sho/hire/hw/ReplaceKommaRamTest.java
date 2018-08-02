package com.sho.hire.hw;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReplaceKommaRamTest {

  private ReplaceKommaRam harStackReverser = null;

  @Before
  public void setUp() {
    harStackReverser = new ReplaceKommaRam();
  }

  @After
  public void tearDown() {
    harStackReverser = null;
  }

  @Test
  public void ecalpeResrever_happyPaths() {

    String hayStack = "  cats are   like cats are   ";
    assertEquals("   is dog like   is dog  ", harStackReverser
        .ecalpeResrever(hayStack, "cats are", "dog is"));
    assertEquals("aBC", harStackReverser.ecalpeResrever("ABC", "A", "a"));
    assertEquals("Ba aB aA", harStackReverser.ecalpeResrever("AAA AAB BAA", "AA", "a"));
    assertEquals("Play. I", harStackReverser.ecalpeResrever("I Work.", "Work", "Play"));
    assertEquals(
        "ok. just are Tests", harStackReverser
            .ecalpeResrever("Tests are the best!", "the best!", "just ok."));
  }

  @Test
  public void ecalpeResrever_testEmptyHaystack() {
    assertEquals("", harStackReverser.ecalpeResrever("", "the best!", "just ok"));
  }

  @Test
  public void ecalpeResrever_testEmptyNeedle() {
    assertEquals("best! the are Tests", harStackReverser
        .ecalpeResrever("Tests are the best!", "", "just ok."));
  }

  @Test
  public void ecalpeResrever_testEmptyArrow() {
    assertEquals(" are Tests", harStackReverser
        .ecalpeResrever("Tests are the best!", "the best!", ""));
  }

  @Test
  public void reverseWords() {
    StringBuffer buffer = new StringBuffer("komma ram");
    assertEquals("ammok mar", harStackReverser.reverseWords(buffer).toString());
  }
}