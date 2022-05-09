package com.questions.recurssion;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
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


    public List<List<Integer>> permuteV3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> slate = new LinkedList<>();
        List<Integer> numbers = Arrays.stream(nums).boxed().collect(toList());
        permute_helper(numbers, slate, result);
        return result;
    }

    private void permute_helper(List<Integer> usable_nums, Deque<Integer> slate, List<List<Integer>> result) {
        if(usable_nums.isEmpty()) {
            result.add(new ArrayList<>(slate));
            return;
        }

        for(int i = 0; i< usable_nums.size(); i++) {

            List<Integer> new_usable_nums = new ArrayList<>(usable_nums);
            new_usable_nums.remove(usable_nums.get(i));

            int num = usable_nums.get(i);
            slate.addLast(num);
            permute_helper(new_usable_nums, slate, result);
            slate.removeLast();
        }
    }


    public static void main(String[] args) {
        PermutationOfIntegers poi = new PermutationOfIntegers();
        int[] numbers = {1, 2, 3};
        System.out.println(poi.permuteV2(numbers));
    }
}
