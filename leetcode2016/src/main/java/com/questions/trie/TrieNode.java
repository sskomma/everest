package com.questions.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
  Map<Character, TrieNode> charMap;
  boolean isTerminating;

  public TrieNode() {
    charMap = new HashMap<>();
    this.isTerminating = false;
  }
}
