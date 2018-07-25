package com.questions.trees.iterative;

import com.questions.trees.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public class BinaryTreeIterative {

  /**
   * https://leetcode.com/problems/binary-tree-paths/description/
   *
   * @param root
   * @return
   */
  public List<String> binaryTreePaths(TreeNode root) {
    if (root == null) {
      return Collections.emptyList();
    }
    List<String> paths = new ArrayList<>();
    Deque<Pair<TreeNode, String>> stack = new LinkedList<>();
    stack.push(Pair.of(root, ""));

    while (!stack.isEmpty()) {
      Pair<TreeNode, String> pair = stack.pop();

      TreeNode node = pair.getLeft();
      String currentPath = pair.getRight() + "->" + node.val;
      if (isLeaf(node)) {
        paths.add(currentPath);
      }
      if (node.left != null) {
        stack.push(Pair.of(node.left, currentPath));
      }
      if (node.right != null) {
        stack.push(Pair.of(node.right, currentPath));
      }
    }
    return paths;
  }

  private boolean isLeaf(TreeNode node) {
    return node.left == null && node.right == null;
  }
}
