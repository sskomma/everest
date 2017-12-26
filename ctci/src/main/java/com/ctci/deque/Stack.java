package com.ctci.deque;

public class Stack<T> {
  StackNode<T> head;

  public void push(T element) {
    StackNode<T> node = new StackNode<T>(element);
    node.next = head;
    head = node;
  }

  public T pop() {
    if (head == null) {
      throw new IndexOutOfBoundsException();
    }
    StackNode<T> result = head;
    head = head.next;
    result.next = null;
    return result.element;
  }

  public T peek() {
    if (head == null) {
      throw new IllegalStateException("No more elements in stack");
    }
    return head.element;
  }

  public boolean isEmpty() {
    return head == null;
  }

  private class StackNode<T> {
    T element;

    StackNode<T> next;

    StackNode(T element) {
      this.element = element;
    }
  }

  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<Integer>();
    stack.push(5);
    stack.push(4);
    stack.push(3);
    stack.push(2);
    System.out.println(stack.peek());
    System.out.println(stack.pop());
    System.out.println(stack.isEmpty());
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.pop());

  }
}
