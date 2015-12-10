package com.questions.arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**Description: http://www.ardendertat.com/2011/09/17/programming-interview-questions-1-array-pair-sum/
 * 
 *Given an integer array, output all pairs that sum up to a specific value k.
 * 
 * @author Ram Komma
 *
 */

public class ArrayPairSum 
{
	public List<Pair> findPairsThatSumUpToK(int[] input, int sum)
	{
		Set<Integer> set = new TreeSet<Integer>();
		List<Pair> list = new ArrayList<Pair>();
		for(int element: input)
		{
			if( set.contains( sum-element) )
			{
				set.remove(element -sum);
				Pair p = new Pair(element, (sum-element));
				list.add(p);
			}
			else
				set.add(element);
		}
		return list;
	}
	public static void main(String[] args)
	{
		ArrayPairSum arrayPairs = new ArrayPairSum();
		int[] input = {1, 1, 2, 3, 4};
		
		List<Pair> list = arrayPairs.findPairsThatSumUpToK(input, 4);
		for(Pair p: list)
			System.out.println(p);
	}
	public class Pair
	{
		int a, b;
		public Pair(int a, int b){ this.a = a; this.b = b;}
		@Override
		public String toString() 
		{
			return "Pair [a=" + a + ", b=" + b + "]";
		}
	}
}

