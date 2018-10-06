package com.questions.trees.iterative;

import com.questions.trees.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.lang3.tuple.Pair;

public class BinaryTreeIterative {

  /**
   *
   * https://leetcode.com/problems/binary-tree-paths/description/
   *
   * @param root root node of the tree.
   * @return {@link List} of Strings with paths.
   */
  public static List<String> binaryTreePaths(TreeNode root) {
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

  /**
   * Iterative approach of maxDepth problem.
   *
   * https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
   *
   * @param root, root node of the tree.
   * @return maxDepth of this tree.
   */
  public static int maxDepth(TreeNode root) {
    if(root == null)
      return 0;
    int maxDepth = Integer.MIN_VALUE;
    Deque<Pair<TreeNode, Integer>> stack = new LinkedList<>();
    stack.push(Pair.of(root,1));
    while (!stack.isEmpty()) {
      Pair<TreeNode, Integer> pair = stack.pop();
      TreeNode node = pair.getLeft();
      int currentDistance = pair.getRight();
      if(isLeaf(node)) {
        maxDepth = Integer.max(maxDepth, currentDistance);
      }
      if( node.left != null) {
        stack.push(Pair.of(node.left, currentDistance+1));
      }
      if(node.right != null) {
        stack.push(Pair.of(node.right, currentDistance+1));
      }
    }
    return maxDepth;
  }

  /**
   * Method to find the kth smallest element in binary search tree.
   * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
   */
  public static int kthSmallest(TreeNode root, int k) {
    if(root == null || k < 0)
      return -1;

    return 0;
  }



    private static boolean isLeaf(TreeNode node) {
    return node.left == null && node.right == null;
  }
}
