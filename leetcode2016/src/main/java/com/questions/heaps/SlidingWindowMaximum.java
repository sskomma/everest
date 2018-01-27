package com.questions.heaps;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * For example,
 *  Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 *
 *  Window position                Max
 *  ---------------               -----
 *  [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *  Therefore, return the max sliding window as [3,3,5,5,6,7].
 *
 *  Note:
 *  You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 *
 *  Follow up:
 *  Could you solve it in linear time?
 *
 * https://leetcode.com/problems/sliding-window-maximum
 * #leetcode239
 */
public class SlidingWindowMaximum {

  public static void main(String[] args) {
    SlidingWindowMaximum swm = new SlidingWindowMaximum();
    int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
    int[] result = swm.maxSlidingWindow(nums, 3);
    for(Integer i : result) {
      System.out.println(i);
    }
  }

  public int[] maxSlidingWindow(int[] nums, int k) {

    if (nums == null || nums.length == 0) {
      return new int[0];
    }
    int n = nums.length;
    int[] result = new int[n - k + 1];

    // Stores indexes of array and the first element is always the largest element in that window.
    Deque<Integer> deque = new LinkedList<>();
    for (int i = 0; i < n; i++) {

      // Remove the first element of the queue, if there are more than k elements in deque.
      if (!deque.isEmpty() && deque.peek() < i - k + 1) {
        deque.pollFirst();
      }
      // Remove all elements in queue from the end if they are smaller than inserting element.
      while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
        deque.pollLast();
      }

      deque.offer(i);

      if (i - k + 1 >= 0) {
        result[i - k + 1] = nums[deque.peek()];
      }
    }
    return result;
  }
}
