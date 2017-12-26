package com.questions.arrays.matrices;

public class MatrixUtils {
  public static void printMatrix(int[][] matrix, int length, int bredth) {
    for (int i = 0; i < length; i++) {
      System.out.print("|");
      for (int j = 0; j < bredth; j++) {
        System.out.format("%2d", matrix[i][j]);
        System.out.print("|");
      }
      System.out.println("");
    }
  }

  public static void printMatrix(int[][] matrix, int length) {
    printMatrix(matrix, length, length);
  }
}
