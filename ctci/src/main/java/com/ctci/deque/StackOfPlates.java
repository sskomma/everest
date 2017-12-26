package com.ctci.deque;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StackOfPlates {
  List<LinkedList<Integer>> stacks;
  LinkedList<Integer> current;
  int capacity;

  public StackOfPlates(int capacity) {
    stacks = new ArrayList<>();
    current = new LinkedList<Integer>();
    this.capacity = capacity;
    stacks.add(current);
  }

  public void push(int x) {
    if(current.size() >= capacity) {
      current = new LinkedList<Integer>();
      stacks.add(current);
    }
    current.push(x);
  }

  public int pop() {
    if(current.size() == 0) {
      stacks.remove(current);
      current = stacks.get(stacks.size()-1);
    }
    return current.pop();
  }

  public static void main(String[] args) {
    StackOfPlates stack = new StackOfPlates(2);
    stack.push(5);
    stack.push(4);
    stack.push(3);
    stack.push(2);
    stack.push(1);
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.pop());
  }
  
}
