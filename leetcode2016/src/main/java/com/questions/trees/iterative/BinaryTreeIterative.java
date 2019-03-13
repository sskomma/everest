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
    if (root == null) {
      return 0;
    }
    int maxDepth = Integer.MIN_VALUE;
    Deque<Pair<TreeNode, Integer>> stack = new LinkedList<>();
    stack.push(Pair.of(root, 1));
    while (!stack.isEmpty()) {
      Pair<TreeNode, Integer> pair = stack.pop();
      TreeNode node = pair.getLeft();
      int currentDistance = pair.getRight();
      if (isLeaf(node)) {
        maxDepth = Integer.max(maxDepth, currentDistance);
      }
      if (node.left != null) {
        stack.push(Pair.of(node.left, currentDistance + 1));
      }
      if (node.right != null) {
        stack.push(Pair.of(node.right, currentDistance + 1));
      }
    }
    return maxDepth;
  }

  /**
   * Method to find the kth smallest element in binary search tree.
   * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
   */
  public static int kthSmallest(TreeNode root, int k) {
    if (root == null || k < 0) {
      return -1;
    }

    return 0;
  }

  private static boolean isLeaf(TreeNode node) {
    return node.left == null && node.right == null;
  }

  /**
   * Given a binary tree, return the zigzag level order traversal of its nodes' values.
   * (ie, from left to right, then right to left for the next level and alternate between).
   *
   * For example:
   * Given binary tree [3,9,20,null,null,15,7],
   *     3
   *    / \
   *   9  20
   *     /  \
   *    15   7
   * return its zigzag level order traversal as:
   * [
   *   [3],
   *   [20,9],
   *   [15,7]
   * ]
   * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
   * @param root root node of the tree
   * @return list of levels, in zigzag.
   */
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    if (root == null) {
      return Collections.emptyList();
    }
    Deque<TreeNode> currentLevel = new LinkedList<>();
    currentLevel.offer(root);
    boolean zigZag = true;
    Deque<TreeNode> nextLevel = new LinkedList<>();
    List<List<Integer>> levels = new ArrayList<>();
    Deque<Integer> currLevelNumbers = new LinkedList<>();

    while (!currentLevel.isEmpty()) {
      TreeNode currNode = currentLevel.poll();
      currLevelNumbers.offer(currNode.val);
      if (currNode.left != null) nextLevel.offer(currNode.left);
      if (currNode.right != null) nextLevel.offer(currNode.right);

      if (currentLevel.isEmpty()) {
        currentLevel = nextLevel;
        nextLevel = new LinkedList<>();
        levels.add(readInDirection(currLevelNumbers, zigZag));
        zigZag = !zigZag;
      }
    }
    return levels;
  }

  private List<Integer> readInDirection(Deque<Integer> numbers, boolean lr) {
    List<Integer> levelElements = new ArrayList<>();
    while (!numbers.isEmpty()) {
      levelElements.add(lr ? numbers.poll(): numbers.pollLast());
    }
    return levelElements;
  }
}
