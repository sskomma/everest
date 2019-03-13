package com.questions.trees;

/**
 * Solution: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/
 */
public class BTFromPreAndInOrder {

  public static void main(String[] args) {
    int[] preOrder = {3, 9, 20, 15, 7};
    int[] inOrder = {9, 3, 15, 20, 7};
    BTFromPreAndInOrder builder = new BTFromPreAndInOrder();
    TreeNode root = builder.buildTree(preOrder, inOrder);
  }

  public TreeNode buildTree(int[] preOrder, int[] inOrder) {
    return buildTree(preOrder, inOrder, 0, 0, inOrder.length - 1);
  }

  private TreeNode buildTree(int[] preOrder, int[] inOrder, int rootIndex, int treeBegin, int treeEnd) {

    if (treeBegin > treeEnd || rootIndex > treeEnd || rootIndex < treeBegin || treeEnd >= inOrder.length) {
      return null;
    }

    TreeNode rootNode = new TreeNode(preOrder[rootIndex]);

    int i = treeBegin;
    while (i < treeEnd) {
      if (inOrder[i] == preOrder[rootIndex]) {
        break;
      }
      i++;
    }
    rootNode.left = buildTree(preOrder, inOrder, rootIndex + 1, treeBegin, i);
    rootNode.right = buildTree(preOrder, inOrder, rootIndex + 2, i, treeEnd);
    return rootNode;
  }
}
