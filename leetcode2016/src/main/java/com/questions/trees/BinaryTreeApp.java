package com.questions.trees;

import com.questions.trees.iterative.BinaryTreeUtils;
import com.questions.trees.recursive.BinaryTree;

public class BinaryTreeApp {

  public static void main(String[] args) {
    testIsBst();
  }

  public static void testIsBst() {
    TreeNode root = new TreeNode(50);
    TreeNode L = new TreeNode(30);
    TreeNode R = new TreeNode(90);
    TreeNode LL = new TreeNode(20);
    TreeNode LR = new TreeNode(60);
    TreeNode RL = new TreeNode(70);
    TreeNode RR = new TreeNode(100);
    root.left = L;
    root.right = R;
    L.left = LL;
    L.right = LR;
    R.left = RL;
    R.right = RR;
    BinaryTree tree = new BinaryTree(root);
    tree.printTree();

    System.out.println("\nIs tree BST: " + BinaryTreeUtils.isBST(root));
  }
}
