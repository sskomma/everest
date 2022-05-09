package com.komma.ik.recurssion.trees;

public class DeleteTreeNodeBST {
        public TreeNode deleteNode(TreeNode root, int key) {
            TreeNode dummy = new TreeNode(Integer.MAX_VALUE);
            dummy.left = root;
            searchAndDelete(dummy, root, key);
            return dummy.left;
        }

        public TreeNode searchAndDelete(TreeNode parent, TreeNode root, int key) {
            if(root == null) {
                return null;
            }
            if(root.val > key) {
                return searchAndDelete(root, root.left, key);
            } else if(root.val < key) {
                return searchAndDelete(root, root.right, key);
            }
            //root.val == key
            return deleteRecurssion(parent, root);
        }

        public TreeNode deleteRecurssion(TreeNode parent, TreeNode root) {
            // Case 1: No children
            if(root.left == null && root.right == null) {
                if(parent.val < root.val) {
                    parent.right = null;
                } else {
                    parent.left = null;
                }
                return root;
            }
            // Case 2: One child
            if(root.left == null || root.right == null) {
                if(parent.val < root.val) {
                    parent.right =
                        root.left == null ? root.right: root.left;
                } else {
                    parent.left =
                        root.left == null ? root.right: root.left;
                }
                return root;
            }
            // Case 3: Two Children
            // Find the left child of right subtree.
            TreeNode parentPrime = root;
            TreeNode nav = root.left;
            while(nav.right != null) {
                parentPrime = nav;
                nav = nav.right;
            }
            int val = nav.val;
            TreeNode result = deleteRecurssion(parentPrime, nav);
            root.val = val;
            return result;
        }

}
