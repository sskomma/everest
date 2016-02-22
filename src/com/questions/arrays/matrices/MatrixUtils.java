package com.questions.arrays.matrices;

public class MatrixUtils
{
    public static void printMatrix(int[][] matrix, int length)
    {
        for(int i=0;i< length; i++)
        {
            System.out.print("|");
            for(int j=0;j<length;j++)
            {
                System.out.format("%2d", matrix[i][j]); 
                System.out.print("|");
            }
            System.out.println("");
        }
        
    }

}
