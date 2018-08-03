package com.sho.hire.hw;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReplaceKommaRamTest {

  private ReplaceKommaRam hayStackReverser = null;

  @Before
  public void setUp() {
    hayStackReverser = new ReplaceKommaRam();
  }

  @After
  public void tearDown() {
    hayStackReverser = null;
  }

  @Test
  public void ecalpeResrever_happyPaths() {

    String hayStack = "  cats are   like cats are   ";
    assertEquals("   is dog like   is dog  ", hayStackReverser
        .ecalpeResrever(hayStack, "cats are", "dog is"));
    assertEquals("aBC", hayStackReverser.ecalpeResrever("ABC", "A", "a"));
    assertEquals("Ba aB aA", hayStackReverser.ecalpeResrever("AAA AAB BAA", "AA", "a"));
    assertEquals("Play. I", hayStackReverser.ecalpeResrever("I Work.", "Work", "Play"));
    assertEquals(
        "ok. just are Tests", hayStackReverser
            .ecalpeResrever("Tests are the best!", "the best!", "just ok."));
  }

  @Test
  public void ecalpeResrever_testEmptyHaystack() {
    assertEquals("", hayStackReverser.ecalpeResrever("", "the best!", "just ok"));
  }

  @Test
  public void ecalpeResrever_testEmptyNeedle() {
    assertEquals("best! the are Tests", hayStackReverser
        .ecalpeResrever("Tests are the best!", "", "just ok."));
  }

  @Test
  public void ecalpeResrever_testEmptyArrow() {
    assertEquals(" are Tests", hayStackReverser
        .ecalpeResrever("Tests are the best!", "the best!", ""));
  }

  @Test
  public void reverseWords() {
    StringBuilder builder = new StringBuilder("komma ram");
    assertEquals("ammok mar", hayStackReverser.reverseWords(builder).toString());
  }

  @Test
  public void testWithLargeFile() {

    String input = getFile("SampleTextFile_1MB.txt");
    long start = System.currentTimeMillis();
    hayStackReverser.ecalpeResrever(input, "sit", "tis");
    long stop = System.currentTimeMillis();
    System.out.println("Time it took: " + (stop - start));
  }

  private String getFile(String fileName) {

    StringBuilder result = new StringBuilder("");

    //Get file from resources folder
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource(fileName).getFile());

    try (Scanner scanner = new Scanner(file)) {

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        result.append(line).append("\n");
      }
      scanner.close();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {

    }

    return result.toString();
  }
}