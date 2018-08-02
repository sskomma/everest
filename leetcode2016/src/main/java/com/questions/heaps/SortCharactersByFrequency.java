package com.questions.heaps;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a string, sort it in decreasing order based on the frequency of characters.
 *
 * https://leetcode.com/problems/sort-characters-by-frequency/description/
 * #leetcode451
 */
public class SortCharactersByFrequency {

  public static String frequencySort(String s) {

    if (s == null || s.length() == 0) {
      return null;
    }

    Map<Character, Integer> frequencyMap = new HashMap<>();
    for (Character c : s.toCharArray()) {
      frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
    }

    PriorityQueue<Map.Entry<Character, Integer>> maxHeap =
        new PriorityQueue<>(frequencyMap.size(), (a, b) -> Integer.compare(b.getValue(), a.getValue()));
    maxHeap.addAll(frequencyMap.entrySet());

    StringBuffer sb = new StringBuffer();
    while (!maxHeap.isEmpty()) {
      Map.Entry e = maxHeap.remove();
      for (int i = 0; i < (Integer) e.getValue(); i++) {
        sb.append(e.getKey());
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(frequencySort("Aabb"));
  }
}
