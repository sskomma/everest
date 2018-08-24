package com.questions.linkedlist;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 *
 * For example, the following two linked lists:
 *
 * A:          a1 → a2
 *                    ↘
 *                      c1 → c2 → c3
 *                    ↗
 * B:     b1 → b2 → b3
 * begin to intersect at node c1.
 *
 * https://leetcode.com/problems/intersection-of-two-linked-lists/description/
 */
public class IntersectionOfTwoLists {
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }

    //Find length and tail of List A
    int pALen = 0;
    ListNode pA = headA;
    ListNode pATail = null;
    while (pA != null) {
      if (pA.next == null) {
        pATail = pA;
      }
      pA = pA.next;
      pALen++;
    }
    pA = headA;

    //Find length and tail of List B
    ListNode pB = headB;
    ListNode pBTail = null;
    int pBLen = 0;
    while (pB != null) {
      if (pB.next == null) {
        pBTail = pB;
      }
      pB = pB.next;
      pBLen++;
    }
    pB = headB;

    //Check the tails are same.
    if (pATail != pBTail) {
      return null;
    }

    int diff = Math.abs(pALen - pBLen);
    if (pALen > pBLen) {
      while (diff > 0) {
        pA = pA.next;
        diff--;
      }
    } else if (pBLen > pALen) {
      while (diff > 0) {
        pB = pB.next;
        diff--;
      }
    }

    while (pA != pB) {
      pA = pA.next;
      pB = pB.next;
    }
    return pA;
  }
}
