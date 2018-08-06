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
    if(nums == null)
      return nums;
    int len = nums.length;
    int[] result = new int[len];

    // Left product array- product of all elements from 0 to i-1
    result[0] = 1;
    for(int i = 1; i < len; i++) {
      result[i] = nums[i-1] * result[i-1];
    }

    //Right product array - product of all elements from n to i -1
    int rightPrd = 1;
    for(int i = len - 2 ; i>=0 ; i-- ) {
      rightPrd = rightPrd * nums[i+1];
      result[i] = result[i] * rightPrd;
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
