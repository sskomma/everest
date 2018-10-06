package com.algorithms.dynamicprogramming;

public class PerfectSquares {
    public int numSquares(int n) {
        int[] squaresCount = new int[n+1];
        for(int i = 1; i<=n; i++) {
            int min = Integer.MAX_VALUE;
            int j = 1;
            while ( i - (j*j) >= 0) {
                min = Math.min(min, squaresCount[i- j*j] + 1);
                j++;
            }
            squaresCount[i] = min;
        }
        return squaresCount[n];
    }

    public static void main(String[] args) {
        PerfectSquares ps = new PerfectSquares();
        System.out.println(ps.numSquares(12));
    }
}
