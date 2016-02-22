package com.questions.arrays.matrices;

public class RotateLayersInMatrix
{

    /**Method to rotate the layers of the array.
     * 
     * @param matrix, to be rotated.
     * @param layer, of the matrix to be rotated. 
     * Before rotating
     * | 0| 1| 2| 3| 4| 5| 6| 
     * |10|11|12|13|14|15|16| 
     * |20|21|22|23|24|25|26| 
     * |30|31|32|33|34|35|36| 
     * |40|41|42|43|44|45|46| 
     * |50|51|52|53|54|55|56| 
     * |60|61|62|63|64|65|66| 
     * 
     * After rotating the 6th layer: 
     *
     * | 0| 1| 2| 3| 4| 5| 6| 
     * |10|15|25|35|45|55|16|
     * |20|14|22|23|24|54|26|
     * |30|13|32|33|34|53|36|
     * |40|12|42|43|44|52|46|
     * |50|11|21|31|41|51|56|
     * |60|61|62|63|64|65|66|
     * 
     */
    public static void rotateMatrixLayer(int[][] matrix, int layer)
    {
        //Make sure array is square array and layer is given is below the size and greater than 0.
        int n= matrix.length;
        assert(layer <= n);
        
        int depth = n - layer;
        
        int[] temp = new int[n];
        //Save the contents of top edge in a temporary array
        for(int i = depth; i < n; i++)
            temp[i] = matrix[depth][i];
        
        //Move contents from right edge to top edge. 
        for(int i = depth +1; i < n-depth ; i++)
            matrix[depth][i] = matrix[i][n-1-depth];
        
        //Move contents from bottom edge to right edge
        for(int i = depth+1 ; i < n-depth; i++)
            matrix[i][n-1-depth] = matrix[n-1-depth][n-1-i];

        //Move contents from left to bottom edge
        for(int i = depth + 1; i < n-depth; i++)
            matrix[n-1-depth][n-1-i] = matrix[n-1-i][depth];

        //Move contents from top edge[temp array] to left edge
        for(int i= depth + 1; i < n - depth; i++)
            matrix[n-1-i][depth] = temp[i];
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
       rotateMatrixLayer(matrix, 6);
    }

}
