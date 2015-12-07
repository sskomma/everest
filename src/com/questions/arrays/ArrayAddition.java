package com.questions.arrays;

/**Give two arrays. Treat each array as an number and add them.. 

 * @author Ram Komma
 */
public class ArrayAddition {

	/** 
	 * This method takes in two arrays, and adds them together. 
	 * @param a, first array of single digit integers to be added to other array.
	 * @param b, second array of single digit integers to be added to the other array.
	 * @return an array of integers, resulted after addition of  input arrays. 
	 */
	public static int[] addArrays(int[] a, int[] b)
	{
		if( (a == null && b == null) || (a.length == 0 && b.length ==0 )) return null;
		else if( a == null || a.length == 0) return b;
		else if( b == null || b.length == 0) return a;
		int[] sum = new int[Math.max(a.length, b.length) + 1];
		int carryFwd = 0,temp = 0, i = 0, j=0;
		while( i < a.length && j <b.length)
		{
			temp = a[i] + b[j] + carryFwd;
			sum[i] = temp % 10;
			carryFwd = temp/10;
			i++;j++;
		}
		while( j < b.length)
		{
			temp = b[j] + carryFwd;
			sum[i] = temp % 10;
			carryFwd = temp/10;
			j++;
		}
		while( i < a.length)
		{
			temp = a[i] + carryFwd;
			sum[i] = temp % 10;
			carryFwd = temp/10;
			i++;
		}
		if(carryFwd > 0)
			sum[Math.max(i, j)] = carryFwd;
		return sum;
	}

	public static String printArray(int [] a)
	{
		if(a == null || a.length ==0) return "";
		StringBuffer sb = new StringBuffer();
		for(int i= a.length -1 ; i >= 0 ;i--)
		{
			sb.append(a[i]);
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		int[] a = {9,3,2,8};
		int[] b = {1,2,3};
		System.out.println("The sum of arrays a: "+printArray(a)+ " and b: "+printArray(b));
		System.out.println(printArray(addArrays(a, b)));
	}
	
}
