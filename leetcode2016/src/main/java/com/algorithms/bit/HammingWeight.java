package com.algorithms.bit;

/**
 * Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).
 *
 * https://leetcode.com/problems/number-of-1-bits/description/
 */
public class HammingWeight {

  public static int hammingWeight(int n) {

    int counter = 0;
    int mask = 1;
    for (int i = 0; i < 32; i++) {
      if((n | mask) == n )
        counter++;
      mask = mask << 1;
    }

    return counter;
  }

  public static void main(String[] args) {
    System.out.println(hammingWeight(255));
  }
}