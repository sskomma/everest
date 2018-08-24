package com.questions.heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 *
 * For example,
 * [2,3,4], the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 *  void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 *
 * https://leetcode.com/problems/find-median-from-data-stream/description/
 */
class MedianFinder {

  // To save smaller numbers that occur before median in max heap.
  private PriorityQueue<Integer> smallerNumbers;

  // To save bigger numbers that occur after median in min heap.
  private PriorityQueue<Integer> biggerNumbers;

  /** initialize your data structure here. */
  public MedianFinder() {
    smallerNumbers = new PriorityQueue<>(Comparator.reverseOrder());
    biggerNumbers = new PriorityQueue<>();
  }

  public static void main(String[] args) {
    MedianFinder mf = new MedianFinder();
    mf.addNum(40);
    System.out.println(mf.findMedian());
    mf.addNum(12);
    System.out.println(mf.findMedian());
    mf.addNum(16);
    System.out.println(mf.findMedian());
  }

  /**
   * Takes in a number and digests it.
   * @param num number to be added to MedianFinder.
   */
  public void addNum(int num) {

    smallerNumbers.add(num);
    biggerNumbers.add(smallerNumbers.poll());
    if (smallerNumbers.size() < biggerNumbers.size()) {
      smallerNumbers.add(biggerNumbers.poll());
    }
  }

  public double findMedian() {
    double smallNums = smallerNumbers.peek() == null ? 0 : smallerNumbers.peek();
    double biggerNums = biggerNumbers.peek() == null ? 0 : biggerNumbers.peek();

    if (smallerNumbers.size() == biggerNumbers.size()) {
      return (smallNums + biggerNums) / 2;
    }
    return smallerNumbers.peek() == null ? 0 : smallerNumbers.peek();
  }
}
