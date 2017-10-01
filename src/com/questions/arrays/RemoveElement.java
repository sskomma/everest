package com.questions.arrays;

import static com.questions.arrays.ArrayUtils.printArrayFromOneToN;

/**Problem Description: https://leetcode.com/problems/jump-game/description/
 *
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false
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
