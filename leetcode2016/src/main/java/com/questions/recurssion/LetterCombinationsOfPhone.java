package com.questions.recurssion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 */
public class LetterCombinationsOfPhone {

  private static Map<Character, char[]> dailpadMap;

  static {
    dailpadMap = new HashMap<>();
    char[] letters = {'a', 'b', 'c'};
    dailpadMap.put('2', letters);
    char[] letters3 = {'d', 'e', 'f'};
    dailpadMap.put('3', letters3);
    char[] letters4 = {'g', 'h', 'i'};
    dailpadMap.put('4', letters4);
    char[] letters5 = {'j', 'k', 'l'};
    dailpadMap.put('5', letters5);
    char[] letters6 = {'m', 'n', 'o'};
    dailpadMap.put('6', letters6);
    char[] letters7 = {'p', 'q', 'r', 's'};
    dailpadMap.put('7', letters7);
    char[] letters8 = {'t', 'u', 'v'};
    dailpadMap.put('8', letters8);
    char[] letters9 = {'w', 'x', 'y', 'z'};
    dailpadMap.put('9', letters9);
  }

  public List<String> letterCombinations(String digits) {
    if (digits == null || digits.isEmpty()) {
      return Collections.emptyList();
    }
    Set<String> result = combinations(digits);
    return new ArrayList<>(result);
  }

  public Set<String> combinations(String digits) {
    Set<String> result = new HashSet<>();
    if (digits == null || digits.isEmpty()) {
      result.add("");
      return result;
    }

    Set<String> combinations = combinations(digits.substring(1));
    char digit = digits.charAt(0);
    if (!dailpadMap.containsKey(digit)) {
      return result;
    }
    for (Character c : dailpadMap.get(digits.charAt(0))) {
      for (String str : combinations) {
        result.add(c + str);
      }
    }
    return result;
  }

  public static void main(String[] args) {
    LetterCombinationsOfPhone lcp = new LetterCombinationsOfPhone();
    System.out.println(lcp.letterCombinations("23"));
  }
}

