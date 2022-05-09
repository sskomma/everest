package com.komma.ik.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ZombieClusters {

    public static int zombieCluster(List<String> zombies) {
        // Write your code here
        // BFS
        boolean[] visited = new boolean[zombies.size()];
        int count = 0;
        for(int i = 0; i< zombies.size(); i++) {
            if(!visited[i]) {
                count++;
                bfs(i, zombies, visited);
            }
        }
        return count;
    }

    private static void bfs(int source, List<String> zombies, boolean[] visited) {

        Deque<Integer> queue = new LinkedList<>();
        queue.addLast(source);
        visited[source] = true;

        while(!queue.isEmpty()) {
            int node = queue.removeFirst();
            List<Integer> neighbors = getNeighbors(node, zombies);
            for(Integer neighbor : neighbors ) {
                if(!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.addLast(neighbor);
                }
            }
        }
    }

    private static List<Integer> getNeighbors(int source, List<String> zombies) {

        String neighbors = zombies.get(source);
        List<Integer> result = new ArrayList<>();

        for( int i = 0; i<neighbors.length(); i++) {
            if(i==source) continue;
            if(neighbors.charAt(i) == '1') result.add(i);
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println(zombieCluster(Arrays.asList("1100", "1110", "0110", "0001")));
    }

}
