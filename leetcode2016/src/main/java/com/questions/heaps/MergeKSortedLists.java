package com.questions.heaps;

import com.questions.linkedlist.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * https://leetcode.com/problems/merge-k-sorted-lists/description/
 * #leetcode23
 *
 * @author Ram Komma
 */
public class MergeKSortedLists {

  public static void main(String[] args) {
    ListNode A = new ListNode(19);
    ListNode B = new ListNode(1);
    ListNode C = new ListNode(8);
    ListNode[] listOfLists = {A, B, C};
    A.next = new ListNode(25);
    A = A.next;
    A.next = new ListNode(33);
    B.next = new ListNode(10);
    B = B.next;
    B.next = new ListNode(16);
    B = B.next;
    B.next = new ListNode(35);
    C.next = new ListNode(27);
    C = C.next;
    C.next = new ListNode(31);
    C = C.next;
    C.next = new ListNode(34);
    MergeKSortedLists mergeLists = new MergeKSortedLists();
    ListNode[] list2 = {null};
    ListNode head = mergeLists.mergeSortedLists(list2);
    if (head != null) {
      while (head.hasNext()) {
        System.out.println(head.val);
        head = head.next;
      }
    }
  }

  public ListNode mergeSortedLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }
    PriorityQueue<ListNode> minHeap =
        new PriorityQueue<>(lists.length, Comparator.comparingInt(ListNode::getVal));

    for (ListNode node : lists) {
      if (node != null) {
        minHeap.add(node);
      }
    }

    ListNode resultHead = new ListNode(0);
    ListNode traverser = resultHead;

    while (!minHeap.isEmpty()) {
      ListNode minElement = minHeap.remove();
      if (minElement.next != null) {
        minHeap.add(minElement.next);
      }
      minElement.next = null;
      traverser.next = minElement;
      traverser = traverser.next;
    }
    return resultHead.next;
  }
}
