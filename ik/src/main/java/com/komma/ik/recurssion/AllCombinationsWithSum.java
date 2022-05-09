package com.komma.ik.recurssion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class AllCombinationsWithSum {

    // Complete the function below.
    public static List<List<Integer>> generate_all_combinations(List<Integer> arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Collections.sort(arr);
        helper(arr, target, 0, new LinkedList<>(), result);
        return result;
    }


    private static boolean helper(List<Integer> arr, int target, int idx_sub, Deque<Integer> slate, List<List<Integer>> result) {
        if(target == 0) {
            result.add(new ArrayList<>(slate));
            return true;
        }
        if(target < 0 || idx_sub >= arr.size() ||  arr.get(idx_sub) > target) {
            return false;
        }

        int freq = 1;
        for(int i = idx_sub+1; i < arr.size() && arr.get(i).equals(arr.get(idx_sub)); i++) {
            freq++;
        }
        // When 'i'th element is not chosen
        helper(arr, target, idx_sub+freq, slate, result);

        // When 'i'th element is chosen
        int i = 1;
        for(; i<=freq; i++) {
            slate.addLast(arr.get(idx_sub));
            if(helper(arr, target - i* arr.get(idx_sub), idx_sub + freq, slate, result)) {
                slate.removeLast();
                break;
            }
        }

        for(int j = 1; j<i; j++) {
            slate.removeLast();
        }
        return false;
    }

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1,2,3,3,4,5);
        System.out.println(generate_all_combinations(nums, 6));
    }

}
