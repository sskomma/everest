package com.algorithms.design;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxStack {
  private PriorityQueue<ListNode> pq;
  private ListNode head;
  private ListNode tail;

  /** initialize your data structure here. */
  public MaxStack() {
    pq = new PriorityQueue<>(Comparator.comparingInt(ListNode::getVal).reversed());
    head = new ListNode();
    tail = new ListNode(head, -1, head);
    head.prev = tail;
    head.next = tail;
  }

  public void push(int x) {
    ListNode node = new ListNode(head, x, head.next);
    head.next = node;
    head.next.prev = node;

    pq.add(node);
  }

  public int pop() {
    return 0;
  }

  public int top() {
    return 0;
  }

  public int peekMax() {
    return 0;
  }

  public int popMax() {
    return 0;
  }

  static class ListNode {
    int val;
    ListNode prev;
    ListNode next;

    ListNode() {
    }

    ListNode(ListNode prev, int val, ListNode next) {
      this.prev = prev;
      this.val = val;
      this.next = next;
    }

    public int getVal() {
      return val;
    }
  }
}
