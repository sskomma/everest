package com.questions.arrays;

import java.util.Arrays;
import java.util.Comparator;

/**Given a list of non negative integers, arrange them such that they form the largest number.
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * Note: The result may be very large, so you need to return a string instead of an integer.
 * 
 * https://leetcode.com/problems/largest-number/
 * 
 * @author Ram Komma
 *
 */
public class LargestNumber {

	public static String largestNumber(int[] nums)
	{
		if(nums == null || nums.length == 0)
			return "";
		String[] numStrs = new String[nums.length];
		int i = 0;
		for(Integer number : nums)
		{
			numStrs[i++] = number.toString();
		}
	    Arrays.sort(numStrs, new Comparator<String>(){
	        public int compare(String s1, String s2){
	            String leftRight = s1+s2;
	            String rightLeft = s2+s1;
	            return leftRight.compareTo(rightLeft);
	        }
	    });
		StringBuffer sb = new StringBuffer();
		for(i = numStrs.length-1; i >= 0; i--)
		{
			System.out.println(numStrs[i]);
			sb.append(numStrs[i]);
		}
		return sb.charAt(0) == '0' ? "0": sb.toString();
	}
	
	public static void main(String[] args) {
		int[] numbers = {8,883};
		System.out.println(largestNumber(numbers));
	}

	/**Over complicated initial solution which works but with a lot of cost. 
	 * Better Comparator was identified and written in anonymous class above. 
	 * 
	 * @author Ram Komma
	 */
	static class NumComparator implements Comparator<String>
	{
		public int compare(String s1, String s2)
		{
			int compare = s1.compareTo(s2);
			//s1 is greater than s2
			if(compare < 0 && s2.startsWith(s1))
			{
				String s2Sub = s2.substring(s1.length());
				return compare(s1, s2Sub);
			}
			//s2 is greater than s1
			else if( compare > 0 && s1.startsWith(s2))
			{
				String s1Sub = s1.substring(s2.length());
				return compare(s1Sub, s2);
			}
			return compare;
		}
	}
}

