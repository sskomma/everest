package com.questions.arrays;

public class RotateArray {
  public static void main(String[] args) {
    int[] nums = {1,2};
    rotate(nums, 2);
    for (Integer i : nums) {
      System.out.println(i);
    }
  }

  public static void rotate(int[] nums, int k) {
    if (nums == null || nums.length == 0 ) {
      return;
    }
    k %= nums.length;
    rotateByIndex(0, nums.length - 1, nums);
    rotateByIndex(0, k - 1, nums);
    rotateByIndex(k, nums.length - 1, nums);
  }

  private static void rotateByIndex(int start, int end, int[] nums) {
    for (int i = start, j = end; i < j; i++, j--) {
      int temp = nums[i];
      nums[i] = nums[j];
      nums[j] = temp;
    }
  }
}
