package com.komma.ik.dynamic.programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumOfPathsInMatrix {
    public static int numberOfPaths(List<List<Integer>> matrix) {
        // Write your code here

        if(matrix.isEmpty()) {
            return 0;
        }
        int rows = matrix.size();
        int cols = matrix.get(0).size();
        int[][] pos= new int[rows][cols];

        // rows init
        for(int i = 0; i < rows; i++ ) {
            if(matrix.get(i).get(0) == 1) {
                pos[i][0] = 1;
            }
        }

        // cols init
        for(int j = 0; j < cols; j++ ) {
            if(matrix.get(0).get(j) == 1) {
                pos[0][j] = 1;
            }
        }

        for(int i = 1; i < rows; i++) {
            for(int j = 1; j < cols; j++) {
                if(matrix.get(i).get(j) == 1) {
                    pos[i][j] = pos[i][j-1] + pos[i-1][j];
                }
            }
        }

        return pos[rows-1][cols-1];
    }

    public static void main(String[] args) {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(1, 1, 0));
        matrix.add(Arrays.asList(1, 1, 1) );
        matrix.add(Arrays.asList(0, 1, 1));
        System.out.println(numberOfPaths(matrix));
    }


}
