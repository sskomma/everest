package com.questions.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description: A Class that represents a binary tree.
 * With implementations of common use cases encountered with it. 
 *
 * @author Ram Komma
 */
public class BinaryTree {
  protected TreeNode root;
  protected boolean allowDuplicates;

  private static void printWhitespaces(int count) {
    for (int i = 0; i < count; i++) {
      System.out.print(" ");
    }
  }

  /**
   * Method traverses the tree in levels and returns a list of each level from top to bottom..
   * where each level is a list of all elements in that level from left to right.
   * https://leetcode.com/problems/binary-tree-level-order-traversal/
   *
   * @return
   */
  public List<List<Integer>> levelOrderTraversal() {
    List<List<Integer>> levelOrderOfTree = new ArrayList<List<Integer>>();
    return levelOrderTraversal(Collections.singletonList(root), levelOrderOfTree);
  }

  private List<List<Integer>> levelOrderTraversal(
      List<TreeNode> nodes, List<List<Integer>> levelOrderOfTree) {

    if (nodes == null || nodes.isEmpty()) {
      return levelOrderOfTree;
    }
    List<Integer> currentLevelNumbers = new ArrayList<Integer>();
    List<TreeNode> nextLevel = new ArrayList<TreeNode>();

    for (TreeNode node : nodes) {
      currentLevelNumbers.add(node.val);
      if (node.left != null) {
        nextLevel.add(node.left);
      }
      if (node.right != null) {
        nextLevel.add(node.right);
      }
    }
    levelOrderOfTree.add(currentLevelNumbers);
    return levelOrderTraversal(nextLevel, levelOrderOfTree);
  }

  /**
   * Method traverses the tree in levels and returns a list of each level from bottom to top..
   * where each level is a list of all elements in that level from left to right.
   * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
   *
   * @param root
   * @return
   */
  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    return levelOrderBottom(Collections.singletonList(root));
  }

  private List<List<Integer>> levelOrderBottom(List<TreeNode> nodes) {

    if (nodes == null || nodes.isEmpty()) {
      return null;
    }
    List<Integer> currentLevelNumbers = new ArrayList<Integer>();
    List<TreeNode> nextLevel = new ArrayList<TreeNode>();

    for (TreeNode node : nodes) {
      if (node != null) {
        currentLevelNumbers.add(node.val);
        if (node.left != null) {
          nextLevel.add(node.left);
        }
        if (node.right != null) {
          nextLevel.add(node.right);
        }
      }
    }
    List<List<Integer>> levelOrderOfTree = levelOrderBottom(nextLevel);
    if (levelOrderOfTree == null) {
      levelOrderOfTree = new ArrayList<List<Integer>>();
    }
    levelOrderOfTree.add(currentLevelNumbers);

    return levelOrderOfTree;
  }

  /**
   * Given a binary tree, return all root-to-leaf paths.
   * For example, given the following binary tree:
   *    1
   *  /   \
   * 2     3
   *  \
   *    5
   *    All root-to-leaf paths are: ["1->2->5", "1->3"]
   *
   * Description: https://leetcode.com/problems/binary-tree-paths/
   * @return List of strings
   */
  public List<String> binaryTreePaths() {
    List<String> paths = new ArrayList<String>();
    int[] path = new int[1000];
    binaryTreePaths(root, path, 0, paths);
    return paths;
  }

  private void binaryTreePaths(TreeNode node, int[] path, int pathLength, List<String> paths) {
    if (node == null) {
      return;
    }
    path[pathLength] = node.val;
    if (node.left == null && node.right == null) {
      paths.add(printArrayAsPath(path, pathLength));
    } else {
      pathLength++;
      binaryTreePaths(node.left, path, pathLength, paths);
      binaryTreePaths(node.right, path, pathLength, paths);
    }
  }

  private String printArrayAsPath(int[] path, int pathLength) {
    StringBuffer pathString = new StringBuffer();
    for (int i = 0; i <= pathLength; i++) {
      pathString.append(path[i]);
      if (i != pathLength) {
        pathString.append("->");
      }
    }
    return pathString.toString();
  }

  /**
   * Method to find if there exists a leaf node, to which if traversed from root adds up to given sum..
   * https://leetcode.com/problems/path-sum/
   *
   * @param sum, the sum of all elements from root to leaf.
   * @return true, if a leaf node exists, such that sum of all elements in its path from root equals sum;
   * <br/>false, otherwise
   */
  public boolean hasPathSum(int sum) {
    return hasPathSum(root, 0, sum);
  }

  private boolean hasPathSum(TreeNode node, int actualSum, int sum) {
    if (node == null) {
      return false;
    }
    actualSum = actualSum + node.val;
    if (node.left == null && node.right == null) {
      return actualSum == sum;
    }
    return hasPathSum(node.left, actualSum, sum) || hasPathSum(node.right, actualSum, sum);
  }

  /**
   * Method tells the maximum height of the tree.
   *
   * @return height, returns the height of the tree <br/>
   *  -1 = Root node is null. <br/>
   *   0 = Root is the only tree node present in the tree. <br/>
   *   n = Number of levels in tree, with out counting root node level.
   */
  public int heightOfTree() {
    if (root == null) {
      return -1;
    }
    return heightOfTree(root) - 1;
  }

  private int heightOfTree(TreeNode node) {
    if (node == null) {
      return 0;
    }
    return Math.max(heightOfTree(node.left), heightOfTree(node.right)) + 1;
  }

  /**To print a tree in a tree format.
   *
   */
  public void printTree() {
    int height = heightOfTree();
    if (height < 0) {
      System.out.println("Empty tree!");
      return;
    }
    printTree(Collections.singletonList(root), 0, height);
  }

  private void printTree(List<TreeNode> nodes, int level, int height) {
    List<TreeNode> nextLevel = new ArrayList<TreeNode>();

    int floor = height - level;
    //multiplied by 2 and subtracted by 2 because, each node in tree is printed to take 2 units of space.
    //to be be updated to 3, if nodes in tree take 3 letter spaces to print each node.
    int firstSpaces = (int) Math.pow(2, (floor)) * 2 - 2;
    int betweenSpaces = (int) Math.pow(2, (floor + 1)) * 2 - 2;

    printWhitespaces(firstSpaces);
    for (TreeNode node : nodes) {
      if (node == null) {
        System.out.print("__");
        nextLevel.add(null);
        nextLevel.add(null);
      } else {
        System.out.format("%02d", node.val);
        nextLevel.add(node.left);
        nextLevel.add(node.right);
      }
      printWhitespaces(betweenSpaces);
    }
    System.out.println("");
    if (height >= ++level) {
      printTree(nextLevel, level, height);
    }
  }

  /**
   * Method to identify the maximum depth of a tree.
   * https://leetcode.com/problems/maximum-depth-of-binary-tree/
   *
   * @return height, maximum height of the tree.
   */
  public int maxDepth() {
    return maxDepth(root);
  }

  private int maxDepth(TreeNode node) {
    if (node == null) {
      return 0;
    }
    return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
  }

  /**
   * Method to identify the minimum depth of a tree.
   * https://leetcode.com/problems/minimum-depth-of-binary-tree/
   *
   * @return height, minimum height of the tree.
   */
  public int minDepth() {
    return minDepth(root);
  }

  private int minDepth(TreeNode node) {
    if (node == null) {
      return 0;
    }
    int l = minDepth(node.left);
    int r = minDepth(node.right);
    if (l == 0 || r == 0) {
      return l + r + 1;
    }
    return Math.min(l, r) + 1;
  }

  /**
   * Invert a binary tree.
   *      4
   *    /   \
   *   2     7
   *  / \   / \
   * 1   3 6   9
   *
   *      to
   *      4
   *    /   \
   *   7     2
   *  / \   / \
   * 9   6 3   1
   *
   * https://leetcode.com/problems/invert-binary-tree/<br>
   * @param root
   * @return
   */
  public TreeNode invertTree(TreeNode root) {
    if (root != null) {
      invertTree(root.left);
      invertTree(root.right);
      TreeNode temp = root.right;
      root.right = root.left;
      root.left = temp;
    }
    return root;
  }

  /**
   * Given two binary trees and imagine that when you put one of them to cover the other,
   * some nodes of the two trees are overlapped while the others are not. You need to merge them into a new binary tree.
   * The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node.
   * Otherwise, the NOT null node will be used as the node of new tree.
   *
   * Example 1:
   * Input:
   * Tree 1                     Tree 2
   *     1                         2
   *    / \                       / \
   *   3   2                     1   3
   *  /                           \   \
   * 5                             4   7
   *
   * Output:
   * Merged tree:
   *     3
   *    / \
   *   4   5
   *  / \   \
   * 5   4   7
   *
   * Note: The merging process must start from the root nodes of both trees.
   * @param oneT
   * @param twoT
   * @return
   */
  public TreeNode mergeTrees(TreeNode oneT, TreeNode twoT) {
    if (oneT == null && twoT == null) {
      return null;
    } else if (oneT == null) {
      return twoT;
    } else if (twoT == null) {
      return oneT;
    }
    TreeNode mergeT = new TreeNode(oneT.val + twoT.val);
    mergeT.left = mergeTrees(oneT.left, twoT.left);
    mergeT.right = mergeTrees(oneT.right, twoT.right);
    return mergeT;
  }

  /**
   * https://leetcode.com/problems/same-tree/description/
   * Given two binary trees, write a function to check if they are the same or not.
   * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
   *
   * @param p root node of first tree to compare.
   * @param q root node of second tree to compare.
   * @return True, if both the trees are same; false otherwise.
   */
  public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) {
      return true;
    } else if (p == null || q == null) {
      return false;
    }

    if (p.val == q.val) {
      return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    return false;
  }

  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> elements = new ArrayList<Integer>();
    return preorderTraversal(root, elements);
  }

  public List<Integer> preorderTraversal(TreeNode node, List<Integer> elements) {
    if (node != null) {
      elements.add(node.val);
      preorderTraversal(node.left, elements);
      preorderTraversal(node.right, elements);
    }
    return elements;
  }
}
