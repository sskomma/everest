package com.ctci.tree;

import com.ctci.linkedlist.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class ListOfDepths {

  public static List<Node> listOfLevels(TreeNode root) {
    return listOfLevels(Collections.singletonList(root), new ArrayList<Node>());
  }

  public static List<Node> listOfLevels(List<TreeNode> currentLevel, List<Node> linkedLists) {
    Node linkHead = new Node(0);
    Node traverser = linkHead;
    List<TreeNode> nextLevel = new ArrayList<TreeNode>();
    for (TreeNode tNode : currentLevel) {
      if (tNode != null) {
        traverser.next = new Node(tNode.data);
        traverser = traverser.next;
        nextLevel.add(tNode.left);
        nextLevel.add(tNode.right);
      }
    }
    linkedLists.add(linkHead.next);

    if (CollectionUtils.isNotEmpty(nextLevel)) {
      listOfLevels(nextLevel, linkedLists);
    }
    return linkedLists;
  }

  public static void main(String[] args) {
    TreeNode tn1 = new TreeNode(1);
    TreeNode tn2 = new TreeNode(2);
    TreeNode tn3 = new TreeNode(3);
    TreeNode tn4 = new TreeNode(4);
    TreeNode tn5 = new TreeNode(5);
    TreeNode tn6 = new TreeNode(6);
    TreeNode tn7 = new TreeNode(7);

    tn1.left = tn2;
    tn1.right = tn3;

    tn2.left = tn4;
    tn2.right = tn5;

    tn3.left = tn6;
    tn3.right = tn7;
    for(Node n: listOfLevels(tn1)) {
      Node.print(n);
      System.out.println();
    }
  }
}
