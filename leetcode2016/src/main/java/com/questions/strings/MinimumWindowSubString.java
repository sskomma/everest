package com.questions.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 *
 * https://leetcode.com/problems/minimum-window-substring/description/
 * #leetcode76
 */
public class MinimumWindowSubString {

  public static String minWindow(String s, String t) {
    if (s == null || t == null || s.length() < t.length()) {
      return "";
    }
    Map<Character, Integer> freqMap = new HashMap<>();
    for (Character c : t.toCharArray()) {
      freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
    }
    int count = freqMap.size();
    int begin = 0, end = 0;
    String result = "";

    while (end < s.length()) {
      char currentChar = s.charAt(end);
      if (freqMap.containsKey(currentChar)) {
        freqMap.put(currentChar, freqMap.get(currentChar) - 1);
        if (freqMap.get(currentChar) == 0) {
          count--;
        }
      }
      end++;
      while (count == 0) {
        char temp = s.charAt(begin);
        if (freqMap.containsKey(temp)) {
          freqMap.put(temp, freqMap.get(temp) + 1);
          if (freqMap.get(temp) > 0) {
            count++;
          }
        }
        if (result.isEmpty()  || (end - begin) < result.length()) {
          result = s.substring(begin, end);
        }
        begin++;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.out.println(minWindow("ADOBECODEBANC", "ABC"));
  }
}
