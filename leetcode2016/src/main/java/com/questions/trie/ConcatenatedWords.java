package com.questions.trie;

import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

public class ConcatenatedWords {
/*  public static List<String> concatednatedWords(String[] words) {

    Trie trie = new Trie();
    Arrays.stream(words).forEach(trie::getWord);

    for (String word : words) {
      Deque<Pair<Integer, TrieNode>> stack = new LinkedList<>();
      stack.push(Pair.of(0, trie.root));
      while (!stack.isEmpty()) {
        Pair<Integer, TrieNode> x = stack.pop();
        TrieNode node = x.getRight();
        Integer position = x.getLeft();
        TrieNode nextTerminal = trie.getWord(node,word.substring(position) );
        stack.push(Pair.of());
      }
    }

    return Collections.emptyList();
  }

  public static void main(String[] args) {
    String[] words = {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat",
        "ratcatdogcat"};
    System.out.println(concatednatedWords(words));
  }

  static class TrieNode {
    Character c;
    boolean isTerminal;
    Map<Character, TrieNode> children;

    TrieNode(Character c) {
      this.c = c;
      children = new HashMap<>();
    }
  }

  static class Trie {
    TrieNode root;

    Trie() {
      root = new TrieNode('-');
    }

    TrieNode getWord(TrieNode node, String word) {
      if (word == null) {
        return null;
      }
      TrieNode current = node == null ? root : node;
      for (Character character : word.toCharArray()) {
        if (current.children.keySet().contains(character)) {
          current = current.children.get(character);
        } else {
          TrieNode node = new TrieNode(character);
          current.children.put(character, node);
          current = node;
        }
      }
      current.isTerminal = true;
      return current;
    }

    boolean contains(String word) {
      if (word == null) {
        return false;
      }
      TrieNode current = root;
      for (Character c : word.toCharArray()) {
        if (!current.children.keySet().contains(c)) {
          return false;
        }
        current = current.children.get(c);
      }
      return current.isTerminal;
    }
  }*/
}
