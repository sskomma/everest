package com.questions.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *Implement FreqStack, a class which simulates the operation of a stack-like data structure.
 *
 * FreqStack has two functions:
 *
 * push(int x), which pushes an integer x onto the stack.
 * pop(), which removes and returns the most frequent element in the stack.
 * If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
 *
 *
 * Example 1:
 *
 * Input:
 * ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 * [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
 * Output: [null,null,null,null,null,null,null,5,7,5,4]
 * Explanation:
 * After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:
 *
 * pop() -> returns 5, as 5 is the most frequent.
 * The stack becomes [5,7,5,7,4].
 *
 * pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
 * The stack becomes [5,7,5,4].
 *
 * pop() -> returns 5.
 * The stack becomes [5,7,4].
 *
 * pop() -> returns 4.
 * The stack becomes [5,7].
 *
 * https://leetcode.com/problems/maximum-frequency-stack/description/
 */
public class FreqStack {
  private Map<Integer, Integer> freqMap;
  private List<ListNode> reverseFreqMap;

  public FreqStack() {
    freqMap = new HashMap<>();
    reverseFreqMap = new ArrayList<>();
    reverseFreqMap.add(new ListNode(Integer.MIN_VALUE, null));
  }

  public static void main(String[] args) {

    /**
     * ["FreqStack","push","push","push","push","pop", "pop", "push", "push", "push", "pop", "pop", "pop"]
     * [[],[1], [1], [1], [2], [], [], [2], [2], [1], [], [], []]
     */
    FreqStack freqStack = new FreqStack();
    freqStack.push(1);
    freqStack.push(1);
    freqStack.push(1);
    freqStack.push(2);
    System.out.println(freqStack.pop());
    System.out.println(freqStack.pop());
    freqStack.push(2);
    freqStack.push(2);
    freqStack.push(1);
    System.out.println(freqStack.pop());
    System.out.println(freqStack.pop());
    System.out.println(freqStack.pop());
  }

  public void push(int x) {
    int freq = freqMap.getOrDefault(x, 0) + 1;
    freqMap.put(x, freq);

    if (freq < reverseFreqMap.size()) {
      ListNode head = reverseFreqMap.get(freq);
      reverseFreqMap.remove(freq);
      reverseFreqMap.add(freq, new ListNode(x, head));
    } else {
      reverseFreqMap.add(new ListNode(x, null));
    }
  }

  public int pop() {
    int maxFreq = reverseFreqMap.size() - 1;
    if (maxFreq < 1) {
      throw new IndexOutOfBoundsException();
    }
    ListNode node = reverseFreqMap.get(maxFreq);
    reverseFreqMap.remove(maxFreq);
    if (node.next != null) {
      reverseFreqMap.add(maxFreq, node.next);
    }
    freqMap.put(node.val, freqMap.get(node.val) - 1);
    return node.val;
  }

  static class ListNode {
    int val;
    ListNode next;

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
}
