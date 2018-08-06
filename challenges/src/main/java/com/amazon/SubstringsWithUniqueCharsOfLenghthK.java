package com.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * Find all substrings of length k, with unique characters.
 * #amazon
 */
public class SubstringsWithUniqueCharsOfLenghthK {

  public static List<String> subStringsWithUniqueCharsOfLenK(String inputStr, int num) {
    Set<String> result = new HashSet<>();
    if (inputStr == null) {
      return new ArrayList<>();
    }
    Map<Character, Integer> charToIndexMap = new HashMap<>();

    for (int i = 0; i < inputStr.length(); i++) {

      if (charToIndexMap.containsKey(inputStr.charAt(i))) {

        int oldIndex = charToIndexMap.get(inputStr.charAt(i));
        for (int j = i - num ; j <= oldIndex; j++) {
          if (j >= 0)
            charToIndexMap.remove(inputStr.charAt(j));
        }
      }
      charToIndexMap.put(inputStr.charAt(i), i);

      if (charToIndexMap.size() == num) {
        result.add(inputStr.substring(i - num + 1, i + 1));
        charToIndexMap.remove(inputStr.charAt(i - num + 1));
      }
    }

    return new ArrayList<>(result);
  }

  public static void main(String[] args) {
    List<String> strings = subStringsWithUniqueCharsOfLenK("awaglknagawunagwkwagl", 4);
    System.out.println(strings);
  }
}
