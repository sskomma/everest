package com.ctci.graphs;

public class GraphNode {
  public int data;
  public boolean visited;

  public GraphNode(int data) {
    this.data = data;
    visited = false;
  }
  public boolean isVisible() {
    return visited;
  }

}
