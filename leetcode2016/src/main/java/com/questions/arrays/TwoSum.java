package com.questions.arrays;

public class TwoSum {
  public static int[] twoSum(int[] numbers, int target) {
    if (numbers == null || numbers.length == 0) {
      return null;
    }
    int sum = 0;
    int i = 0, j = numbers.length - 1;
    int[] result = new int[2];
    while (i < j) {
      sum = numbers[i] + numbers[j];
      if(sum == target) {
        result[0] = i+1;
        result[1] = j+1;
        break;
      } else if (sum > target) {
        j--;
      }
      else {
        i++;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int[] nums = {2, 7, 11, 15};
    for (int x : twoSum(nums, 9)) {
      System.out.println(x);
    }
  }
}
