package com.ctci.array;

import org.apache.commons.lang3.StringUtils;

/**
 * One Away: There are three types of edits that can be performed on strings: insert a character,
 * remove a character, or replace a character. Given two strings, write a function to check if they are
 * one edit (or zero edits) away.
 *
 * EXAMPLE
 * pale, ple -> true
 * pales, pale -> true
 * pale, bale -> true
 * pale, bake -> false
 */
public class OneAway {
  public static boolean oneAway(String s1, String s2) {
    if (StringUtils.isEmpty(s1) || StringUtils.isEmpty(s2)) {
      throw new IllegalArgumentException();
    }

    int l1 = s1.length(), l2 = s2.length();
    int delta = Math.abs(l1 - l2);
    if (delta > 1) {
      return false;
    }
    // To add one character
    else if (delta == 1 && l1 > l2) {
      return oneDeleteAway(s1.toCharArray(), s2.toCharArray(), l1, l2);
    }
    //To delete one character
    else if (delta == 1 && l2 > l1) {
      return oneDeleteAway(s2.toCharArray(), s1.toCharArray(), l2, l1);
    }
    else if (l1 == l2) {
      int count = 0;
      for (int i = 0; i < l1; i++) {
        if (s1.charAt(i) != s2.charAt(i)) {
          count++;
        }
      }
      return (count == 1);
    } else {
      return false;
    }
  }

  //c1 > c2, l1 >l2
  public static boolean oneDeleteAway(char[] c1, char[] c2, int l1, int l2) {
    int i = 0, j = 0;
    while (i < l1 && j < l2) {
      if (c1[i] == c2[j]) {
        j++;
      }
      i++;
    }
    return (j == l2) && (i == l1 || i == l1-1);
  }

  public static void main(String[] args) {
    System.out.println(oneAway("pale", "bake"));
  }
}
