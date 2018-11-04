package com.algorithms.backtracking;

import java.util.ArrayList;

/**
 * Give a dollar value, and list of denominations find the least number of denominations,
 * to make up the dollar value.
 *
 * https://leetcode.com/problems/coin-change/
 * @author Ram Komma
 */
public class CoinChangeProblem {

  public static void main(String[] args) {
    CoinChangeProblem c = new CoinChangeProblem();
    int[] denominations = {186, 419, 83, 408};
    int total = 6249;
    System.out.println("Least number of coins to make total of " + total + " are :" + c
        .countLeastNumOfDenominations(total, denominations));
  }

  /**
   * The method finds out the least number of coins to make up the dollar amount.
   *
   * @param total, the total dollar amount to make up with the denominations.
   * @param denomination, list of coins we have.. we have these in infinite supply
   * @return Map with key denomination and value, number of denominations.
   */
  public int findLeastDenominations(int total, int[] denomination) {
    if (total == 0 || denomination == null || denomination.length == 0) {
      return 0;
    }
    int[] numOfCoins = new int[total + 1];
    int[] coinUsed = new int[total + 1];
    for (int i = 0; i <= total; i++) {
      numOfCoins[i] = 60000; //Random Maximum number
      coinUsed[i] = -1;
    }
    numOfCoins[0] = 0;
    for (int i = 0; i < denomination.length; i++) {
      for (int temp_tot = 1; temp_tot <= total; temp_tot++) {
        if (temp_tot == denomination[i]) {
          numOfCoins[temp_tot] = 1;
          coinUsed[temp_tot] = i;
        } else if (denomination[i] < temp_tot) {
          numOfCoins[temp_tot] =
              Math.min(numOfCoins[temp_tot], 1 + numOfCoins[temp_tot - denomination[i]]);
          coinUsed[temp_tot] =
              numOfCoins[temp_tot] == (1 + numOfCoins[temp_tot - denomination[i]]) ? i
                  : coinUsed[temp_tot];
        }
      }
    }
    //Identify all coins used to make up the total
    ArrayList<Integer> coins = new ArrayList<>();
    int i = total;
    while (i > 0) {
      int coin = coinUsed[i];
      i = i - denomination[coin];
      coins.add(denomination[coin]);
    }
    System.out.println("Coins used to make total with least number");
    for (int coin : coins) {
      System.out.print(coin + " ");
    }
    System.out.println("");
    return numOfCoins[total];
  }

  /**
   * A recursive approach to identify number of coins required to render amount.
   * It uses backtracking and memoization to keep track of
   * @param amount
   * @param coins
   * @return
   */
  public int countLeastNumOfDenominations(int amount, int[] coins) {
    if (amount == 0 || coins == null || coins.length == 0) {
      return 0;
    }
    //Arrays.sort(coins);
    return countDenominations(amount, coins, new int[amount]);
  }

  private int countDenominations(int remaining, int[] coins, int[] buffer) {
    if (remaining < 0) {
      return -1;
    }
    if (remaining == 0) {
      return 0;
    }
    if (buffer[remaining - 1] != 0) {
      return buffer[remaining - 1];
    }
    int min = Integer.MAX_VALUE;
    for (int coin : coins) {
      int num = countDenominations(remaining - coin, coins, buffer);
      min = num >= 0 ? Math.min(min, num) + 1 : min;
    }
    buffer[remaining - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
    return buffer[remaining - 1];
  }
}
