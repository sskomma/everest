package com.algorithms.dynamicprogramming;

public class MinCostClimbingStairs {
  /**
   * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
   * Once you pay the cost, you can either climb one or two steps.
   * You need to find minimum cost to reach the top of the floor,
   * and you can either start from the step with index 0, or the step with index 1.
   *
   * https://leetcode.com/problems/min-cost-climbing-stairs/
   * #leetcode746
   * @param cost
   * @return
   */
  public static int minCostClimbingStairs(int[] cost) {
    if (cost == null || cost.length == 0) {
      return -1;
    }

    int[] costTracker = new int[cost.length];
    int length = cost.length;
    costTracker[0] = cost[0];
    costTracker[1] = cost[1];

    for (int i = 2; i < length; i++) {
      costTracker[i] =
          Math.min(costTracker[i - 1], costTracker[i - 2]) + cost[i];
    }
    return Math.min(costTracker[length - 1], costTracker[length - 2]);
  }

  /**
   * You are climbing a stair case. It takes n steps to reach to the top.
   * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
   * Note: Given n will be a positive integer.
   *
   * https://leetcode.com/problems/climbing-stairs/description/
   * #leetcode70
   * @param n
   * @return
   */
  public static int climbingStairs(int n) {
    if (n <= 2) {
      return n;
    }
    int[] numOfSteps = new int[n + 1];
    numOfSteps[1] = 1;
    numOfSteps[2] = 2;
    for (int i = 3; i < n + 1; i++) {
      numOfSteps[i] = numOfSteps[i - 1] + numOfSteps[i - 2];
    }
    return numOfSteps[n];
  }

  public static void main(String[] args) {
    int[] costs = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
    //System.out.println(minCostClimbingStairs(costs));
    System.out.println(climbingStairs(5));
  }
}
