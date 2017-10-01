package com.questions.arrays;

/**Problem Description: https://leetcode.com/problems/jump-game/description/
 *
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 *
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false
 *
 * @author Ram Komma
 */
public class JumpGame {

  public static boolean canJump(int[] numbers) {
    int max = 0;
    for(int i = 0; i < numbers.length ; i++){
      if(i > max) { return false; }
      max = Math.max( numbers[i] + i, max);
    }
    return true;
  }

  public static void main(String[] args) {
    int[] numbers = {3,2,1,0,4};
    System.out.println(canJump(numbers));
  }

}
