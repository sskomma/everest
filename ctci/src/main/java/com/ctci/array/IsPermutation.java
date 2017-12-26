package com.ctci.array;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class IsPermutation {
  /*  public static boolean isPermutation(String s1, String s2) {
      if(StringUtils.isEmpty(s1) || StringUtils.isEmpty(s2)){
        throw new IllegalArgumentException();
      }
      if(s1.length() == s2.length()) {
        int xor = 0;
        for(char c:s1.toCharArray()) {
          xor = xor ^ c;
        }
        for(char c:s2.toCharArray()) {
          xor = xor ^ c;
        }
        return (xor == 0);
      }
      return false;
    }*/
  public static boolean isPermutation(String s1, String s2) {
    if (StringUtils.isEmpty(s1) || StringUtils.isEmpty(s2)) {
      throw new IllegalArgumentException();
    }
    Map<Character, Integer> checker = new HashMap<>();

    for (char c : s1.toCharArray()) {
      if (checker.containsKey(c)) {
        int val = checker.get(c);
        checker.put(c, val + 1);
      } else {
        checker.put(c, 1);
      }
    }

    for (char c : s2.toCharArray()) {
      if (checker.containsKey(c)) {
        int val = checker.get(c);
        checker.put(c, val - 1);
        if (checker.get(c) == 0) {
          checker.remove(c);
        }
      } else {
        return false;
      }
    }
    return (checker.size() == 0);
  }

  public static void main(String[] args) {
    System.out.println(isPermutation("komma", "ammko"));
  }
}
