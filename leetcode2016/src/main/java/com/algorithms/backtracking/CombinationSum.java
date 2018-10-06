package com.algorithms.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 *      All numbers (including target) will be positive integers.
 *      The solution set must not contain duplicate combinations.
 *
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 * https://leetcode.com/problems/combination-sum/description/
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if(candidates == null || candidates.length == 0){
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backTrack(result, new ArrayList<>(), target,0, candidates);
        return result;
    }

    private void backTrack(List<List<Integer>> combinations, List<Integer> temp, int remain, int start,
        int[] candidates) {
        if(remain < 0) return;
        else if(remain == 0) combinations.add(new ArrayList<>(temp));
        else{
            for(int i = start; i < candidates.length; i++) {
                temp.add(candidates[i]);
                backTrack(combinations, temp, remain-candidates[i], i, candidates);
                temp.remove(temp.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        CombinationSum cs = new CombinationSum();
        int[] numbers = {2,3, 5, 6,7};
        System.out.println(cs.combinationSum(numbers, 8));
    }
}
