package com.questions.trees.iterative;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/
 */
public class TreeRightPointers {

  /**
   * This solution uses extra space. Checkout Version 2 of this for optimized space.
   * @param root
   */
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
   *Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
   *
   * Initially, all next pointers are set to NULL.
   *
   * Note:
   *
   * * You may only use constant extra space.
   * * Recursive approach is fine, implicit stack space does not count as extra space for this problem.
   * * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
   *
   * Example:
   * Given the following perfect binary tree,
   *
   *      1
   *    /  \
   *   2    3
   *  / \  / \
   * 4  5  6  7
   * After calling your function, the tree should look like:
   *
   *      1 -> NULL
   *    /  \
   *   2 -> 3 -> NULL
   *  / \  / \
   * 4->5->6->7 -> NULL
   *
   * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
   * @param root, root node of the tree.
   */
  public void connectV2(TreeLinkNode root) {
    if(root == null)
      return;
    TreeLinkNode traverser = root;
    TreeLinkNode nxtLevel;
    while (traverser.left != null) {
      nxtLevel = traverser.left;
      while (traverser != null) {
        traverser.left.next = traverser.right;
        traverser.right.next = traverser.next == null ? null: traverser.next.left;
        traverser = traverser.next;
      }
      traverser = nxtLevel;
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
