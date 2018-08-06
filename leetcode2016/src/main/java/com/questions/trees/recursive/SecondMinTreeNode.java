package com.questions.trees.recursive;

import com.questions.trees.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class SecondMinTreeNode {
  /**
   * Given a non-empty special binary tree consisting of nodes with the non-negative value,
   * where each node in this tree has exactly two or zero sub-node.
   * If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.
   *
   * Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
   *
   * If no such second minimum value exists, output -1 instead.
   *
   * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/solution/
   *
   * @param root root node of the tree.
   * @return value of the second least node.
   */
  public int secondMinimumNode(TreeNode root) {
    if (root == null) {
      return -1;
    }

    int minNode = root.val;
    int secondMin = Integer.MAX_VALUE;
    Deque<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      TreeNode node = queue.pop();
      if (node.val > minNode) {
        secondMin = Math.min(secondMin, node.val);
      }
      if (node.left != null) {
        queue.offer(node.left);
        queue.offer(node.right);
      }
    }
    return secondMin == Integer.MAX_VALUE ? -1: secondMin;
  }
}
