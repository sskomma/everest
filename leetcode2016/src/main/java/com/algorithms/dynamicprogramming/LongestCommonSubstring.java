package com.algorithms.dynamicprogramming;

/**Long common substring of two strings. 
 * https://www.youtube.com/watch?v=BysNXJHzCEs&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=14
 *
 * @author Ram Komma
 *
 */
public class LongestCommonSubstring {

  public static int longestSubString(String s1, String s2) {
    if (s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty()) {
      return 0;
    }
    int[][] matrix = new int[s1.length() + 1][s2.length() + 1];
    int longestSubstringlen = 0;
    int li = 0;

    for (int i = 1; i <= s1.length(); i++) {
      for (int j = 1; j <= s2.length(); j++) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          matrix[i][j] = matrix[i - 1][j - 1] + 1;
        } else {
          matrix[i][j] = 0;
        }
        if (matrix[i][j] > longestSubstringlen) {
          longestSubstringlen = matrix[i][j];
          li = i;
        }
      }
    }

    System.out.println(s1.substring(li - longestSubstringlen, li));

    return longestSubstringlen;
  }

  public static void main(String[] args) {
    System.out.println(longestSubString("abacdabcdfm", "abcdaxyz"));
  }
}
