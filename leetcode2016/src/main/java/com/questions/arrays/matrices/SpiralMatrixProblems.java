package com.questions.arrays.matrices;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrixProblems {

  /**
   * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
   *
   * For example,
   * Given the following matrix:
   *[
   * [ 1, 2, 3 ],
   * [ 4, 5, 6 ],
   * [ 7, 8, 9 ]
   *]
   *
   * You should return [1,2,3,6,9,8,7,4,5]
   * https://leetcode.com/problems/spiral-matrix/
   *
   * @author Ram Komma
   *
   */
  public static List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> spiralOrder = new ArrayList<>();
    if (matrix == null) {
      return null;
    }
    if (matrix.length == 0 && matrix[0].length == 0) {
      return spiralOrder;
    }
    int left = 0;
    int right = matrix[0].length - 1;
    int top = 0;
    int bottom = matrix.length - 1;
    while (left < right && top < bottom) {
      //Copy elements from top edge(row)- i.e elements column 1 to n.
      for (int i = left; i <= right; i++) {
        spiralOrder.add(matrix[top][i]);
      }
      top++;

      //copy contents from right edge(column) - i.e elements from row 1 to m
      for (int i = top; i <= bottom; i++) {
        spiralOrder.add(matrix[i][right]);
      }
      right--;

      //Copy contents from bottom edge
      for (int i = right; i >= left; i--) {
        spiralOrder.add(matrix[bottom][i]);
      }
      bottom--;

      //Copy contents from left edge
      for (int i = bottom; i >= top; i--) {
        spiralOrder.add(matrix[i][left]);
      }
      left++;
    }


    return spiralOrder;
  }

  public static List<Integer> spiralOrderII(int[][] matrix) {
    List<Integer> spiralOrder = null;
    if (matrix != null) {
      spiralOrder = new ArrayList<Integer>();
      int rows = matrix.length;
      if (rows == 0) {
        return spiralOrder;
      }
      int columns = matrix[0].length;
      int m = rows - 1;
      int n = columns - 1;
      //is a vector matrix
      if (rows == 1 && columns != 1) {
        for (int i = 0; i <= n; i++) {
          spiralOrder.add(matrix[0][i]);
        }
      } else if (rows != 1 && columns == 1) {
        for (int i = 0; i <= m; i++) {
          spiralOrder.add(matrix[i][0]);
        }
      } else if (rows != 0 && columns != 0) {
        int depth = (int) Math.ceil(Math.min((double) m, (double) n) / (double) 2);

        for (int d = 0; d <= depth; d++) {
          //Copy elements from top edge(row)- i.e elements column 1 to n.
          for (int i = d; i < n - d; i++) {
            spiralOrder.add(matrix[d][i]);
          }

          //copy contents from right edge(column) - i.e elements from row 1 to m
          for (int i = d; i < m - d; i++) {
            spiralOrder.add(matrix[i][n - d]);
          }

          //Copy contents from bottom edge
          for (int i = d; i < n - d; i++) {
            spiralOrder.add(matrix[m - d][n - i]);
          }

          for (int i = d; i < m - d; i++) {
            spiralOrder.add(matrix[m - i][d]);
          }
        }

        //Print 1 x 1 matrix either in itself or inside a odd x odd matrix
        if (rows == columns && columns % 2 != 0) {
          int index = (int) Math.ceil((double) n / (double) 2);
          spiralOrder.add(matrix[index][index]);
        }
      }
    }
    return spiralOrder;
  }

  /**
   * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
   *
   * Example:
   * Input: 3
   * Output:
   * [
   *  [ 1, 2, 3 ],
   *  [ 8, 9, 4 ],
   *  [ 7, 6, 5 ]
   * ]
   *
   * https://leetcode.com/problems/spiral-matrix-ii/description/
   */
  public static int[][] generateSpiralMatrix(int n) {
    if (n < 1) {
      return null;
    }
    int runner = 1;
    int left = 0, right = n - 1;
    int top = 0, bottom = n - 1;
    int[][] result = new int[n][n];

    if (n % 2 != 0) {
      result[n / 2][n / 2] = n * n;
    }

    while (top < bottom && left < right) {

      for (int i = left; i <= right; i++) {
        result[top][i] = runner++;
      }
      top++;

      for (int i = top; i <= bottom; i++) {
        result[i][right] = runner++;
      }
      right--;

      for (int i = right; i >= left; i--) {
        result[bottom][i] = runner++;
      }
      bottom--;

      for (int i = bottom; i >= top; i--) {
        result[i][left] = runner++;
      }
      left++;
    }
    return result;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    //int m = 5, n = 3;
    //int[][] matrix = new int[m][n];
    //
    //for (int i = 0; i < m; i++) {
    //  int rowField = i * 10;
    //  for (int j = 0; j < n; j++) {
    //    matrix[i][j] = rowField + j;
    //  }
    //}
    //
    //MatrixUtils.printMatrix(matrix, m, n);
    //System.out.println("Elements spiraling from outer layer to inner layer");
    //for (int i : spiralOrder(matrix)) {
    //  System.out.print(i + ",");
    //}
    MatrixUtils.printMatrix(generateSpiralMatrix(5), 5, 5);
  }
}
