package com.questions.sorting;

import java.util.ArrayList;
import java.util.Collections;

import com.google.common.collect.Lists;


public class Test {


    public static void dutch_flag_sort(char[] balls) {
        if(balls == null || balls.length <3) {
            return;
        }
        int i = 0 , j = 0, k = 0;
        while(k < balls.length) {
            if(balls[k] == 'B') {
                k++;
            } else if (balls[j] == 'G'){
                swap(balls, j, k);
                j++; k++;
            } else if(balls[k] == 'R'){
                swap(balls, j, k);
                swap(balls, i, j);
                i++;j++;k++;
            }
        }
    }

    static void swap (char[] chars, int begin, int end) {
        char temp = chars[end];
        chars[end] = chars[begin];
        chars[begin] = temp;
    }


    public static void main(String[] args) {
        int[] input = {7, 5, 8, 3, 9, 4, 1, 7};
        char[] charInput = {'2','G','R'};
        ArrayList<Integer> numbers = Lists.newArrayList(7, 5, 8, 3, 9, 4, 1, 7);
        System.out.println(charInput);
    }

}
