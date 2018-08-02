package com.questions.trees;

public class BSTCloser {
  public static void main(String[] args) {

  }

  public double closestValue(TreeNode root, double target) {
    double a = root.val;
    TreeNode kid = target < a ? root.left : root.right;
    if (kid == null) {
      return a;
    }
    double b = closestValue(kid, target);
    return Math.abs(a - target) < Math.abs(b - target) ? a : b;
  }

  static class TreeNode {
    public double val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(double x) {
      val = x;
    }

    public String toString() {
      return Double.toString(val);
    }
  }
}
