package com.questions.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.
 * The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.
 *
 * Example:
 * Input: [[1,2], [3], [3], []]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: The graph looks like this:
 * 0--->1
 * |    |
 * v    v
 * 2--->3
 * There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 *
 * https://leetcode.com/problems/all-paths-from-source-to-target/
 */
public class AllPaths {
  public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    if (graph == null || graph.length == 0) {
      return Collections.emptyList();
    }

    Deque<List<Integer>> stack = new LinkedList<>();
    List<List<Integer>> paths = new ArrayList<>();

    List<Integer> init = new ArrayList<>();
    init.add(0);
    stack.push(init);

    while (!stack.isEmpty()) {
      List<Integer> path = stack.pop();
      int lastNode = path.get(path.size() - 1);
      if (lastNode == graph.length - 1) {
        paths.add(path);
      }

      for (Integer sibling : graph[lastNode]) {
        List<Integer> newPath = new ArrayList<>(path);
        newPath.add(sibling);
        stack.push(newPath);
      }
    }

    return paths;
  }

  public static void main(String[] args) {
    int[][] graph = {{1, 2}, {3}, {3}, {}};
    System.out.println(allPathsSourceTarget(graph));
  }
}
