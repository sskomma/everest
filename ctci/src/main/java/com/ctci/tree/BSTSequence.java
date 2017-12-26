package com.ctci.tree;

import java.util.ArrayList;
import java.util.List;

public class BSTSequence {
/*  public static List<ArrayList<Integer>> bstSequences(TreeNode root) {
    if(root == null) {
      return null;
    }
    ArrayList<Integer> input = new ArrayList<Integer>();
    ArrayList<Integer> right;
    ArrayList<Integer> left;
    if(root.right != null) {
      right = bstSequences(root.right);
    }
    if(root.left != null) {
      left = bstSequences(root.left);
    }

    //
    for(ArrayList<Integer> fromLeft: left) {
      for(ArrayList<Integer> fromRight: right){
        ArrayList<Integer> leftFirst = new ArrayList(root);
        leftFirst.addAll(fromRight);
        leftFirst.addAll(fromLeft);
        ArrayList<Integer> rightFirst = new ArrayList(root);
        rightFirst.addAll(fromLeft);
        rightFirst.addAll(fromRight);
        input.add(fromRight);
        input.add(fromLeft);
      }
    }
    //

    return input;

  }
  public static void main(String[] args) {

  }*/
}
