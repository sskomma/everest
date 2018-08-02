package com.ctci.array;

/**
 * Write a method to replace all spaces in a string with '%20'. You may assume that the string
 * has sufficient space at the end to hold the additional characters, and that you are given the "true"
 * length of the string. (Note: If implementing in Java, please use a character array so that you can
 * perform this operation in place.)
 *
 * EXAMPLE:
 * Input:  "Mr John Smith    ", 13
 * Output: "Mr%20John%20Smith"
 */
public class URLify {
  public static String urlify(char[] sequence, int length) {
    if(length >= 0) {
      int i = length-1, j = sequence.length-1;
      while(j>i) {
        if(sequence[i] == ' ') {
          sequence[j--] = '0';
          sequence[j--] = '2';
          sequence[j] = '%';
        }
        else {
          sequence[j] = sequence[i];
        }
        i--;
        j--;
      }
    }
    return new String(sequence);
  }

  public static void main(String[] args) {
    System.out.println(urlify("Mr John Smith    ".toCharArray(), 13));
  }
}
