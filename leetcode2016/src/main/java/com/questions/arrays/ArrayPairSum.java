package com.questions.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**Description: http://www.ardendertat.com/2011/09/17/programming-interview-questions-1-array-pair-sum/
 *
 *Given an integer array, output all pairs that sum up to a specific value k.
 *
 * @author Ram Komma
 *
 */

public class ArrayPairSum {
  public static void main(String[] args) {
    ArrayPairSum arrayPairs = new ArrayPairSum();
    int[] input = {1, 1, 2, 3, 4};

    List<Pair> list = arrayPairs.findPairsThatSumUpToK(input, 4);
    for (Pair p : list) {
      System.out.println(p);
    }
  }

  /**Description: https://leetcode.com/problems/two-sum/
   *
   * Better solution: refer to findPairsThatSumUpToK() method above.
   *
   * Given an array of integers, find two numbers such that they add up to a specific target number.
   * The function twoSum should return indices of the two numbers such that they add up to the target,
   * where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
   *
   * You may assume that each input would have exactly one solution.
   *
   * Input: numbers={2, 7, 11, 15}, target=9
   * Output: index1=1, index2=2
   *
   * @author Ram Komma
   *
   */
  public static int[] twoSum(int[] nums, int target) {

    Map differencesMap = new HashMap();
    for (int i = 0; i < nums.length; i++) {
      differencesMap.put(target - nums[i], i);
    }
    Integer index = null;
    int j = 0;
    for (; j < nums.length; j++) {
      index = (Integer) differencesMap.get(nums[j]);
      if (index != null && index != j) {
        break;
      }
    }

    int[] results = {j + 1, index + 1};
    return results;
  }

  public List<Pair> findPairsThatSumUpToK(int[] input, int sum) {
    Set<Integer> set = new TreeSet<Integer>();
    List<Pair> list = new ArrayList<Pair>();
    for (int element : input) {
      if (set.contains(sum - element)) {
        set.remove(element - sum);
        Pair p = new Pair(element, (sum - element));
        list.add(p);
      } else {
        set.add(element);
      }
    }
    return list;
  }

  public class Pair {
    int a, b;

    public Pair(int a, int b) {
      this.a = a;
      this.b = b;
    }

    @Override
    public String toString() {
      return "Pair [a=" + a + ", b=" + b + "]";
    }
  }

  /**
   * @param args

  public static void main(String[] args)
  {
  int[] nums = {2, 7, 11, 15};
  int target = 9;
  int[] result = twoSum(nums, target);
  System.out.println(result[0]);
  System.out.println(result[1]);

  }*/

}

