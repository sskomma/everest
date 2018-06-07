package com.questions.trees.recursive;

import com.questions.trees.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * A utilities class, that does various things with Binary trees and Binary search trees.
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
   * @param root, root node of the tree.
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
    List<TreeNode> nextLevel = new ArrayList<>();
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

  /**
   * #leetcode199
   * @param root
   * @return
   */
  public static List<Integer> rightSideViewII(TreeNode root) {
    if (root == null) {
      return Collections.EMPTY_LIST;
    }
    List<Integer> rightSide = new ArrayList<>();
    Deque<TreeNode> queueLevels = new LinkedList<>();
    queueLevels.add(root);
    Deque<TreeNode> queueOfElements = new LinkedList<>();

    while (!queueLevels.isEmpty()) {
      TreeNode node = queueLevels.remove();

      if (node.left != null) {
        queueOfElements.add(node.left);
      }
      if (node.right != null) {
        queueOfElements.add(node.right);
      }
      if (queueLevels.isEmpty()) {
        rightSide.add(node.val);
        Deque<TreeNode> temp = queueLevels;
        queueLevels = queueOfElements;
        queueOfElements = temp;
      }
    }
    return rightSide;
  }

  /**
   * https://leetcode.com/problems/boundary-of-binary-tree/description/
   * #leetcode545
   *
   * The following method fails because it uses one BFS traversal to pick both left and right boundaries.
   * In cases where there is only one element in a level, we do not have enough information to distinguish if
   * that node should belong to right boundary or left boundary..
   *
   * Simple solution:
   * https://leetcode.com/problems/boundary-of-binary-tree/discuss/101280/Java(12ms)-left-boundary-left-leaves-right-leaves-right-boundary
   * @param root
   * @return
   */
  public static List<Integer> boundaryOfBinaryTree(TreeNode root) {
    if (root == null) {
      return Collections.EMPTY_LIST;
    }
    List<TreeNode> left = new ArrayList<>();

    /*Elements on bottom side are collected into a stack, because DFS collects them in right to left order.
     But for boundary purposes, we need this in reverse order.*/
    Deque<TreeNode> leafsStack = new LinkedList<>();

    /*Elements on right side are collected into a stack, because BFS collects them in top - bottom order.
     But for boundary purposes, we need this in reverse order.*/
    Deque<TreeNode> rightStack = new LinkedList<>();

    Deque<TreeNode> currentLevelQ = new LinkedList<>();
    Deque<TreeNode> nextLevelQ = new LinkedList<>();

    // Perform BFS to collect left and right boundaries.
    currentLevelQ.add(root);
    left.add(root);
    while (!currentLevelQ.isEmpty()) {
      TreeNode node = currentLevelQ.remove();
      if (node.left != null) {
        nextLevelQ.add(node.left);
      }
      if (node.right != null) {
        nextLevelQ.add(node.right);
      }
      if (currentLevelQ.isEmpty()) {
        rightStack.push(node);
        if (nextLevelQ.size() > 1) {
          left.add(nextLevelQ.peek());
        }
        Deque<TreeNode> temp = currentLevelQ;
        currentLevelQ = nextLevelQ;
        nextLevelQ = temp;
      }
    }

    // Perform DFS to collect leaf nodes, since they can be spread across multiple levels.
    Deque<TreeNode> stackForDFS = new LinkedList<>();
    stackForDFS.add(root);
    while (!stackForDFS.isEmpty()) {
      TreeNode node = stackForDFS.pop();
      if (isLeafNode(node)) {
        leafsStack.push(node);
      }
      if (node.left != null) {
        stackForDFS.push(node.left);
      }
      if (node.right != null) {
        stackForDFS.push(node.right);
      }
    }

    //Remove last element of right, since it would be the root, which is already on left.
    if (!rightStack.isEmpty()) {
      rightStack.removeLast();
    }

    List<Integer> boundary = new ArrayList<>();
    boundary.addAll(left
        .stream()
        .filter(a -> !isLeafNode(a))
        .map(a -> a.val)
        .collect(Collectors.toList())
    );
    boundary.addAll(leafsStack
        .stream()
        .map(a -> a.val)
        .collect(Collectors.toList())
    );
    boundary.addAll(rightStack
        .stream()
        .filter(a -> !isLeafNode(a))
        .map(a -> a.val)
        .collect(Collectors.toList())
    );
    return boundary;
  }

  public static boolean isLeafNode(TreeNode node) {
    if (node == null) {
      return false;
    }
    return node.left == null && node.right == null;
  }

  /**
   * Given a binary tree, write a function to get the maximum width of the given tree.
   * The width of a tree is the maximum width among all levels.
   * The binary tree has the same structure as a full binary tree, but some nodes are null.
   * The width of one level is defined as the length between the end-nodes (the leftmost and right
   * most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.
   *
   * https://leetcode.com/problems/maximum-width-of-binary-tree/
   * #leetcode662
   * @param root
   * @return
   */
  public static int widthOfBinaryTree(TreeNode root) {
    if (root == null) {
      return 0;
    }
    Deque<TreeNode> currentLevel = new LinkedList<>();
    Deque<TreeNode> nextLevel = new LinkedList<>();
    currentLevel.add(root);
    int width = 1;
    while (!currentLevel.isEmpty()) {
      TreeNode node = currentLevel.remove();
      if (node != null) {
        nextLevel.add(node.left);
        nextLevel.add(node.right);
      } else {
        nextLevel.add(null);
        nextLevel.add(null);
      }

      if (currentLevel.isEmpty()) {
        while(!nextLevel.isEmpty()  && nextLevel.peekFirst() == null) {
          nextLevel.removeFirst();
        }
        while (!nextLevel.isEmpty()  && nextLevel.peekLast() == null) {
          nextLevel.removeLast();
        }
        width = Math.max(width, nextLevel.size());
        Deque<TreeNode> temp = currentLevel;
        currentLevel = nextLevel;
        nextLevel = temp;
      }
    }
    return width;
  }

  /**
   * A tree is "superbalanced" if the difference between the depths of any two leaf nodes
   * is no greater than one.
   * @param root root node of the binary tree.
   * @return True if super balanced: false otherwise.
   */
  public static boolean isSuperBalanced(TreeNode root) {
    if(root == null) {
      return false;
    }
    Set<Integer> heights = new HashSet<>(2);


  }

  private static int isSuperBalanced(TreeNode node, int length) {
    if(node == null )
  }

  public static void main(String[] args) {
    int[] numbers = {1, 2, 3, 4, 5, 8, 9, 10, 13, 14, 15, 16, 18, 19};
    int[] numbers2 = {1};
    BinarySearchTree bst = new BinarySearchTree(sortedArrayToBST(numbers));
    bst.printTree();
    System.out.println(widthOfBinaryTree(bst.root));
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
}
 