package com.algorithms.dynamicprogramming;

public class BuySellStock {

  /**
   * Say you have an array for which the ith element is the price of a given stock on day i.
   *	If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
   * 	design an algorithm to find the maximum profit.
   * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
   *
   * (Notes:In short, difference between the least and highest numbers in an array,
   * but least number should occur before higest number,i.e buy before you sell it)
   *
   * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
   * @param prices
   */
  public static int maxProfitOneTransaction(int[] prices) {
    int profit = 0;
    if (prices == null || prices.length < 2) {
      return profit;
    }
    int minPrice = prices[0];

    for (int price : prices) {
      profit = Math.max(profit, price - minPrice);
      minPrice = Math.min(minPrice, price);
    }
    return profit;
  }

  /**Say you have an array for which the ith element is the price of a given stock on day i.
   * Design an algorithm to find the maximum profit. You may complete as many transactions as you like
   * (ie, buy one and sell one share of the stock multiple times).
   * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
   *
   * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
   * Algorithm:
   * Assume we bought a stock yesterday, what will we do today?
   * 		If today's price is higher than yesterday, we can sell it right now and get profit.
   * 		What about tomorrow ? If the price grows higher,We just buy today's stock,that's equal buying yesterday and selling tomorrow.
   * 		What if today's price is lower than yesterday's? We just pretend we buy today's stock but not yesterday's.
   *
   *In a word, what's we should do is compare today's price to yesterday's.If higher,we get profit,
   *set buy-in price to today's price.If lower, we don't get profit,and set buy-in price to today's price.
   */
  public static int maxProfitUnlimitedTransactions(int[] prices) {
    int aggregateProfit = 0;
    if (prices == null || prices.length < 2) {
      return aggregateProfit;
    }

    int buyInPrice = prices[0];
    for (int price : prices) {
      int tempProfit = price - buyInPrice;
      if (tempProfit > 0) {
        aggregateProfit += tempProfit;
      }
      buyInPrice = price;
    }
    return aggregateProfit;
  }

  public static void main(String[] args) {
    int[] prices = {4, 2};
    System.out.println(maxProfitOneTransaction(prices));
  }
}
