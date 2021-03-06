package com.algorithms.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/** Description: To solve 0/1 KnapSack problem.
 *
 * That is, of the given items, identify the ones that needs to be picked to get maximum value. 
 * User can either pick or not pick an item into their knapsack. But can not take piece of it.
 * https://www.youtube.com/watch?v=8LusJS5-AGo&index=1&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr
 *
 * @author Ram Komma
 */
public class KnapsackProblem01 {

  /**
   * Method to solve KnapSack 1/0 problem. i.e.. Include or exclude a given item and not splitting it into pieces.
   *
   * @param weight, maximum weight knapsack can handle.
   * @param itemsWeight, weight of items in increasing order that can be put.
   * @param itemsValue, Value of items, positioned in array, in correspondence with weights array.
   * @return an array of weights that are to be included in knapsack to get maximum value.
   */
  public static List<Integer> knapSack01(int weight, int[] itemsWeight, int[] itemsValue) {
    int[][] temp = new int[itemsWeight.length + 1][weight + 1];
    int i = 0, j = 0;
    for (i = 1; i <= itemsWeight.length; i++) {
      for (j = 1; j <= weight; j++) {
        if (itemsWeight[i - 1] > j) {
          temp[i][j] = temp[i - 1][j];
        } else {
          //Value when including i th element.
          int including = itemsValue[i - 1] + temp[i - 1][j - itemsWeight[i - 1]];
          int excluding = temp[i - 1][j];
          temp[i][j] = Math.max(including, excluding);
        }
      }
    }
    i--;
    j--;
    System.out.println("Max Value in Kanpsack 0/1 is :" + temp[i][j]);
    //Grab items that are in knapsack.
    List<Integer> selectedItems = new ArrayList<>();
    while (i > 0 && j > 0 && temp[i][j] != 0) {
      //The i'th element is included in selected items
      if (temp[i][j] != temp[i - 1][j]) {
        selectedItems.add(itemsWeight[i - 1]);
        j = j - itemsWeight[i - 1];
      }
      i--;
    }
    for (Integer num : selectedItems) {
      System.out.print(num + " ");
    }

    return selectedItems;
  }

  public static void main(String[] args) {
    int[] itemsWeight = {1, 3, 4, 5};
    int[] itemsValue = {1, 4, 5, 7};
    knapSack01(7, itemsWeight, itemsValue);
  }
}
