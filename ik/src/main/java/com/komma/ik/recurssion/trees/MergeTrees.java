package com.komma.ik.recurssion.trees;

import com.komma.ik.common.TreeNode;

public class MergeTrees {


    public static TreeNode mergeTwoBST(TreeNode root1, TreeNode root2) {
        // right shift trees.
        TreeNode new_root1 = flatten_in_order(root1).left_ptr;
        TreeNode new_root2 = flatten_in_order(root2).left_ptr;

        // merge two linked lists
        TreeNode mergedNode = mergeTreeNodeOnRight(new_root1, new_root2);
        int count = countBST(mergedNode);

        // re-balance tree
        TreeNode[] root = new TreeNode[1];
        root[0] = mergedNode;
        return rebalance(root, 0, count -1);
    }

    private static TreeNode flatten_in_order(TreeNode root) {
        if(root == null) return null;

        // dto is a transport datapoint. left_ptr is used to indicate head of subtree, and right_ptr for tail of subtree.
        TreeNode dto = new TreeNode(-1);
        if (root.left_ptr == null && root.right_ptr == null) {
            dto.left_ptr = root;
            dto.right_ptr = root;
            return dto;
        }
        TreeNode lDto = flatten_in_order(root.left_ptr);
        TreeNode rDto = flatten_in_order(root.right_ptr);

        if(lDto != null) {
            dto.left_ptr = lDto.left_ptr;
            // Attach root to tail of left subtree
            lDto.right_ptr.right_ptr = root;
        } else {
            dto.left_ptr = root;
        }
        root.left_ptr = null;
        if(rDto != null) {
            // attach right dto's head to root's right
            root.right_ptr = rDto.left_ptr;
            dto.right_ptr = rDto.right_ptr;
        } else {
            dto.right_ptr = root;
        }

        return dto;
    }

    private static TreeNode mergeTreeNodeOnRight(TreeNode root1, TreeNode root2) {
        TreeNode myHead = new TreeNode(-1);
        TreeNode p = myHead;
        TreeNode p1 = root1;
        TreeNode p2 = root2;

        while (p1 != null && p2 != null) {
            if(p1.label < p2.label) {
                p.right_ptr = p1;
                p1 = p1.right_ptr;
            } else {
                p.right_ptr = p2;
                p2 = p2.right_ptr;
            }
            p = p.right_ptr;
        }
        if(p1 != null) {
            p.right_ptr = p1;
        }
        if(p2 != null) {
            p.right_ptr = p2;
        }
        return myHead.right_ptr;
    }

    private static TreeNode rebalance(TreeNode[] root, int low, int high) {
        if (root[0] == null || low > high) {
            return null;
        }
        int mid = (low + high) / 2;
        TreeNode left = rebalance(root, low, mid-1);
        TreeNode head = root[0];
        root[0] = root[0].right_ptr;

        TreeNode right = rebalance(root, mid+1, high);
        head.left_ptr = left;
        head.right_ptr = right;
        return head;

    }

    private static int countBST(TreeNode root) {
        TreeNode current = root;
        int count = 0;
        while(current != null) {
            current = current.right_ptr;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(5);
        root1.left_ptr = new TreeNode(3);
        root1.left_ptr.left_ptr = new TreeNode(2);
        root1.left_ptr.right_ptr = new TreeNode(4);
        root1.right_ptr = new TreeNode(6);
        root1.right_ptr.right_ptr = new TreeNode(7);

        TreeNode root2 = new TreeNode(8);
        root2.left_ptr = new TreeNode(1);
        root2.right_ptr = new TreeNode(9);
        TreeNode final_root = mergeTwoBST(root1, root2);

        System.out.println(final_root);
    }

}
