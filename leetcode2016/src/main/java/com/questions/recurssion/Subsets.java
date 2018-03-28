package com.questions.recurssion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * For example,
 * If nums = [1,2,3], a solution is:
 *
 * https://leetcode.com/problems/subsets/description/
 **/
public class Subsets {

  public static void main(String[] args) {
    int[] nums = {1, 2, 3};
    System.out.println(subSets(nums));
  }

  public static List<List<Integer>> subSets(int[] nums) {
    if (nums == null || nums.length == 0) {
      return Collections.emptyList();
    }
    return subSets(nums, 0);
  }

  private static List<List<Integer>> subSets(int[] nums, int index) {
    if (nums.length == index) {
      List<List<Integer>> emptyList = new ArrayList<>();
      emptyList.add(new ArrayList<>());
      return emptyList;
    }
    List<List<Integer>> subSetsWithOutCurrentElement = subSets(nums, index + 1);
    List<List<Integer>> subSets = new ArrayList<>(subSetsWithOutCurrentElement);
    for (List<Integer> set : subSetsWithOutCurrentElement) {
      List<Integer> temp = new ArrayList<>(set);
      temp.add(nums[index]);
      subSets.add(temp);
    }
    return subSets;
  }
}
