package com.questions.trees;

import java.util.Deque;
import java.util.LinkedList;

public class BSTIterator {

  Deque<TreeNode> stack;

  public BSTIterator(TreeNode root) {
    stack = new LinkedList<>();
    updateBuffer(root);
  }

  /** @return whether we have a next smallest number */
  public boolean hasNext() {
    return !stack.isEmpty();
  }

  /** @return the next smallest number */
  public int next() {
    if (hasNext()) {
      TreeNode next = stack.pop();
      updateBuffer(next.right);
      return next.val;
    }
    return -1;
  }

  private void updateBuffer(TreeNode root) {
    if (root == null) {
      return;
    }
    stack.push(root);
    updateBuffer(root.left);
  }

  public static void main(String[] args) {
    BinarySearchTree tree = new BinarySearchTree(8);
    tree.addToTree(3);
    tree.addToTree(10);
    tree.addToTree(1);
    tree.addToTree(6);
    tree.addToTree(4);
    tree.addToTree(7);
    tree.addToTree(14);
    tree.addToTree(13);
    tree.printTree();
    BSTIterator iterator = new BSTIterator(tree.root);
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */