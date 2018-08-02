package com.questions.trees;

import org.junit.Test;

public class BinaryTreeTest {

  @Test
  public void str2tree() {
    TreeNode root = BinaryTree.str2tree("-4(2(3)(1))(6(5)(7))");
    BinaryTree tree = new BinaryTree(root);
    tree.printTree();
  }
}