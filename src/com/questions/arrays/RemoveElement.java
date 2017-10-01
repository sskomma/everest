package com.questions.arrays;

import static com.questions.arrays.ArrayUtils.printArrayFromOneToN;

/**Problem Description: https://leetcode.com/problems/remove-element/description/
 *
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * Example:
 * Given input array nums = [3,2,2,3], val = 3
 *
 * Your function should return length = 2, with the first two elements of nums being 2
 *
 * @author Ram Komma
 */
public class RemoveElement {

  public static int removeElement(int[] nums, int val) {
    if( nums == null || nums.length == 0) {
      return 0;
    }
    int fastRunner = 0, slowRunner = 0;
    for(;fastRunner < nums.length; fastRunner++){
      if(nums[fastRunner] != val){
        nums[slowRunner++] = nums[fastRunner];
      }
    }
    return slowRunner;
  }

  public static void main(String[] args) {
    int[] numbers = {3,2,2,3};
    int val = 3;
    int length = removeElement(numbers, val);
    System.out.printf("Length of array without element {%d} is: %d\n", val, length);
    System.out.println(printArrayFromOneToN(numbers));

  }
}
