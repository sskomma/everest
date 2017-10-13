package com.questions.linkedlist;

/**
 * Description: https://leetcode.com/problems/add-two-numbers/
 *
 * You are given two linked lists representing two non-negative numbers. 
 * The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 *
 * Output: 7 -> 0 -> 8
 *
 * @author Ram Komma
 *
 */

public class AddTwoNumbers {

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    return findSum(l1, l2, null, 0);
  }

  public static ListNode findSum(ListNode l1, ListNode l2, ListNode sum, int carryFwd) {

    if (l1 == null && l2 == null && carryFwd == 0) {
      return sum;
    } else {
      ListNode nextNode = new ListNode(0);

      if (sum == null) {
        sum = nextNode;
      } else {
        sum.next = nextNode;
      }
      int total = 0;
      if (l1 == null && l2 == null && carryFwd > 0) {
        nextNode.val = carryFwd;
      } else if (l1 == null) {
        total = carryFwd + l2.val;
        nextNode.val = total % 10;
        findSum(null, l2.next, nextNode, total / 10);
      } else if (l2 == null) {
        total = carryFwd + l1.val;
        nextNode.val = total % 10;
        findSum(l1.next, null, nextNode, total / 10);
      } else {
        total = carryFwd + l1.val + l2.val;
        nextNode.val = total % 10;
        findSum(l1.next, l2.next, nextNode, total / 10);
      }
    }

    return sum;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
    ListNode l1 = new ListNode(1);
    //l1.next=new ListNode(4);
    //l1.next.next=new ListNode(3);

    ListNode l2 = new ListNode(9);
    l2.next = new ListNode(9);
    //l2.next.next=new ListNode(4);

    ListNode total = addTwoNumbers(l1, l2);
    printList(total);
  }

  public static void printList(ListNode l) {

    if (l != null) {
      System.out.println(l.val);
      while (l.next != null) {
        l = l.next;
        System.out.println(l.val);
      }
    } else {
      System.out.println("Empty List");
    }
  }
}
