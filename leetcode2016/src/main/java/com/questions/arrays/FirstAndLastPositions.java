package com.questions.arrays;

/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/submissions/
 *
 * use Binary Search.
 */

public class FirstAndLastPositions {

    private static int[] array = {-1, -1};
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return array;
        }
        int window_begin = 0;
        int window_end = nums.length-1;

        for (int i = 0; i < nums.length / 2; i++) {
            int midPoint = (window_begin + window_end) / 2;
            int window_one_result = inRange(nums, target, window_begin, midPoint);
            int window_two_result = inRange(nums, target, midPoint + 1, window_end);
            if(window_one_result == -1 && window_two_result == -1) {
                return array;
            }
            if (window_one_result > -1) {
                window_end = midPoint;
                if(window_one_result == 0)
                    break;
            }
            if (window_two_result > -1) {
                window_begin = midPoint + 1;
                if (window_two_result == 0)
                break;
            }
        }
        return (nums[window_begin] == target) ?
               getRange(nums, target, window_begin) : getRange(nums, target, window_end);
    }

    private int inRange(int[] nums, int target, int window_being, int window_end) {
        if (window_being > window_end) {
            return -1;
        }
        if(nums[window_being] == target || nums[window_end] == target) {
            return 0;
        }
        if(nums[window_being] < target && nums[window_end] > target) {
            return 1;
        }
        return -1;
     }

     private int[] getRange(int[] nums, int target, int pointer) {
        if(nums[pointer] !=target) {
            return array;
        }
         int begin_index = pointer;
         int end_index = pointer;

         while (begin_index > 0 && nums[begin_index - 1] == target) {
             begin_index--;
         }
         while (end_index < nums.length - 1 && nums[end_index + 1] == target) {
             end_index++;
         }
         int[] indexs =  {begin_index, end_index};
         return indexs;
     }

    public static void main(String[] args) {
        int[] array = {1};
        FirstAndLastPositions flp = new FirstAndLastPositions();
        System.out.println(flp.searchRange(array, 1));
    }

}
