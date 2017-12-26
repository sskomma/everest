package com.questions.arrays;

/** Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * https://leetcode.com/problems/container-with-most-water/
 * @author Ram Komma
 */
public class ContainerWithMostWater {

  public static int maxArea(int[] heights) {
    if (heights == null || heights.length < 2) {
      return 0;
    }
    int maxArea = Integer.MIN_VALUE;
    int rightPtr = 0;
    int leftPtr = heights.length - 1;
    while (leftPtr > rightPtr) {
      int rightEdge = heights[rightPtr];
      int leftEdge = heights[leftPtr];
      int containerHeight = Math.min(rightEdge, leftEdge);
      int area = (leftPtr - rightPtr) * containerHeight;
      maxArea = Math.max(maxArea, area);

      if (heights[rightPtr] <= heights[leftPtr]) {
        while (leftPtr > rightPtr && heights[rightPtr] <= rightEdge) {
          rightPtr++;
        }
      } else {
        while (leftPtr > rightPtr && heights[leftPtr] <= leftEdge) {
          leftPtr--;
        }
      }
    }
    return maxArea;
  }

  public static void main(String[] args) {
    int[] heights = {4, 2};
    System.out.println(maxArea(heights));
  }
}
