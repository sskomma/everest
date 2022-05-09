package com.komma.ik.recurssion;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Generate All Subsets Of A Set
 *
 * Generate ALL possible subsets of a given set. The set is given in the form of a string s containing distinct lowercase characters 'a' - 'z'.
 *
 * Example
 *
 * Input: "xy"
 *
 * Output: ["", "x", "y", "xy"]
 *
 * Notes
 *
 * Input Parameters: There is only one argument denoting string s.
 *
 * Output: Return array of strings res, containing ALL possible subsets of given string. You need not to worry about order of strings in your output array. E.g. s = "a", arrays ["", "a"] and ["a", ""]  both will be accepted.
 *
 * Order of characters in any subset must be same as in the input string. For s = "xy", array ["", "x", "y", "xy"] will be accepted, but ["", "x", "y", "yx"] will not be accepted.
 *
 * Constraints:
 *
 * 0 <= |s| <= 19
 * s only contains distinct lowercase alphabetical letters ('a' - 'z').
 * Empty set is a subset of any set.
 *
 * Any set is a subset of itself.
 */
public class GenerateAllSubSets {

    static String[] generate_all_subsets(String s) {
        List<String> result = new ArrayList<>();
        setBuilder(s, new LinkedList<>(), 0, result);
        return result.toArray(new String[0]);
    }

    private static void setBuilder(String input, Deque<Character> slate, int index, List<String> result) {
        if(index >= input.length()) {
            String s = fromDeque(slate);
            result.add(s);
            return;
        }

        // Inclusion
        slate.addLast(input.charAt(index));
        setBuilder(input, slate, index+1, result);
        slate.removeLast();

        // Exclusion.
        setBuilder(input, slate, index+1, result);

    }

    private static String fromDeque(Deque<Character> slate) {
        StringBuilder sb = new StringBuilder();
        Iterator<Character> it = slate.iterator();
        while(it.hasNext()) {
            sb.append(it.next());
        }
        return sb.toString();

    }

    public static void main(String[] args) {
        for(String s: generate_all_subsets("abc")) {
            System.out.println(s);
        }
    }

}
