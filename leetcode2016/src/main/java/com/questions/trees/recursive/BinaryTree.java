package com.questions.trees.recursive;

import com.questions.trees.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Description: A Class that represents a binary tree.
 * With implementations of common use cases encountered with it. 
 *
 * @author Ram Komma
 */
public class BinaryTree {
  protected TreeNode root;
  protected boolean allowDuplicates;

  public BinaryTree(TreeNode root) {
    this.root = root;
  }

  private static void printWhitespaces(int count) {
    for (int i = 0; i < count; i++) {
      System.out.print(" ");
    }
  }

  /**
   * https://leetcode.com/problems/construct-string-from-binary-tree/description/
   * #leetcode606
   *
   * You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.
   * The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't
   * affect the one-to-one mapping relationship between the string and the original binary tree.
   *
   *
   * Input: Binary tree: [1,2,3,4]
   *        1
   *      /   \
   *     2     3
   *    /
   *   4
   *
   * Output: "1(2(4))(3)"
   *
   * Explanation: Originally it needs to be "1(2(4)())(3()())",
   * but you need to omit all the unnecessary empty parenthesis pairs.
   * And it will be "1(2(4))(3)".
   */
  public static String tree2str(TreeNode root) {
    if (root == null) {
      return "";
    }
    String treeString = String.valueOf(root.val);
    String leftTreeStr = tree2str(root.left);
    String rightTreeStr = tree2str(root.right);
    if (leftTreeStr.isEmpty() && rightTreeStr.isEmpty()) {
      return treeString;
    } else if (leftTreeStr.isEmpty()) {
      treeString = treeString + "()" + "(" + rightTreeStr + ")";
    } else if (rightTreeStr.isEmpty()) {
      treeString = treeString + "(" + leftTreeStr + ")";
    } else {
      treeString = treeString + "(" + leftTreeStr + ")" + "(" + rightTreeStr + ")";
    }
    return treeString;
  }

  /**
   * You need to construct a binary tree from a string consisting of parenthesis and integers.
   * The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis.
   * The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.
   * You always start to construct the left child node of the parent first if it exists.
   *
   * Input: "4(2(3)(1))(6(5))"
   * Output: return the tree root node representing the following tree:
   *        4
   *      /   \
   *     2     6
   *    / \   /
   *   3   1 5
   *
   * https://leetcode.com/problems/construct-binary-tree-from-string/description/
   * #leetcode536
   *
   * @param treeStr
   * @return
   */
  public static TreeNode str2tree(String treeStr) {
    if (treeStr == null || treeStr.isEmpty()) {
      return null;
    }
    //RegEx to parse root node and children
    Pattern p = Pattern.compile("^(-?\\d*)(.*)$");
    Matcher m = p.matcher(treeStr);
    if (!m.matches()) {
      return null;
    }
    TreeNode root = new TreeNode(Integer.parseInt(m.group(1)));
    List<String> subTrees = getTreeString(m.group(2));
    if (subTrees.size() > 0 && !subTrees.get(0).isEmpty()) {
      root.left = str2tree(subTrees.get(0));
    }
    if (subTrees.size() > 1 && !subTrees.get(1).isEmpty()) {
      root.right = str2tree(subTrees.get(1));
    }
    return root;
  }

  /**
   * A private helper method to retrieve tree strings of children.
   * Returns a list of children, from left to right.
   * @param treeStr
   * @return
   */
  private static List<String> getTreeString(String treeStr) {
    int count = 0;
    int startIndex = 0;
    int i = 0;
    List<String> subTrees = new ArrayList<>();
    for (; i < treeStr.length(); i++) {
      char c = treeStr.charAt(i);
      if (c == '(') {
        count++;
      } else if (c == ')') {
        count--;
      }

      if (count == 0) {
        String subTree = treeStr.substring(startIndex, i + 1);
        subTrees.add(subTree.substring(1, subTree.length() - 1));
        startIndex = i + 1;
      }
    }
    return subTrees;
  }

  /**
   * Method traverses the tree in levels and returns a list of each level from top to bottom..
   * where each level is a list of all elements in that level from left to right.
   *
   * https://leetcode.com/problems/binary-tree-level-order-traversal/
   * #leetcode102
   * @return
   */
  public List<List<Integer>> levelOrderTraversal() {
    List<List<Integer>> levelOrderOfTree = new ArrayList<>();
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
      levelOrderOfTree = new ArrayList<>();
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
    List<String> paths = new ArrayList<>();
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
   *
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
    List<Integer> elements = new ArrayList<>();
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

  /**
   * Given a binary tree where every node has a unique value, and a target key k,
   * find the value of the NEAREST leaf node to target k in the tree.
   * Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree.
   * Also, a node is called a leaf if it has no children.
   * In the following examples, the input tree is represented in flattened form row by row.
   * The actual root tree given will be a TreeNode object.
   *
   * HINT: NEAREST leaf, may not be a child. It can be leaf of a Sibling.
   *
   * https://leetcode.com/problems/closest-leaf-in-a-binary-tree/description/
   * #leetcode742
   * @param k, value of kth node.
   * @return, value in the leaf node closer to k. Returns -1, if not found.
   */
  // Use BFS to search for tree and then search for leaf node
  public int closerLeaf(int k) {
    if (root == null) {
      return -1;
    }
    //Search for node with value K.
    Deque<TreeNode> queue = new LinkedList<>();
    TreeNode kNode = null;
    queue.offer(root);
    while (!queue.isEmpty()) {
      TreeNode current = queue.poll();
      if (current == null) {
        continue;
      }
      if (current.val == k) {
        kNode = current;
        break;
      }
      queue.offer(current.left);
      queue.offer(current.right);
    }
    //Return -1 if not found.
    if (kNode == null) {
      return -1;
    }
    //Do BFS to get to the the first leaf node.
    queue = new LinkedList<>();
    queue.offer(kNode);
    while (!queue.isEmpty()) {
      TreeNode current = queue.poll();
      if (current != null) {
        if (current.left == null && current.right == null) {
          return current.val;
        }
        queue.offer(current.left);
        queue.offer(current.right);
      }
    }
    return -1;
  }

  /**
   * Given a binary tree, you need to compute the length of the diameter of the tree.
   * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
   * This path may or may not pass through the root.
   *
   * Example:
   * Given a binary tree
   *     1
   *    / \
   *   2   3
   *  / \
   * 4   5
   *
   * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3]
   * Note: The length of path between two nodes is represented by the number of edges between them.
   * @return the diameter of the tree.
   */
  public int diameterOfABinaryTree(TreeNode root) {
    return diameterOfBinaryTree(root, 0).getLeft();
  }

  /**
   * A private recursive method to support diameter calculation.
   * @param node Node for which diameter is to be calculated.
   * @param maxDiameter maximum diameter so far.
   * @return {@link Pair} with diameter to left and max height to right
   */
  private Pair<Integer, Integer> diameterOfBinaryTree(TreeNode node, int maxDiameter) {
    if (node == null) {
      return Pair.of(0, 0);
    }
    Pair<Integer, Integer> left = diameterOfBinaryTree(node.left, maxDiameter);
    Pair<Integer, Integer> right = diameterOfBinaryTree(node.right, maxDiameter);
    int diameterOfNode = left.getRight() + right.getRight() + 1;

    return Pair
        .of(Math.max(maxDiameter, diameterOfNode), Math.max(left.getRight(), right.getRight()) + 1);
  }
}
