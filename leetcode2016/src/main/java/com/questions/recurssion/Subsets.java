package com.questions.recurssion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;


public class Subsets {

  public static void main(String[] args) {
    int[] nums = {1, 2, 3};
    System.out.println(subSetsV2(nums));
    int[] nums2 = {1, 2, 2};
    System.out.println(subSetsWithDups(nums2));
  }

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
  public static List<List<Integer>> subSetsV1(int[] nums) {
    if (nums == null || nums.length == 0) {
      return Collections.emptyList();
    }
    return subSetsV1(nums, 0);
  }

  private static List<List<Integer>> subSetsV1(int[] nums, int index) {
    if (nums.length == index) {
      List<List<Integer>> emptyList = new ArrayList<>();
      emptyList.add(new ArrayList<>());
      return emptyList;
    }
    List<List<Integer>> subSetsWithOutCurrentElement = subSetsV1(nums, index + 1);
    List<List<Integer>> subSets = new ArrayList<>(subSetsWithOutCurrentElement);
    for (List<Integer> set : subSetsWithOutCurrentElement) {
      List<Integer> temp = new ArrayList<>(set);
      temp.add(nums[index]);
      subSets.add(temp);
    }
    return subSets;
  }

  /**
   * Version 2 of above problem is inspired from:
   * https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)/181677
   */
  public static List<List<Integer>> subSetsV2(int[] nums) {
    if(nums == null)
      return Collections.emptyList();
    List<List<Integer>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), nums, 0);
    return result;
  }

  private static void backtrack(List<List<Integer>> lists, List<Integer> trackerList, int[] nums, int start) {
    lists.add(new ArrayList<>(trackerList));
    for(int i = start; i < nums.length; i++) {
      trackerList.add(nums[i]);
      backtrack(lists, trackerList,nums, i+1);
      trackerList.remove(trackerList.size()-1);
    }
  }

  /**
   * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
   * Note: The solution set must not contain duplicate subsets.
   *
   * Example:
   * Input: [1,2,2]
   * Output:
   * [
   *   [2],
   *   [1],
   *   [1,2,2],
   *   [2,2],
   *   [1,2],
   *   []
   * ]
   *
   *  https://leetcode.com/problems/subsets-ii/description/
   */
  public static List<List<Integer>> subSetsWithDups(int[] nums) {
    if(nums == null)
      return Collections.emptyList();
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<>();
    backtrackWithDups(result, new ArrayList<>(), nums, 0);
    return result;
  }
  private static void backtrackWithDups(List<List<Integer>> lists, List<Integer> trackerList, int[] nums, int start) {
    lists.add(new ArrayList<>(trackerList));
    for(int i = start; i < nums.length; i++) {
      if(i > start && nums[i] == nums[i-1]) continue;
      trackerList.add(nums[i]);
      backtrackWithDups(lists, trackerList,nums, i+1);
      trackerList.remove(trackerList.size()-1);
    }
  }

}
