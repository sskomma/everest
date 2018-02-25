package com.questions.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    System.out.println(new String(t2));
    return new String(s2).equals(new String(t2));
  }

  /**
   * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
   * Strings consists of lowercase English letters only and the length of both strings s and p will
   * not be larger than 20,100.
   *
   * The order of output does not matter.
   * #leetcode438
   * https://leetcode.com/problems/find-all-anagrams-in-a-string/
   * @param s, input string
   * @param p, patten string find anagrams of.
   */
  //This code is timing out.
  public static List<Integer> findAnagrams(String s, String p) {
    List<Integer> anagramIndexes = new ArrayList<>();

    //Validations
    if (s == null || s.isEmpty() || p == null || p.length() == 0 || s.length() < p.length()) {
      return anagramIndexes;
    }
    Map<Character, Integer> characterCount = new HashMap<>();
    for (Character c : p.toCharArray()) {
      characterCount.put(c, characterCount.getOrDefault(c, 0) + 1);
    }
    int lastIndex = s.length() - p.length();
    for (int window = 0; window <= lastIndex; window++) {
      char[] subStr = s.substring(window, window + p.length()).toCharArray();
      Map<Character, Integer> charCount = new HashMap<>(characterCount);
      for (Character c : subStr) {
        Integer count = charCount.get(c);
        if (count == null) {
          break;
        } else if (count == 1) {
          charCount.remove(c);
        } else {
          charCount.put(c, --count);
        }
      }
      if (charCount.isEmpty()) {
        anagramIndexes.add(window);
      }
    }
    return anagramIndexes;
  }

  /**
   * This code is written with the below technique
   * https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/sliding-window-algorithm-template-to-solve-all-the-leetcode-substring-search-problem
   *
   * @param s
   * @param p
   * @return
   */
  public static List<Integer> findAnagramV2(String s, String p) {
    if (s == null || p == null || s.length() < p.length()) {
      return Collections.emptyList();
    }

    Map<Character, Integer> freqMap = new HashMap<>(p.length());
    for (Character c : p.toCharArray()) {
      freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
    }
    int count = freqMap.size();
    int begin = 0, end = 0;
    List<Integer> result = new ArrayList<>();
    while (end < s.length()) {
      Character c = s.charAt(end);

      if (freqMap.containsKey(c)) {
        freqMap.put(c, freqMap.get(c) - 1);
        if (freqMap.get(c) == 0) {
          count--;
        }
      }
      end++;

      while (count == 0) {
        Character temp = s.charAt(begin);
        if (freqMap.containsKey(temp)) {
          freqMap.put(temp, freqMap.get(temp) + 1);
          if (freqMap.get(temp) > 0) {
            count++;
          }
        }
        if (end - begin == p.length()) {
          result.add(begin);
        }
        begin++;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    String[] words = {"iceman", "cinema", "mary", "army", "cat", "baba", "nana", "abba", "nnaa"};
    /*findAnagramsInList(words);
    System.out.println("is anagram" + isAnagram("ab", "ba"));*/

    System.out.println(findAnagramV2("cbaebabacd", "abc"));
  }
}
