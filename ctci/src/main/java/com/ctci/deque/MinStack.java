package com.ctci.deque;

public class MinStack {
  private Node head;
  private int minSoFar = Integer.MAX_VALUE;

  public void push(int element) {
    minSoFar = Math.min(minSoFar, element);
    Node node = new Node();
    node.element = element;
    node.minVal = minSoFar;
    node.next = head;
    head = node;
  }

  public int pop() {
    if(head == null) {
      throw new IllegalStateException();
    }
    Node result = head;
    head = head.next;
    return result.element;
  }

  public int peek() {
    if(head == null) {
      throw new IllegalStateException();
    }
    return head.element;
  }

  public int min() {
    if(head == null) {
      throw new IllegalStateException();
    }
    return head.minVal;
  }

  private class Node {
    int element;
    int minVal;
    Node next;
  }

  public static void main(String[] args) {
    MinStack stack = new MinStack();
    stack.push(9);
    stack.push(5);
    stack.push(6);
    stack.push(8);
    stack.push(2);
    stack.push(11);
    System.out.println("Peek: "+stack.peek());
    System.out.println("Min: "+stack.min());
    System.out.println("Pop: "+stack.pop());
    System.out.println("Min: "+stack.min());
    System.out.println("Pop: "+stack.pop());
    System.out.println("Min: "+stack.min());
    System.out.println("Pop: "+stack.pop());
    System.out.println("Min: "+stack.min());
    System.out.println("Pop: "+stack.pop());
    System.out.println("Min: "+stack.min());
  }
}
