package com.questions.arrays;

/**Description: http://www.ardendertat.com/2011/09/24/programming-interview-questions-3-largest-continuous-sum/
 *
 * Find the largest sum, of continuous elements given an array.
 *
 * @author Ram Komma
 *
 */
public class LargestContiniousSum {

  /**Static method to return largest continuous sum possible when given an array of integers.
   * It, prints out starting and ending positions, of the array that gives the largest sum.
   *
   * @param input, an array of integers.
   * @return sum, largest sum possible.
   */
  public static int largestContiniousSum(int[] input) {
    if (input == null || input.length == 0) {
      System.out.println("Empty Input");
      return Integer.MIN_VALUE;
    }

    int maxSum = Integer.MIN_VALUE, currentSum = 0;
    int windowBegin = 0, windowEnd = 0;
    int currentBegin = 0, currentEnd = 0;
    for (int i = 0; i < input.length; i++) {
      currentSum = currentSum + input[i];
      if (currentSum > maxSum) {
        maxSum = currentSum;
        windowBegin = currentBegin;
        windowEnd = currentEnd = i;
      } else if (currentSum <= 0) {
        currentSum = 0;
        currentBegin = i + 1;
        currentEnd = i + 1;
      } else {
        currentEnd = i;
      }
    }
    System.out.println("Window Range:" + windowBegin + " to " + windowEnd);
    return maxSum;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    int[] input = {-2, -9, -3, 1, 11, -2, -3, -4, -2, -9};
    int[] input1 = {0, 0};
    int max = largestContiniousSum(input1);
    System.out.println(max);
  }
}
