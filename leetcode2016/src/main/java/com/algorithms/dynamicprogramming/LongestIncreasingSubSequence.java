package com.algorithms.dynamicprogramming;

/**Given an unsorted array of integers, find the length of longest increasing subsequence.
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. 
 * Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 *
 * https://leetcode.com/problems/longest-increasing-subsequence/
 * https://www.youtube.com/watch?v=CE2b_-XfVDk&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=5
 *
 * @author Ram Komma
 */
public class LongestIncreasingSubSequence {

  public static int lenOfLongestIncreasingSubSeq(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int size = nums.length;
    int[] temp = new int[size];
    int length = 1;

    for (int i = 0; i < size; i++) {
      temp[i] = 1;
    }

    for (int i = 1; i < size; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i]) {
          temp[i] = Math.max(temp[i], 1 + temp[j]);
          length = Math.max(temp[i], length);
        }
      }
    }
    return length;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    int[] nums = {1, 3, 6, 7, 9, 4, 10, 5, 6};
    System.out.println("Length = " + lenOfLongestIncreasingSubSeq(nums));
  }
}
