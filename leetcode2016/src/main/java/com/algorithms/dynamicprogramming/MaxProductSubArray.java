package com.algorithms.dynamicprogramming;

/**
 * Find the contiguous sub array within an array (containing at least one number) which has the largest product.
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 *
 * https://leetcode.com/problems/maximum-product-subarray/
 *
 * @author Ram Komma
 */
public class MaxProductSubArray {
  public static int maxSubarrayProduct(int arr[]) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    if (arr.length == 1) {
      return arr[0];
    }
    int n = arr.length;
    // max positive product at the current position.
    //Code written to make sure its >=0
    int maxProduct = 0;

    // min negative product at the current position
    //Code written to make sure its <=0
    int minProduct = 0;

    // Initialize overall max product
    int max_so_far = Integer.MIN_VALUE;

    for (int i = 0; i < n; i++) {
             /* If this element is positive, update maxProduct.
                 Update minProduct only if min_ending_here is
                 negative */
      if (arr[i] > 0) {
        maxProduct = Math.max(maxProduct * arr[i], arr[i]);
        minProduct = Math.min(minProduct * arr[i], 0);
      }
             /* If this element is 0, then the maximum product cannot
                end here, make both maxProduct and minProduct 0. */
      else if (arr[i] == 0) {
        maxProduct = 0;
        minProduct = 0;
      } else {
        int temp = maxProduct;
        maxProduct = Math.max(0, (minProduct == 0 ? arr[i] : minProduct * arr[i]));
        minProduct = Math.min(temp * arr[i], arr[i]);
      }
      max_so_far = Math.max(max_so_far, maxProduct);
    }
    return max_so_far;
  }

  public static void main(String[] args) {
    //int[] nums = {2, -5, -2, -4, 3};
    int[] nums = {-2, -1};

    System.out.println("Maximum sub array product is: " + maxSubarrayProduct(nums));
  }
}
