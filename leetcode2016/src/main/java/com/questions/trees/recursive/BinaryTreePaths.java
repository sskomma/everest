package com.questions.trees.recursive;

import com.questions.trees.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {

  public List<String> binaryTreePaths(TreeNode root) {
    List<String> result = new ArrayList<>();
    binaryTreePaths(root, new ArrayList<>(), 0, result);
    return result;
  }

  private void binaryTreePaths(TreeNode node, List<String> nodes, int l, List<String> result) {
    if (node == null) {
      return;
    }
    nodes.add(++l, String.valueOf(node.val));

    if (node.left == null && node.right == null) {
      result.add(String.join("->", nodes));
    }

    binaryTreePaths(node.left, nodes, l, result);
    while (nodes.size() > l) {
      nodes.remove(l + 1);
    }
    binaryTreePaths(node.right, nodes, l, result);
    while (nodes.size() > l) {
      nodes.remove(l + 1);
    }
  }

}
