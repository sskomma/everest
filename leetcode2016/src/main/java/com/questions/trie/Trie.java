package com.questions.trie;

import java.util.ArrayList;
import java.util.List;

/**T
 * rie Datastructure implememnted in Java.
 * Here is the explanation of how its done.
 * https://www.youtube.com/watch?v=AXjmTQ8LEoI
 *
 * @author Komma
 *
 */
public class Trie {
  private TrieNode root;

  public static void main(String[] args) {
    Trie t = new Trie();
    t.insert("sivaram");
    t.insert("siva");
    t.insert("sivaraman");
    for (String s : t.getPrefixWords("sivaraman")) {
      System.out.println(s);
    }
  }

  /**
   * Insert a word into trie.
   * @param word word to be inserted.
   */
  public void insert(String word) {
    if (root == null) {
      root = new TrieNode();
    }
    TrieNode navigator = root;
    int strLen = word.length();
    for (Character c : word.toCharArray()) {
      if (navigator.charMap.containsKey(c)) {
        navigator = navigator.charMap.get(c);
      } else {
        TrieNode tn = new TrieNode();
        navigator.charMap.put(c, tn);
        navigator = tn;
      }
      if (strLen == 1) {
        navigator.isTerminating = true;
      }
      strLen--;
    }
  }

  public List<String> insertAndGetPrefixes(String word) {
    if (root == null) {
      root = new TrieNode();
    }
    TrieNode navigator = root;
    StringBuffer prefixString = new StringBuffer();
    List<String> prefixes = new ArrayList<String>();
    int strLen = word.length();
    for (Character c : word.toCharArray()) {
      prefixString.append(c);
      if (navigator.charMap.containsKey(c)) {
        navigator = navigator.charMap.get(c);
        if (navigator.isTerminating) {
          prefixes.add(prefixString.toString());
        }
      } else {
        TrieNode tn = new TrieNode();
        navigator.charMap.put(c, tn);
        navigator = tn;
      }
      if (strLen == 1) {
        navigator.isTerminating = true;
      }
      strLen--;
    }
    return prefixes;
  }

  public boolean searchWord(String word) {
    if (word == null || word.length() == 0) {
      return false;
    }
    TrieNode navigator = root;
    for (Character c : word.toCharArray()) {
      if (navigator.charMap.containsKey(c)) {
        navigator = navigator.charMap.get(c);
      } else {
        return false;
      }
    }
    return navigator.isTerminating;
  }

  public List<String> getPrefixWords(String word) {
    if (root == null) {
      return null;
    }
    TrieNode navigator = root;
    StringBuffer prefixString = new StringBuffer();
    List<String> prefixes = null;
    int i = 0;
    for (; i < word.length() - 1; i++) {
      Character c = word.charAt(i);
      prefixString.append(c);

      if (navigator.charMap.containsKey(c)) {
        prefixes = new ArrayList<String>();
        navigator = navigator.charMap.get(c);
        if (navigator.isTerminating) {
          prefixes.add(prefixString.toString());
        }
      } else {
        break;
      }
    }
    return prefixes;
  }
}
