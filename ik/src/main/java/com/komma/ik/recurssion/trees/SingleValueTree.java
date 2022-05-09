package com.komma.ik.recurssion.trees;

public class SingleValueTree {

    private static class TreeNode{
        public int val;
        public TreeNode left_ptr;
        public TreeNode right_ptr;
        TreeNode (int val) {
            this.val = val;
        }
    }

    static int findSingleValueTrees(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int[] numOfUvalTree = new int[1];
        univalue_dfs(root, numOfUvalTree);
        return numOfUvalTree[0];
    }

    static Integer univalue_dfs(TreeNode root, int[] numOfUvalTree) {
        if(root.left_ptr == null && root.right_ptr == null) {
            numOfUvalTree[0] = numOfUvalTree[0]+1;
            return root.val;
        }

        boolean leftCheck = true;
        if(root.left_ptr != null)  {
            Integer leftUnivalue = univalue_dfs(root.left_ptr, numOfUvalTree);
            leftCheck = leftUnivalue != null && leftUnivalue.equals(root.val);
        }
        boolean rightCheck = true;
        if(root.right_ptr != null)  {
            Integer rightUnivalue = univalue_dfs(root.right_ptr, numOfUvalTree);
            rightCheck = rightUnivalue != null && rightUnivalue.equals(root.val);
        }
        if (leftCheck && rightCheck)  {
            numOfUvalTree[0] = numOfUvalTree[0]+1;
            return root.val;
        }
        return null;
    }

    public static void main(String[] args) {
        TreeNode leftChild = new TreeNode(5);
        leftChild.left_ptr = new TreeNode(5);
        leftChild.right_ptr = new TreeNode(5);

        TreeNode rightChild = new TreeNode(5);
        rightChild.right_ptr = new TreeNode(5);
        TreeNode root = new TreeNode(5);
        root.left_ptr = leftChild;
        root.right_ptr = rightChild;
        System.out.println(findSingleValueTrees(root));
    }

}
