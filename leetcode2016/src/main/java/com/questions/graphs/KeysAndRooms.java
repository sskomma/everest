package com.questions.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/keys-and-rooms/description/
 */
public class KeysAndRooms {
  public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
    if(rooms == null || rooms.isEmpty())
      return false;

    Set<Integer> visited = new HashSet<>();
    visited.add(0);

    Deque<Integer> keysQueue = new LinkedList<>();
    keysQueue.addAll(rooms.get(0));

    while (!keysQueue.isEmpty() && visited.size() != rooms.size()) {
      Integer room = keysQueue.remove();
      if(visited.contains(room))
        continue;

      visited.add(room);
      keysQueue.addAll(rooms.get(room));
    }
    return visited.size() == rooms.size();
  }

  public static void main(String[] args) {
    //[[1,3],[3,0,1],[2],[0]]
    List<List<Integer>> rooms = new ArrayList<>();
    rooms.add(Arrays.asList(1,3));
    rooms.add(Arrays.asList(3,0,1));
    rooms.add(Collections.singletonList(2));
    rooms.add(Collections.singletonList(0));
    System.out.println(canVisitAllRooms(rooms));
  }
}
