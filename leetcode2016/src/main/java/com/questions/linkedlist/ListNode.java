package com.questions.linkedlist;

/**
 * Definition for singly-linked list.*/

public class ListNode {
  public int val;
  public ListNode next;

  public ListNode(int x) {
    val = x;
  }

  public boolean hasNext() {
    return next != null;
  }

  public ListNode getNext() {
    return next;
  }

  public int getVal() {
    return val;
  }

  public String toString() {
    return Integer.toString(val);
  }
}
