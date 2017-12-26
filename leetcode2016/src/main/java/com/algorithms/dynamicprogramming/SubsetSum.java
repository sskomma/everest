package com.algorithms.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/**From a given set of numbers, identify sub set of numbers that form a given total value.   
 * https://www.youtube.com/watch?v=s6FhG--P7z0&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=6
 *
 * @author Ram Komma
 *
 */
public class SubsetSum {

  public static boolean subsetSumElements(int sum, int[] set) {
    if (sum == 0 || set == null || set.length == 0) {
      return false;
    }
    boolean temp[][] = new boolean[set.length + 1][sum + 1];
    //Initialization: For 0 total its always true
    for (int i = 0; i <= set.length; i++) {
      temp[i][0] = true;
    }
    for (int j = 0; j <= sum; j++) {
      temp[0][j] = false;
    }

    for (int i = 1; i <= set.length; i++) {
      for (int temp_tot = 1; temp_tot <= sum; temp_tot++) {
        if (i == 0) {
          temp[0][temp_tot] = (set[i - 1] == temp_tot);
        } else {
          if (set[i - 1] < temp_tot) {
            temp[i][temp_tot] = temp[i - 1][temp_tot] || temp[i - 1][temp_tot - set[i - 1]];
          } else if (set[i - 1] == temp_tot) {
            temp[i][temp_tot] = true;
          } else {
            temp[i][temp_tot] = temp[i - 1][temp_tot];
          }
        }
      }
    }
    //Print out subset of elements that make total
    List<Integer> subSet = new ArrayList<Integer>();
    int i = set.length;
    int j = sum;
    while (i > 0 && j > 0) {
      if (temp[i][j] && temp[i - 1][j]) {
        i--;
      } else if (temp[i][j]) {
        subSet.add(set[i - 1]);
        j = j - set[i - 1];
        i--;
      } else {
        break;
      }
    }
    for (int n : subSet) {
      System.out.print(n + " ");
    }
    System.out.println("");
    return temp[set.length][sum];
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    int[] set = {2, 3, 7, 8, 10};
    int total = 11;
    System.out.println(subsetSumElements(total, set));
  }
}
