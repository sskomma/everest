package com.questions.arrays;

/** Description: http://www.ardendertat.com/2011/09/27/programming-interview-questions-4-find-missing-element/
 *
 * Given two array, find single missing element in second array. 
 * Assumptions: The second array B is a subset of A. And B is missing only single element.  
 *
 * @author Ram Komma
 *
 */
public class FindMissingNumber {

  /**Find the single element that's missing in second array
   *
   * @param a, First array, that has all original elements.
   * @param b, Second array, that has a single missing element from first array.
   * @return the element that's missing in second array.
   * @throws IllegalStateException, when more than one element is missing from second array.
   */
  public static int findMissingElements(int[] a, int[] b) throws IllegalStateException {
    if ((a == null && b == null) || (a.length == 0 && b.length == 0)) {
      return Integer.MIN_VALUE;
    }
    if (a.length - 1 != b.length) {
      throw new IllegalStateException("There is more than one element missing in second array");
    }
    int xor = 0;
    for (int i : a) {
      xor = xor ^ i;
    }
    for (int j : b) {
      xor = xor ^ j;
    }
    return xor;
  }

  public static void main(String[] args) {
    int[] a = {4, 5, 6};
    int[] b = {4};

    System.out.println(findMissingElements(a, b));
  }
}
