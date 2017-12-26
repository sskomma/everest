package com.ctci.linkedlist;

import java.util.Stack;

public class Palindrome {
  public static boolean isPalindrome(Node node) {
    if(node == null) {
      throw new IllegalArgumentException();
    }

    Node p1 = node, p2 = node;
    Stack<Integer> stack = new Stack<Integer>();
    while(p1 != null && p1.next != null) {
      p1 = p1.next;
      p1 = p1.next;
      stack.push(p2.data);
      p2 = p2.next;
    }

    if(p1 != null)  {
      stack.push(p2.data);
    }

    while(!stack.empty()) {
        if(p2.data != stack.pop()){
          return false;
        }
        p2 = p2.next;
    }
    return true;
  }
  public static void main(String[] args) {
    int[] n1 = {1,2,2,1};
    Node a1 = Node.add(n1);
    System.out.println(isPalindrome(a1));
  }
}
