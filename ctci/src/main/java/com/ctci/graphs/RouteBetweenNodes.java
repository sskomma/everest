package com.ctci.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouteBetweenNodes {

  public static void main(String[] args) {
    Map<GraphNode, List<GraphNode>> al = new HashMap<>();

    GraphNode gn1 = new GraphNode(1);
    GraphNode gn2 = new GraphNode(2);
    GraphNode gn3 = new GraphNode(3);
    GraphNode gn4 = new GraphNode(4);
    GraphNode gn5 = new GraphNode(5);

    al.put(gn1, new ArrayList<>());
    al.put(gn2, new ArrayList<>());
    al.put(gn3, new ArrayList<>());
    al.put(gn4, new ArrayList<>());
    al.put(gn5, new ArrayList<>());

    al.get(gn1).add(gn2);
    al.get(gn2).add(gn3);

    al.get(gn4).add(gn2);
    al.get(gn4).add(gn5);

    al.get(gn5).add(gn1);
    al.get(gn5).add(gn3);
    //System.out.println(routeBetweenNodes(al, gn1, gn4));
    System.out.println(routeBetweenNodes(al, gn4, gn3));
    //System.out.println(routeBetweenNodes(al, gn5, gn4));
  }

  public static boolean routeBetweenNodes(
      Map<GraphNode, List<GraphNode>> adjList, GraphNode start, GraphNode end) {

    List<GraphNode> adjNodes = adjList.get(start);
    for (GraphNode node : adjNodes) {
      if (!node.visited) {
        if (node == end) {
          return true;
        }
        node.visited = true;
      }
      if (routeBetweenNodes(adjList, node, end)) {
        return true;
      }
    }
    return false;
  }
}
