package com.ctci.deque;

import java.util.Deque;
import java.util.LinkedList;

public class SortStack {

  public static Deque<Integer> sortStack(Deque<Integer> input) {
    Deque<Integer> buffer = new LinkedList<Integer>();
    if(input == null || input.isEmpty()) {
      return input;
    }

    while(!input.isEmpty()) {
      Integer element = input.pop();
      int count = 0;
      while(!buffer.isEmpty() && buffer.peekFirst() > element) {
        count++;
        input.push(buffer.pop());
      }
      buffer.push(element);
      for(int i = 0; i< count; i++) {
        buffer.push(input.pop());
      }
    }

    while(!buffer.isEmpty()) {
      input.push(buffer.pop());
    }
    return input;
  }

  public static void main(String[] args) {
    Deque<Integer> stack = new LinkedList<Integer>();
    stack.push(2);
    stack.push(3);
    stack.push(4);
    stack.push(1);
    stack.push(5);
    sortStack(stack);
    for (Object e : stack.toArray()) {
      System.out.println(Integer.toString((Integer)e));
    }
  }
}
