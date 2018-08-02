package com.questions.recurssion;

import java.util.HashSet;
import java.util.Set;

public class CombinationsOfAnArrayOfArrays {

  public static Set<String> combinationsOfArrays(char[][] arrayOfChars) {

    if (arrayOfChars == null || arrayOfChars.length == 0) {
      return null;
    }

    Set<String> stringSet = new HashSet<>();
    stringSet.add("");
    for (char[] chars : arrayOfChars) {
      Set<String> newStringSet = new HashSet<>();
      for (char c : chars) {
        for (String str : stringSet) {
          newStringSet.add(str + String.valueOf(c));
        }
      }
      stringSet = newStringSet;
    }

    return stringSet;
  }

  public static void main(String[] args) {
    char[][] arrayOfChars =
        {{'a', 'b'}, {'a', 'm', 'n', 'f'}, {'e', 'x', 'y', 'z'}, {'q', 'r', 's', 't'}};
    for (String str : combinationsOfArrays(arrayOfChars)) {
      System.out.println(str);
    }
  }
}
