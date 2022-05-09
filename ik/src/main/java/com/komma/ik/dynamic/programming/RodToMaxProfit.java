package com.komma.ik.dynamic.programming;

import java.util.Arrays;
import java.util.List;

public class RodToMaxProfit {

    /*
     * Complete the function below.
     */
    static int get_maximum_profit_memoization(List<Integer> price) {
        int size = price.size();
        int[] prices = new int[size+1];
        int[] profits = new int[size+1];

        for(int i = 1; i<= price.size(); i++) {
            prices[i] = price.get(i-1);
            profits[i] = -1;
        }
        // base cases
        profits[0] = 0;
        profits[1] = prices[1];

        max_profit(size, prices, profits);
        return profits[size];
    }

    private static int max_profit(int size, int[] prices, int[] profits) {
        if(profits[size] != -1) {
            return profits[size];
        }

        int max_profit = 0;
        int var = 0;
        for( int i = size; i> 0; i--) {
            int profit = prices[i] + max_profit(var++, prices, profits);
            max_profit = Math.max(profit, max_profit);
        }

        profits[size] = max_profit;
        return profits[size];
    }

    public static void main(String[] args) {
        System.out.println(get_maximum_profit_memoization(Arrays.asList(1, 5, 8, 9)));
    }

    static int get_maximum_profit_tabulation(List<Integer> price) {
        int size = price.size();
        int[] prices = new int[size+1];
        int[] profits = new int[size+1];

        for(int i = 1; i<= price.size(); i++) {
            prices[i] = price.get(i-1);
            profits[i] = -1;
        }
        // base cases
        profits[0] = 0;
        profits[1] = prices[1];

        for(int i = 2; i <=size; i++) {
            int max_price = -1;
            for(int j = i; j > 0; j--) {
                int temp = prices[j] + profits[i-j];
                max_price = Math.max(max_price, temp);
            }
            profits[i] = max_price;
        }
        return profits[size];
    }


}
