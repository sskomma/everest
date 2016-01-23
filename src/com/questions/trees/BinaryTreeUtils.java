package com.questions.trees;

import java.util.ArrayList;
import java.util.List;

/**A utilities class, that does various things with Binary trees and Binary search trees.  
 * 
 * @author Ram Komma
 */
public class BinaryTreeUtils 
{

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

}
