package com.questions.strings;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.
 *
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three",
 * it is the fifth second-level revision of the second first-level revision.
 *
 * https://leetcode.com/problems/compare-version-numbers/
 */
public class CompareVersionNumbers {

  public static void main(String[] args) {
    System.out.println(compareVersion("0.1", "1.1"));
    System.out.println(compareVersion("1.0.1", "1"));
    System.out.println(compareVersion("7.5.2.4", "7.5.3"));
    System.out.println(compareVersion("01", "1.0.0.0"));
    System.out.println(compareVersion("", ""));
  }

  /**
   * Compares two versions.
   * @param version1 first version in comparision.
   * @param version2 second version in comparision.
   * @return 1 if version1 > version2 ; -1 if version1 < version2 ; 0 otherwise
   */
  public static int compareVersion(String version1, String version2) {

    if (version1 == null || version1.trim().equals("") || version2 == null || version2.trim()
        .equals("")) {
      return -1;
    }

    int[] versionOne = parseNumbers(version1);
    int[] versionTwo = parseNumbers(version2);

    int i = 0, j = 0;

    while (i < versionOne.length && j < versionTwo.length) {
      int comparision = Integer.compare(versionOne[i], versionTwo[j]);
      if (comparision != 0) {
        return comparision;
      }
      i++;
      j++;
    }

    return i < versionOne.length ? 1 : j < versionTwo.length ? -1 : 0;
  }

  /**
   * Cleans up a given version string by converting each version part to int and removes
   * trailing zeroes in version.
   * @param version version to be cleansed.
   * @return an array of ints.
   */
  private static int[] parseNumbers(String version) {
    Deque<Integer> deque = new LinkedList<>();

    // Put all numbers in deque
    for (String numStr : version.split("\\.")) {
      deque.addLast(Integer.parseInt(numStr));
    }

    //Remove trailing zeros
    while (!deque.isEmpty() && deque.peekLast() == 0) {
      deque.removeLast();
    }
    int[] versions = new int[deque.size()];
    int i = 0;
    while (!deque.isEmpty()) {
      versions[i] = deque.removeFirst();
      i++;
    }
    return versions;
  }
}
