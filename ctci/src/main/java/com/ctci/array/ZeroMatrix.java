package com.ctci.array;

/**
 * Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
 * column are set to 0.
 */
public class ZeroMatrix {

  public static int[][] zeroMatrix(int[][] input, int n) {
    if (input == null || n <= 0) {
      throw new IllegalArgumentException();
    }
    boolean[] row = new boolean[n];
    boolean[] column = new boolean[n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (input[i][j] == 0) {
          row[i] = true;
          column[j] = true;
        }
      }
    }

    for (int i = 0; i < n; i++) {
      if (row[i]) {
        for (int j = 0; j < n; j++) {
          input[i][j] = 0;
        }
      }
    }

    for (int j = 0; j < n; j++) {
      if (column[j]) {
        for (int i = 0; i < n; i++) {
          input[i][j] = 0;
        }
      }
    }
    return input;
  }

  public static void printArray(int[][] input, int n) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(input[i][j]+", ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int[][] matrix = {
                      {1, 2, 3, 4},
                      {1, 2, 3, 4},
                      {1, 2, 3, 4},
                      {0, 2, 3, 4}
                      };
    printArray(zeroMatrix(matrix, 4), 4);
  }
}
