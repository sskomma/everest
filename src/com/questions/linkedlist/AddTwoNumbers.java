package com.questions.linkedlist;

/**
 * Description: https://leetcode.com/problems/add-two-numbers/
 * 
 * You are given two linked lists representing two non-negative numbers. 
 * The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 
 * Output: 7 -> 0 -> 8
 * 
 * @author Ram Komma
 *
 */

public class AddTwoNumbers
{
     
    public static Node addTwoNumbers(Node l1, Node l2) {
        return findSum(l1, l2, null, 0);
    }
        
    public static Node findSum(Node l1, Node l2, Node sum, int carryFwd) {

        if(l1 == null && l2 == null && carryFwd == 0){
            return sum;
        }
        else{
            Node nextNode = new Node(0);

            if(sum == null)
                sum=nextNode;
            else
                sum.next = nextNode;
            int total = 0;
            if(l1 == null && l2 == null && carryFwd > 0){
                nextNode.val = carryFwd;
            }
            else if(l1 == null){
                total = carryFwd + l2.val;
                nextNode.val = total % 10;
                findSum(null, l2.next, nextNode, total/10);
            }
             else if(l2 == null){
                total = carryFwd + l1.val;
                nextNode.val = total % 10;
                findSum(l1.next, null, nextNode, total/10);
            }
            else{
                total = carryFwd + l1.val+ l2.val;
                nextNode.val = total % 10;
                findSum(l1.next, l2.next, nextNode, total/10);
            }
        }
        
        return sum;
    }
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
        Node l1= new Node(1);
        //l1.next=new ListNode(4);
        //l1.next.next=new ListNode(3);

        Node l2= new Node(9);
        l2.next=new Node(9);
        //l2.next.next=new ListNode(4);
        
        Node total = addTwoNumbers(l1,l2);
        printList(total);

    }
    public static void printList(Node l){
        
        if(l!=null)
        {
            System.out.println(l.val);   
            while(l.next != null)
            {
                l = l.next;
                System.out.println(l.val);
            }
        }
        else
            System.out.println("Empty List");
    }

}
