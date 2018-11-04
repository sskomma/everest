package com.algorithms.dynamicprogramming;

import java.util.Arrays;

/**A program to print longest common subsequence of two given strings.
 *
 * Thanks to @TusharRoy. https://www.youtube.com/watch?v=NnD96abizww&index=2&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr
 *
 *
 * @author Ram Komma
 *
 */
public class LongestCommonSubSequence {

  public static int getLCSSLength(char[] s1, char[] s2) {
    if (s1 == null || s1.length == 0 || s2 == null || s2.length == 0) {
      return 0;
    }
    int[][] matrix = new int[s1.length + 1][s2.length + 1];
    for (int i = 1; i <= s1.length; i++) {
      for (int j = 1; j <= s2.length; j++) {
        if (s1[i - 1] == s2[j - 1]) {
          matrix[i][j] = matrix[i - 1][j - 1] + 1;
        } else {
          matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
        }
      }
    }
    //Print the longest common subsequence
    int i = s1.length;
    int j = s2.length;
    StringBuffer sb = new StringBuffer();
    while (i > 0 && j > 0) {
      if (matrix[i][j] == matrix[i - 1][j]) {
        i--;
      } else if (matrix[i][j] == matrix[i][j - 1]) {
        j--;
      } else {
        sb.insert(0, s1[i - 1]);
        i--;
        j--;
      }
    }
    System.out.println(sb.toString());

    return matrix[s1.length][s2.length];
  }

  public int lengthOfLIS(int[] nums) {
    int[] dp = new int[nums.length];
    int len = 0;
    for (int num : nums) {
      int i = Arrays.binarySearch(dp, 0, len, num);
      if (i < 0) {
        i = -(i + 1);
      }
      dp[i] = num;
      if (i == len) {
        len++;
      }
    }
    return len;
  }

  public static void main(String[] args) {
    String s1 = "abcdaf";
    String s2 = "acbcf";
    System.out.println("Longest sub sequence:" + getLCSSLength(s1.toCharArray(), s2.toCharArray()));
  }
}
