package com.sho.hire.hw;

import org.apache.commons.lang3.StringUtils;

/**
 * A class to test Ram Komma's coding skills for showtime's backend engineer.
 */
public class ReplaceKommaRam {

  /**
   * The method will perform a left-to-right string replacement, followed by reversing the resultant
   * words
   * <pre>
   *   For Example:
   *    ("I like cats", "cat", "dog") -> "dogs like I"
   * </pre>
   *
   * @param hayStack haystack on which replace & reverse are performed. .
   * @param needle needle to be replaced in the haystack
   * @param arrow arrow replacing the needle.
   * @return haystack replaced and reversed.
   */
  public String ecalpeResrever(String hayStack, String needle, String arrow) {
    if (StringUtils.isBlank(hayStack)) {
      return hayStack;
    }

    StringBuffer hayStackBuffer = new StringBuffer(hayStack);

    // Replace
    replaceNeedlesWithArrows(hayStackBuffer, needle, arrow);

    // Reverse haystack
    hayStackBuffer.reverse();

    // Reverse words in haystack.
    hayStackBuffer = reverseWords(hayStackBuffer);

    return hayStackBuffer.toString();
  }

  /**
   *  Replace needles with arrows in hayStack.
   *  Uses KMP pattern matching algorithm to find needles in haystack.
   *
   * @param hayStack in which needle needs to be searched.
   * @param needle needle to be replaced.
   * @param arrow arrow to replaceNeedlesWithArrows needle with.
   * @return {@link StringBuffer} haystack with arrows.
   */
  StringBuffer replaceNeedlesWithArrows(StringBuffer hayStack, String needle, String arrow) {

    if(StringUtils.isEmpty(needle))
      return hayStack;

    //Return with out searching for needle if haystack is smaller than needle.
    if (needle.length() > hayStack.length()) {
      return hayStack;
    }

    int[] partialMatchArray = computePartialMatchArray(needle.toCharArray());
    int hayStackRunner = 0, needleRunner = 0;

    while (hayStackRunner < hayStack.length() && needleRunner < needle.length()) {
      if (hayStack.charAt(hayStackRunner) == needle.charAt(needleRunner)) {
        hayStackRunner++;
        needleRunner++;
        if (needleRunner == needle.length()) {
          hayStack.replace(hayStackRunner - needleRunner, hayStackRunner, arrow);
          needleRunner = 0;
        }
      } else {
        if (needleRunner != 0) {
          needleRunner = partialMatchArray[needleRunner - 1];
        } else {
          hayStackRunner++;
        }
      }
    }
    return hayStack;
  }

  /**
   * This private method computes the partial match array which tells,
   * where the next comparison of a pattern can occur.
   *
   * @param pattern that needs to be found in text
   * @return integer array, with comparision positions.
   */
  private int[] computePartialMatchArray(char[] pattern) {

    int j = 0, i = 1;
    int[] partialMatchArray = new int[pattern.length];
    partialMatchArray[j] = 0;

    while (i < pattern.length) {
      if (pattern[j] == pattern[i]) {
        partialMatchArray[i] = j + 1;
        i++;
        j++;
      } else {
        if (j != 0) {
          j = partialMatchArray[j - 1];
        } else {
          partialMatchArray[i] = 0;
          i++;
        }
      }
    }
    return partialMatchArray;
  }

  /**
   * Reverse words in-place for given input text.
   * @param text reverses words in-place.
   * @return text will all of its words reversed in-place.
   */
  StringBuffer reverseWords(StringBuffer text) {
    int begin = 0;
    int end = 0;
    text.append(' ');
    for (int i = 0; i < text.length(); i++) {
      if(text.charAt(i) == ' '){
        end = i;
        String word = text.substring(begin, end);
        String reversed = new StringBuffer(word).reverse().toString();
        text.replace(begin, end, reversed);
        begin = end + 1;
      }
    }
    return text.deleteCharAt(end);
  }
}
