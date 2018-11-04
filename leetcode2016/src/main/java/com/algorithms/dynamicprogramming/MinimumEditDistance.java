package com.algorithms.dynamicprogramming;

/**
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 *
 * You have the following 3 operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 *
 * https://leetcode.com/problems/edit-distance/
 * Solution: https://www.youtube.com/watch?v=We3YDTzNXEk
 */
public class MinimumEditDistance {
  public int minDistance(String word1, String word2) {
    if((word1 == null || word1.length() == 0) && (word2 == null || word2.length() == 0)) {
      return 0;
    } else if(word1 == null || word1.length() == 0) {
      return word2.length();
    } else if(word2 == null || word2.length() == 0) {
      return word1.length();
    } else if(word1.equals(word2)) {
      return 0;
    }

    int first = word1.length();
    int second = word2.length();
    //Place word1 on Y-Axis, word2 on X-Axis.
    int[][] dp = new int[first+1][second+1];
    // When word1 is not null and word 2 is null
    for(int i = 0; i <= first; i++) {
      dp[i][0] = i;
    }
    // When word2 is not null and word1 is null
    for(int i = 0; i <= second; i++) {
      dp[0][i] = i;
    }
    for(int i = 1; i<= first; i++) {
      for(int j = 1; j <= second; j++) {
        if(word1.charAt(i -1) == word2.charAt(j-1) ) {
          dp[i][j] = dp[i-1][j-1];
        }else {
          dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
        }
      }
    }
    return dp[first][second];
  }
}
