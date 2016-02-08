package com.questions.linkedlist;

/**
 * Definition for singly-linked list.*/

public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
      boolean hasNext()
      {
          return next != null;
      }
      ListNode getNext(){ return next;}
      public String toString(){return Integer.toString(val);}
}
