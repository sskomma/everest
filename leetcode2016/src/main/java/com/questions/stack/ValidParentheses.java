package com.questions.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 *
 * @author Ram Komma
 */
public class ValidParentheses {

  public static boolean validBrackets(String s) {
    if (s == null || s.isEmpty()) {
      return false;
    }
    Map<Character, Integer> valueMap = new HashMap<>();
    valueMap.put('(', 1);
    valueMap.put(')', -1);
    valueMap.put('[', 2);
    valueMap.put(']', -2);
    valueMap.put('{', 3);
    valueMap.put('}', -3);
    Deque<Integer> stack = new ArrayDeque<>();

    for (Character c : s.toCharArray()) {
      int charValue = valueMap.get(c);
      if (charValue < 0) {
        if (stack.peek() == null) {
          return false;
        } else if ((stack.peek() + charValue) != 0) {
          return false;
        }
        stack.pop();
      } else {
        stack.push(charValue);
      }
    }
    return stack.isEmpty();
  }

  /**
   * Given a string containing just the characters '(' and ')',
   * find the length of the longest valid (well-formed) parentheses substring.
   *
   * Example 1:
   * Input: "(()"
   * Output: 2
   * Explanation: The longest valid parentheses substring is "()"
   *
   * Example 2:
   * Input: ")()())"
   * Output: 4
   * Explanation: The longest valid parentheses substring is "()()"
   *
   * https://leetcode.com/problems/longest-valid-parentheses/solution/
   *
   * @param input string with parentheses.
   * @return length of valid substring.
   */
  public static int lengthOfValidParentheses(String input) {

    if (input == null || input.isEmpty()) {
      return 0;
    }
    int maxLength = 0;

    Deque<Integer> stack = new LinkedList<>();
    stack.push(-1);
    char[] c = input.toCharArray();
    for (int index = 0; index < c.length; index++) {
      if (c[index] == '(') {
        stack.push(index);
      } else if (c[index] == ')') {

        if (!stack.isEmpty()) {
          stack.pop();
        }

        if (!stack.isEmpty()) {
          maxLength = Math.max(index - stack.peek(), maxLength);
        }
      }
    }

    return maxLength;
  }

  public static void main(String[] args) {
    System.out.println(lengthOfValidParentheses(")()())"));
  }
}
