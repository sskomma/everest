package com.questions.trie;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 *
 * Example 1:
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 *
 * https://leetcode.com/problems/word-break/description/
 */
public class WordBreak {

  public static void main(String[] args) {
    List<String> dictionary = new ArrayList<>();
    dictionary.add("cats");
    dictionary.add("dog");
    dictionary.add("sand");
    dictionary.add("and");
    dictionary.add("cat");
    WordBreak wb = new WordBreak();
    TrieNode tn = new TrieNode();
    wb.wordBreak("catsandog", dictionary);
  }

  public boolean wordBreak(String word, List<String> dictionary) {
    TrieNode root = new TrieNode();
    for (String legalWord : dictionary) {
      addWord(root, legalWord);
    }
    Deque<Integer> stack = new LinkedList<>();
    stack.push(0);
    while (!stack.isEmpty()) {
      int position = stack.pop();

      for (int i = position + 1; i < word.length(); i++) {
        if (searchWord(root, word.substring(position, i))) {
          if (i == word.length() - 1) {
            return true;
          }
          stack.push(position);
        }
      }
    }
    return false;
  }

  public void addWord(TrieNode root, String word) {
    int l = word.length();
    TrieNode traverser = root;

    for (Character c : word.toCharArray()) {
      if (!traverser.charMap.containsKey(c)) {
        traverser.charMap.put(c, new TrieNode());
      }
      traverser = traverser.charMap.get(c);
      if (l == 1) {
        traverser.isTerminating = true;
      }
      l--;
    }
  }

  public boolean searchWord(TrieNode root, String word) {
    if (root == null || word == null) {
      return false;
    }
    TrieNode traverser = root;
    int l = word.length();
    for (Character character : word.toCharArray()) {
      traverser = traverser.charMap.get(character);
      if (traverser == null) {
        return false;
      }
      l--;
    }
    return traverser.isTerminating;
  }
}
