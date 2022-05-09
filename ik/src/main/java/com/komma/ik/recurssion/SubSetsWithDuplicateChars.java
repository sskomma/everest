package com.komma.ik.recurssion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SubSetsWithDuplicateChars {


    // Complete the function below.
    public static List<String> get_distinct_subsets(String str) {
        if(str == null)
            return Collections.emptyList();
        List<String> result = new ArrayList<>();
        helper(str, 0, new LinkedList<>(), result);
        return result;
    }

    private static void helper(String input, Integer idx_sub, Deque<Character> slate, List<String> result) {
        if(idx_sub == input.length()) {
            result.add(buildStr(slate));
            return;
        }

        // Count number of times input[idx] occurs consequtively.
        int frequency = 1;
        char current = input.charAt(idx_sub);
        for(int i = idx_sub+1; i < input.length() && input.charAt(i) == current; i++) {
            frequency++;
        }

        // recursive calls
        for(int j = 0; j <= frequency; j++) {
            if(j == 0) {
                helper(input, idx_sub+frequency, slate, result);
            } else {
                slate.addLast(current);
                helper(input, idx_sub+frequency, slate, result);
            }
        }
        for(int j = 0; j < frequency; j++) {
            slate.removeLast();
        }
    }

    private static String buildStr(Deque<Character> slate) {
        if(slate.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Character> it = slate.iterator();
        while(it.hasNext()) {
            sb.append(it.next());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        get_distinct_subsets("aabaa");
    }
}
