package com.komma.ik.dynamic.programming;

import java.util.Arrays;
import java.util.List;

public class MinNumOfCoins {
    public static int minimum_coins(List<Integer> coins, int value) {
        // Write your code here
        int[] memo = new int[value+1];
        memo[0] = 0;

        for(int bal = 1; bal <= value; bal++) {
            int numOfCoins = -1;
            for(Integer coin: coins) {
                if(coin > bal) continue;
                numOfCoins = (numOfCoins == -1 || numOfCoins > memo[bal-coin] ) ? memo[bal-coin]: numOfCoins;
            }
            memo[bal] = (numOfCoins != -1) ? (numOfCoins + 1) : -1;
        }
        return memo[value];
    }

    public static void main(String[] args) {
        System.out.println(minimum_coins(Arrays.asList(1, 3, 5), 9));
    }

}
