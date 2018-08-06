package com.questions.trees.iterative;

import com.questions.trees.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Given a binary tree, return all root-to-leaf paths.
 *
 * Note: A leaf is a node with no children.
 * https://leetcode.com/problems/binary-tree-paths/description/
 *
 * Input:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * Output: ["1->2->5", "1->3"]
 *
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 *
 */
public class BinaryTreePath {

  public static List<String> binaryTreePaths(TreeNode root) {
    if (root == null) {
      return Collections.emptyList();
    }
    List<String> paths = new ArrayList<>();
    Deque<Pair<TreeNode, String>> stack = new LinkedList<>();
    stack.push(Pair.of(root, ""));

    while (!stack.isEmpty()) {

      Pair<TreeNode, String> nodePair = stack.pop();
      TreeNode node = nodePair.getLeft();
      String depth = nodePair.getRight();
      if (node == null) {
        continue;
      }

      //Check if node is null
      if (node.left == null && node.right == null) {
        depth = depth + Integer.toString(node.val);
        paths.add(depth);
      } else {
        depth = depth + Integer.toString(node.val) + "->";
      }
      stack.push(Pair.of(node.left, depth));
      stack.push(Pair.of(node.right, depth));
    }
    return paths;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(5);
    root.right = new TreeNode(3);
    System.out.println(binaryTreePaths(root));
  }
}
