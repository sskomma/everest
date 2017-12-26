package com.questions.arrays.matrices;

import java.util.HashMap;
import java.util.Map;

/**Description: http://www.ardendertat.com/2011/09/20/programming-interview-questions-2-matrix-region-sum/
 *
 * Given an Matrix of number, compute the sum of a give square of numbers.
 *
 * @author Ram Komma
 *
 */

public class MatrixRegionProblem {
  private int[][] matrix = null;
  private Map<Box, Integer> sumOfBoxes = null;

  public MatrixRegionProblem() {
    matrix = new int[][] {
        {70, 37, 23, 57, 27, 22, 90, 99, 22, 59},
        {47, 63, 33, 1, 42, 46, 6, 70, 98, 93},
        {36, 62, 50, 21, 92, 27, 60, 29, 15, 34},
        {53, 3, 88, 45, 57, 39, 83, 81, 79, 56},
        {28, 63, 89, 20, 47, 15, 84, 18, 82, 33},
        {26, 87, 11, 76, 79, 5, 94, 55, 73, 51},
        {17, 82, 86, 10, 96, 5, 42, 43, 51, 6},
        {44, 76, 51, 4, 15, 99, 52, 11, 70, 89},
        {66, 36, 92, 85, 50, 21, 72, 27, 52, 65},
        {60, 0, 67, 37, 59, 14, 33, 13, 36, 36}
    };
    MatrixUtils.printMatrix(matrix, 10);
    computeSumOfAllBoxesInMatrixWithZeroCorner(matrix);
    printSumsMap();
  }

  public static void main(String[] args) {
    MatrixRegionProblem m = new MatrixRegionProblem();
    Box b = m.new Box(2, 2, 7, 7);
    System.out.println("\n\nSum of Box" + b + "\n is");
    System.out.println(m.computeSumOfBox(b));
  }

  private void computeSumOfAllBoxesInMatrixWithZeroCorner(int[][] matrix) {
    sumOfBoxes = new HashMap<MatrixRegionProblem.Box, Integer>();
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        Box box = new Box(0, 0, i, j);
        int sum = getTopBox(i, j) + getLeftBox(i, j) - getInnerMatrix(i, j) + matrix[i][j];
        sumOfBoxes.put(box, sum);
      }
    }
  }

  private int getTopBox(int x2, int y2) {
    if (x2 == 0) {
      return 0;
    }
    return (Integer) sumOfBoxes.get(new Box(0, 0, x2 - 1, y2));
  }

  private int getLeftBox(int x2, int y2) {
    if (y2 == 0) {
      return 0;
    }
    return (Integer) sumOfBoxes.get(new Box(0, 0, x2, y2 - 1));
  }

  private int getInnerMatrix(int x2, int y2) {
    if (x2 == 0 || y2 == 0) {
      return 0;
    }
    x2 = x2 > 0 ? x2 - 1 : x2;
    y2 = y2 > 0 ? y2 - 1 : y2;
    return (Integer) sumOfBoxes.get(new Box(0, 0, x2, y2));
  }

  public int computeSumOfBox(Box b) {
    return sumOfBoxes.get(new Box(0, 0, b.x2, b.y2))
        - sumOfBoxes.get(new Box(0, 0, b.x1, b.y2))
        - sumOfBoxes.get(new Box(0, 0, b.x2, b.y1))
        + sumOfBoxes.get(new Box(0, 0, b.x1, b.y1));
  }

  public void printSumsMap() {
    for (Box b : sumOfBoxes.keySet()) {
      System.out.print(b + " :" + (Integer) sumOfBoxes.get(b) + "\n");
    }
  }

  public class Box {
    int x1, y1, x2, y2;

    public Box(int x1, int y1, int x2, int y2) {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
    }

    @Override
    public String toString() {
      return "Box:(x1, y1) = (" + x1 + "," + y1 + ")(x2, y2) = (" + x2 + "," + y2 + ")";
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + getOuterType().hashCode();
      result = prime * result + x1;
      result = prime * result + x2;
      result = prime * result + y1;
      result = prime * result + y2;
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      Box other = (Box) obj;
      if (!getOuterType().equals(other.getOuterType())) {
        return false;
      }
      if (x1 != other.x1) {
        return false;
      }
      if (x2 != other.x2) {
        return false;
      }
      if (y1 != other.y1) {
        return false;
      }
      if (y2 != other.y2) {
        return false;
      }
      return true;
    }

    private MatrixRegionProblem getOuterType() {
      return MatrixRegionProblem.this;
    }
  }
}
