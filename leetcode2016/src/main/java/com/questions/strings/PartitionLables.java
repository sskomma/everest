package com.questions.strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A string S of lowercase letters is given. We want to partition this string into as many parts as possible
 * so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 * Example 1:
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 * Note:
 *
 * S will have length in range [1, 500].
 * S will consist of lowercase letters ('a' to 'z') only.
 *
 * https://leetcode.com/problems/partition-labels/description/
 * #leetcode763
 */
public class PartitionLables {
  public static List<Integer> partitionLabels(String S) {
    if (S == null || S.isEmpty()) {
      return Collections.EMPTY_LIST;
    }
    int[] lastOccurance = new int[26];
    List<Integer> result = new ArrayList<>();
    char[] chars = S.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      lastOccurance[chars[i] - 'a'] = i;
    }
    int endOfStr = 0;
    int prevStrLen = -1;
    for (int i = 0; i < chars.length; i++) {
      endOfStr = Math.max(endOfStr, lastOccurance[chars[i] - 'a']);
      if (endOfStr == i) {
        endOfStr = 0;
        result.add(i - prevStrLen);
        prevStrLen = i;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    List<Integer> res = PartitionLables.partitionLabels("ababcbacadefegdehijhklij");
    System.out.println(res);
  }
}
