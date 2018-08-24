package com.algorithms.design;

import com.google.common.primitives.Ints;

import java.util.Iterator;

/**
 * https://leetcode.com/problems/peeking-iterator/description/
 * #leetcode284
 */
class PeekingIterator implements Iterator<Integer> {
  private Integer nextElement;
  private Iterator<Integer> iterator;

  public PeekingIterator(Iterator<Integer> iterator) {
    this.iterator = iterator;
    nextElement =  this.iterator.hasNext() ? this.iterator.next() : null;
  }

  // Returns the next element in the iteration without advancing the iterator.
  public Integer peek() {
    return nextElement;
  }

  // hasNext() and next() should behave the same as in the Iterator interface.
  // Override them if needed.
  @Override
  public Integer next() {
    Integer next = nextElement;
    nextElement = iterator.hasNext() ? iterator.next() : null;
    return next;
  }

  @Override
  public boolean hasNext() {
    return nextElement != null;
  }


  public static void main(String[] args) {
    int[] integers = {};

    PeekingIterator pi = new PeekingIterator(Ints.asList(integers).iterator());
    System.out.println(pi.next());
    System.out.println(pi.peek());
    System.out.println(pi.next());
    System.out.println(pi.next());
    System.out.println(pi.hasNext());
  }

}
