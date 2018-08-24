package com.questions.linkedlist;

/**
 * Algorithm of Insertion Sort:
 *
 * Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
 * It repeats until no input elements remain.
 *
 * Example 1:
 *
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 *
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 *
 *
 * https://leetcode.com/problems/insertion-sort-list/description/
 */
public class InsertionSortLinkedList {
  public ListNode insertionSortList(ListNode head) {
    ListNode sortedPreHead = new ListNode(Integer.MAX_VALUE);

    // Insert
    ListNode traveller = head;
    while(traveller != null) {
      ListNode temp = traveller.next;
      traveller.next = null;
      insert(sortedPreHead, traveller);
      traveller = temp;
    }
    return sortedPreHead.next;
  }

  private void insert(ListNode head, ListNode node) {

    ListNode traveller = head;
    while(traveller != null) {
      if(traveller.next == null){
        traveller.next = node;
        break;
      } else if(traveller.next.val >= node.val) {
        ListNode temp = traveller.next;
        traveller.next = node;
        node.next = temp;
        break;
      }
      traveller = traveller.next;
    }
  }
}
