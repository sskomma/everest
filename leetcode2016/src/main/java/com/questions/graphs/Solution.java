package com.questions.graphs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution {

    static class GraphNode {

        Integer value;
        ArrayList<GraphNode> neighbors;

        GraphNode(Integer value) {
            this.value = value;
            this.neighbors = new ArrayList<>();
        }
    }

    static GraphNode create_transpose(GraphNode node) {
        // Write your code here.
        Map<Integer, List<Integer>> newAdjMap = new HashMap<>();
        Map<Integer, Boolean> visited = new HashMap<>();
        bfs_transpose(node, newAdjMap, visited);

        return buildNodeFromAdjList(newAdjMap, node.value);
    }

    static GraphNode buildNodeFromAdjList(Map<Integer, List<Integer>> newAdjMap, int source) {
        Map<Integer, GraphNode> nodeIndex = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : newAdjMap.entrySet()) {
            GraphNode key = nodeIndex.computeIfAbsent(entry.getKey(), x -> new GraphNode(x));
            for (Integer neighbor : entry.getValue()) {
                GraphNode n = nodeIndex.computeIfAbsent(neighbor, x -> new GraphNode(x));
                key.neighbors.add(n);
            }
        }
        return nodeIndex.get(source);
    }

    // DFS
    static void bfs_transpose(GraphNode source, Map<Integer, List<Integer>> newAdjMap, Map<Integer, Boolean> visited) {

        Deque<GraphNode> queue = new LinkedList<>();
        queue.addLast(source);
        visited.put(source.value, Boolean.TRUE);

        while (!queue.isEmpty()) {
            GraphNode node = queue.removeFirst();
            for (GraphNode neighbor : source.neighbors) {
                if (!visited.get(neighbor.value)) {
                    newAdjMap.computeIfAbsent(neighbor.value, x -> new ArrayList<>()).add(node.value);
                }
            }
        }
    }


    /*
Given an array of integers A any element is at most k places away from its sorted position, code a function sortKOffArray() that sorts A.

Example
input:  A = [1, 4, 5, 2, 3, 7, 8, 6, 10, 9], k = 2

output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

For instance in an array with k = 3, an element located at 7 in the sorted array will be located at  4 though 10 in the input array.

Time complexity: N*K +>

/*
Given an array of integers A any element is at most k places away from its sorted position, code a function sortKOffArray() that sorts A.

Example
input:  A = [1, 4, 5, 2, 3, 7, 8, 6, 10, 9], k = 2

output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

For instance in an array with k = 3, an element located at 7 in the sorted array will be located at  4 though 10 in the input array.

Time complexity: N*K +>
     */
}
