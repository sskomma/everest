package com.questions.linkedlist;

import java.util.Stack;

public class LinkedList
{
    private ListNode head;
    
    public LinkedList(){} 
    public LinkedList(ListNode node){head = node;} 
    
    public ListNode getHead(){return head;}
    
    /**Adds a node at the end of the linked list. If the list is empty, it will create a new head. 
     * 
     * @param n number to be placed in the new node. 
     */
    public void addNode(int n)
    {
       if(head == null)
           head = new ListNode(n);
       else
       {
           ListNode traverse = head;
           while(traverse.hasNext())
           {
               traverse = traverse.getNext();
           }
           traverse.next = new ListNode(n);
       }
    }

    /**Removes all nodes that have a given value. 
     * https://leetcode.com/problems/remove-linked-list-elements/
     * 
     * @param number, value of nodes in linked list to be removed. 
     */
    public void removeNodesWithValue(int number)
    {
        if(head == null)
            return;
        
        if(head.val == number)
        {
            if(head.hasNext())
            {
                head = head.getNext();
                removeNodesWithValue(number);
            }
            else{
                head = null;
            }
            return;
        }
        
        ListNode traverser = head;
        ListNode previous = null;
        while(traverser.hasNext())
        {
            previous = traverser;
            traverser = traverser.getNext(); 
            if(traverser.val == number)
            {
                previous.next = traverser.next;
                traverser = previous;
            }
        }
        return;
    }
    
    /**Method to delete the nth node from the end of the list. 
     * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
     * 
     * @param n, number from the end of list. 
     */
    public ListNode removeNthNodeFromEndofList(int n)
    {
        ListNode navigator = head;
        ListNode nthNode = head;
        ListNode n_1ThNode = null;
        int i = 1;
        for(; navigator != null; i++)
        {
            navigator = navigator.getNext();
            if(i > n){
                n_1ThNode = nthNode;
                nthNode = nthNode.getNext();
            }
                
        }
        if(i-1 < n){
            System.out.println("The linked list does not have n elements in it.");
            return head;
        }
        
        if(nthNode == head)
            head = head.next;
        else
            n_1ThNode.next = nthNode.next;
        return head;
    }
    
    /**Returns a reversed linked linked list.
     * Time Complexity	: O(n)
     * Space Complexity	: O(n) ~ Because we use stack to save each node
     * Although, a better implementation of reversing a linked list is done
     * in method reverseListII()
     * https://leetcode.com/problems/reverse-linked-list/
     * 
     * @return
     */
    public LinkedList reverseList()
    {
        if(head == null)
            return null;
        ListNode navigator = head;
        ListNode reverseHead = null;
        Stack<ListNode> stack = new Stack<ListNode>();
        while(navigator!= null)
        {
            stack.push(navigator);
            navigator = navigator.next;
        }
        navigator = null;
        while(!stack.isEmpty())
        {
            ListNode popped = stack.pop();
            if(reverseHead == null){
                reverseHead = popped;
                navigator = reverseHead;
            }
            else{
                navigator.next = popped;
                navigator = navigator.next;
            }
            popped.next = null;
        }
        return new LinkedList(reverseHead);
    }

    /**Returns a reversed linked linked list.
     * Time Complexity	: O(n)
     * Space Complexity	: O(1)
     * An older implementation of reversing a linked list is done
     * in method reverseList()
     * https://leetcode.com/problems/reverse-linked-list/
     */
    public ListNode reverseListII(ListNode current)
    {
    	ListNode previous = null;
    	ListNode next;
    	while(current!=null)
    	{
    		next = current.next;
    		current.next = previous;
    		
    		previous = current;
    		current = next;
    	}
    	return previous;
    }

    /**Given a singly linked list L: L0→L1→…→Ln-1→Ln,
     * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
     * 
     * You must do this in-place without altering the nodes' values.
     * 
     * For example,
     * Given {1,2,3,4}, reorder it to {1,4,2,3}.
     * https://leetcode.com/problems/reorder-list/
     */
    public void reOrder()
    {
    	if(head == null || head.next == null)
    		return;
    	
    	ListNode slowPtr = head;
    	ListNode fastPtr = head.next;
    	
    	while(fastPtr.next != null && fastPtr.next.next != null)
    	{
    		slowPtr = slowPtr.next;
    		fastPtr = fastPtr.next.next;
    	}
    	//List is of odd length..Moving slowPtr one more pointer up to get to middle
    	if(fastPtr.next != null)
    	{
    		slowPtr = slowPtr.next;
    		fastPtr = fastPtr.next;
    	}
    	ListNode secondHalf = slowPtr.next;
    	slowPtr.next = null;
    	ListNode reversedSecondHalf = reverseListII(secondHalf);
    	
    	ListNode firstHalf = head;
    	while( reversedSecondHalf !=null && firstHalf!= null)
    	{
    		ListNode popped = reversedSecondHalf;
    		reversedSecondHalf = reversedSecondHalf.next;
    		popped.next = firstHalf.next;
    		firstHalf.next = popped;
    		firstHalf = popped.next;
    	}
    }
    
    /**Prints out the linked list. 
     * 
     */
    public void printList(){LinkedListUtils.printListFromNode(head);}

    
    public static void main(String[] args)
    {
        LinkedList list = new LinkedList();
        list.addNode(1);
        list.addNode(2);
        list.addNode(3);
        list.addNode(4);
        list.addNode(5);
        list.addNode(6);
        list.addNode(7);
        
        System.out.println("Linked list before reordering");
        list.printList();
        list.reOrder();
        System.out.println("Linked list after reordering");
        list.printList();
        /*list.addNode(2);
        list.addNode(5);
        list.addNode(5);
        list.addNode(3);
        System.out.println("Linked list before printing");
        list.printList();
        list.removeNodesWithValue(5);
        System.out.println("Linked list after printing");
*/
    }

}
