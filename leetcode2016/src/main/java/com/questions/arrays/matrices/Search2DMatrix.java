package com.questions.arrays.matrices;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * Example 1:
 *
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * Output: true
 * Example 2:
 *
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * Output: false
 *
 * https://leetcode.com/problems/search-a-2d-matrix/description/
 */
public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = 0;
        int numOfRows = matrix.length;

        //Identify row
        for(int i = 0; i < numOfRows; i++) {
            if(i < numOfRows-1 && matrix[i+1][0] > target) {
                row = i;
                break;
            }else if (i == numOfRows-1) {
                row = i;
            }
        }
        // search row
        return presentInRow(matrix, target, row);
    }

    private boolean presentInRow(int[][] matrix, int target, int row) {
        System.out.println("row: " + row);
        if(row < 0)
            row = 0;
        for(int j = 0; j<matrix[row].length; j++) {
            if(matrix[row][j] == target)
                return true;
        }
        return false;
    }
}
