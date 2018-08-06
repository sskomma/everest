package com.questions.strings;

/**
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 *
 * Note:
 *
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 * https://leetcode.com/problems/add-strings/description/
 */
public class AddStrings {

  public static String addStrings(String numberOne, String numberTwo) {

    StringBuilder result = new StringBuilder();
    int carryFwd = 0;
    int i = numberOne.length() - 1, j = numberTwo.length() - 1;
    for (; i >= 0 || j >= 0 || carryFwd != 0; i--, j--) {
      int n1 = i >= 0 ? numberOne.charAt(i) - '0' : 0;
      int n2 = j >= 0 ? numberTwo.charAt(j) - '0' : 0;
      int sum = n1 + n2 + carryFwd;
      result.append(sum % 10);
      carryFwd = sum / 10;
    }

    return result.reverse().toString();
  }

  public static void main(String[] args) {
    System.out.println(addStrings("349", "890"));
  }
}
