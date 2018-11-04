package com.algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 *Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * For example, given n = 3, a solution set is:
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * https://leetcode.com/problems/generate-parentheses/description/
 */
public class GenerateParanthesis {
  public List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<>();
    generateParenthesis(result, "", 0, 0, n);
    return result;
  }

  private void generateParenthesis(
    List<String> result, String buffer, int open, int close, int max) {
    if (buffer.length() == max * 2) {
      result.add(buffer);
    } else {
      if (open < max) {
        generateParenthesis(result, buffer + "(", open + 1, close, max);
      }
      if (open > close) {
        generateParenthesis(result, buffer + ")", open, close + 1, max);
      }
    }
  }
}
