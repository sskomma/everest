package com.questions.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://leetcode.com/problems/string-to-integer-atoi
 */
public class StringToInteger {
  public static int myAtoi(String str) {
    if (str == null || str.length() == 0) {
      return 0;
    }
    String regEx = "^\\s*(-?)(\\+?)(\\d+).*";
    Pattern pattern = Pattern.compile(regEx);
    Matcher matcher = pattern.matcher(str);
    if (matcher.matches()) {
      try {
        int num = Integer.parseInt(matcher.group(3));
        return matcher.group(1).equals("-") ? -1 * num : num;
      } catch (NumberFormatException nfe) {
        return matcher.group(1).equals("-") ? Integer.MIN_VALUE : Integer.MAX_VALUE;
      }
    }
    return 0;
  }

  public static void main(String[] args) {
    System.out.println(myAtoi("-+1"));
  }
}
