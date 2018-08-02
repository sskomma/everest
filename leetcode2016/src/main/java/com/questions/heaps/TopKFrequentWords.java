package com.questions.heaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentWords {
  public static List<String> topKFrequent(String[] words, int k) {

    Map<String, Integer> frequencyMap = new HashMap<>();

    for (String word : words) {
      frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
    }

    PriorityQueue<Map.Entry<String, Integer>> freqs =
        new PriorityQueue<>(k, (a, b) -> {
          int i = b.getValue().compareTo(a.getValue());
          return i == 0 ? b.getKey().compareTo(a.getKey()) : i;
        });

    freqs.addAll(frequencyMap.entrySet());

    List<String> strings = new ArrayList<>();
    for (int i = 0; i < k; i++) {
      strings.add(freqs.remove().getKey());
    }
    return strings;
  }

  public static void main(String[] args) {
    String[] strings = {"i", "love", "leetcode", "i", "love", "coding"};
    System.out.println(topKFrequent(strings, 2));
  }
}
