package com.questions.arrays;

/**Given an array of n integers where n > 1, nums, 
 * return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 *
 * Solve it without division and in O(n).
 * For example, given [1,2,3,4], return [24,12,8,6].
 * https://leetcode.com/problems/product-of-array-except-self/
 *
 * @author Ram Komma
 *
 */
public class ProductofArrayExceptSelf {

  //This uses division.
  public static int[] productOfArrExceptSelf(int[] nums) {
    int product = 1;
    int zeroCount = 0;
    int zeroElementIndex = 0;
    for (int i = 0; i < nums.length && zeroCount < 2; i++) {
      if (nums[i] == 0) {
        zeroCount++;
        zeroElementIndex = i;
      } else {
        product = product * nums[i];
      }
    }
    int[] output = new int[nums.length];

    if (zeroCount == 1) {
      output[zeroElementIndex] = product;
    } else if (zeroCount == 0) {
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] != 0) {
          output[i] = product / nums[i];
        }
      }
    }
    return output;
  }

  public static int[] productOfArrayExceptSelfNoDiv(int[] nums) {
    int[] t1 = new int[nums.length];
    int[] t2 = new int[nums.length];
    int[] result = new int[nums.length];

    t1[0] = t2[nums.length - 1] = 1;
    for (int i = 0; i < nums.length - 1; i++) {
      t1[i + 1] = nums[i] * t1[i];
    }
    for (int i = nums.length - 1; i > 0; i--) {
      t2[i - 1] = nums[i] * t2[i - 1];
    }
    for (int i = 0; i < nums.length; i++) {
      result[i] = t1[i] * t2[i];
    }
    return result;
  }

  public static void main(String[] args) {
    int[] nums = {1, 1};
    for (int n : productOfArrExceptSelf(nums)) {
      System.out.println(n);
    }
  }
}
