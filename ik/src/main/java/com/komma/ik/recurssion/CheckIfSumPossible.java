package com.komma.ik.recurssion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckIfSumPossible {

    static boolean check_if_sum_possible(long[] arr, long k) {

        if(arr == null || arr.length == 0) {
            return false;
        }

        List<Long> input = new ArrayList<>();
        for(int i = 0 ; i < arr.length; i++) {
            input.add(arr[i]);
        }
        Collections.sort(input);

        return sumCheck(input, 0, k);
    }

    private static boolean sumCheck(List<Long> input, int index, long partialSum) {
        // Base Case II
        if(index < 0 || index>=input.size()) {
            return false;
        }

        //Base Case
        if(input.get(index) == partialSum) {
            return true;
        }

        return (sumCheck(input, index+1, partialSum-input.get(index)) // INclude
            ||
            sumCheck(input, index+1, partialSum)); // Exclude
    }

    public static void main(String[] args) {
        long[] input = {2, 4, 8};
        System.out.println(check_if_sum_possible(input, 6));
    }

}
