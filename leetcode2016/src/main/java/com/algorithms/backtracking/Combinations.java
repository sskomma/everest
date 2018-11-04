package com.algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * Example:
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * https://leetcode.com/problems/combinations/description/
 *
 * // Choose
 * // Explore
 * // un-choose
 * https://www.youtube.com/watch?v=78t_yHuGg-0
 */
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backTrack(result, new ArrayList<>(), 1, n, k);
        return result;
    }
    private void backTrack( List<List<Integer>> combinations, List<Integer> combination, int start, int n, int k) {
        if(combination.size() == k) {
            combinations.add(new ArrayList<>(combination));
        } else {
            for(int i = start; i <= n ; i++ ) {
                combination.add(i);
                backTrack(combinations, combination, i+1, n , k);
                combination.remove(combination.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        Combinations c = new Combinations();
        System.out.println(c.combine(4,2));
    }
}
