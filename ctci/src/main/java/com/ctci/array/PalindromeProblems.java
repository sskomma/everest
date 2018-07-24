package com.ctci.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class PalindromeProblems {

  /**
   * Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome.
   * A palindrome is a word or phrase that is the same forwards and backwards. A permutation
   * is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.
   *
   * EXAMPLE
   * Input: Tact Coa
   * Output: True (permutations: "taco cat", "atco eta", etc.)
   * https://leetcode.com/problems/palindrome-permutation/description/
   * @param str
   * @return
   */
  public static boolean isPermutationPalindrome(String str) {
    if (StringUtils.isEmpty(str)) {
      throw new IllegalArgumentException();
    }
    Map<Character, Integer> checker = new HashMap<>();
    for (Character c : str.toCharArray()) {
      if (c != ' ') {
        if (checker.containsKey(c)) {
          int val = checker.get(c);
          val++;
          if (val % 2 == 0) {
            checker.remove(c);
          }
        } else {
          checker.put(c, 1);
        }
      }
    }
    return (checker.size() == 0 || checker.size() == 1);
  }

  /**
   * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
   *
   * This is case sensitive, for example "Aa" is not considered a palindrome here.
   *
   * Note:
   * Assume the length of given string will not exceed 1,010.
   * https://leetcode.com/problems/longest-palindrome/description/
   *
   * @return
   */
  public static int longestPalindrome(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }

    Map<Character, Integer> freqMap = new HashMap<>();
    for (Character c : s.toCharArray()) {
      freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
    }

    int usableChars = 0, unUsableChars = 0;
    for (Character c : freqMap.keySet()) {
      int frequency = freqMap.get(c);
      usableChars += (frequency / 2) * 2;
      unUsableChars += frequency % 2;
    }
    return unUsableChars > 0 ? ++usableChars : usableChars;
  }

  /**
   * Given a string s, return all the palindromic permutations (without duplicates) of it.
   * Return an empty list if no palindromic permutation could be form.
   *
   * https://leetcode.com/problems/palindrome-permutation-ii/description/
   * @param s
   * @return
   */
  public static List<String> generatePalindromes(String s) {
    if (s == null || s.length() == 0) {
      return Collections.emptyList();
    }
    if(s.length() == 1) {
      return Collections.singletonList(s);
    }
    Map<Character, Integer> freqMap = new HashMap<>();
    for (Character c : s.toCharArray()) {
      freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
    }
    char[] permutationChars = new char[s.length() / 2];
    char[] extraChars = new char[s.length()];

    int index = 0, index2 = 0;
    for (Character c : freqMap.keySet()) {
      int frequency = freqMap.get(c);
      for (int i = 0; i < frequency / 2; i++) {
        permutationChars[index++] = c;
      }
      if (frequency % 2 > 0) {
        extraChars[index2++] = c;
      }
    }

    if (index == 0) {
      return Collections.emptyList();
    }

    Set<String> permutations = new HashSet<>();
    for (String str : permutations(permutationChars, 0, index)) {
      if(index2 > 0) {
        for (int i = 0; i< index2; i++) {
          permutations.add(str + extraChars[i] + new StringBuffer(str).reverse());
        }
      } else {
        permutations.add(str + new StringBuffer(str).reverse());
      }
    }
    return new ArrayList<>(permutations);
  }

  private static List<String> permutations(char[] chars, int beginIndex, int endIndex) {
    List<String> permutations = new ArrayList<>();
    if (beginIndex >= endIndex) {
      permutations.add("");
      return permutations;
    }

    char c = chars[beginIndex];
    List<String> words = permutations(chars, beginIndex + 1, endIndex);
    for (String word : words) {
      for (int i = 0; i <= word.length(); i++) {
        permutations.add(word.substring(0, i) + c + word.substring(i, word.length()));
      }
    }
    return permutations;
  }

  public static void main(String[] args) {
    char[] test = {'a', 'a', 'b'};
    System.out.println(generatePalindromes("aabb"));
  }
}
