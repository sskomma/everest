package com.komma.ik.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FloodFill {


    static ArrayList<ArrayList<Integer>> flood_fill(Integer pixel_row, Integer pixel_column, Integer new_color, ArrayList<ArrayList<Integer>> image) {
        // Write your code here.
        if(image == null || image.isEmpty()) {
            return image;
        }

        dfs_flood_fill(pixel_row, pixel_column, image.get(pixel_row).get(pixel_column), new_color, image);
        return image;
    }

    private static void dfs_flood_fill(Integer row, Integer col, Integer old_color, Integer new_color, ArrayList<ArrayList<Integer>> image) {
        if (image.get(row).get(col).equals(old_color)) {
            return;
        }

        image.get(row).set(col, new_color);

        List<List<Integer>> neighbours = getNeighbors(image, row, col);

        for (List<Integer> ngb : neighbours) {
            if (image.get(ngb.get(0)).get(ngb.get(1)).equals(old_color)) {
                dfs_flood_fill(ngb.get(0), ngb.get(1), old_color, new_color, image);
            }
        }
    }

    private static List<List<Integer>> getNeighbors(ArrayList<ArrayList<Integer>> image, int i, int j) {
        List<List<Integer>> neighbours = new ArrayList<>();
        int row_size = image.size();
        int col_size = image.get(0).size();
        if( i-1 >= 0) neighbours.add(Arrays.asList(i-1, j));
        if( i+1 < row_size) neighbours.add(Arrays.asList(i+1, j));
        if( j-1 >= 0) neighbours.add(Arrays.asList(i, j-1));
        if( j+1 < col_size) neighbours.add(Arrays.asList(i, j+1));
        return neighbours;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> image = new ArrayList<>();
        image.add(new ArrayList<>(Arrays.asList(7, 7, 7, 7, 7, 7)));
        System.out.println(flood_fill(0, 4, 7, image));
    }

}
