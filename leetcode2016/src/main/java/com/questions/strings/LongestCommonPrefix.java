package com.questions.strings;

/**Write a function to find the longest common prefix string amongst an array of strings.
 *
 * https://leetcode.com/problems/longest-common-prefix/
 *
 * @author Ram Komma
 */
public class LongestCommonPrefix {

  public static String longestCommonPrefix(String[] strs) {
    if (strs == null) {
      return null;
    }
    if (strs.length == 0) {
      return "";
    }
    int prefixLength = Integer.MAX_VALUE;
    for (String s : strs) {
      prefixLength = Math.min(prefixLength, s.length());
    }
    int len = 1;
    boolean isValidPrefix = true;
    for (; len <= prefixLength; len++) {
      String prefix = strs[0].substring(0, len);
      for (int j = 1; j < strs.length; j++) {
        String temp = strs[j];
        if (!temp.startsWith(prefix)) {
          isValidPrefix = false;
          break;
        }
      }
      if (!isValidPrefix) {
        break;
      }
    }
    return strs[0].substring(0, len - 1);
  }

  public static void main(String[] args) {
    String[] strs = {"ram", "ramarao", "ramji", "ramesh", "ramkumar", "ra"};
    //String[] strs = {"a", "b"};
    System.out.println("Longest Common prefix: " + longestCommonPrefix(strs));
  }
}
