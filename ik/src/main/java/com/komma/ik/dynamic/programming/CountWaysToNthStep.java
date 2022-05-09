package com.komma.ik.dynamic.programming;

public class CountWaysToNthStep {

    static int countWaysToClimb(int[] steps, int n) {

        int[] memo = new int[n+1];
        for(int i = 0; i<=n; i++) {
            memo[i] = -1;
        }
        memo[0] = 1;
        for(int i = 1; i <=n ;i++ ) {
            int stepsToTake = i;
            int count = 0;
            for(int step: steps) {
                if(step > stepsToTake) {continue;}
                count += memo[i - step];
            }
            memo[stepsToTake] = count;
        }
        return memo[n];
    }

    public static void main(String[] args) {
        int[] steps = {1, 2};
        System.out.println(countWaysToClimb(steps, 5));
    }

}
