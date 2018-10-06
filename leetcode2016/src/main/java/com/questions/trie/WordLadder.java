package com.questions.trie;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: 0
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 * https://leetcode.com/problems/word-ladder/description/
 */
public class WordLadder {

    /*
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null || wordList.isEmpty()) {
            return 0;
        }
        int count = 0;
        //TrieNode trie = initTrie(wordList);
        List<Set<Character>> possibleTransitions = charPossibilities(wordList, beginWord.length());
        Deque<Pair> stack = new LinkedList<>();
        stack.push(new Pair(beginWord, 0));
        while (!stack.isEmpty()) {

            Pair pair = stack.pop();
            int position = pair.position;
            String string = pair.str;
            for (Character c : possibleTransitions.get(pair.position)) {
                String temp =
                    string.substring(0, position) + c +
                        ((position + 1) < string.length() ? string.substring(position + 1) : "");
                if(temp.equals(endWord))
                    return count;
                if(wordList.contains(temp)){
                    count++;
                    stack.push(new Pair(temp,0));
                }
            }
        }
        return count;
    }

    private List<Set<Character>> charPossibilities(List<String> wordList, int size) {
        List<Set<Character>> possibleChars = new ArrayList<>(size);
        for(int i=0;i<size;i++)
            possibleChars.add(new HashSet<>());
        for (String word : wordList) {
            for (int i = 0; i < size; i++) {
                possibleChars.get(i).add(word.charAt(i));
            }
        }
        return possibleChars;
    }

    private TrieNode initTrie(List<String> wordList) {
        TrieNode root = new TrieNode('-', false);

        for (String str : wordList) {
            TrieNode traverser = root;
            for (int i = 0; i < str.length(); i++) {
                Character character = str.charAt(i);
                if (traverser.children.containsKey(character)) {
                    traverser = traverser.children.get(character);
                } else {
                    TrieNode node = new TrieNode(character);
                    traverser.children.put(character, node);
                    traverser = node;
                }
                if (i == (str.length() - 1)) {
                    traverser.isWord = true;
                }
            }
        }
        return root;
    }

    class Pair {
        String str;
        int position;

        Pair(String str, int position) {
            this.str = str;
            this.position = position;
        }
    }

    class TrieNode {
        Map<Character, TrieNode> children;
        Character letter;
        boolean isWord;

        TrieNode(Character letter) {
            this.letter = letter;
        }

        TrieNode(Character letter, boolean isWord) {
            this.letter = letter;
            this.isWord = isWord;
        }
    }
    */


    public int ladderLength(String beginWord, String endWord, List<String> dictionary) {
        if(!dictionary.contains(endWord) || beginWord.equals(endWord)) {
            return 0;
        }
        Set<String> visited = new HashSet<>();
        Set<String> beginSet = new HashSet<>(); beginSet.add(beginWord);
        Set<String> endSet = new HashSet<>(); endSet.add(endWord);

        int hops = 0;
        while(!beginSet.isEmpty() && !endSet.isEmpty()) {
            hops++;
            if(beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }
            visited.addAll(beginSet);
            Set<String> nextBegin = new HashSet<>();
            for(String currentWord: beginSet) {
                Set<String> nextPossibilities = nextPossibleDestinations(currentWord, dictionary, visited);
                for(String word: nextPossibilities) {
                    if(endSet.contains(word)) {
                        return ++hops;
                    }
                }
                nextBegin.addAll(nextPossibilities);
            }
            beginSet = nextBegin;
        }
        return 0;
    }

    private Set<String> nextPossibleDestinations(String word, List<String> dictionary, Set<String> visited) {
        assert(word!=null && word.length()>0);
        assert(dictionary != null && !dictionary.isEmpty());

        int length = word.length();
        char[] currWord = word.toCharArray();
        char oldChar;
        Set<String> destinations = new HashSet<>();
        for(int i = 0; i< length; i++) {
            oldChar = currWord[i];
            for(char j = 'a'; j < 'z'; j++) {
                currWord[i] = j;
                String possibleNextString = String.valueOf(currWord);
                if(!visited.contains(possibleNextString) && dictionary.contains(possibleNextString)) {
                    destinations.add(possibleNextString);
                }
            }
            currWord[i] = oldChar;
        }
        return destinations;
    }
    public static void main(String[] args) {
        String[] strings = {"hot","dot","dog","lot","log","cog"};
        String[] strings2 = {"a","b","c"};

        WordLadder wl = new WordLadder();
        /*System.out.println(wl.nextPossibleDestinations("hot", Arrays.asList(strings),
            Sets.newHashSet("dot")));*/
        System.out.println(wl.ladderLength("hit","cog", Arrays.asList(strings)));

    }
}
