package com.ctci.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

public class BuildOrder {

  public static Graph constructGraph(List<Pair<Character, Character>> dependencies) {
    Graph graph = new Graph();
    for (Pair<Character, Character> p : dependencies) {
      graph.addNode(p.getRight());
      graph.addNode(p.getLeft());
      graph.addEdge(p.getLeft(), p.getRight());
    }
    return graph;
  }

  public static List<Character> buildOrder(
      List<Pair<Character, Character>> dependencies, Character[] projects) {
    Graph graph = constructGraph(dependencies);
    List<Character> buildOrder = new ArrayList<>();
    for (Character project : projects) {
      if (graph.buildStatus.get(project) != Graph.Build.BUILT) {
        buildProject(project, graph, buildOrder);
      }
    }
    return buildOrder;
  }

  private static void buildProject(
      Character project, Graph graph, List<Character> buildOrder) {
    if (graph.buildStatus.get(project) == Graph.Build.BUILDING) {
      throw new IllegalStateException("Circular dependencies present");
    } else if (graph.buildStatus.get(project) == Graph.Build.BUILT) {
      return;
    }
    graph.buildStatus.put(project, Graph.Build.BUILDING);
    if (graph.adjList.get(project) != null) {
      for (Character child : graph.adjList.get(project)) {
        buildProject(child, graph, buildOrder);
      }
    }

    graph.buildStatus.put(project, Graph.Build.BUILT);
    buildOrder.add(project);
  }

  public static void main(String[] args) {
    Character[] projects = {'a', 'b', 'c', 'd', 'e', 'f'};
    List<Pair<Character, Character>> dependencies = new ArrayList<>();
    dependencies.add(Pair.of('a', 'f'));
    dependencies.add(Pair.of('b', 'f'));
    dependencies.add(Pair.of('c', 'd'));
    dependencies.add(Pair.of('d', 'a'));
    dependencies.add(Pair.of('d', 'b'));
    List<Character> buildOrder = buildOrder(dependencies, projects);
    for (Character c : buildOrder) {
      System.out.println(c);
    }
  }

  public static class Graph {
    List<Character> nodes;
    Map<Character, List<Character>> adjList;
    Map<Character,Build> buildStatus;

    public Graph() {
      nodes = new ArrayList<>();
      adjList = new HashMap<>();
      buildStatus = new HashMap<>();
    }
    public enum Build {
      UNBUILT, BUILDING, BUILT;
    }
    public void addNode(Character c) {
      if(!nodes.contains(c)) {
        nodes.add(c);
        buildStatus.put(c, Build.UNBUILT);
      }
    }

    public void addEdge(Character start, Character end) {
      if(adjList.get(start) == null) {
        adjList.put(start, new ArrayList<>());
      }
      if(!adjList.get(start).contains(end)) {
        adjList.get(start).add(end);
      }
    }
  }
}
