package com.questions.recurssion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PermutationOfIntegers {
    // Approach One
    public List<List<Integer>> permuteV1(int[] nums) {
        if(nums == null)
            return Collections.emptyList();
        return permuteV1(nums, 0);
    }

    private List<List<Integer>> permuteV1(int[] nums, int begin) {
        List<List<Integer>> permutations = new ArrayList<>();
        if(begin == nums.length-1) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[begin]);
            permutations.add(list);
            return permutations;
        }

        int pivot = nums[begin];
        List<List<Integer>> subPermutations = permuteV1(nums, begin+1);
        for(List<Integer> permuation: subPermutations ){
            for(int i = 0; i<= permuation.size(); i++) {
                permutations.add(insertAt(permuation,i,pivot));
            }
        }
        return permutations;
    }

    private List<Integer> insertAt(List<Integer> list, int position, int value) {
        List<Integer> newList = new ArrayList<>(list.size()+1);
        for(int i = 0, j =0 ; i < list.size()+1; i++  ) {
            if(i == position) {
                newList.add(value);
            } else {
                newList.add(list.get(j));
                j++;
            }
        }
        return newList;
    }

    public List<List<Integer>> permuteV2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        PermutationOfIntegers poi = new PermutationOfIntegers();
        int[] numbers = {1, 2, 3};
        System.out.println(poi.permuteV2(numbers));
    }
}
