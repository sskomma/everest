package com.algorithms.design;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 * Example:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 *
 * https://leetcode.com/problems/min-stack/description/
 */
public class MinStack {

  private StackNode[] stack;
  private int top;
  private int capacity;

  /** initialize your data structure here. */
  public MinStack() {
    //Zero indexed
    top = -1;
    // One indexed
    capacity = 10;
    stack = new StackNode[capacity];
  }

  public void push(int x) {

    ensureCapacity();
    stack[top+1] = new StackNode(x, Math.min(x, getMin()));
    top = top+ 1;
  }

  public void pop() {
    top = top -1;
  }

  public int top() {
    if (isEmpty()) {
      return Integer.MAX_VALUE;
    }
    return stack[top].val;
  }

  public int getMin() {
    if (isEmpty()) {
      return Integer.MAX_VALUE;
    }
    return stack[top].min;
  }

  public boolean isEmpty() {
    return top < 0;
  }

  private void ensureCapacity() {

    if (top + 1 >= capacity) {
      int newCapacity = capacity * 2;
      StackNode[] newStack = new StackNode[newCapacity];
      int i = 0;
      for (StackNode sNode : stack) {
        newStack[i++] = sNode;
      }
      capacity = newCapacity;
      stack = newStack;
    }
  }

  static class StackNode {
    int min;
    int val;

    StackNode(int val, int min) {
      this.val = val;
      this.min = min;
    }
  }

  public static void main(String[] args) {
    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    minStack.getMin();  // --> Returns -3.
    minStack.pop();
    minStack.top();      //--> Returns 0.
    minStack.getMin();   //--> Returns -2.
  }
}
