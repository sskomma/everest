package com.questions.sorting;

public class QuickSorting {
  /**Utility method to perform quick sort in an array of ints
   *
   * @param A, array to be sorted.
   * @return sorted array.
   */
  public static int[] quickSort(int[] A) {
    return quickSort(A, 0, A.length - 1);
  }

  private static int[] quickSort(int[] A, int startIndex, int endIndex) {
    int p = 0;
    if (startIndex < endIndex) {
      p = partition(A, startIndex, endIndex);
      quickSort(A, startIndex, p - 1);
      quickSort(A, p + 1, endIndex);
    }
    return A;
  }

  private static int partition(int[] A, int startIndex, int endIndex) {
    int pivot = A[endIndex];
    int pivotIndex = startIndex - 1;

    for (int i = startIndex; i <= endIndex - 1; i++) {
      if (A[i] <= pivot) {
        pivotIndex++;
        swapElementsInArray(A, pivotIndex, i);
      }
    }
    swapElementsInArray(A, ++pivotIndex, endIndex);
    return pivotIndex;
  }

  private static void swapElementsInArray(int[] A, int i, int j) {
    int temp = A[i];
    A[i] = A[j];
    A[j] = temp;
  }

  public static void main(String[] args) {
    int[] a = {6, 8, 4, 3, 9, 2, 5};
    for (int i : quickSort(a)) {
      System.out.print(i + " ");
    }
  }
}
