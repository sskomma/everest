package com.questions.arrays;

public class ArrayUtils {

  public static String printArrayFromNtoOne(int[] a) {
    if (a == null || a.length == 0) {
      return "";
    }
    StringBuffer sb = new StringBuffer();
    for (int i = a.length - 1; i >= 0; i--) {
      sb.append(a[i]);
      if (i != a.length - 1) {
        sb.append(", ");
      }
    }
    return sb.toString();
  }

  public static String printArrayFromOneToN(int[] a) {
    if (a == null || a.length == 0) {
      return "";
    }
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < a.length; i++) {
      sb.append(a[i]);
      if (i != a.length - 1) {
        sb.append(", ");
      }
    }
    return sb.toString();
  }
}
