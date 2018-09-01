package com.algorithms.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 *
 * https://leetcode.com/problems/zigzag-iterator/description/
 */
public class ZigzagIterator {

  private int next;
  private int size;
  private List<Iterator<Integer>> iterators;

  public ZigzagIterator(List<List<Integer>> lists) {
    iterators = lists.stream()
        .map(List::iterator)
        .collect(Collectors.toList());
    next = 0;
    size = lists.size();
  }

  public int next() {
    while(size > 0 && !iterators.get(next % size).hasNext()){
      iterators.remove(next % size);
      int newNext = next % size;
      size--;
      next = newNext;
    }
    return !(iterators.isEmpty()) && iterators.get(next % size).hasNext()?
        iterators.get(next++ %size).next(): Integer.MIN_VALUE;
  }

  public boolean hasNext() {
    return iterators.stream().anyMatch(Iterator::hasNext);
  }

  public static void main(String[] args) {
    Integer[] v1 = {1,2};
    Integer[] v2 = {3,4,5,6};
    List<List<Integer>> lists = new ArrayList<>();
    lists.add(Arrays.asList(v1));
    lists.add(Arrays.asList(v2));
    ZigzagIterator iterator = new ZigzagIterator(lists);
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }

}