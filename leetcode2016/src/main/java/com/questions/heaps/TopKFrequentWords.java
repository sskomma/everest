package com.questions.heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;

/**
 * Given a non-empty list of words, return the k most frequent elements.
 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency,
 * then the word with the lower alphabetical order comes first.
 *
 * Example 1:
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 *     Note that "i" comes before "love" due to a lower alphabetical order.
 *
 * Example 2:
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 *     with the number of occurrence being 4, 3, 2 and 1 respectively.
 *
 *  Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Input words contain only lowercase letters.
 *
 *
 * https://leetcode.com/problems/top-k-frequent-words/description/
 */
public class TopKFrequentWords {
  public static List<String> topKFrequent(String[] words, int k) {

    Map<String, Integer> frequencyMap = new HashMap<>();
    Arrays.stream(words).forEach(word -> frequencyMap.put(word, frequencyMap.getOrDefault(word,0) +1));

    Function<Map.Entry<String, Integer>, Integer> valueExt = Map.Entry::getValue;
    Function<Map.Entry<String, Integer>, String> keyExt = Map.Entry::getKey;
    PriorityQueue<Map.Entry<String, Integer>> freqs = new PriorityQueue<>(k,
      Comparator.comparing(valueExt, Comparator.reverseOrder()).thenComparing(keyExt));
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
