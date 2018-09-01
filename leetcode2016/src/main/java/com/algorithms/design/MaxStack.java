package com.algorithms.design;

import java.util.Deque;
import java.util.LinkedList;

public class MaxStack {

  private StackNode[] stack;
  private int top;
  private int capacity;

  /** initialize your data structure here. */
  public MaxStack() {
    //Zero indexed
    top = -1;
    // One indexed
    capacity = 10;
    stack = new StackNode[capacity];
  }

  public void push(int x) {
    ensureCapacity();
    stack[top + 1] = new StackNode(x, Math.min(x, peekMax()));
    top = top + 1;
  }

  public int pop() {
    if (!isEmpty()) {
      return stack[top--].val;
    }
    return Integer.MAX_VALUE;
  }

  public int top() {
    if (!isEmpty()) {
      return stack[top].val;
    }
    return Integer.MAX_VALUE;
  }

  public int peekMax() {
    if (!isEmpty()) {
      return stack[top].max;
    }
    return Integer.MIN_VALUE;
  }

  public int popMax() {
    int max = peekMax();
    Deque<Integer> tempStack = new LinkedList<>();
    boolean found = false;
    while (!isEmpty()){
      StackNode node = stack[top--];
      tempStack.push(node.val);
      if(node.val == max) {
        found = true;
        break;
      }
    }
    if(found){
      //stack[top] = new StackNode(tempStack.pop(), Math.max());
      top++;

    }
    return Integer.MIN_VALUE;
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
    int max;
    int val;

    StackNode(int val, int max) {
      this.val = val;
      this.max = max;
    }
  }
}
