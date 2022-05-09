package com.komma.ik.recurssion.trees;

public class BalanceBST {

    public TreeNode balanceBST(TreeNode root) {
        if (root == null) {
            return root;
        }

        TreeNode head = leanRightInOrder(root).left;
        int count = count(head);
        TreeNode[] finalHead = new TreeNode[1];
        finalHead[0] = head;
        head = balance(finalHead, 0, count);
        return head;
    }

    private TreeNode balance(TreeNode[] root, int begin, int end) {
        if (root[0] == null || end < begin) {
            return null;
        }
        int mid = (end + begin) / 2;
        TreeNode l = balance(root, begin, mid - 1);
        TreeNode head = root[0];
        root[0] = root[0].right;
        TreeNode r = balance(root, mid + 1, end);
        head.left = l;
        head.right = r;
        return head;
    }

    private int count(TreeNode root) {
        int count = 0;
        while (root.right != null) {
            root = root.right;
            count++;
        }
        return count;
    }

    private TreeNode leanRightInOrder(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode dto = new TreeNode(-1);
        if (root.left == null && root.right == null) {
            dto.left = root;
            dto.right = root;
            return dto;
        }

        TreeNode ldto = leanRightInOrder(root.left);
        TreeNode rdto = leanRightInOrder(root.right);

        if (ldto != null) {
            dto.left = ldto.left;
            ldto.right.right = root;
        } else {
            dto.left = root;
        }

        root.left = null;

        if (rdto != null) {
            dto.right = rdto.right;
            root.right = rdto.left;
        } else {
            dto.right = root;
        }
        return dto;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4, null, null))));
        BalanceBST bbst = new BalanceBST();
        bbst.balanceBST(root);
    }




}
