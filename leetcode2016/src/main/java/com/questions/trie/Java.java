package com.questions.trie;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public class Java {

  public static void main(String[] args) {
    Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    Integer start = -1;

    List<Integer> numbersList = Lists.newArrayList(nums);
    long MAX_SHARD_SIZE = 4;
    Iterator<Integer> hBaseIterator = numbersList.iterator();
    List<Pair<Integer, Integer>> shardBoundaries = Lists.newArrayList();


    for (long count = 1; hBaseIterator.hasNext(); count++) {
      Integer row = hBaseIterator.next();
      if (count == 1) {
        start = row;
      }

      if (count > 1 && count % MAX_SHARD_SIZE == 1 || !hBaseIterator.hasNext()) {
        Pair<Integer, Integer> boundary = Pair.of(start, row);
        shardBoundaries.add(boundary);
        start = row;
      }
    }
    for(Pair<Integer, Integer> boundary: shardBoundaries) {
      System.out.println(boundary);
    }

  }
}
