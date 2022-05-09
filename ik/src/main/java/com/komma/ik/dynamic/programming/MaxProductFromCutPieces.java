package com.komma.ik.dynamic.programming;

public class MaxProductFromCutPieces {
    static long max_product_from_cut_pieces(int n) {
        /*
         * Write your code here.
         */
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 0;

        for(int i = 2; i<=n; i++ ) {
            int max = 0;
            for(int j = 1; j < i; i++) {
                 max = Math.max( max, Math.max(dp[i], i) * Math.max(dp[i-j], (i-j)));
            }
            dp[i] = max;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(max_product_from_cut_pieces(11));
    }

}
