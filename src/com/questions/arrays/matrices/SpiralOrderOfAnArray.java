package com.questions.arrays.matrices;

import java.util.ArrayList;
import java.util.List;

/**Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * 
 * For example, 
 * Given the following matrix: 
 *[
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 *]
 *   
 * You should return [1,2,3,6,9,8,7,4,5]
 * https://leetcode.com/problems/spiral-matrix/
 * 
 * @author Ram Komma
 *
 */
public class SpiralOrderOfAnArray {
	
    public static List<Integer> spiralOrder(int[][] matrix) 
    {
    	List<Integer> spiralOrder = null;
    	if(matrix != null)
    	{
	    	spiralOrder = new ArrayList<Integer>();
	    	int m = matrix.length;
	    	if(m!=0)
	    	{
		    	int n = matrix[0].length;
		    	
		        int depth = (int) Math.ceil(Math.min((double)m,(double)n)/(double)2);
		        
		        for(int d = 0; d < depth; d++)
		        {
			        //Copy elements from top edge(row)- i.e elements column 1 to n. 
			        for(int i = d; i < n-1-d ; i++)
			            spiralOrder.add(matrix[d][i]); 
			        
			        //copy contents from right edge(column) - i.e elements from row 1 to m
			        for(int i = d ; i < m-1-d; i++)
			        	spiralOrder.add(matrix[i][n-1-d]);
			
			        //Copy contents from bottom edge
			        for(int i = d ; i < n-1-d; i++)
			            spiralOrder.add(matrix[m-1-d][n-1-i]);
			        
			        for(int i= d; i < m-1-d; i++)
			            spiralOrder.add(matrix[m-1-i][d]);
		        }
		        
		        if( m == n && n%2 != 0)
		        	spiralOrder.add(matrix[depth][depth]);
    		}
    	}
    	return spiralOrder;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int m= 7, n= 7;
       int[][] matrix = new int[m][n];
       for(int i = 0; i < m; i++)
       {
           int rowField = i*10;
           for(int j=0; j< n; j++)
               matrix[i][j] = rowField + j;
       }
       MatrixUtils.printMatrix(matrix, m,n);
       System.out.println("Elements spiraling from outer layer to inner layer");
       for(int i:spiralOrder(matrix))
    	   System.out.print(i + ",");
	}

}
