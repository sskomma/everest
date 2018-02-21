package com.questions.arrays.matrices;

/**
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 *
 * Note:
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation
 *
 * https://leetcode.com/problems/rotate-image/description/
 * #leetcode48
 */
public class RotateImage {

  public static void main(String[] args) {
    int[][] input = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    System.out.println("Input:");
    MatrixUtils.printMatrix(input, 3);
    System.out.println("Rotating Clockwise:");
    rotateClockWise(input);
    MatrixUtils.printMatrix(input, 3);
    System.out.println("Rotate anti clockwise:");
    rotateAntiClockWise(input);
    MatrixUtils.printMatrix(input,3);

  }

  public static void rotateClockWise(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return;
    }
    int row = matrix.length;
    int col = matrix[0].length;

    //Move up to down
    for (int i = 0; i <= row / 2; i++) {
      for (int j = 0; j < col; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[row - i - 1][j];
        matrix[row - i - 1][j] = temp;
      }
    }

    // Switch Symetrically
    for (int i = 0; i < row; i++) {
      for (int j = i; j < col; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
      }
    }
  }

  public static void rotateAntiClockWise(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return;
    }
    int row = matrix.length;
    int col = matrix[0].length;

    //Move left to right
    for (int j = 0; j <= col / 2; j++) {
      for (int i = 0; i < row; i++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[i][col - j - 1];
        matrix[i][col - j - 1] = temp;
      }
    }

    // Switch Symetrically
    for (int i = 0; i < row; i++) {
      for (int j = i; j < col; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
      }
    }
  }
}
