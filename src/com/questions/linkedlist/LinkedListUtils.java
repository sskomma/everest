package com.questions.linkedlist;

/**A Utility class with Static methods, to perform various jobs on LinkedList and Nodes.  
 * 
 * @author Ram Komma
 *
 */
public class LinkedListUtils {
	
	/**Description: Give a node, delete it from the linked list. 
	 * https://leetcode.com/problems/delete-node-in-a-linked-list/
	 * 
	 * @param node, Node to be deleted from the linkedlist. 
	 */
	public static void deleteNode(ListNode node)
	{
		if(node == null){
			return ;
		}
		else if(node.getNext() == null){
			node = null;
		}
		else {
			ListNode next = node.getNext();
			node.val = next.val;
			node.next = next.next;
		}
		
	}
	
	public static void oddEventList(ListNode head)
	{
		if(head == null || head.next == null)
			return;
		ListNode oddListPointer = head;
		ListNode evenListPointer =head.next;
		ListNode navigator = head;
		while(evenListPointer.hasNext())
		{
			navigator = evenListPointer.next;
			ListNode temp = oddListPointer.next;
			
			oddListPointer.next = navigator;
		}
		
		
	}
	
	public static void main(String[] args)
	{
		ListNode n = new ListNode(0);
		n.next = new ListNode(1);
		deleteNode(n);
	}
	
	

}
