package com.ctci.tree;

public class FirstCommonAncestor {
  public static Result lca(TreeNode root, TreeNode n1, TreeNode n2) {
    if (root == null) {
      return null;
    } else if (root.data == n1.data || root.data == n2.data) {
      return new Result(root, false);
    } else {
      Result left = lca(root.left, n1, n2);
      if (left != null && left.isAncestor) {
        return left;
      }
      Result right = lca(root.right, n1, n2);
      if (right != null && right.isAncestor) {
        return right;
      }

      if (left != null && right != null) {
        return new Result(root, true);
      } else if (left == null) {
        return right;
      } else {
        return left;
      }
    }
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(2);
    TreeNode n11 = new TreeNode(7);
    TreeNode n12 = new TreeNode(5);
    TreeNode n21 = new TreeNode(22);
    TreeNode n22 = new TreeNode(6);
    TreeNode n23 = new TreeNode(3);
    TreeNode n24 = new TreeNode(62);
    TreeNode n31 = new TreeNode(51);
    TreeNode n32 = new TreeNode(81);
    TreeNode n33 = new TreeNode(4);
    TreeNode n34 = new TreeNode(9);
    TreeNode n35 = new TreeNode(8);
    TreeNode n36 = new TreeNode(41);
    TreeNode n37 = new TreeNode(5);
    TreeNode n38 = new TreeNode(89);

    root.left = n11;
    root.right = n12;

    n11.left = n21;
    n11.right = n22;
    n12.left = n23;
    n12.right = n24;

    n21.left = n31;
    n21.right = n32;
    n22.left = n33;
    n22.right = n34;
    n23.left = n35;
    n23.right = n36;
    n24.left = n37;
    n24.right = n38;
    BTreePrinter.printNode(root);
    System.out.println(n33.data);
    System.out.println(n38.data);
    System.out.println(lca(root, n33, n38).node.data);
  }

  public static class Result {
    TreeNode node;
    boolean isAncestor;

    Result(TreeNode n, boolean isAncestor) {
      node = n;
      this.isAncestor = isAncestor;
    }
  }
}
