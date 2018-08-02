package com.questions.trees;

public class TreeNode {
  public int val;
  public TreeNode left;
  public TreeNode right;

  public TreeNode(int x) {
    val = x;
  }

  public String toString() {
    return Integer.toString(val);
  }
}