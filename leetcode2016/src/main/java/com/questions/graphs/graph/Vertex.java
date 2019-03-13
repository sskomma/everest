package com.questions.graphs.graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex<T> {
  private long id;
  private T data;
  private List<Edge<T>> adjacentEdges = new ArrayList<>();
  private List<Vertex<T>> adjacentVertices = new ArrayList<>();

  public Vertex(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public List<Vertex<T>> getAdjacentVertices() {
    return adjacentVertices;
  }

  public void addAdjcentVertex(Edge<T> edge, Vertex<T> adjcentVertex) {
    adjacentEdges.add(edge);
    adjacentVertices.add(adjcentVertex);
  }

  public List<Edge<T>> getAdjacentEdges() {
    return adjacentEdges;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Vertex<T> other = (Vertex<T>) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Vertex [id=" + id + "]";
  }
}