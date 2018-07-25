package com.apple.graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implements a directed graph of people.
 */
public class DirectedGraph {
  private Set<Node> people;
  private Map<String, Node> peopleIndex;

  private DirectedGraph(Set<Node> people, Map<String, Node> peopleIndex) {
    this.people = people;
    this.peopleIndex = peopleIndex;
  }

  /**
   * A static instanciator for graph.
   * @param adjacencyMap an adjacency map to construct graph.
   * @return an instance of Graph, constructed from map provided, null if a map cannot be created, without breaking integrity.
   */
  public static DirectedGraph getInstance(Map<String, Set<String>> adjacencyMap) {
    Set<Node> people = new HashSet<>();
    Map<String, Node> peopleIndex = new HashMap<>();

    if (!checkNetworkIntegrity(adjacencyMap)) {
      return null;
    }

    for (String name : adjacencyMap.keySet()) {
      Person p = new Person(name);
      people.add(p);
      peopleIndex.put(name, p);
    }

    for (Map.Entry<String, Set<String>> entry : adjacencyMap.entrySet()) {

      Person p = (Person) peopleIndex.get(entry.getKey());
      for (String s : entry.getValue()) {
        p.addChild(peopleIndex.get(s));
      }
    }
    return new DirectedGraph(people, peopleIndex);
  }

  /**
   * A validation method to check if all the following network integrity properties are upheld.
   *  - The graph should contain at least 10 people
   *  - Each person should have at least 3, first level
   *  - The graph should be cyclic
   * @return true, if all properties are upheld; false otherwise.
   */
  static boolean checkNetworkIntegrity(Map<String, Set<String>> adjacencyMap) {
    return validateNetworkSize(adjacencyMap)
        && validNumberOfChildren(adjacencyMap)
        && isCyclic(adjacencyMap);
  }

  /**
   * A validation method to check if the given network is big enough.
   * @return true, if the graph contains at least 10 people; false otherwise.
   */
  static boolean validateNetworkSize(Map<String, Set<String>> adjacencyMap) {
    if (adjacencyMap == null || adjacencyMap.size() < 10) {
      throw new IllegalArgumentException("Number of people in the network cannot be less than 10");
    }
    return true;
  }

  /**
   * A validation method to check if each person has at least 3 children.
   * @return true, if each person has at least 3 children; false otherwise.
   */
  static boolean validNumberOfChildren(Map<String, Set<String>> adjacencyMap) {
    for (Map.Entry<String, Set<String>> personEntry : adjacencyMap.entrySet()) {
      if (personEntry.getValue().size() < 3) {
        throw new IllegalArgumentException(
            "Person : " + personEntry.getKey() + " has does not have enough children");
      }
    }
    return true;
  }

  /**
   * A validation function to check if the network is cyclic.
   * Uses Depth First Search (DFS), to identify loops in graph.
   *
   * @return true, if network is cyclic; false otherwise.
   */
  static boolean isCyclic(Map<String, Set<String>> adjacencyMap) {
    Set<String> visited = new HashSet<>();
    Set<String> visiting = new HashSet<>();

    for (Map.Entry<String, Set<String>> personEntry : adjacencyMap.entrySet()) {
      if (isCyclicHelper(personEntry.getKey(), adjacencyMap, visited, visiting)) {
        return true;
      }
    }
    return false;
  }

  /**
   * A recursive helper method to {@link #isCyclic(Map)}, to determine, if the given map is cyclic.
   */
  private static boolean isCyclicHelper(
      String person, Map<String, Set<String>> adjacencyMap, Set<String> visited,
      Set<String> visiting) {

    if (visiting.contains(person)) {
      return true;
    }
    if (visited.contains(person)) {
      return false;
    }

    visited.add(person);
    visiting.add(person);

    Set<String> children = adjacencyMap.get(person);

    if (children != null) {
      for (String c : children) {
        if (isCyclicHelper(c, adjacencyMap, visited, visiting)) {
          return true;
        }
      }
    }
    visiting.remove(person);
    return false;
  }

  /**
   * Function to get Node of a given person name.
   * @param name name of the person to be found in the network.
   * @return {@link Node} instance returned.
   */
  public Node getPerson(String name) {
    return peopleIndex.get(name);
  }

  /**
   * The size of the network, number of people in the network.
   * @return the number of people in the network.
   */
  public int networkSize() {
    return people.size();
  }

  /**
   * Implement a function that traverses the graph and returns a map that consists of all people
   * in the graph as "keys" that map to a list of all associated connections up to a certain depth.
   *
   * For example, if the depth is given as 2, the list of
   * "connections" will contain all 1st and 2nd level connections for each person.
   *
   * @param person starting person.
   * @param depth depth of network to be returned.
   * @return {@link Map} of all {@link Person} in network to a {@link List} of their connections at given depth.
   */
  public Map<Node, List<Node>> findConnections(Node node, int depth) {

    // A dataStore to save children at each level for each person.
    Map<Node, Map<Integer, Set<Node>>> dataStore = new HashMap<>();
    Map<Node, Set<Node>> aggregateChildren = new HashMap<>();
    for (Node person : people) {
      aggregateChildren.putIfAbsent(person, new HashSet<>());
      for (int i = 1; i <= depth; i++) {
        childrenAtDepth(person, i, dataStore);
        aggregateChildren.get(person).addAll(dataStore.get(person).get(i));
      }
    }

    //System.out.println(dataStore);
    Map<Node, List<Node>> results = new HashMap<>();
    for (Map.Entry<Node, Set<Node>> personEntry : aggregateChildren.entrySet()) {
      Node person = personEntry.getKey();
      Set<Node> children = personEntry.getValue();
      children.remove(person);
      results.putIfAbsent(person, new ArrayList<>(children));
    }
    return results;
  }

  private void childrenAtDepth(Node node, int depth, Map<Node, Map<Integer, Set<Node>>> store) {

    store.putIfAbsent(node, new HashMap<>());
    if (store.get(node).containsKey(depth)) {
      return;
    }

    Map<Integer, Set<Node>> connections = store.get(node);
    connections.putIfAbsent(depth, new HashSet<>());

    if (depth == 1) {
      connections.get(depth).addAll(node.children());
      return;
    }

    for (Node child : node.children()) {
      childrenAtDepth(child, depth - 1, store);

      Set<Node> childrenAtDm1 = store.get(child).get(depth - 1);
      connections.get(depth).addAll(childrenAtDm1);
    }
  }

  /**
   * Uses Breadth First Search (BFS) to find find distance between two given people.
   * Given this is a Directed graph, distance between two nodes is not transitive.
   *
   * @param personA first person.
   * @param personB second person.
   * @return the distance between the two people.
   */
  public int connected(Node personA, Node personB) {

    if (personA == null || personB == null) {
      return -1;
    } else if (personA == personB) {
      return 0;
    }

    Set<Node> visited = new HashSet<>();
    Deque<Node> current = new LinkedList<>();
    current.add(personA);
    int distance = 1;
    Deque<Node> nxtLevel = new LinkedList<>();

    while (!current.isEmpty()) {
      Node person = current.remove();
      visited.add(person);

      if (person.children().contains(personB)) {
        return distance;
      }

      person.children().stream()
          .filter(child -> !visited.contains(child))
          .forEach(nxtLevel::add);

      if (current.isEmpty()) {
        distance++;
        Deque<Node> temp = current;
        current = nxtLevel;
        nxtLevel = temp;
      }
    }
    return -1;
  }
}
