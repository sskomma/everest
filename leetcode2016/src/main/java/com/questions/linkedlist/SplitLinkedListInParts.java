package com.questions.linkedlist;

/**
 * Given a (singly) linked list with head node root, write a function to split the linked list into k consecutive linked list "parts".
 * The length of each part should be as equal as possible: no two parts should have a size differing by more than 1.
 * This may lead to some parts being null. The parts should be in order of occurrence in the input list,
 * and parts occurring earlier should always have a size greater than or equal parts occurring later.
 * Return a List of ListNode's representing the linked list parts that are formed.
 *
 * Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ]
 *
 * https://leetcode.com/problems/split-linked-list-in-parts/description/
 * #leetcode725
 */
public class SplitLinkedListInParts {

  public static void main(String[] args) {
    LinkedList list = new LinkedList();
    list.addNode(1);
    list.addNode(2);
    list.addNode(3);
    /*list.addNode(4);
    list.addNode(5);
    list.addNode(6);
    list.addNode(7);
    list.addNode(8);
    list.addNode(9);
    list.addNode(10);*/
    SplitLinkedListInParts splitter = new SplitLinkedListInParts();
    ListNode[] parts = splitter.splitListToParts(null, 5);
    for (ListNode node : parts) {
      new LinkedList(node).printList();
    }
  }

  public ListNode[] splitListToParts(ListNode root, int k) {
/*    if (root == null || k < 0) {
      return null;
    }*/
    double size = 0;
    ListNode traveller = root;
    while (traveller != null) {
      size++;
      traveller = traveller.next;
    }
    traveller = root;
    ListNode[] parts = new ListNode[k];
    double nextSize = 0;
    for (int j = 0; k > 0; j++) {
      parts[j] = traveller;
      size = size - nextSize;
      nextSize = Math.ceil(size / k--);
      for (int i = 0; i < nextSize - 1; i++) {
        if (traveller != null) {
          traveller = traveller.next;
        }
      }
      ListNode nextPivot = null;
      if (traveller != null) {
        nextPivot = traveller.next;
        traveller.next = null;
      }
      traveller = nextPivot;
    }
    return parts;
  }
}