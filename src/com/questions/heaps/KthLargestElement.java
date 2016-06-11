package com.questions.heaps;

import java.util.PriorityQueue;

/**Find the kth largest element in an unsorted array. 
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * 
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 * 
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * @author Ram Komma
 */
public class KthLargestElement {

	public static int findKthLargest(int[] nums, int k) 
	{
		if(nums == null || nums.length == 0)
			return 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		int j =0;
		for(Integer i : nums)
		{
			if(j < k)
				pq.add(i);
			else
			{
				if(pq.peek() < i)
				{
					pq.poll();
					pq.add(i);
				}
			}
			j++;
		}
		return pq.poll();
	}
	public static void main(String[] args) 
	{
		int[] nums = {1, 2};
		System.out.println(findKthLargest(nums, 2));
	}

}
