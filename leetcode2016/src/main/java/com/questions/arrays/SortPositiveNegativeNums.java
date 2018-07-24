package com.questions.arrays;

import java.util.Deque;
import java.util.LinkedList;

public class SortPositiveNegativeNums {

  public static int[] sortPositiveNegativeNums(int[] nums) {
    if (nums == null || nums.length == 0) {
      return nums;
    }
    Deque<Integer> posInts = new LinkedList<>();
    int zerosCount = 0;
    int fastPointer = 0, slowPointer = 0;
    for (; fastPointer < nums.length; fastPointer++) {
      if (nums[fastPointer] < 0) {
        nums[slowPointer] = nums[fastPointer];
        slowPointer++;
      } else if (nums[fastPointer] == 0) {
        zerosCount++;
      } else {
        posInts.add(nums[fastPointer]);
      }
    }

    while (zerosCount > 0) {
      nums[slowPointer++] = 0;
      zerosCount--;
    }
    while (!posInts.isEmpty()) {
      nums[slowPointer] = posInts.remove();
      slowPointer++;
    }
    return nums;
  }

  public static void main(String[] args) {
    int[] nums = {-2, 4, 8, 10, -19, 11, 0};
    for (Integer i : sortPositiveNegativeNums(nums)) {
      System.out.println(i);
    }
  }
}
