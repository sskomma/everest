package com.ccti.moderate;

/**
 * You are given an array of integers (both positive and negative). Find the
 * contiguous sequence with the largest sum. Return the sum.
 * EXAMPLE
 * lnput:2, -8, 3, -2, 4, -10
 * Output: 5 ( i. e • , { 3, -2, 4})
 */
public class ContiguousSequence {

  public static int contiguosSeq(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
      return Integer.MIN_VALUE;
    }

    int maxSum = Integer.MIN_VALUE;
    int currentMax = Integer.MIN_VALUE;
    for (int i : numbers) {
      if (i >= 0) {
        currentMax = currentMax < 0 ? i : currentMax + i;
      } else {
        if (currentMax < 0) {
          currentMax = i;
        } else {
          currentMax = currentMax + i;
        }
      }
      maxSum = Math.max(maxSum, currentMax);
    }

    return maxSum;
  }

  public static void main(String[] args) {
    int[] numbers = {2, -8, 3, -2, 4, -10};
    System.out.println("Max sum: " + contiguosSeq(numbers));
  }
}
