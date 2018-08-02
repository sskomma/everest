package com.questions.recurssion;

/**
 * Convert a non-negative integer to its english words representation. Given input is guaranteed
 * to be less than 231 - 1.
 *
 * For example,
 *  123 -> "One Hundred Twenty Three"
 *  12345 -> "Twelve Thousand Three Hundred Forty Five"
 *  1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 *  https://leetcode.com/problems/integer-to-english-words/description/
 */
public class IntegerToString {
  private static final String[] ones =
      {"One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine "};
  private static final String[] teens =
      {"Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ",
          "Eighteen ", "Nineteen "};
  private static final String[] tens =
      {"Ten ", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ",
          "Ninety "};
  private static final String[] hundreds = {"", "Thousand ", "Million ", "Billion "};

  private static String convertIntToString(int number) {
    if(number == 0) {
      return "Zero";
    }
    String result = "";

    for (int i = 0; i <= 3 && number != 0; i++) {
      int q = number % 1000;
      number = number / 1000;
      if (q != 0) {
        result = threeDigit(q) + hundreds[i] + result;
      }
    }
    return result.substring(0, result.length() > 0 ? result.length() - 1 : 0);
  }

  public static String threeDigit(int number) {
    StringBuffer sb = new StringBuffer();
    int hundred = number / 100;
    number = number % 100;
    if (hundred != 0) {
      sb.append(ones[hundred - 1] + "Hundred ");
    }
    if (number / 10 == 1 && number % 10 > 0) {
      sb.append(teens[(number % 10) - 1]);
    } else {
      if (number / 10 != 0) {
        sb.append(tens[(number / 10) - 1]);
      }
      if (number % 10 != 0) {
        sb.append(ones[(number % 10) - 1]);
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(convertIntToString(1234567891));
  }
}
