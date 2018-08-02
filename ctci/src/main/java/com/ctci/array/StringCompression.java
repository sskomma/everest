package com.ctci.array;

import org.apache.commons.lang3.StringUtils;

/**
 * Implement a method to perform basic string compression using the counts
 * of repeated characters. For example, the string aabcccccaaa would become a2blc5a3. If the
 * "compressed" string would not become smaller than the original string, your method should return
 * the original string. You can assume the string has only uppercase and lowercase letters (a - z).
 */
public class StringCompression {

  public static String stringCompression(String s) {
    if (StringUtils.isEmpty(s)) {
      throw new IllegalArgumentException();
    }

    StringBuilder sb = new StringBuilder();
    char[] array = s.toCharArray();
    char prev = array[0];
    int count = 1;
    for (int i = 1; i < s.length(); i++) {
      if (prev == array[i]) {
        count++;
      } else {
        sb.append(prev);
        sb.append(count);
        prev = array[i];
        count = 1;
      }
    }
    sb.append(prev);
    sb.append(count);

    String s2 = sb.toString();
    System.out.println(s2);
    return s.length() < s2.length() ? s : s2;
  }

  public static void main(String[] args) {
    System.out.println(stringCompression("abc"));
  }
}
