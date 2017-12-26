package com.ctci.tree;

public class BTreeSuccessor {
  public static TreeNode getSuccessor(TreeNode node) {
    if (node == null) {
      return null;
    }
    if (node.right != null) {
      return node.right;
    } else if (node.parent != null && node.parent.left == node) {
      return node.parent;
    } else {
      while (node.parent != null && node.parent.right == node) {
        node = node.parent;
      }
      return node.parent;
    }
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(50);
    TreeNode t1 = new TreeNode(40);
    TreeNode t2 = new TreeNode(30);
    TreeNode t3 = new TreeNode(20);
    TreeNode t4 = new TreeNode(10);
    TreeNode t5 = new TreeNode(11);

    root.left = t1;

    t1.parent = root;
    t1.left = t3;
    t1.right = t2;

    t3.parent = t1;

    t2.parent = t1;
    t2.right = t4;

    t4.parent = t2;
    t4.right = t5;

    t5.parent = t4;

    BTreePrinter.printNode(root);
    TreeNode successor = getSuccessor(t5);
    System.out.println(successor != null ? successor.data : null);
  }
}
