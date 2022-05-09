package com.komma.ik.dynamic.programming;

import java.util.Arrays;

public class Robbery {

    static int maxStolenValue(int[] values) {

        int n = values.length;
        values = Arrays.copyOf(values, values.length+1);

        int[] robbed = new int[n+2];
        robbed[0] = 0;
        robbed[1] = values[0];

        for(int i = 2; i < n + 2; i++) {
            robbed[i]  = Math.max(  (robbed[i-2] + values[i-1] ), robbed[i-1]);
        }
        return robbed[n+1];
    }

    public static void main(String[] args) {
        int[] input = {6, 1, 2, 7};
        System.out.println(maxStolenValue(input));
    }

}
