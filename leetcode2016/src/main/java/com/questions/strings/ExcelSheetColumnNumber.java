package com.questions.strings;

/**
 *
 * Related to question Excel Sheet Column Title
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 *
 * For example:
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 *
 * https://leetcode.com/problems/excel-sheet-column-number/
 * #leetcode171
 */
public class ExcelSheetColumnNumber {
  public static void main(String[] args) {
    char ch = 'C';
    System.out.println(constructString("ABC"));
  }

  public static int constructString(String s) {
    int result = 0;
    if (s == null || s.length() == 0) {
      return result;
    }
    char[] chars = s.toCharArray();
    int k = 0;
    for (Character c : chars) {
      k = k * 26 + (c - 'A' + 1);
    }
    return k;
  }
}
