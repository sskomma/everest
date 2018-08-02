package com.questions.recurssion;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

  public static void main(String[] args) {
    System.out.println(combine(4, 3));
  }

  public static List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    if (k > n || k < 0) {
      return result;
    }
    if (k == 0) {
      result.add(new ArrayList<>());
      return result;
    }
    result = combine(n - 1, k - 1);
    for (List<Integer> list : result) {
      list.add(n);
    }
    result.addAll(combine(n - 1, k));
    return result;
  }
}
