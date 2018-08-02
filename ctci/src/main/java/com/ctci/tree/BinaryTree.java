package com.ctci.tree;

public class BinaryTree {
  public static int checkBalancedTree(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = checkBalancedTree(root.left);
    if (left == Integer.MIN_VALUE) {
      return left;
    }
    int right = checkBalancedTree(root.right);
    if (right == Integer.MIN_VALUE) {
      return right;
    }

    if (Math.abs(left - right) > 1) {
      return Integer.MIN_VALUE;
    }
    return Math.max(left, right) + 1;
  }

  public static boolean isBST(TreeNode n) {
    return isBST(n, Integer.MIN_VALUE, Integer.MAX_VALUE);

  }
  private static boolean isBST(TreeNode n, int min, int max) {
    if(n == null) {
      return true;
    }

    if(n.data > min && n.data < max) {
      return isBST(n.left,min, n.data) && isBST(n.right,n.data, max);
    }
    return false;
  }

  public static void main(String[] args) {
    TreeNode tn1 = new TreeNode(50);
    TreeNode tn2 = new TreeNode(20);
    TreeNode tn3 = new TreeNode(60);
    TreeNode tn4 = new TreeNode(15);
    TreeNode tn5 = new TreeNode(26);
    TreeNode tn6 = new TreeNode(22);
    TreeNode tn7 = new TreeNode(35);

    tn1.left = tn2;
    tn1.right = tn3;

    tn2.left = tn4;
    tn2.right = tn5;

    tn5.left = tn6;
    tn5.right = tn7;
    System.out.println(isBST(tn1));
  }
}
