package com.komma.ik.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CriticalConnections {

/*    public static List<List<Integer>> find_critical_connections(
        int number_of_servers, List<List<Integer>> connections) {

        List<List<Integer>> adjList = buildAdjList(number_of_servers, connections);
        boolean[] visited = new boolean[number_of_servers];
        int[] parent = new int[number_of_servers];
        Set<Integer> critical_nodes = new HashSet<>();

        for (int i = 0; i < number_of_servers; i++) {
            if (visited[i]) {
                continue;
            }
            bfs(i, adjList, visited, parent, critical_nodes);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (Integer node : critical_nodes) {
            result.add(List.of(parent[node], node));
        }

        return result;
    }

    private static void bfs(Integer source, List<List<Integer>> adjList, boolean[] visited, int[] parent, Set<Integer> critical_nodes) {
        Deque<Integer> queue = new LinkedList<>();
        queue.addLast(source);
        visited[source] = true;
        parent[source] = -1;
        while (!queue.isEmpty()) {
            Integer node = queue.removeFirst();
            List<Integer> neighbors = adjList.get(node);
            for (Integer neighbor : neighbors) {
                if (!visited[neighbor]) {
                    queue.addLast(neighbor);
                    visited[neighbor] = true;
                    parent[neighbor] = node;
                    critical_nodes.add(neighbor);
                } else {
                    if(neighbor.equals(node))
                        critical_nodes.remove(neighbor);
                }
            }
        }
    }*/

    private static int timer = 0;
    public static List<List<Integer>> find_critical_connections(
        int number_of_servers, List<List<Integer>> connections) {
        timer = 0;
        List<List<Integer>> adjList = buildAdjList(number_of_servers, connections);
        boolean[] visited = new boolean[number_of_servers];
        int[] parent = new int[number_of_servers];
        int[] discovery_time = new int[number_of_servers];
        int[] least_time = new int[number_of_servers];
        List<List<Integer>> crit_connections = new ArrayList<>();
        for(int i = 0; i < number_of_servers; i++) {
            if(!visited[i]) {
                dfs_critical(i, adjList, visited, parent, discovery_time, least_time, crit_connections);
            }
        }
        return crit_connections;
    }

    private static void dfs_critical(int source, List<List<Integer>> adjList, boolean[] visited, int[] parent,
                                    int[] discovery_time, int[] least_time, List<List<Integer>> connections ) {

        discovery_time[source] = least_time[source] = timer++;
        visited[source] = true;

        for(Integer neighbor: adjList.get(source)) {
            parent[neighbor] = source;
            if(neighbor == parent[source])
                continue;

            if(visited[neighbor]) {
                least_time[source] = Math.min(least_time[neighbor], discovery_time[source]);
            } else {
                dfs_critical(neighbor, adjList, visited, parent, discovery_time, least_time, connections);
                if(least_time[neighbor] > discovery_time[source]) {
                    connections.add(List.of(source, neighbor));
                }
                least_time[source] = Math.min(least_time[neighbor], least_time[source]);
            }
        }
    }

    private static List<List<Integer>> buildAdjList(int number_of_servers, List<List<Integer>> connections) {
        if (number_of_servers <= 0 || connections == null || connections.isEmpty()) {
            return Collections.emptyList();
        }

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < number_of_servers; i++) {
            adjList.add(new ArrayList<>());
        }

        for (List<Integer> edge : connections) {
            adjList.get(edge.get(0)).add(edge.get(1));
            adjList.get(edge.get(1)).add(edge.get(0));
        }
        return adjList;
    }

    public static void main(String[] args) {
        List<List<Integer>> edges = List.of(List.of(0, 1), List.of(0, 2), List.of(1, 2), List.of(5, 4),
                                            List.of(4, 6), List.of(6, 5), List.of(3, 1), List.of(3, 4));

        List<List<Integer>> result = find_critical_connections(7, edges);
        System.out.println(result);
    }

}
