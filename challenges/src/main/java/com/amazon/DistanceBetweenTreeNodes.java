package com.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * Question:
 * Given a list of n unique integer, construct a BST by inserting each integer in the given order without rebalancing the tree.
 * Then find the distance between the two nodes, node1 and node2.
 * Return integer representing the distance between the two nodes or -1.
 */
public class DistanceBetweenTreeNodes {
  // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
  public int bstDistance(
      int[] values, int n,
      int node1, int node2) {
    if (values == null || values.length == 0) {
      return -1;
    }
    BST bst = new BST();
    for (Integer val : values) {
      bst.addToTree(val);
    }
    return bst.distanceBetweenNodes(node1, node2);
  }

  class BST {
    TreeNode root;
    Map<Integer, TreeNode> nodeIndex = new HashMap<>();

    void addToTree(int n) {
      TreeNode node = new TreeNode(n);
      nodeIndex.put(n, node);
      root = addToTree(root, node);
    }

    private TreeNode addToTree(TreeNode root, TreeNode node) {
      if (root == null) {
        return node;
      }

      if (root.val < node.val) {
        root.right = addToTree(root.right, node);
      } else if (root.val > node.val) {
        root.left = addToTree(root.left, node);
      }
      return root;
    }

    int distanceBetweenNodes(int node1, int node2) {
      TreeNode a = nodeIndex.get(node1);
      TreeNode b = nodeIndex.get(node2);
      if (a == null || b == null) {
        return -1;
      }
      TreeNode lca = lowestCommonAncestor(root, a, b);
      return distanceFromR(lca, a) + distanceFromR(lca, b);
    }

    TreeNode lowestCommonAncestor(TreeNode node, TreeNode a, TreeNode b) {
      if (node == null || a == null || b == null) {
        return null;
      }
      if (a.val < node.val && b.val < node.val) {
        return lowestCommonAncestor(node.left, a, b);
      }
      if (a.val > node.val && b.val > node.val) {
        return lowestCommonAncestor(node.right, a, b);
      }
      return node;
    }

    int distanceFromR(TreeNode root, TreeNode node) {
      if (root == null || root.val == node.val) {
        return 0;
      }
      int distance = node.val < root.val ?
          distanceFromR(root.left, node) :
          distanceFromR(root.right, node);
      return distance + 1;
    }
  }

  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) {
    int[] nums = {5, 6, 3, 1, 2, 4};
    DistanceBetweenTreeNodes dbt = new DistanceBetweenTreeNodes();
    dbt.bstDistance(nums, 6, 2, 4);
  }
  // METHOD SIGNATURE ENDS
}
