package com.ctci.tree;

import java.util.Deque;
import java.util.LinkedList;

public class CheckSubTree {
  public static boolean isSubTree(TreeNode t1, TreeNode t2) {

    Deque<TreeNode> queue = new LinkedList<TreeNode>();
    queue.add(t1);
    while (!queue.isEmpty()) {
      TreeNode t = queue.remove();
      if (t.data == t2.data) {
        if(compareTrees(t, t2))
          return true;
      }
      if(t.left != null) {
        queue.add(t.left);
      }
      if(t.right != null) {
        queue.add(t.right);
      }
    }
    return false;
  }

  public static boolean compareTrees(TreeNode t1, TreeNode t2) {
    if (t1 == null && t2 == null) {
      return true;
    } else if (t1 == null || t2 == null) {
      return false;
    }

    return compareTrees(t1.left, t2.left) && (t1.data == t2.data) && compareTrees(
        t1.right, t2.right);
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

    TreeNode t2 = new TreeNode(4);
    TreeNode t21 = new TreeNode(6);
    TreeNode t22 = new TreeNode(8);
    t2.left = t21;
    t2.right = t22;
    System.out.println(isSubTree(tn1, t2));
  }
}
