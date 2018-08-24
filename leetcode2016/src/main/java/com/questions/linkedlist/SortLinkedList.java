package com.questions.linkedlist;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
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
 * https://leetcode.com/problems/sort-list/description/
 *
 * uses Merge sort to sort linkedlist.
 */
public class SortLinkedList {
  public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    // Break list
    ListNode listOne = head;
    ListNode fast = head, slow = head;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      if (fast == null || fast.next == null) {
        ListNode temp = slow;
        slow = slow.next;
        temp.next = null;
      } else {
        slow = slow.next;
      }
    }
    ListNode listTwo = slow;

    //Sort Lists
    listOne = sortList(listOne);
    listTwo = sortList(listTwo);

    //Merge lists
    ListNode sortedHead = new ListNode(0);
    ListNode sortedNext = sortedHead;

    while (listOne != null && listTwo != null) {
      if (listOne.val <= listTwo.val) {
        sortedNext.next = listOne;
        listOne = listOne.next;
      } else {
        sortedNext.next = listTwo;
        listTwo = listTwo.next;
      }
      sortedNext = sortedNext.next;
      sortedNext.next = null;
    }
    sortedNext.next = listOne != null ? listOne : listTwo;

    return sortedHead.next;
  }
}
