package com.questions.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem: https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/
 *
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 *
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 *
 *Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 */
public class DisappearedNumbers {
  public static List<Integer> findDisappearedNumbers(int[] numbers) {
    List<Integer> missing = new ArrayList<Integer>();
    int val = 0;
    //Mark positions in array negative for all existing numbers
    for(int i =0; i< numbers.length; i++ ){
      val = Math.abs(numbers[i]) - 1;
      if(numbers[val] > 0) {
        numbers[val] = -numbers[val];
      }
    }
    //Scan the array and find non-negative positions and they must be the missing elements in the array
    for(int i = 0; i < numbers.length; i++) {
      if(numbers[i] > 0) {
        missing.add(i+1);
      }
    }
    return missing;
  }

  public static void main(String[] args) {
    int[] numbers = {4,3,2,7,8,2,3,1};
    List<Integer> missing = findDisappearedNumbers(numbers);
    System.out.println(missing);
  }
}
