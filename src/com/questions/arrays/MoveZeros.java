package com.questions.arrays;

/**Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.<br>
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].<br>
 * <br>
 * <b>Note:</b><br>
 * You must do this in-place without making a copy of the array.<br>
 * Minimize the total number of operations.<br><br>
 * 
 * https://leetcode.com/problems/move-zeroes/<br>
 * 
 * @author Ram Komma
 *
 */
public class MoveZeros
{

    public static void moveZeros(int[] A)
    {
        if(A==null) return;
        int length = A.length;
        int j = 0;
        for(int i = 0 ; i < length-1; i++)
        {
            if( A[i]== 0 )
            {
                j = j<i ?i + 1:j;
                while(j<length-1 && A[j]==0){j++;}
                int temp = A[j];
                A[j] = A[i];
                A[i] = temp;
            }
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        int[] array = {1,3, 0,5,0, 13,0, 14,0,0};
        moveZeros(array);
        for(int i: array)
        {
            System.out.print(i+" ");
        }
    }

}
