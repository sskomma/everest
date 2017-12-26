package com.ctci.tree;

public class MinimalTree {

  public static TreeNode minimalTree(int[] array, int start, int end) {
    if (start < 0 || start > end || end > array.length) {
      return null;
    }
    int r = (start + end) / 2;
    TreeNode root = new TreeNode(array[r]);
    root.left = minimalTree(array, start, r - 1);
    root.right = minimalTree(array, r + 1, end);
    return root;
  }

  public static TreeNode minimalTree(int[] array) {
    return minimalTree(array, 0, array.length - 1);
  }

  public static void main(String[] args) {
    int[] array = {1, 2, 3, 4, 5, 6, 7};
    BTreePrinter.printNode(minimalTree(array));
  }
}
