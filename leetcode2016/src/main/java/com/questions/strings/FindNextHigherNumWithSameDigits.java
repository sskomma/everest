package com.questions.strings;

import java.util.Arrays;

/**Given a number, find the next higher number using only the digits in the given number. 
 * For example if the given number is 1234, next higher number with same digits is 1243.
 * http://www.ardendertat.com/2012/01/02/programming-interview-questions-24-find-next-higher-number-with-same-digits/
 * @author Komma
 *
 */
public class FindNextHigherNumWithSameDigits {

  public static int findNextHigherNumberWithSameDigits(int number) {
    char[] numChars = (new Integer(number).toString()).toCharArray();
    for (int i = numChars.length - 1; i > 0; i--) {
      if (numChars[i] > numChars[i - 1]) {
        char t = numChars[i - 1];
        Arrays.sort(numChars, i - 1, numChars.length);
        int j = i - 1;
        for (; j < numChars.length; j++) {
          if (numChars[j] > t) {
            break;
          }
        }
        char temp = numChars[j];
        for (int k = j; k > i - 1; k--) {
          numChars[k] = numChars[k - 1];
        }
        numChars[i - 1] = temp;
        break;
      }
    }
    return Integer.parseInt(new String(numChars));
  }

  public static void main(String[] args) {
    System.out.println(findNextHigherNumberWithSameDigits(1232));
  }
}
