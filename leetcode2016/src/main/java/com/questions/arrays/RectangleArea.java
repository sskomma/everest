package com.questions.arrays;

import java.util.Arrays;

public class RectangleArea {

  public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {

    if (!validRectangle(A, B, C, D)) {
      return areaOfRectangle(E, F, G, H);
    }
    else if (!validRectangle(E, F, G, H)) {
      return areaOfRectangle(A, B, C, D);
    }
    int[] Y = {B, D, F, H};
    int[] X = {A, C, E, G};
    Arrays.sort(Y);
    Arrays.sort(X);
    return (X[2] - X[1]) * (Y[2] - Y[1]);
  }

  private static boolean validRectangle(int A, int B, int C, int D) {
    if (A == 0 && B == 0 && C == 0 && D == 0) {
      return false;
    }
    return true;
  }

  private static int areaOfRectangle(int A, int B, int C, int D) {
    return (D - B) * (C - A);
  }
}
