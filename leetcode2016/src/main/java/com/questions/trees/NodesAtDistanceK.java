package com.questions.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import org.apache.commons.lang3.tuple.Pair;

/**
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/
 */
public class NodesAtDistanceK {

  public static void main(String[] args) {
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(5);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(6);
    root.left.right = new TreeNode(2);
    root.left.right.left = new TreeNode(7);
    root.left.right.right = new TreeNode(4);
    root.right.left = new TreeNode(0);
    root.right.right = new TreeNode(8);
/*    TreeNode root = new TreeNode(0);
    root.left = new TreeNode(1);
    root.left.left = new TreeNode(3);
    root.left.right = new TreeNode(2);*/
    System.out.println(new NodesAtDistanceK().distanceK(root, root.left, 2));
  }

  public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
    if(K == 0)
      return Collections.singletonList(target.val);
    Map<TreeNode, Set<TreeNode>> adjMap = new HashMap<>();
    List<Integer> nodesAtK = new ArrayList<>();
    constructAdjacencyMap(root, null, adjMap);
    Set<Integer> visited = new HashSet<>();
    visited.add(target.val);

    Deque<Pair<TreeNode, Integer>> stack = new LinkedList<>();
    for (TreeNode node : adjMap.get(target)) {
      stack.push(new Pair<>(node, K-1));
    }

    while (!stack.isEmpty()) {
      Pair<TreeNode, Integer> pair = stack.pop();
      TreeNode node = pair.getLeft();
      Integer currentDistance = pair.getRight();
      if(visited.contains(node.val)) {
        continue;
      }
      visited.add(node.val);
      if (currentDistance == 0) {
        nodesAtK.add(node.val);
      } else {
        for(TreeNode adj: adjMap.get(node)) {
          if(visited.contains(adj.val))
            continue;
          stack.push(new Pair<>(adj, currentDistance -1));
        }
      }
    }
    return nodesAtK;
  }

  private void constructAdjacencyMap(
      TreeNode node, TreeNode parent, Map<TreeNode, Set<TreeNode>> adjMap) {
    if (node == null || adjMap.containsKey(node)) {
      return;
    }

    Set<TreeNode> adjSet = new HashSet<>();
    adjMap.put(node, adjSet);
    if (parent != null) {
      adjSet.add(parent);
    }
    if (node.left != null) {
      adjSet.add(node.left);
      constructAdjacencyMap(node.left, node, adjMap);
    }
    if (node.right != null) {
      adjSet.add(node.right);
      constructAdjacencyMap(node.right, node, adjMap);
    }
  }


  class Pair<X, Y> {
    X left;
    Y right;

    public Pair(X x, Y y) {
      this.left = x;
      this.right = y;
    }

/*    public <X, Y>Pair<X, Y> of(X x, Y y) {
      return new Pair(x, y);
    }*/
    public X getLeft() {
      return left;
    }

    public void setLeft(X left) {
      this.left = left;
    }

    public Y getRight() {
      return right;
    }

    public void setRight(Y right) {
      this.right = right;
    }
  }
}
