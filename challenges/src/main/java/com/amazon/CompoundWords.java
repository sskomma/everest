package com.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * You are given a potentially large list of words. Some of these are compounds, where all parts are also in the list.
 * Identify all combinations where one words can be composed of two or more words of the same list and print or return them.
 *
 * Sample:
 * Input: [rockstar, rock, star, rocks, tar, stars, rockstars, super, highway, high, way, superhighway]
 *
 * Output:
 * rockstar -> rock + star
 * rockstar -> rocks + tar
 * superhighway -> super + highway
 * superhighway -> super + high + way
 *
 * You can return the output as a list:
 * [[rock, star], [rocks, tar], [super, highway], [super, high, way],...]
 */
public class CompoundWords {

    public static List<List<String>> breakCompundWords(List<String> input) {
        Map<String,List<List<String>>> result = new HashMap<>();
        //Set<String> dict = new HashSet<>(input);
        for(String sample: input) {
            result.put(sample, wordBreak(sample, input));
        }
        return result.values().stream().flatMap(List::stream).collect(Collectors.toList());

    }

    public static List<List<String>> wordBreak(String s, List<String> wordDict) {
        Deque<String> slate = new LinkedList<>();
        List<List<String>> result = new ArrayList<>();
        helper(s, new HashSet<>(wordDict), 0, slate, result);
        return result;
    }

    private static void helper(String s, Set<String> dict, int index,
                        Deque<String> slate, List<List<String>> result) {

        if(index == s.length()) {
            result.add(new ArrayList<>(slate));
        }

        for(int i = index+1; i <= s.length(); i++) {
            String temp = s.substring(index, i);
            if(dict.contains(temp)) {
                slate.addLast(temp);
                helper(s, dict, i, slate, result);
                slate.removeLast();
            }
        }
    }

    public static void main(String[] args) {
       String[] input = {"rockstar", "rock", "star", "rocks", "tar", "stars", "rockstars", "super", "highway", "high", "way", "superhighway"};

        System.out.println(breakCompundWords(Arrays.asList(input)));
    }

}
