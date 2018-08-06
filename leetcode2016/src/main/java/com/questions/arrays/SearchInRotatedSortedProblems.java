package com.questions.arrays;


public class SearchInRotatedSortedProblems {

  /**
   * Question:
   *  Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
   *  (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
   *  You are given a target value to search. If found in the array return its index, otherwise return -1.
   *  You may assume no duplicate exists in the array.
   *  Your algorithm's runtime complexity must be in the order of O(log n)
   *
   *  https://leetcode.com/problems/search-in-rotated-sorted-array/description/
   *
   * Solution:
   * inspired from Steven pochmann.
   * https://leetcode.com/problems/search-in-rotated-sorted-array/discuss/14435/Clever-idea-making-it-simple
   *
   * @param array
   * @param target
   * @return
   */
  public static int search(int[] array, int target) {
    if (array == null) {
      return -1;
    }
    int begin = 0;
    int end = array.length;

    while (begin < end) {
      int mid = (begin + end) / 2;
      int midNumber;

      if (array[mid] < array[0] != target < array[0]) {
        midNumber = target < array[0] ? Integer.MIN_VALUE : Integer.MAX_VALUE;
      } else {
        midNumber = array[mid];
      }

      if (midNumber < target) {
        begin = mid + 1;
      } else if (midNumber > target) {
        end = mid ;
      } else {
        return mid;
      }
    }
    return -1;
  }

  //TODO

  /**
   * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
   * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
   * Find the minimum element.
   * You may assume no duplicate exists in the array.
   *
   * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
   * @param array
   * @return
   */
  public int findMin(int[] array){
    return -1;
  }


  //TODO

  /**
   * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
   * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
   * You are given a target value to search. If found in the array return true, otherwise return false.
   *
   * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
   * @param array
   * @param target
   */
  public static boolean searchInArrayWithDuplicates(int[] array, int target ) {
    return false;
  }
  public static void main(String[] args) {
    int[] array = {12, 13, 14, 15, 16, 17, 18, 19, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    System.out.println(search(array, 20));
  }
}
