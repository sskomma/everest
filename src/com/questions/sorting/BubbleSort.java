package com.questions.sorting;

public class BubbleSort {

  public static int[] bubbleSort(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
      return numbers;
    }

    for (int i = 0; i < numbers.length - 1; i++) {
      for (int j = i + 1; j < numbers.length; j++) {
        if (numbers[i] > numbers[j]) {
          int temp = numbers[i];
          numbers[i] = numbers[j];
          numbers[j] = temp;
        }
      }
    }
    return numbers;
  }

  public static void main(String[] args) {
    int[] a = {6, 8, 4, 3, 9, 2, 5};
    for (int i : bubbleSort(a)) {
      System.out.print(i + " ");
    }
  }
}
