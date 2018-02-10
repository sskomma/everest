package com.questions.arrays;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 *
 * This matrix has the following properties:
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *
 * For example,
 * Consider the following matrix:
 * [
 *  [1,   4,  7, 11, 15],
 *  [2,   5,  8, 12, 19],
 *  [3,   6,  9, 16, 22],
 *  [10, 13, 14, 17, 24],
 *  [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * Given target = 20, return false.
 */
public class SearchMatrix {
  public static boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0) {
      return false;
    }
    int m = matrix.length;
    int n = matrix[0].length;
    int i = m - 1, j = 0;
    while (i >= 0 && i < m && j >= 0 && j < n) {
      int element = matrix[i][j];
      if (element == target) {
        return true;
      } else if (element > target) {
        i--;
      } else {
        j++;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    int[][] matrix = {
        {1, 4, 7, 11, 15},
        {2, 5, 8, 12, 19},
        {3, 6, 9, 16, 22},
        {10, 13, 14, 17, 24},
        {18, 21, 23, 26, 30}
    };
    System.out.println(searchMatrix(matrix, 20));
  }
}
