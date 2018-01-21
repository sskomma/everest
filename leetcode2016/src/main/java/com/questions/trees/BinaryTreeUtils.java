package com.questions.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**A utilities class, that does various things with Binary trees and Binary search trees.  
 *
 * @author Ram Komma
 */
public class BinaryTreeUtils {
  /**Method to see if the given tree with the root node is a binary search tree or not.
   *
   * @param root, root node of a binary tree, which is to be determined as binary search tree or not.
   * @return boolean, true if it is a binary search tree; false, otherwise.
   */
  public static boolean isBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    List<Integer> sequence = new ArrayList<Integer>();
    sequence = inOrderTraversal(root, sequence);
    if (!sequence.isEmpty()) {
      int lastVisited = sequence.get(0);
      for (int i = 1; i < sequence.size(); i++) {
        if (lastVisited >= sequence.get(i)) {
          return false;
        }
        lastVisited = sequence.get(i);
      }
    }
    return true;
  }

  private static List<Integer> inOrderTraversal(TreeNode node, List<Integer> sequence) {
    if (node == null) {
      return sequence;
    }
    inOrderTraversal(node.left, sequence);
    sequence.add(node.val);
    inOrderTraversal(node.right, sequence);
    return sequence;
  }

  /**Method to convert a sorted array to balanced Binary search tree.
   * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
   *
   * @param numbers, array of sorted numbers of which tree to be constructed.
   * @return TreeNode, root node of the constructed Binary search tree.
   */
  public static TreeNode sortedArrayToBST(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
      return null;
    }
    TreeNode root = sortedArrayToBST(numbers, 0, numbers.length - 1);
    return root;
  }

  private static TreeNode sortedArrayToBST(int[] numbers, int begin, int end) {
    if (end < begin) {
      return null;
    }
    int currentElement = begin + (end - begin) / 2;
    TreeNode currentNode = new TreeNode(numbers[currentElement]);
    currentNode.left = sortedArrayToBST(numbers, begin, currentElement - 1);
    currentNode.right = sortedArrayToBST(numbers, currentElement + 1, end);
    return currentNode;
  }

  /** Method to find the least common ancestor in a Binary tree
   * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
   *
   * @param node, root node of the tree.
   * @param a, fist node of which common ancestor is to be found.
   * @param b, second node of which common ancestor is to be found.
   * @return TreeNode, least common ancestor of node a and node b.
   */
  public static TreeNode lowestCommonAncestorOfBinaryTree(TreeNode root, TreeNode a, TreeNode b) {
    assert (root != null && a != null && b != null);
    Stack<TreeNode> aStack = new Stack<TreeNode>();
    findNodeInTree(root, a, aStack);
    Stack<TreeNode> bStack = new Stack<TreeNode>();
    findNodeInTree(root, b, bStack);

    TreeNode lca = root;

    while (!aStack.isEmpty() && !bStack.isEmpty()) {
      if (aStack.peek().equals(bStack.peek())) {
        lca = aStack.pop();
        bStack.pop();
      } else {
        return lca;
      }
    }
    return lca;
  }

  public static boolean findNodeInTree(TreeNode node, TreeNode a, Stack<TreeNode> path) {
    if (node != null && a != null) {
      if (node.equals(a)) {
        path.push(a);
        return true;
      }
      if (findNodeInTree(node.left, a, path) || findNodeInTree(node.right, a, path)) {
        path.push(node);
        return true;
      }
    }
    return false;
  }

  public static TreeNode getRightMostNode(TreeNode node) {
    if (node == null) {
      return null;
    }
    if (node.right != null) {
      return getRightMostNode(node.right);
    }

    return node;
  }


  /**Method to flatten a tree to right heavy tree. For more details look at the link below.
   * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
   * @param node
   */
  public static void flattenBinaryTree(TreeNode node) {
    if (node == null) {
      return;
    }
    TreeNode leftSubTree = node.left;
    TreeNode rightSubTree = node.right;
    TreeNode rightMostInLeftSubTree = null;
    if (leftSubTree != null) {
      node.right = leftSubTree;
      node.left = null;
      flattenBinaryTree(leftSubTree);
      rightMostInLeftSubTree = getRightMostNode(leftSubTree);
    }
    if (rightMostInLeftSubTree != null) {
      rightMostInLeftSubTree.right = rightSubTree;
    }
    if (rightSubTree != null) {
      flattenBinaryTree(rightSubTree);
    }
  }

  /**Given a binary tree, imagine yourself standing on the right side of it,
   * return the values of the nodes you can see ordered from top to bottom.
   *
   * For example:
   * Given the following binary tree,
   *       1            <---
   *     /   \
   *    2     3         <---
   *     \     \
   *       5     4       <---
   * You should return [1, 3, 4].
   *
   *
   * https://leetcode.com/problems/binary-tree-right-side-view/
   * @param node
   * @return
   */
  public static List<Integer> rightSideView(TreeNode node) {
    if (node == null) {
      return Collections.emptyList();
    }
    List<TreeNode> nodes = new ArrayList<TreeNode>();
    nodes.add(node);
    List<Integer> rightElements = new ArrayList<Integer>();
    return rightSideView(nodes, rightElements);
  }

  private static List<Integer> rightSideView(List<TreeNode> nodes, List<Integer> rightElements) {
    if (nodes.isEmpty()) {
      return null;
    }
    int size = nodes.size();
    List<TreeNode> nextLevel = new ArrayList<TreeNode>();
    for (int i = 0; i < size; i++) {
      TreeNode n = nodes.get(i);
      if (n.left != null) {
        nextLevel.add(n.left);
      }
      if (n.right != null) {
        nextLevel.add(n.right);
      }
      if (i == size - 1) {
        rightElements.add(n.val);
      }
    }
    rightSideView(nextLevel, rightElements);
    return rightElements;
  }

  /** Method to find the least common ancestor in a Binary Search tree
   * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
   *
   * @param node, root node of the tree.
   * @param a, fist node of which common ancestor is to be found.
   * @param b, second node of which common ancestor is to be found.
   * @return TreeNode, least common ancestor of node a and node b.
   */
  public TreeNode lowestCommonAncestorOfBST(TreeNode node, TreeNode a, TreeNode b) {
    if (node == null || a == null || b == null) {
      return null;
    }
    if (a.val < node.val && b.val < node.val) {
      return lowestCommonAncestorOfBST(node.left, a, b);
    }
    if (a.val > node.val && b.val > node.val) {
      return lowestCommonAncestorOfBST(node.right, a, b);
    }
    if (a.val == node.val) {
      return a;
    }
    if (b.val == node.val) {
      return b;
    }
    return node;
  }

  public static void main(String[] args) {
    int[] numbers = {1, 2, 3, 4, 5, 8, 9, 10, 13, 14, 15, 16, 18, 19};
    BinarySearchTree bst = new BinarySearchTree(sortedArrayToBST(numbers));
    bst.printTree();
    TreeNode root = bst.getRoot();
    flattenBinaryTree(root);
    bst.printTree();
  }
}
 