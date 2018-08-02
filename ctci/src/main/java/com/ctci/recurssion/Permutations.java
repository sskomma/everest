
package com.ctci.recurssion;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

  public static List<String> permutations(String str) {
    if (str == null) {
      return null;
    }

    List<String> perms = new ArrayList<>();

    if (str.isEmpty()) {
      perms.add("");
      return perms;
    }

    char c = str.charAt(0);
    List<String> words = permutations(str.substring(1));
    for (String word : words) {
      for (int i = 0; i <= word.length(); i++) {
        String result = word.substring(0, i) + c + word.substring(i);
        perms.add(result);
      }
    }
    return perms;
  }

  public static void main(String[] args) {
    for (String str : permutations("abc")) {
      System.out.println(str);
    }
  }
}


