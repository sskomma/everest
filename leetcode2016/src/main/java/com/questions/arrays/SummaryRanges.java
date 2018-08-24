package com.questions.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 *
 * Example 1:
 * Input:  [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
 *
 *  Example 2:
 * Input:  [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
 *
 *  https://leetcode.com/problems/summary-ranges/description/
 */
public class SummaryRanges {


  public static List<String> summaryRange(int[] numbers) {
    if (numbers == null) {
      return Collections.emptyList();
    }
    int begin = 0, end = 0;
    List<String> result = new ArrayList<>();

    while (end < numbers.length) {

      //look fwd
      if (end == numbers.length - 1 || numbers[end] + 1 != numbers[end + 1]) {
        if (begin == end) {
          result.add(String.valueOf(numbers[begin]));
        } else {
          result.add(numbers[begin] + "->" + numbers[end]);
        }
        begin = end + 1;
      }
      end++;
    }
    return result;
  }

  public static void main(String[] args) {
    int[] numbers = {0, 1, 2, 4, 5, 7};
    //int[] numbers = {0, 2, 3, 4, 6, 8, 9};
    System.out.println(summaryRange(numbers));
  }
}
