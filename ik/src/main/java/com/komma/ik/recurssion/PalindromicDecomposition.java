package com.komma.ik.recurssion;

import java.util.List;

/**
 * Palindromic Decomposition Of A String
 *
 * Find all palindromic decompositions of a given string s.
 * A palindromic decomposition of string is a decomposition of the string into substrings, such that all those substrings are valid palindromes.
 *
 * Example
 * Input: "abracadabra"
 *
 * Output: [ "a|b|r|a|c|a|d|a|b|r|a", "a|b|r|a|c|ada|b|r|a", "a|b|r|aca|d|a|b|r|a" ]
 */
public class PalindromicDecomposition {

    private static void decompose(String str, int index, String partialSolution, List<String> result) {
        if(index == str.length()) {
            result.add(partialSolution);
        }



        // Palindrome exists


        // single palindrome


    }



}
