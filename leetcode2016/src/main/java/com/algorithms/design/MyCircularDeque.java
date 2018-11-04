package com.algorithms.design;

/**
 * Design your implementation of the circular double-ended queue (deque).
 *
 * Your implementation should support following operations:
 *
 * MyCircularDeque(k): Constructor, set the size of the deque to be k.
 * insertFront(): Adds an item at the front of Deque. Return true if the operation is successful.
 * insertLast(): Adds an item at the rear of Deque. Return true if the operation is successful.
 * deleteFront(): Deletes an item from the front of Deque. Return true if the operation is successful.
 * deleteLast(): Deletes an item from the rear of Deque. Return true if the operation is successful.
 * getFront(): Gets the front item from the Deque. If the deque is empty, return -1.
 * getRear(): Gets the last item from Deque. If the deque is empty, return -1.
 * isEmpty(): Checks whether Deque is empty or not.
 * isFull(): Checks whether Deque is full or not.
 *
 * https://leetcode.com/problems/design-circular-deque/description/
 */
public class MyCircularDeque {

  private DoubleListNode begin;
  private DoubleListNode end;
  private int currCapacity;
  private int maxCapacity;

  /** Initialize your data structure here. Set the size of the deque to be k. */
  public MyCircularDeque(int k) {
    this.currCapacity = k;
    this.maxCapacity = k;
    begin = new DoubleListNode(-1);
    end = new DoubleListNode(-1);
    begin.next = end;
    end.prev = begin;
    begin.prev = end;
    end.next = begin;
  }

  /** Adds an item at the front of Deque. Return true if the operation is successful. */
  public boolean insertFront(int value) {
    System.out.println("Insert Front: "+ value);
    if(currCapacity > 0) {
      DoubleListNode node = new DoubleListNode(value);
      node.next = begin.next;
      node.prev = begin;
      begin.next.prev = node;
      begin.next = node;
      currCapacity--;
      return true;
    }
    return false;
  }

  /** Adds an item at the rear of Deque. Return true if the operation is successful. */
  public boolean insertLast(int value) {
    System.out.println("Insert last: "+ value);
    if(currCapacity > 0) {
      DoubleListNode node = new DoubleListNode(value);
      node.next = end;
      node.prev = end.prev;
      end.prev.next = node;
      end.prev = node;
      currCapacity--;
      return true;
    }
    return false;

  }

  /** Deletes an item from the front of Deque. Return true if the operation is successful. */
  public boolean deleteFront() {
    System.out.println("Delete front");
    if(currCapacity < maxCapacity) {
      DoubleListNode node = begin.next;
      begin.next = node.next;
      node.next.prev = begin;
      currCapacity++;
      return true;
    }
    return false;
  }

  /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
  public boolean deleteLast() {
    System.out.println("Delete last");
    if(currCapacity < maxCapacity) {
      DoubleListNode node = end.prev;
      end.prev = node.prev;
      node.prev.next = end;
      currCapacity++;
      return true;
    }
    return false;
  }

  /** Get the front item from the deque. */
  public int getFront() {
    System.out.println("Get front");
    if(currCapacity < maxCapacity) {
      return begin.next.value;
    }
    throw new ArrayIndexOutOfBoundsException();
  }

  /** Get the last item from the deque. */
  public int getRear() {
    System.out.println("Get last");
    if(currCapacity < maxCapacity) {
      return end.prev.value;
    }
    throw new ArrayIndexOutOfBoundsException();
  }

  /** Checks whether the circular deque is empty or not. */
  public boolean isEmpty() {
    System.out.println("isEmpty");
    return currCapacity == maxCapacity;
  }

  /** Checks whether the circular deque is full or not. */
  public boolean isFull() {
    System.out.println("isFull");
    return currCapacity == 0;
  }

  /**
   * A double linked list node.
   */
  private static class DoubleListNode {
    int value;
    DoubleListNode prev;
    DoubleListNode next;

    public DoubleListNode(int value) {
      this.value = value;
    }
  }

  public static void main(String[] args) {
    MyCircularDeque mcd = new MyCircularDeque(3);
    mcd.insertLast(1);
    mcd.insertLast(2);
    mcd.insertFront(3);
    mcd.insertFront(4);
    mcd.getRear();
    mcd.isFull();
    mcd.deleteLast();
    mcd.insertFront(4);
    mcd.getFront();
  }
}
