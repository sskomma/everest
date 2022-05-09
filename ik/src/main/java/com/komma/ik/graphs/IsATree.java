package com.komma.ik.graphs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

public class IsATree {

    // Complete the function below
    public static boolean is_it_a_tree(int node_count, List<Integer> edge_start, List<Integer> edge_end) {

        List<List<Integer>> adjNodes = new ArrayList<>();

        for(int i =0 ; i< node_count; i++) {
            adjNodes.add(new ArrayList<>());
        }
        for(int i =0; i< edge_start.size(); i++) {
            Integer a = edge_start.get(i);
            Integer b = edge_end.get(i);
            if( a == b) {
                // Self loop.
                return false;
            }
            adjNodes.get(a).add(b);
            adjNodes.get(b).add(a);
        }

        int components = 0;
        boolean visited[] = new boolean[node_count];

        for(int i = 0; i< node_count; i++) {
            if(visited[i]){
                continue;
            }

            components++;
            if(components > 1) {
                return false;
            }
            if(bfs_hasXNodes(i, node_count, adjNodes, visited)) {
                return false;
            }
        }
        return true;
    }

    private static boolean bfs_hasXNodes(int node, int node_count, List<List<Integer>> adjList, boolean[] visited) {

        Deque<Integer> queue = new LinkedList<>();
        queue.addLast(node);
        visited[node] = true;
        Integer[] parent = new Integer[node_count];

        while(!queue.isEmpty()) {
            Integer source = queue.removeFirst();
            for(Integer neighbour : adjList.get(source) ) {
                if(visited[neighbour] && parent[neighbour] != null && !neighbour.equals(parent[source])) {
                    return true;
                } else if(!visited[neighbour]){
                    visited[neighbour] = true;
                    queue.addLast(neighbour);
                    parent[neighbour] = source;

                }

            }
        }
        return false;
    }

    public static void main(String[] args) {
        /**
         * 7 6
         * 4 0
         * 5 2
         * 5 6
         * 3 4
         * 5 0
         * 6 1
         */

        System.out.println(is_it_a_tree(8,
                                        Lists.newArrayList(7, 4, 5, 5, 3, 5, 6),
                                        Lists.newArrayList(6, 0, 2, 6, 4, 0, 1)
        ));
    }

}
