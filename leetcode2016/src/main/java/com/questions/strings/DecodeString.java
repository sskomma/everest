package com.questions.strings;

public class DecodeString {
  public static int decode(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    char[] numbers = s.toCharArray();
    int count = 0;

    for (int i = 1; i < numbers.length; i++) {
      if ( (numbers[i-1] - '0')*10 + numbers[i] - '0' <= 26 ) {
        count++; i++;
      }
      count++;
    }
    return count+1;
  }

  public static void main(String[] args) {
    System.out.println(decode("122"));
  }
}
