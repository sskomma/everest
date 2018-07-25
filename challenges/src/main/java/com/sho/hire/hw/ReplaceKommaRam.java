package com.sho.hire.hw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * A class to test Ram Komma's coding skills for showtime's backend engineer.
 */
public class ReplaceKommaRam {

  public static void main(String[] args) {
    ReplaceKommaRam rkr = new ReplaceKommaRam();
    //System.out.println(rkr.replaceReverser("I like cats", "cat", "dog"));
    //System.out.println(rkr.replaceReverser("ABC", "A", "a"));
    //System.out.println(rkr.replaceReverser("AAA AAB BAA", "AA", "a"));
    //System.out.println(rkr.replaceReverser("I Work.", "Work", "Play"));
    //System.out.println(rkr.replaceReverser("Tests are the best!","the best!","just ok."));

    try {
      FileInputStream fio = new FileInputStream(new File("~/Downloads/SampleTextFile_1MB.txt"));
    } catch (FileNotFoundException e) {
      System.out.println("Well, File not found man");
    }
  }

  /**
   * The method will perform a left-to-right string replacement, followed by reversing the resultant
   * words
   * <pre>
   *   For Example:
   *    ("I like cats", "cat", "dog") -> "dogs like I"
   * </pre>
   *
   * @param hayStack the text, in which needles are replaced by replacement string.
   * @param needle the string to be replaced in the haystack
   * @param replacement the string to be replaced by in haystack.
   * @return haystack replaced and reversed.
   */
  public String replaceReverser(String hayStack, String needle, String replacement) {
    if (validateString(hayStack) && validateString(needle) && validateString(replacement)) {
      return hayStack;
    }
    hayStack = hayStack.replaceAll(needle, replacement);
    hayStack = reverseWords(hayStack);
    return hayStack;
  }

  private boolean validateString(String text) {
    return (text == null || text.length() == 0);
  }

  private String reverseWords(String s) {
    StringBuilder sb = new StringBuilder();
    String[] words = s.split(" ");

    for (int i = words.length - 1; i >= 0; i--) {
      if (validateString(words[i])) {
        continue;
      }
      sb.append(words[i]);
      sb.append(" ");
    }
    return sb.toString().trim();
  }
}
