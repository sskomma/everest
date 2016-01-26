package com.questions.trees;

import java.util.ArrayList;
import java.util.List;

/**A utilities class, that does various things with Binary trees and Binary search trees.  
 * 
 * @author Ram Komma
 */
public class BinaryTreeUtils 
{
    /**Method to see if the given tree with the root node is a binary search tree or not. 
     * 
     * @param root, root node of a binary tree, which is to be determined as binary search tree or not. 
     * @return boolean, true if it is a binary search tree; false, otherwise. 
     */
	public static boolean isBST(TreeNode root){
		if(root == null)
			return true;
		List<Integer> sequence = new ArrayList<Integer>();
		sequence = inOrderTraversal(root,sequence );
		if(!sequence.isEmpty())
		{
			int lastVisited = sequence.get(0);
			for(int i = 1; i<sequence.size(); i++)
			{
				if(lastVisited >= sequence.get(i))
					return false;
				lastVisited = sequence.get(i);
			}
		}
		return true;
	}
	private static List<Integer> inOrderTraversal(TreeNode node, List<Integer> sequence)
	{
		if(node == null)
			return sequence;
		inOrderTraversal(node.left, sequence);
		sequence.add(node.val);
		inOrderTraversal(node.right, sequence);
		return sequence;
	}
	
	/**Method to convert a sorted array to balanced Binary search tree.
	 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/ 
	 * 
	 * @param numbers, array of sorted numbers of which tree to be constructed. 
	 * @return TreeNode, root node of the constructed Binary search tree. 
	 */
    public static TreeNode sortedArrayToBST(int[] numbers ){
        if(numbers == null || numbers.length == 0)
            return null;
        TreeNode root = sortedArrayToBST(numbers, 0, numbers.length-1);
        return root;
    }
	private static TreeNode sortedArrayToBST(int[] numbers, int begin, int end )
	{
	    if(end < begin)
	        return null;
	    int currentElement = begin + (end - begin)/2;
	    TreeNode currentNode = new TreeNode(numbers[currentElement]);
	    currentNode.left = sortedArrayToBST(numbers,begin, currentElement -1);
	    currentNode.right = sortedArrayToBST(numbers, currentElement + 1, end);
	    return currentNode;
	}
	
	public static void main(String[] args)
	{
	    int[] numbers = {1, 2, 3, 4, 5, 8, 9, 10, 13, 14, 15, 16, 18, 19}; 
	    BinarySearchTree bst = new BinarySearchTree(sortedArrayToBST(numbers));
	    bst.printTree();
	}

	
}
