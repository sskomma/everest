package com.questions.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AnagramProblems {

  /**
   * This method fails in case with words like "Nana", "papa".. because both end up having xor of 0.
   * */
  public static void findAnagram(String[] words) {
    Map<Integer, List<Integer>> xorMap = new Hashtable<Integer, List<Integer>>();

    for (int i = 0; i < words.length; i++) {
      int xor = 0;
      for (char c : words[i].toCharArray()) {
        xor = xor ^ c;
      }
      List<Integer> list;

      if (xorMap.get(xor) == null) {
        list = new ArrayList<Integer>();
      } else {
        list = xorMap.get(xor);
      }

      list.add(i);
      xorMap.put(xor, list);
    }
    for (int key : xorMap.keySet()) {
      if (((List<Integer>) xorMap.get(key)).size() > 1) {
        System.out.println("Anagrams words are");
        for (int i : xorMap.get(key)) {
          System.out.println(words[i]);
        }
      }
    }
  }

  /**This method finds out sets of all anagram strings in list.
   * Given an array of strings, group anagrams together.
   *
   * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
   * Return:
   *
   * [
   *   ["ate", "eat","tea"],
   *   ["nat","tan"],
   *   ["bat"]
   * ]
   * https://leetcode.com/problems/anagrams/
   *
   * @param words
   */
  public static List<List<String>> findAnagramsInList(String[] words) {
    Map<String, List<String>> anagramMap = new HashMap<String, List<String>>();
    for (String word : words) {
      char[] arrayHolder = word.toCharArray();
      Arrays.sort(arrayHolder);
      String sortedWord = String.valueOf(arrayHolder);

      List<String> anagramList;
      if (!anagramMap.containsKey(sortedWord)) {
        anagramList = new LinkedList<String>();
      } else {
        anagramList = anagramMap.get(sortedWord);
      }
      anagramList.add(word);
      anagramMap.put(sortedWord, anagramList);
    }

    for (String key : anagramMap.keySet()) {
      List<String> anagramList = anagramMap.get(key);
      if (anagramList.size() > 1) {
        System.out.println("Set of anagrams");
        for (String word : anagramList) {
          System.out.println(word);
        }
      }
    }
    return new ArrayList<List<String>>(anagramMap.values());
  }

  /**Given two strings, check if they are anagrams of each other.
   * https://leetcode.com/problems/valid-anagram/
   *
   * @param s, String one
   * @param t, String two
   * @return true if the two strings are anagrams, false otherwise.
   */
  public static boolean isAnagram(String s, String t) {
    if (s == null || t == null) {
      return false;
    }
    if (s.equals(t)) {
      return true;
    }
    char[] s2 = s.toCharArray();
    Arrays.sort(s2);
    char[] t2 = t.toCharArray();
    Arrays.sort(t2);
    System.out.println();
    System.out.println(t2.toString());
    return new String(s2).equals(new String(t2));
  }

  public static void main(String[] args) {
    String[] words = {"iceman", "cinema", "mary", "army", "cat", "baba", "nana", "abba", "nnaa"};
    findAnagramsInList(words);
    System.out.println("is anagram" + isAnagram("ab", "ba"));
  }
}
