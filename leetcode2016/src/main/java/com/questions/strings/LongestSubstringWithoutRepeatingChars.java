package com.questions.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Given a string, find the length of the longest substring without repeating characters. 
 * For example, the longest substring without repeating letters for "cababcbb" is "abc", which the length is 3.
 * For "bbbbb" the longest substring is "b", with the length of 1.
 *
 *  @author Ram Komma
 */
public class LongestSubstringWithoutRepeatingChars {

  public static int lengthOfLongestSubstring(String s) {
    int i = 0;
    int length = 0;
    int cur_len = 0;
    int window_start = 0;
    int window_end = 0;
    if (s == null || s.isEmpty()) {
      return 0;
    }

    Map<Character, Integer> existing = new HashMap<Character, Integer>();

    while (i < s.length()) {
      if (existing.get(s.charAt(i)) == null) {
        cur_len++;
      } else {
        if (existing.get(s.charAt(i)) < window_start) {
          cur_len++;
        } else {
          window_start = existing.get(s.charAt(i)) + 1;
          length = length < cur_len ? cur_len : length;
          cur_len = window_end - window_start + 1;
        }
      }
      window_end++;
      existing.put(s.charAt(i), i);
      i++;
    }
    length = length < cur_len ? cur_len : length;

    return length;
  }

  public static String longestSubStrWithOutDups(String s) {
    if (s == null || s.length() == 0) {
      return null;
    }

    int begin = 0, end = 0, counter = 0;
    Map<Character, Integer> map = new HashMap<>();
    String result = "";
    while (end < s.length()) {
      Character c = s.charAt(end);
      map.put(c, map.getOrDefault(c, 0) + 1);
      if (map.get(c) > 1) {
        counter++;
      } else {
        result = (end - begin) > result.length() ? s.substring(begin, end) : result;
      }
      end++;

      while (counter > 0) {
        char temp = s.charAt(begin);
        if (temp == c) {
          counter--;
        }
        map.put(c, map.get(c) - 1);
        begin++;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    String str =
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCD";
    System.out.println("Length of maximum sub-string is: " + longestSubStrWithOutDups(str));
  }
}
