package com.questions.arrays;

import static com.questions.arrays.ArrayUtils.printArrayFromNtoOne;

/**
 * Give two arrays. Treat each array as an number and add them..
 *
 * @author Ram Komma
 */
public class ArrayAddition {

  /**
   * This method takes in two arrays, and adds them together. Where the number is put together from 0 - n
   *
   * @param a, first array of single digit integers to be added to other array.
   * @param b, second array of single digit integers to be added to the other array.
   * @return an array of integers, resulted after addition of  input arrays.
   */
  public static int[] addArrays(int[] a, int[] b) {
    if ((a == null && b == null) || (a.length == 0 && b.length == 0)) {
      return null;
    } else if (a == null || a.length == 0) {
      return b;
    } else if (b == null || b.length == 0) {
      return a;
    }
    int[] sum = new int[Math.max(a.length, b.length) + 1];
    int carryFwd = 0, temp = 0, i = 0, j = 0;
    while (i < a.length && j < b.length) {
      temp = a[i] + b[j] + carryFwd;
      sum[i] = temp % 10;
      carryFwd = temp / 10;
      i++;
      j++;
    }
    while (j < b.length) {
      temp = b[j] + carryFwd;
      sum[i] = temp % 10;
      carryFwd = temp / 10;
      j++;
    }
    while (i < a.length) {
      temp = a[i] + carryFwd;
      sum[i] = temp % 10;
      carryFwd = temp / 10;
      i++;
    }
    if (carryFwd > 0) {
      sum[Math.max(i, j)] = carryFwd;
    }
    return sum;
  }

  public static void main(String[] args) {
    int[] a = {9, 3, 2, 8};
    int[] b = {1, 2, 3};
    System.out.println(
        "The sum of arrays a: " + printArrayFromNtoOne(a) + " and b: " + printArrayFromNtoOne(b));
    System.out.println(printArrayFromNtoOne(addArraysTake2(a, b)));
  }

  /**
   * This method takes in two arrays, and adds them together. Where the number is put together from n - 0
   * @param a, first array of single digit integers to be added to other array.
   * @param b, second array of single digit integers to be added to the other array.
   * @return an array of integers, resulted after addition of  input arrays.
   */
  public static int[] addArraysTake2(int[] a, int[] b) {
    if (a == null && b == null) {
      return null;
    } else if (a == null) {
      return b;
    } else if (b == null) {
      return a;
    }

    int s1 = a.length, s2 = b.length, carryForward = 0, i = 0;
    int size = Math.max(s1, s2) + 1;
    int[] sum = new int[size];

    for (; i < s1 && i < s2; i++) {
      int temp = a[s1 - 1 - i] + b[s2 - 1 - i] + carryForward;
      sum[size - 1 - i] = temp % 10;
      carryForward = temp / 10;
    }
    for (; i < s2; i++) {
      sum[size - 1 - i] = b[s2 - 1 - i] + carryForward;
      carryForward = 0;
    }
    for (; i < s1; i++) {
      sum[size - 1 - i] = a[s1 - 1 - i] + carryForward;
      carryForward = 0;
    }
    if (carryForward > 0) {
      sum[size - 1 - i] = carryForward;
    }

    return sum;
  }
}
