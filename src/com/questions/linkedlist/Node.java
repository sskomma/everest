package com.questions.linkedlist;

/**
 * Definition for singly-linked list.*/

public class Node {
      int val;
      Node next;
      Node(int x) { val = x; }
      boolean hasNext()
      {
          return next != null;
      }
      Node getNext(){ return next;}
}
