package com.questions.arrays.matrices;

public class RotateLayersInMatrix
{

    public static void rotateMatrixLayer(int[][] matrix, int layer)
    {
        //Make sure array is square array and layer is given is below the size and greater than 0.
        int n= matrix.length;
        assert(layer <= n+1);
        
        int[] temp = new int[n];
        //Save the contents of top edge in a temporary array
        for(int i =0; i<n; i++)
            temp[i] = matrix[0][i];
        
        //Move contents from right edge to top edge. 
        for(int i = 1; i < n ; i++)
            matrix[0][i] = matrix[i][n-1];
        
        //Move contents from bottom edge to right edge
        for(int i = 1; i < n; i++)
            matrix[i][n-1] = matrix[n-1][n-1-i];

        //Move contents from left to bottom edge
        for(int i = 1; i<n; i++)
            matrix[n-1][n-1-i] = matrix[n-1-i][0];

        //Move contents from top edge[temp array] to left edge
        for(int i=1; i<n; i++)
            matrix[n-1-i][0] = temp[i];
        MatrixUtils.printMatrix(matrix, n);
    }
    
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
       int[][] matrix = new int[7][7];
       for(int i = 0; i < 7; i++)
       {
           int rowField = i*10;
           for(int j=0; j< 7; j++)
               matrix[i][j] = rowField + j;
       }
       System.out.println("Before rotating");
       MatrixUtils.printMatrix(matrix, 7);
       System.out.println("After rotating");
       rotateMatrixLayer(matrix, 7);
    }

}
