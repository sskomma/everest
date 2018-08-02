package com.questions.trees;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/
 */
public class TreeRightPointers {

  public void connect(TreeLinkNode root) {
    if (root == null) {
      return;
    }
    Deque<TreeLinkNode> current = new LinkedList<>();
    current.add(root);
    Deque<TreeLinkNode> nextLevel = new LinkedList<>();
    while (!current.isEmpty()) {
      TreeLinkNode node = current.remove();
      node.next = current.peekFirst();
      if(node.left != null)
        nextLevel.add(node.left);
      if(node.right != null)
        nextLevel.add(node.right);
      if(current.isEmpty()) {
        Deque<TreeLinkNode> temp = current;
        current = nextLevel;
        nextLevel = temp;
      }
    }
  }

  /**
   * Definition for binary tree with next pointer.
   */
  class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;

    TreeLinkNode(int x) {
      val = x;
    }
  }
}
