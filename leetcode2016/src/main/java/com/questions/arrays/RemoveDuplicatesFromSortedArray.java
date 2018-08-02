package com.questions.arrays;

import static com.questions.arrays.ArrayUtils.printArrayFromOneToN;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
 * @author Ram Komma
 */
public class RemoveDuplicatesFromSortedArray {

  public static int lengthOfUniqueElementsInSortedArray(int[] nums) {

    if (nums == null || nums.length == 0) {
      return 0;
    }
    if (nums.length == 1) {
      return 1;
    }

    int fastRunner = 1, slowRunner = 0;
    while (fastRunner < nums.length) {
      if (nums[slowRunner] == nums[fastRunner]) {
        fastRunner++;
      } else {
        nums[++slowRunner] = nums[fastRunner++];
      }
    }
    return slowRunner + 1;
  }

  public static void main(String[] args) {
    int[] numbers = {1, 1, 2, 2, 2, 2, 3, 3};
    int length = lengthOfUniqueElementsInSortedArray(numbers);
    System.out.println("Length of unique element array: " + length);
    System.out.println(printArrayFromOneToN(numbers));
  }
}
