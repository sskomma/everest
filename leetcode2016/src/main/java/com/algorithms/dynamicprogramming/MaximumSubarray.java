package com.algorithms.dynamicprogramming;

/**
 * Find the contiguous sub-array within an array (containing at least one number) which has the largest sum.
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * the contiguous sub-array [4,-1,2,1] has the largest sum = 6.
 *
 * https://leetcode.com/problems/maximum-subarray/
 *
 * @author Ram Komma
 */
public class MaximumSubarray {

  public static int maxSubArray(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int maxSum = Integer.MIN_VALUE;
    int sum = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] >= 0) {
        sum = sum < 0 ? nums[i] : sum + nums[i];
      } else {
        if (sum < 0) {
          sum = Math.max(sum, nums[i]);
        } else {
          int temp = sum + nums[i];
          sum = temp < 0 ? 0 : temp;
        }
      }
      maxSum = Math.max(maxSum, sum);
    }
    return maxSum;
  }

  public static void main(String[] args) {
    int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    //int[] nums = {-2,-11,-3};
    System.out.println("Max Sum: " + maxSubArray(nums));
  }
}
