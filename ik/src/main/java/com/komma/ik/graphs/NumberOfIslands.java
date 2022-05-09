package com.komma.ik.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfIslands {

    public static int count_islands(List<List<Integer>> matrix) {
        // Write your code here
        if(matrix == null || matrix.size() == 0) {
            return 0;
        }
        int islands = 0;
        for(int i = 0; i< matrix.size(); i++) {
            for(int j = 0; j < matrix.get(i).size(); j++) {
                if(matrix.get(i).get(j) == 1) {
                    islands++;
                    dfs_traversal(matrix, i, j);
                }
            }
        }
        return islands;
    }

    private static void dfs_traversal(List<List<Integer>> matrix, int i, int j) {
        if(matrix.get(i).get(j) == 0) {
            return;
        }

        // Set current vertex as discovered, by setting 0.
        matrix.get(i).set(j, 0);

        List<List<Integer>> neighbours = getNeighbours(matrix, i, j);
        for(int k = 0; k< neighbours.size(); k++) {
            int row = neighbours.get(k).get(0);
            int col = neighbours.get(k).get(1);
            if(matrix.get(row).get(col) == 1) {
                dfs_traversal(matrix, row, col);
            }
        }
    }


    private static List<List<Integer>> getNeighbours(List<List<Integer>> matrix, int i, int j) {
        int rows = matrix.size();
        int cols = matrix.get(0).size();
        List<List<Integer>> neighbours = new ArrayList<>();

        if(i-1 >= 0) {
            neighbours.add(Arrays.asList(i-1, j));
            if(j-1 >= 0) neighbours.add(Arrays.asList(i-1, j-1));
            if(j+1 < cols) neighbours.add(Arrays.asList(i-1, j+1));
        }

        if(j-1 >= 0) neighbours.add(Arrays.asList(i, j-1));
        if(j+1 < cols) neighbours.add(Arrays.asList(i, j+1));

        if(i+1 < rows) {
            neighbours.add(Arrays.asList(i+1, j));
            if(j-1 >= 0) neighbours.add(Arrays.asList(i+1, j-1));
            if(j+1 < cols) neighbours.add(Arrays.asList(i+1, j+1));
        }
        return neighbours;
    }

    public static void main(String[] args) {
        /**
         *     [1, 1, 0, 0, 0],
         *
         *     [0, 1, 0, 0, 1],
         *
         *     [1, 0, 0, 1, 1],
         *
         *     [0, 0, 0, 0, 0],
         *
         *     [1, 0, 1, 0, 1]
         */

        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(1, 1, 0, 0, 0));
        matrix.add(Arrays.asList(0, 1, 0, 0, 1));
        matrix.add(Arrays.asList(1, 0, 0, 1, 1));
        matrix.add(Arrays.asList(0, 0, 0, 0, 0));
        matrix.add(Arrays.asList(1, 0, 1, 0, 1));
        System.out.println(count_islands(matrix));
    }

}
