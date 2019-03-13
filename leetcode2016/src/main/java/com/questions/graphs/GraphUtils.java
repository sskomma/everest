package com.questions.graphs;

import com.questions.graphs.graph.Graph;
import com.questions.graphs.graph.Vertex;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphUtils {

  /**
   * BFS Algorithm implementation. This method traverses the graph Breadth first
   * and prints each node as it visits it.
   *
   * @param graph
   */
  public static void BFS(Graph<Integer> graph) {
    Set<Vertex<Integer>> visited = new HashSet<Vertex<Integer>>();
    Queue<Vertex<Integer>> vertecesQueue = new LinkedList<Vertex<Integer>>();

    //Outer for loop is to make sure to print all nodes, even in disconnected graph
    for (Vertex<Integer> vertex : graph.getAllVerteces()) {
      if (!visited.contains(vertex)) {
        vertecesQueue.add(vertex);
        while (!vertecesQueue.isEmpty()) {
          Vertex<Integer> v = vertecesQueue.poll();
          if (!visited.contains(v)) {
            vertecesQueue.addAll(v.getAdjacentVertices());
            visited.add(v);
            System.out.println(v);
          }
        }
      }
    }
  }

  /**
   * DFS Algorithm implemented the way BFS is but with a stack to store the next node to visit.
   * This method traverses the graph Depth first and prints each node.
   *
   * @param graph
   */
  public static void DFS1(Graph<Integer> graph) {
    Set<Vertex<Integer>> visited = new HashSet<Vertex<Integer>>();
    Stack<Vertex<Integer>> vertecesStack = new Stack<Vertex<Integer>>();
    for (Vertex<Integer> vertex : graph.getAllVerteces()) {
      if (!visited.contains(vertex)) {
        vertecesStack.add(vertex);
        while (!vertecesStack.isEmpty()) {
          Vertex<Integer> v = vertecesStack.pop();
          if (!visited.contains(v)) {
            for (Vertex<Integer> adj : v.getAdjacentVertices()) {
              vertecesStack.add(adj);
            }
            visited.add(v);
            System.out.println(v);
          }
        }
      }
    }
  }

  /**
   *
   * @param graph
   */
  public static void DFS2(Graph<Integer> graph) {
    Set<Vertex<Integer>> visited = new HashSet<Vertex<Integer>>();
    for (Vertex<Integer> vertex : graph.getAllVerteces()) {
      if (!visited.contains(vertex)) {
        DFS2_VISIT(vertex, visited);
      }
    }
  }

  private static void DFS2_VISIT(Vertex<Integer> vertex, Set<Vertex<Integer>> visited) {
    visited.add(vertex);
    System.out.println(vertex);
    for (Vertex<Integer> adj : vertex.getAdjacentVertices()) {
      if (!visited.contains(adj)) {
        DFS2_VISIT(adj, visited);
      }
    }
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    Graph<Integer> graph = new Graph<Integer>(false);
    graph.addEdge(1, 2);
    graph.addEdge(1, 4);
    graph.addEdge(1, 3);
    graph.addEdge(4, 3);
    graph.addEdge(2, 4);
    graph.addEdge(4, 6);
    graph.addEdge(2, 5);
    graph.addEdge(6, 7);
    graph.addEdge(5, 7);
    graph.addEdge(7, 8);
    DFS2(graph);
  }
}
