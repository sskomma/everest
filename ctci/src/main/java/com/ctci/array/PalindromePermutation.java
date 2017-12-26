package com.ctci.array;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class PalindromePermutation {

  public static boolean isPermutationPalindrome(String str) {
    if(StringUtils.isEmpty(str)) {
      throw new IllegalArgumentException();
    }
    Map<Character, Integer> checker = new HashMap<Character, Integer>();
    for(Character c: str.toCharArray()) {
      if(c != ' ') {
        if(checker.containsKey(c)) {
          int val = checker.get(c);
          val++;
          if(val % 2 == 0) {
            checker.remove(c);
          }
        }
        else {
          checker.put(c,1);
        }
      }
    }
    return (checker.size() == 0 || checker.size() == 1);
  }
  public static void main(String[] args) {
    System.out.println(isPermutationPalindrome("taco at"));
  }
}
