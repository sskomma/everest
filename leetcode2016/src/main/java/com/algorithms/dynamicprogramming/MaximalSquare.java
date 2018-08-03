package com.algorithms.dynamicprogramming;

import com.questions.arrays.matrices.MatrixUtils;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square
 * containing only 1's and return its area.
 *
 * https://leetcode.com/problems/maximal-square/description/
 */
public class MaximalSquare {

  public static void main(String[] args) {
    char[][] matrix1 =
        {
            {'0', '1', '1', '1', '0'},
            {'1', '1', '1', '1', '0'},
            {'0', '1', '1', '1', '1'},
            {'0', '1', '1', '1', '1'},
            {'0', '0', '1', '1', '1'}
        };
    char[][] matrix2 =
        {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
    System.out.println(maximalSquare(matrix2));
  }

  public static int maximalSquare(char[][] matrix) {
    int rows = matrix.length;
    int cols = rows > 0 ? matrix[0].length : 0;
    int[][] dynamicArray = new int[rows + 1][cols + 1];

    int maxEdge = 0;
    for (int i = 1; i <= rows; i++) {
      for (int j = 1; j <= cols; j++) {
        if (matrix[i - 1][j - 1] == '1') {
          dynamicArray[i][j] = 1 + Math.min(
              dynamicArray[i - 1][j - 1], Math.min(dynamicArray[i][j - 1], dynamicArray[i - 1][j]));

          maxEdge = Math.max(maxEdge, dynamicArray[i][j]);
        }
      }
    }
    MatrixUtils.printMatrix(dynamicArray, dynamicArray.length);
    return maxEdge * maxEdge;
  }
}
