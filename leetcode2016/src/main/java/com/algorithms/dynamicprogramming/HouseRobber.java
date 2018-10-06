package com.algorithms.dynamicprogramming;

/**
 * Description: https://leetcode.com/problems/house-robber/ 
 *
 * You are a professional robber planning to rob houses along a street. 
 * Each house has a certain amount of money stashed, 
 * the only constraint stopping you from robbing each of them is that adjacent houses have security system connected 
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house, 
 * determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * @author Ram Komma
 */
public class HouseRobber {

  /**Algorithm Notes: At any house, to make a decision if should rob or not I need two value
   * one, what's the best value when previous house is picked.
   * second,  what's the best value when previous house is NOT picked.
   *
   * Hence construct two arrays, each of size of given input.
   * Array1(picked): This array contains the best value when previous house is picked.
   * Array2(notPicked):This array contains the best value when previous house is NOT picked.
   * Formula,
   * picked[i]: if I have to rob i'th house the best I can rob is, by picking value that is
   *  best from i-2'th house when i-1 is robbed or i-1'th house when i-1th house is not robbed.
   *
   * notPicked[i]: if I don't rob i'th house the best I can rob is the best of picked[i-1] and notPicked[i-1]
   * Example: {2,1,1,2}: Best grab is 1st house and 4th house 4.
   *
   * Iteration 1: robbing one house
   * picked[1st house] 	= 2, the best I can do when robbing 1st house is 2
   * noPicked[1st house]	= 0, the best I can do when not robbing 1st house is 0
   *
   * Iteration 2: robbing 2 houses
   * picked[2nd house] 	= 1, Since I have to rob 2nd, I cannot rob 1st hence 1.
   * noPicked[2nd house]	= 2, if I don't rob 2nd house, the best is best of picked[1] and notPicked[1]
   *
   * Iteration 3: robbing 3 houses
   * picked[3rd house] 	= 3, Since I have to rob 3rd, I cannot rob 2nd and hence I pick the best of (picked[1] and notPicked[2]) plus 3rd house
   * noPicked[3rd house]	= 2, if I don't rob 3rd house, the best is best of picked[2] and notPicked[2]
   *
   * Iteration 4: robbing 4 houses
   * picked[4th house] 	= 4, Since I have to rob 4th, I cannot rob 3rd and hence I pick the best of of (picked[2] and notPicked[3]) plus 4th house
   * noPicked[4th house]	= 3, if I don't rob 4th house, the best is best of picked[3] and notPicked[3]
   *
   * Answer: Best grab when we have i houses is max of picked[i] and notPicked[i]
   *
   * @param money
   * @return
   */
  public static int maxRobValue(int[] money) {
    if (money == null || money.length == 0) {
      return 0;
    }
    int numOfHouses = money.length;
    int[] picked = new int[numOfHouses];
    int[] notPicked = new int[numOfHouses];

    //Init
    picked[0] = money[0];
    notPicked[0] = 0;

    for (int i = 1; i < numOfHouses; i++) {
        if(i==1){
            picked[i] = notPicked[i - 1] + money[i];
        }else {
            picked[i] = Math.max(picked[i - 2], notPicked[i - 1]) + money[i];
        }

      notPicked[i] = Math.max(picked[i - 1], notPicked[i - 1]);
    }
    return Math.max(picked[numOfHouses-1], notPicked[numOfHouses-1]);
  }

  public static void main(String[] args) {
    int[] money = {2, 1, 1, 2};
    System.out.println(maxRobValue(money));
  }
}
