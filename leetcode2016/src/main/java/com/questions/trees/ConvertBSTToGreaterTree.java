package com.questions.trees;

/**
 * #LeetCode538 #amazon
 * https://leetcode.com/problems/convert-bst-to-greater-tree/description/
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original
 * BST is changed to the original key plus sum of all keys greater than the original key in BST.
 */
public class ConvertBSTToGreaterTree {
  int sum = 0;

  public static void main(String[] args) {

    TreeNode root = new TreeNode(2);
    root.left = new TreeNode(0);
    root.left.left = new TreeNode(-4);
    root.left.right = new TreeNode(1);
    root.right = new TreeNode(3);
    new ConvertBSTToGreaterTree().convertBST(root);
  }

  public TreeNode convertBST(TreeNode root) {
    convert(root);
    return root;
  }

  public void convert(TreeNode root) {

    if (root == null) {
      return;
    }
    convert(root.right);
    root.val = root.val + sum;
    sum = root.val;
    System.out.println(root.val);
    convert(root.left);
  }
}
