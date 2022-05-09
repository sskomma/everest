package com.questions.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromePair {

    public List<List<Integer>> palindromePairs(String[] words) {

        Map<String, Integer> freqMap = new HashMap<>();
        int k = 0;
        for (String word : words) {
            freqMap.put(word, k++);
        }

        List<List<Integer>> result = new ArrayList<>();
        String current;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            current = "";
            // check if reverse of all prefixes of word[i] for a match.
            // If a word[y] matches reverse of prefix(word[i])..
            // Then prefix(word[i]) + rest(word[i]) + word[y] is a palindrome if rest(word[i]) is palindrome.
            // Example: {batt, tab}
            // word[i] = "batt", reverse of prefix "bat" matches a word "tab" so "batttab" is a palindrome.
            for (int j = 0; j < word.length(); j++) {
                current = word.charAt(j) + current;

                if (freqMap.containsKey(current)) {

                    // check if rest of the string is palindrome
                    if (!word.equals(current) && isPalindrome(word.substring(j))) {
                        List<Integer> pair = new ArrayList<>();
                        pair.add(i);
                        pair.add(freqMap.get(current));
                        result.add(pair);
                        break;
                    }
                }
            }

            current = "";
            // Check if reverse of suffixes of word[i] for a match.
            // If word[y] matches a suffix(word[i])..
            // then word[y] + restof(word[i]) + suffix(word[x]) form a palindrome.
            // example: { sssll, lls}  reverse of suffix of word "sssll" -> "lls" matches a string.
            // so y + x "llssssll" is palindrome.
            for (int j = word.length() - 1; j >= 0; j--) {
                current = current + word.charAt(j);

                if (freqMap.containsKey(current)) {
                    // check if rest of the string is palindrome
                    if (word.length() != current.length() && isPalindrome(word.substring(0, j))) {
                        List<Integer> pair = new ArrayList<>();
                        pair.add(freqMap.get(current));
                        pair.add(i);
                        result.add(pair);
                    }
                }
            }
        }
        return result;
    }

    private boolean isPalindrome(String str) {
        if(str.length() == 0) return true;
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePair pair = new PalindromePair();
        String[] words = {"a",""};
        List<List<Integer>> indices = pair.palindromePairs(words);
        for(List<Integer> index : indices) {
            System.out.printf("a: %d, b: %d\n", index.get(0), index.get(1));
        }

    }

}
