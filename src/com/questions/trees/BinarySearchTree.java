package com.questions.trees;

import java.util.LinkedHashSet;
import java.util.Set;

/**Description: A Class that represents a binary search tree.
 * With implementations of common use cases encountered with it. 
 * 
 * @author Ram Komma
 *
 */
public class BinarySearchTree extends BinaryTree{
	
	public TreeNode getRoot(){return root;}
	
	/** Constructor to initialize a binary search tree. 
	 * 
	 * @param n, value of the root node.
	 * @param allowDuplicates, indicates if duplicate values are to be added to tree.
	 */
 	public BinarySearchTree(int n, boolean allowDuplicates)
	{
		root = new TreeNode(n);
		this.allowDuplicates = allowDuplicates;  
	}
	/** Constructor to initialize a binary search tree.
	 * By default, tree is configured to allow duplicate values into tree.  
	 * 
	 * @param n, value of the root node.
	 */
	public BinarySearchTree(int n){
		root = new TreeNode(n);
		allowDuplicates = true;
	}
	public BinarySearchTree(TreeNode n){
	    root = n;
	}
	
	/**To add a given value to tree.
	 * If the @param allowDuplicates is configured to true, a duplicated value is ignored and not added to tree. 
	 * Otherwise, a duplicate value is placed in the left sub-tree.
	 * 
	 * @param n, value of the new that gets added to tree. 
	 */
	public void addToTree(int n)
	{
		root = addToTree(root, new TreeNode(n));
	}
	private TreeNode addToTree(TreeNode root, TreeNode node)
	{
		if(root == null)
			return node;
		
		if(root.val < node.val)
			root.right = addToTree(root.right, node);
		else if(root.val > node.val)
			root.left = addToTree(root.left, node);
		else if(allowDuplicates)
			root.left = addToTree(root.left, node);
		return root;
	}
	
	/**Method deletes a node with given value from tree. In case of deleting a node with children, it replaces the node with 
	 * right most node of left subtree. In case of duplicates, it deletes only one node. 
	 * 
	 * @param n, Nodes with this value to be deleted from the tree. 
	 */
	public void removeNode(int n)
	{
		root = removeNode(root, n);
	} 
	private TreeNode removeNode(TreeNode node, int n)
	{
	    if(n > node.val)
	       node.right = removeNode(node.right, n);
	    else if (n<node.val)
	        node.left = removeNode(node.left, n);
	    else
	    {
	        //Node is a leaf node. 
	        if(node.left == null && node.right == null)
	            return null;
	        //Node has only right child
	        else if(node.left == null && node.right != null)
	            return node.right;
	        //Node has only left child
            else if(node.right == null && node.left != null)
                return node.left;
            else
            {
                TreeNode rightMostChildOfLeftSubTree = BinaryTreeUtils.getRightMostNode(node.left);
                node.val = rightMostChildOfLeftSubTree.val;
                node.left = removeNode(node.left, node.val);
            }
	    }
	    return node;
	}
	
	/**Method prints out tree node values, encountered while traversing tree inline.  
	 */
	public void inOrderTraversal()
	{
		inOrderTraversal(root);
	}
	private void inOrderTraversal(TreeNode node)
	{
		if(node == null)
			return;
		inOrderTraversal(node.left);
		System.out.print(node.val + "->");
		inOrderTraversal(node.right);
	}

	/**Method to see check, if the tree is a balanced tree or not. 
	 * https://leetcode.com/problems/balanced-binary-tree/
	 *  
	 * @return boolean, true if balanced, false otherwise. 
	 */
	public boolean isBalanced(){
		return isBalanced(root)!=Integer.MAX_VALUE;
	}
	private int isBalanced(TreeNode node)
	{
		if(node == null)
			return 0;
		int left = isBalanced(node.left);
		int right = isBalanced(node.right);
		int diff =  left - right;
		if(Math.pow(diff, 2) > 1 || left == Integer.MAX_VALUE || right == Integer.MAX_VALUE )
			return Integer.MAX_VALUE;
		
		return Math.max(left, right)+1;
	}

	/**Method to find the kth smallest element in binary search tree.
	 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
	 * 
	 * @param k, kth smallest element to be found in tree. 
	 * @return int, value of the kth smallest node. 
	 */
	public int kthSmallestElementInBST(int k)
	{
	    Set<Integer> setOfUnique  = kthSmallestElementInBST(root, k, new LinkedHashSet<Integer>());
	    if(setOfUnique.size() < k)
	        return -1;
	    else
	        return (Integer) setOfUnique.toArray()[k -1];
	}
	private Set<Integer> kthSmallestElementInBST(TreeNode node, int k, Set<Integer> setOfUnique)
	{
	    if( node == null)
	        return setOfUnique;
	    if(setOfUnique.size() < k){
	        setOfUnique = kthSmallestElementInBST(node.left, k, setOfUnique);
	        setOfUnique.add(node.val);
	        setOfUnique = kthSmallestElementInBST(node.right, k, setOfUnique);
	    }
	    return setOfUnique;
	}
		
	public static void main(String[] args)
	{
		BinarySearchTree tree = new BinarySearchTree(10);
		tree.addToTree(5);
		tree.addToTree(15);
		tree.addToTree(3);
		tree.addToTree(1);
		tree.addToTree(2);
		tree.addToTree(4);
	    tree.addToTree(4);
		tree.addToTree(7);
		tree.addToTree(6);
		tree.addToTree(9);
		tree.addToTree(8);
        tree.addToTree(13);
        tree.addToTree(14);
        tree.addToTree(11);
        tree.addToTree(12);
        tree.addToTree(18);
        tree.addToTree(16);
        tree.addToTree(19);		
		tree.printTree();
		for(String s:tree.binaryTreePaths())
		{
		    System.out.println(s);
		}

	}
}
