package com.apple.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class DirectedGraphTest {
  private Map<String, Set<String>> adjMap = null;
  private DirectedGraph directedGraph = null;

  @Before
  public void setUp() {
    adjMap = new HashMap<>(10);
    String[] oneChildren = {"Two", "Three", "Four"};
    adjMap.put("One", new HashSet<>(Arrays.asList(oneChildren)));
    String[] twoChildren = {"Three", "Five", "Six"};
    adjMap.put("Two", new HashSet<>(Arrays.asList(twoChildren)));
    String[] threeChildren = {"Four", "Seven", "Eight"};
    adjMap.put("Three", new HashSet<>(Arrays.asList(threeChildren)));
    String[] fourChildren = {"Two", "Nine", "Ten", "One"};
    adjMap.put("Four", new HashSet<>(Arrays.asList(fourChildren)));
    String[] fiveChildren = {"Six", "Three", "Four"};
    adjMap.put("Five", new HashSet<>(Arrays.asList(fiveChildren)));
    String[] sixChildren = {"Seven", "Four", "One",};
    adjMap.put("Six", new HashSet<>(Arrays.asList(sixChildren)));
    String[] sevenChildren = {"Eight", "One", "Two"};
    adjMap.put("Seven", new HashSet<>(Arrays.asList(sevenChildren)));
    String[] eightChildren = {"Nine", "Two", "Three"};
    adjMap.put("Eight", new HashSet<>(Arrays.asList(eightChildren)));
    String[] nineChildren = {"Ten", "Three", "Four"};
    adjMap.put("Nine", new HashSet<>(Arrays.asList(nineChildren)));
    String[] tenChildren = {"Five", "Six", "Seven"};
    adjMap.put("Ten", new HashSet<>(Arrays.asList(tenChildren)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValidateNetworkSize() {
    Map<String, Set<String>> adjMap = new HashMap<>(10);
    String[] oneChildren = {"Two", "Three", "Four"};
    adjMap.put("One", new HashSet<>(Arrays.asList(oneChildren)));

    DirectedGraph.getInstance(adjMap);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValidNumberOfChildren() {
    Map<String, Set<String>> adjMap = new HashMap<>();
    String[] oneChildren = {"Two", "Three"};
    adjMap.put("One", new HashSet<>(Arrays.asList(oneChildren)));
    DirectedGraph.getInstance(adjMap);
  }

  @Test
  public void testIsCyclic() {

    Map<String, Set<String>> adjMap = new HashMap<>(3);
    String[] oneChildren = {"Two"};
    adjMap.put("One", new HashSet<>(Arrays.asList(oneChildren)));
    String[] twoChildren = {"Three"};
    adjMap.put("Two", new HashSet<>(Arrays.asList(twoChildren)));
    assertFalse(DirectedGraph.isCyclic(adjMap));
    String[] threeChildren = {"One"};
    adjMap.put("Three", new HashSet<>(Arrays.asList(threeChildren)));
    assertTrue(DirectedGraph.isCyclic(adjMap));
  }

  @Test
  public void test_isNotCyclic() {
    Map<String, Set<String>> adjMap = new HashMap<>(3);
    String[] oneChildren = {"Two"};
    adjMap.put("One", new HashSet<>(Arrays.asList(oneChildren)));
    String[] twoChildren = {"Three"};
    adjMap.put("Two", new HashSet<>(Arrays.asList(twoChildren)));
    assertFalse(DirectedGraph.isCyclic(adjMap));

  }

  @Test
  public void testGetInstance() {
    directedGraph = DirectedGraph.getInstance(adjMap);
    assertNotNull(directedGraph);
  }

  @Test
  public void testGraphSize() {
    directedGraph = DirectedGraph.getInstance(adjMap);
    assertEquals(10, directedGraph.networkSize());
  }

  @Test
  public void testConnected() {
    directedGraph = DirectedGraph.getInstance(adjMap);
    Node one = directedGraph.getPerson("One");
    Node ten = directedGraph.getPerson("Ten");
    System.out.println(directedGraph.connected(one, ten));
  }

  @Test
  public void testFindConnections() {
    directedGraph = DirectedGraph.getInstance(adjMap);
    Map<Node, List<Node>> actualResultsMap =
        directedGraph.findConnections(directedGraph.getPerson("One"), 2);

    for (Map.Entry e : actualResultsMap.entrySet()) {
      System.out.println(e.getKey());
      System.out.println(e.getValue());
    }

    // Construct expected results.
    Map<String, Node> index = new HashMap<>();
    for (Node person : actualResultsMap.keySet()) {
      index.putIfAbsent(person.name(), person);
    }
    Map<Node, List<Node>> expectedResultsMap = new HashMap<>();

    Node[] ones = {index.get("Two"), index.get("Three"), index.get("Four"), index.get("Five"),
        index.get("Six"), index.get("Seven"), index.get("Eight"), index.get("Nine"),
        index.get("Ten")};
    Node[] twos = {index.get("One"), index.get("Three"), index.get("Four"), index.get("Five"),
        index.get("Six"), index.get("Seven"), index.get("Eight")};
    Node[] threes = {index.get("One"), index.get("Two"), index.get("Three"), index.get("Four"),
        index.get("Seven"), index.get("Eight"), index.get("Nine"), index.get("Ten")};
    Node[] fours =
        {index.get("One"), index.get("Two"), index.get("Three"), index.get("Five"), index.get("Six"),
            index.get("Seven"), index.get("Nine"), index.get("Ten")};
    Node[] fives = {index.get("One"), index.get("Two"), index.get("Three"), index.get("Four"),
        index.get("Six"), index.get("Seven"), index.get("Eight"), index.get("Nine"),
        index.get("Ten")};
    Node[] sixes = {index.get("One"), index.get("Two"), index.get("Three"), index.get("Four"),
        index.get("Seven"), index.get("Eight"), index.get("Nine"), index.get("Ten")};
    Node[] sevens = {index.get("One"), index.get("Two"), index.get("Three"), index.get("Four"),
        index.get("Five"), index.get("Six"), index.get("Eight"), index.get("Nine")};
    Node[] eights = {index.get("Two"), index.get("Three"), index.get("Four"), index.get("Five"),
        index.get("Six"), index.get("Seven"), index.get("Eight"), index.get("Nine"),
        index.get("Ten")};
    Node[] nines = {index.get("One"), index.get("Two"), index.get("Three"), index.get("Four"),
        index.get("Five"), index.get("Six"), index.get("Seven"), index.get("Eight"),
        index.get("Nine"), index.get("Ten")};
    Node[] tens = {index.get("One"), index.get("Two"), index.get("Three"), index.get("Four"),
        index.get("Five"), index.get("Six"), index.get("Seven"), index.get("Eight")};

    assertTrue(Arrays.asList(ones).containsAll(actualResultsMap.get(index.get("One"))));
    assertTrue(Arrays.asList(twos).containsAll(actualResultsMap.get(index.get("Two"))));
    assertTrue(Arrays.asList(threes).containsAll(actualResultsMap.get(index.get("Three"))));
    assertTrue(Arrays.asList(fours).containsAll(actualResultsMap.get(index.get("Four"))));
    assertTrue(Arrays.asList(fives).containsAll(actualResultsMap.get(index.get("Five"))));
    assertTrue(Arrays.asList(sixes).containsAll(actualResultsMap.get(index.get("Six"))));
    assertTrue(Arrays.asList(sevens).containsAll(actualResultsMap.get(index.get("Seven"))));
    assertTrue(Arrays.asList(eights).containsAll(actualResultsMap.get(index.get("Eight"))));
    assertTrue(Arrays.asList(nines).containsAll(actualResultsMap.get(index.get("Nine"))));
    assertTrue(Arrays.asList(tens).containsAll(actualResultsMap.get(index.get("Ten"))));
  }
}