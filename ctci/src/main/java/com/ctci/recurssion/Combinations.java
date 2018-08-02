package com.ctci.recurssion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Combinations {
  public static Set<List<Character>> combinations(char[] chars) {
    return combine(chars, chars.length - 1);
  }

  public static Set<List<Character>> combine(char[] chars, int start) {
    if (start < 0) {
      Set<List<Character>> emptySet = new HashSet<>();
      emptySet.add(new ArrayList<>());
      return emptySet;
    }
    char current = chars[start];
    Set<List<Character>> combinations = combine(chars, start - 1);
    Set<List<Character>> iterator = new HashSet<>(combinations);
    for (List<Character> combination : iterator) {
      List<Character> newList = new ArrayList<>(combination);
      newList.add(current);
      combinations.add(newList);
    }

    return combinations;
  }

  public static void main(String[] args) {
    char[] chars = {'a','a','b', 'c'};
    System.out.println(combinations(chars));
  }
}
