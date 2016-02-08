package com.questions.linkedlist;

import java.util.Stack;

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
	
	/**Description: A method, to put all nodes in odd positions in list before all even nodes. 
	 * where, the sequence of odd nodes and even nodes is kept intact. 
	 * Input   : 1->2->3->4->5->6
	 * Output  : 1->3->5->2->4->6
	 * Note: The nodes in odd positions are put upfront and not the elements with odd value.
	 * https://leetcode.com/problems/odd-even-linked-list/
	 * 
	 */
	public static ListNode oddEventList(ListNode head)
	{
		if(head != null && head.next != null)
		{
		    //refers to the last odd-node in the list.  
    		ListNode oddListPointer = head;
    		//refers to the last even-node in the list.
    		ListNode evenListPointer =head.next;
    		//refers to a navigator, that moves in hunt of odd positioned nodes in list. 
    		ListNode navigator = head;
    		while(evenListPointer.hasNext())
    		{
    			navigator = evenListPointer.next;
    			ListNode evenListStart = oddListPointer.next;
    			oddListPointer.next = navigator;
    			oddListPointer = oddListPointer.next;
    			ListNode nextEvenNode = navigator.next;
    			navigator.next = evenListStart;
    			evenListPointer.next = nextEvenNode;
    			if(evenListPointer.hasNext())
    			    evenListPointer = evenListPointer.next;
    		}
		}
		return head;
	}
	
	/**Given a linked list, swap every two adjacent nodes and return its head.
	 * For example,
	 * Given 1->2->3->4, you should return the list as 2->1->4->3.
	 * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
	 * 
	 * https://leetcode.com/problems/swap-nodes-in-pairs/
	 * 
	 * @param head of the linked list, of which nodes are to be swapped. 
	 * @return head
	 */
	public static ListNode swapPairs(ListNode head){
		if(head == null)
			return null;
		ListNode current = head;
		ListNode next = head.next;
		
		while(next != null)
		{
			int temp = current.val;
			current.val = next.val;
			next.val = temp;
			if(next.next == null)
				break;
			current = next.next;
			next = current.next;
		}
		return head;
	}
	
	public static void main(String[] args)
	{
	    LinkedList list = new LinkedList();
	    list.addNode(1);
	    list.addNode(2);
	    list.addNode(3);
	    list.addNode(4);
	    list.printList();
	    swapPairs(list.getHead());
	    list.printList();
		
	}
	
	

}
