package com.komma.ik.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

public class Bipartitie {

    public static boolean can_be_divided(int num_of_people, List<Integer> dislike1, List<Integer> dislike2) {
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i = 0; i< num_of_people; i++) {
            adjList.add(new ArrayList<Integer>());
        }

        for(int i = 0; i< dislike1.size(); i++) {
            Integer begin = dislike1.get(i);
            Integer end = dislike2.get(i);
            adjList.get(begin).add(end);
            adjList.get(end).add(begin);
        }
        boolean[] visited = new boolean[num_of_people];
        int[] distance = new int[num_of_people];
        boolean result = true;
        for(int i = 0; i< num_of_people; i ++) {
            if(!visited[i]) {
                if(!bfs_bipartite(i, adjList, visited, distance)) {
                    return false;
                }
            }
        }
        return result;
    }

    private static boolean bfs_bipartite(int source, List<List<Integer>> adjList, boolean[] visited, int[] distance) {
        Deque<Integer> queue = new LinkedList<>();
        queue.addLast(source);
        visited[source] = true;

        while(!queue.isEmpty()) {

            Integer node = queue.removeFirst();
            List<Integer> neighbors = adjList.get(node);

            for(Integer neighbor: neighbors) {
                if(visited[neighbor]) {
                    if(distance[neighbor] == distance[node]) {
                        return false;
                    }
                } else {
                    visited[neighbor] = true;
                    distance[neighbor] = distance[node] + 1;
                    queue.addLast(neighbor);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        /**
         * 5 5
         * 0 1
         * 1 2
         * 2 3
         * 3 4
         * 4 1
         */
        List<Integer> dislike1 = Arrays.asList( 0, 1, 2, 3, 4);
        List<Integer> dislike2 = Arrays.asList( 1, 2, 3, 4, 1);
        System.out.println(can_be_divided(6, dislike1, dislike2));
    }

}
