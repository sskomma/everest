package com.questions.heaps;

import com.questions.sorting.QuickSorting;

import java.util.PriorityQueue;

/**
 * Find the kth largest element in an unsorted array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 *
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * @author Ram Komma
 */
public class KthLargestElement {

  /**
   * The following method uses a heap to find the kth largest element.
   * which has a complexity of O(NlogK)
   * @param numbers
   * @param k
   * @return
   */
  public static int findKthLargest(int[] numbers, int k) {
    if (numbers == null || numbers.length == 0) {
      return 0;
    }
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int j = 0;
    for (Integer num : numbers) {
      if (j < k) {
        pq.add(num);
      } else {
        if (pq.peek() < num) {
          pq.poll();
          pq.add(num);
        }
      }
      j++;
    }
    return pq.poll();
  }


  public static int findKthLargestNumber(int[] numbers, int k) {
    int low = 0;
    int high = numbers.length - 1;
    k = numbers.length - k;
    while (low < high) {
      int pivot = QuickSorting.partition(numbers, low, high);
      if (pivot == k - 1) {
        return numbers[pivot];
      } else if (pivot <= k - 1) {
        low = pivot;
      } else {
        high = pivot;
      }
    }
    return numbers[k-1];
  }

  public static void main(String[] args) {
    int[] nums = {3, 2, 1, 5, 6, 4};
    System.out.println(findKthLargestNumber(nums, 2));
  }
}
