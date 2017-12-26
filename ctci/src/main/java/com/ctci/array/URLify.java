package com.ctci.array;

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
