package com.questions.linkedlist;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * Example:
 * Given this linked list: 1->2->3->4->5
 * For k = 2, you should return: 2->1->4->3->5
 * For k = 3, you should return: 3->2->1->4->5
 *
 * Note:
 *      Only constant extra memory is allowed.
 *      You may not alter the values in the list's nodes, only nodes itself may be changed.
 *
 * https://leetcode.com/problems/reverse-nodes-in-k-group/description/
 */
public class ReverseNodesInKGroup {

    public static ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k <= 1)
            return head;

        //find length of list.
        int length = 0;
        ListNode traverser = head;
        while(traverser != null) {
            traverser = traverser.next;
            length++;
        }
        //Adjust k.
        int numGroups = length / k;

        traverser = head;
        ListNode prevTail = null;

        for(int i = 0; i < numGroups; i++) {
            ListNode node = reverserKNodesAt(traverser, k);

            if(prevTail != null){
                prevTail.next = node;
            }else {
                head = node;
            }
            // Since the list reversed at traverser, it becomes the last node after reversal.
            prevTail = traverser;
            traverser = traverser.next;

        }
        return head;
    }

    private static ListNode reverserKNodesAt(ListNode head, int k) {
        //reverse first k elements.
        ListNode prevNode = null;
        ListNode traverser = head;
        for(int i = 0; i < k; i++ ) {
            ListNode currNode = traverser;
            traverser = traverser.next;

            currNode.next = prevNode;
            prevNode = currNode;
            if(i == k-1) {
                head.next = traverser;
                head = currNode;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode traverser = head;
        traverser.next = new ListNode(2);
        traverser = traverser.next;
        traverser.next = new ListNode(3);
        traverser = traverser.next;
        traverser.next = new ListNode(4);
        traverser = traverser.next;
        traverser.next = new ListNode(5);
        traverser = traverser.next;
        traverser.next = new ListNode(6);

        System.out.println(reverseKGroup(head, 2));
    }
}
