package com.questions.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**Description: A Class that represents a binary search tree.
 * With implementations of common use cases encountered with it. 
 * 
 * @author Ram Komma
 *
 */
public class BinarySearchTree {
	
	private TreeNode root;
	private boolean allowDuplicates;
	
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
                TreeNode rightMostChildOfLeftSubTree = getRightMostNode(node.left);
                node.val = rightMostChildOfLeftSubTree.val;
                node.left = removeNode(node.left, node.val);
            }
	    }
	    return node;
	}
	private TreeNode getRightMostNode(TreeNode node){
	    if(node == null)
	        return null;
	    if(node.right != null)
	        return getRightMostNode(node.right);

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
	
	/**Method traverses the tree in levels and returns a list of each level from top to bottom.. 
	 * where each level is a list of all elements in that level from left to right. 
	 * https://leetcode.com/problems/binary-tree-level-order-traversal/
	 * 
	 * @return 
	 */
	public List<List<Integer>> levelOrderTraversal(){
		List<List<Integer>> levelOrderOfTree = new ArrayList<List<Integer>>();
		return levelOrderTraversal(Collections.singletonList(root),levelOrderOfTree);
	}
	private List<List<Integer>> levelOrderTraversal(List<TreeNode> nodes, List<List<Integer>> levelOrderOfTree){
		
		if( nodes== null || nodes.isEmpty())
			return levelOrderOfTree;
		List<Integer> currentLevelNumbers = new ArrayList<Integer>();
		List<TreeNode> nextLevel = new ArrayList<TreeNode>();
		
		for(TreeNode node: nodes)
		{
			if(node != null){
				currentLevelNumbers.add(node.val);
				if(node.left != null)
					nextLevel.add(node.left);
				if(node.right != null)
					nextLevel.add(node.right);
			}
		}
		levelOrderOfTree.add(currentLevelNumbers);
		return levelOrderTraversal(nextLevel, levelOrderOfTree);
	}
	
	/**Method traverses the tree in levels and returns a list of each level from bottom to top.. 
	 * where each level is a list of all elements in that level from left to right.
	 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
	 * 
	 * @param root
	 * @return
	 */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        return levelOrderBottom(Collections.singletonList(root));
    }
	private List<List<Integer>> levelOrderBottom(List<TreeNode> nodes){
		
		if( nodes== null || nodes.isEmpty())
			return null;
		List<Integer> currentLevelNumbers = new ArrayList<Integer>();
		List<TreeNode> nextLevel = new ArrayList<TreeNode>();
		
		for(TreeNode node: nodes)
		{
			if(node != null){
				currentLevelNumbers.add(node.val);
				if(node.left != null)
					nextLevel.add(node.left);
				if(node.right != null)
					nextLevel.add(node.right);
			}
		}
		List<List<Integer>> levelOrderOfTree = levelOrderBottom(nextLevel);
		if(levelOrderOfTree== null)
			levelOrderOfTree = new ArrayList<List<Integer>>();
		levelOrderOfTree.add(currentLevelNumbers);
		
		return levelOrderOfTree;
	}
	
    /**Method to find if there exists a leaf node, to which if traversed from root adds up to given sum.. 
     * https://leetcode.com/problems/path-sum/
     * 
     * @param sum, the sum of all elements from root to leaf.
     * @return true, if a leaf node exists, such that sum of all elements in its path from root equals sum; 
     * <br/>false, otherwise
     */
	public boolean hasPathSum(int sum)
	{
		return hasPathSum(root,0, sum);
	}
	private boolean hasPathSum(TreeNode node, int actualSum, int sum) {
		if(node == null)
			return false;
		actualSum = actualSum + node.val;
		if(node.left == null && node.right == null)
			return actualSum == sum;
		return hasPathSum(node.left, actualSum, sum) || hasPathSum(node.right, actualSum, sum) ;
    }
	
	/** Method tells the maximum height of the tree. 
	 *  
	 * @return height, returns the height of the tree <br/>
	 *  -1 = Root node is null. <br/>
	 *   0 = Root is the only tree node present in the tree. <br/>
	 *   n = Number of levels in tree, with out counting root node level.
	 */
	public int heightOfTree(){
		if(root == null)
			return -1;
		return heightOfTree(root) -1;
	}
	private int heightOfTree(TreeNode node){
		if(node == null)
			return 0;
		return Math.max(heightOfTree(node.left), heightOfTree(node.right)) + 1;
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
	
	/**Method to identify the maximum depth of a tree. 
	 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
	 * 
	 * @return height, maximum height of the tree. 
	 */
	public int maxDepth()
	{
		return maxDepth(root);
	}
	private int maxDepth(TreeNode node)
	{
		if(node == null)
			return 0;
		return Math.max(maxDepth(node.left),maxDepth(node.right) ) + 1;
	}
	
	/**Method to identify the minimum depth of a tree. 
	 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
	 * 
	 * @return height, minimum height of the tree. 
	 */
	public int minDepth(){
		return minDepth(root);
	}
	private int minDepth(TreeNode node){
		if(node == null)
			return 0;
		int l = minDepth(node.left);
		int r = minDepth(node.right);
		if( l == 0 || r == 0)
			return l + r + 1;
		return Math.min(l , r ) + 1;
	}
	
	/**To print a tree in a tree format. 
	 * 
	 */
	public void printTree()
	{
		int height = heightOfTree();
		if(height < 0){
			System.out.println("Empty tree!");
			return;
		}
		printTree(Collections.singletonList(root), 0, height);
	}
	private void printTree(List<TreeNode> nodes, int level, int height)
	{
		List<TreeNode> nextLevel = new ArrayList<TreeNode>();
		
        int floor = height - level;
        //multiplied by 2 and subtracted by 2 because, each node in tree is printed to take 2 units of space.
        //to be be updated to 3, if nodes in tree take 3 letter spaces to print each node. 
        int firstSpaces = (int) Math.pow(2, (floor))*2 -2;
        int betweenSpaces = (int) Math.pow(2, (floor + 1))*2-2;
		
		printWhitespaces(firstSpaces);
		for(TreeNode node: nodes)
		{
			if(node == null){
				System.out.print("__");
				nextLevel.add(null);
				nextLevel.add(null);
			}
			else {
				System.out.format("%02d",node.val);
				nextLevel.add(node.left);
				nextLevel.add(node.right);
			}
			printWhitespaces(betweenSpaces);
		}
		System.out.println("");
		if(height >= ++level)
			printTree(nextLevel, level, height);
	}
	private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
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
		System.out.println(tree.kthSmallestElementInBST(5));

	}
}
