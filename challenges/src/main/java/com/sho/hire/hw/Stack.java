package com.sho.hire.hw;

import java.util.EmptyStackException;

public class Stack {
  private Object[] elements;
  private int size = 0;

  public Stack(int initialCapacity) {
    this.elements = new Object[initialCapacity];
  }

  public void push(Object e) {
    ensureCapacity();
    elements[size++] = e;
  }

  public Object pop() {
    if (size == 0) {
      throw new EmptyStackException();
    }
    Object pop = elements[--size];
    return pop;
  }

  /**
   * Ensure space for at least one more element, roughly
   * doubling the capacity each time the array needs to grow.
   */
  private void ensureCapacity() {
    if (elements.length == size) {
      Object[] oldElements = elements;
      elements = new Object[2 * elements.length + 1];
      System.arraycopy(oldElements, 0, elements, 0, size);
    }
  }

  public static void main(String[] args) {
    Stack stack = new Stack(0);
    stack.push("1");
    stack.push("2");
    stack.push("3");
    stack.push("4");
    stack.push("5");
    /*System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.pop());*/
    stack.push("6");
    stack.push("7");
    System.out.println(stack.pop());
    System.out.println(stack.pop());
  }
}