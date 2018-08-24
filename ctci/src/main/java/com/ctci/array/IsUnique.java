package com.ctci.array;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Implement an algorithm to determine if a string has all unique characters. What if you
 * cannot use additional data structures?
 */
public class IsUnique {

  public static boolean isUnique(String str) {
    if(str == null || str.length() == 0) {
      return false;
    }

    char[] chars = str.toCharArray();
    Arrays.sort(chars);
    for(int i = 1; i< chars.length; i++) {
      if(chars[i-1] == chars[i]) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println( isUnique("Koma"));
  }
}
