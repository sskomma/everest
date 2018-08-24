package com.algorithms.design;

/**
 * Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list.
 * A node in a singly linked list should have two attributes: val and next. val is the value of the current node,
 * and next is a pointer/reference to the next node. If you want to use the doubly linked list,
 * you will need one more attribute prev to indicate the previous node in the linked list.
 * Assume all nodes in the linked list are 0-indexed.
 *
 * Implement these functions in your linked list class:
 *
 * get(index)             : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
 * addAtHead(val)         : Add a node of value val before the first element of the linked list.
 *                          After the insertion, the new node will be the first node of the linked list.
 * addAtTail(val)         : Append a node of value val to the last element of the linked list.
 * addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list.
 *                          If index equals to the length of linked list, the node will be appended to the end of linked list.
 *                          If index is greater than the length, the node will not be inserted.
 * deleteAtIndex(index)   : Delete the index-th node in the linked list, if the index is valid.
 *
 * https://leetcode.com/problems/design-linked-list/description/
 *
 * #design
 */
public class MyLinkedList {
  private DLLNode head;
  private DLLNode tail;
  private int size;

  /**
   * Initialize your data structure here.
   */
  public MyLinkedList() {
    head = new DLLNode(null);
    tail = new DLLNode(null);
    head.prev = tail;
    head.next = tail;
    tail.next = head;
    tail.prev = head;
    size = -1;
  }

  /**
   * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
   */
  public int get(int index) {
    DLLNode ithNode = ithNode(index);
    return ithNode != null ? ithNode.val : -1;
  }

  /**
   * Add a node of value val before the first element of the linked list. After the insertion,
   * the new node will be the first node of the linked list.
   */
  public void addAtHead(int val) {
    addNode(head, val);
  }

  /** Append a node of value val to the last element of the linked list. */
  public void addAtTail(int val) {
    addNode(tail.prev, val);
  }

  /**
   * Add a node of value val before the index-th node in the linked list.
   * If index equals to the length of linked list, the node will be appended to the end of linked list.
   * If index is greater than the length, the node will not be inserted.
   */
  public void addAtIndex(int index, int val) {
    if (index == size + 1) {
      addAtTail(val);
    } else {
      DLLNode ithNode = ithNode(index);
      if (ithNode != null) {
        addNode(ithNode.prev, val);
      }
    }
  }

  /** Delete the index-th node in the linked list, if the index is valid.
   */
  public void deleteAtIndex(int index) {
    DLLNode ithNode = ithNode(index);
    if (ithNode != null) {
      ithNode.prev.next = ithNode.next;
      ithNode.next.prev = ithNode.prev;
    }
  }

  private DLLNode ithNode(int index) {
    if (index > size) {
      return null;
    }
    DLLNode traveller = null;
    if (size / 2 > index) {
      traveller = head.next;
      for (int i = 0; i < index; i++) {
        traveller = traveller.next;
      }
    } else {
      traveller = tail.prev;
      for (int i = size; i > index; i--) {
        traveller = traveller.prev;
      }
    }
    return traveller;
  }

  private void addNode(DLLNode node, int val) {
    DLLNode newNode = new DLLNode(val);
    newNode.next = node.next;
    newNode.prev = node;
    newNode.next.prev = newNode;
    node.next = newNode;

    size++;
  }

  static class DLLNode {
    Integer val;
    DLLNode prev;
    DLLNode next;

    DLLNode(Integer val) {
      this.val = val;
    }
  }
}
