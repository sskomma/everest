package com.questions.graphs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NetworkDelay {
  static List<Node> nodes = new ArrayList<>();
  static Map<Integer, Node> map = new HashMap<>();

  public static List<Node> constructGraph(int[][] times, int N) {
    for (int i = 1; i <= N; i++) {
      Node node = new Node(i);
      nodes.add(node);
      map.put(i, node);
    }
    for (int i = 0; i < times.length; i++) {
      Node source = map.get(times[i][0]);
      source.edges.put(map.get(times[i][1]), times[i][2]);
    }
    return nodes;
  }

  public static int computeDelay(int[][] times, int N, int K) {
    constructGraph(times, N);
    int delay = signalDelay(map.get(K));
    for (Node n : nodes) {
      if (!n.visited) {
        return -1;
      }
    }
    return delay;
  }

  public static int signalDelay(Node root) {
    int delay = 0;
    if (root == null) {
      return 0;
    }
    root.visited = true;
    Deque<Node> queue = new LinkedList<Node>();
    queue.add(root);
    while(!queue.isEmpty()) {
      Node sibling = queue.remove();
      
    }
    for (Node sibling: root.edges.keySet()) {
      if (sibling != null && !sibling.visited) {
        delay = Math.max(delay, root.edges.get(sibling) + signalDelay(sibling));
      }
    }
    return delay;
  }

  public static void main(String[] args) {
    int[][] delay = {{1, 2, 1},{1, 3, 2}, {2, 3, 2} };
    System.out.println(computeDelay(delay, 3, 1));
  }

  private static class Node {
    int data;
    Map<Node, Integer> edges;
    boolean visited;

    Node(int n) {
      this.data = n;
      visited = false;
      edges = new HashMap<>();
    }

    @Override
    public int hashCode() {
      return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Node) {
        Node other = (Node) obj;
        return this.data == other.data;
      }
      return false;
    }
  }
}
