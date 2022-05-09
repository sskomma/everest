package com.komma.ik.common;

public class TreeNode {

    public int label;
    public TreeNode left_ptr;
    public TreeNode right_ptr;

    public TreeNode() {
        this.left_ptr = null;
        this.right_ptr = null;
    }

    public TreeNode(int label) {
        this.label = label;
        this.left_ptr = null;
        this.right_ptr = null;
    }
}
