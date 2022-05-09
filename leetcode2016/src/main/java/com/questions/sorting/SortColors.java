package com.questions.sorting;

public class SortColors {

    public static void sortColors(int[] nums) {
        int p0=0, p1=0, p2=nums.length-1;

        while(p1 <= p2) {
            int current = nums[p1];
            if (current == 0) {
                swap(nums, p0, p1);
                p0++;
                p1++;
            } else if (current == 2) {
                swap(nums, p1, p2);
                p2--;
            }
            else {
                p1++;
            }
        }
    }

    private static void swap(int[] nums, int s, int e) {
        if (s < 0|| e > nums.length || s >= e) {
            return;
        }
        int temp = nums[e];
        nums[e] = nums[s];
        nums[s] = temp;
    }

    public static void main(String[] args) {
        int[] numbers = {1,2,0};
        sortColors(numbers);
        for(Integer i : numbers) {
            System.out.println(i);
        }
    }

}
