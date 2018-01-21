package com.questions.heaps;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortCharactersByFrequency {

  public static String frequencySort(String s) {

    if (s == null || s.length() == 0) {
      return null;
    }

    Map<Character, Integer> charCountMap = new HashMap<>();
    PriorityQueue<Map.Entry<Character, Integer>> maxHeap =
        new PriorityQueue<>(s.length(), (a, b) -> b.getValue().compareTo(a.getValue()));

    for (Character c : s.toCharArray()) {
      charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
    }
    maxHeap.addAll(charCountMap.entrySet());

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
