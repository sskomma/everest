package com.questions.heaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**Given a non-empty array of integers, return the k most frequent elements.
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2]
 * 
 * https://leetcode.com/problems/top-k-frequent-elements/
 * @author Ram Komma
 *
 */

class Pair
{
	int element;
	int frequency;
}

public class TopKFrequentElements {

	public static List<Integer> topKFrequentElements(int[] numbers, int k)
	{
		if(numbers == null || numbers.length == 0)
			return null;
					
		Map<Integer, Pair> numFreqMap = new HashMap<Integer, Pair>();
		for(int i:numbers)
		{
			Pair pair = numFreqMap.get(i);
			if(pair == null)
			{
				pair = new Pair();
				pair.element = i;
				pair.frequency = 0;
			}
			pair.frequency = pair.frequency +1;
			numFreqMap.put(i, pair);
		}

		PriorityQueue<Pair> kElements = new PriorityQueue<Pair>(k, new Comparator<Pair>() {
			public int compare(Pair a, Pair b)
			{
				return new Integer(a.frequency).compareTo(new Integer(b.frequency));
			}
		});
		int j = 0;
		for(Integer key: numFreqMap.keySet())
		{
			if(j < k)
			{
				kElements.add(numFreqMap.get(key));
			}
			else
			{
				Pair head = kElements.peek();
				Pair temp = numFreqMap.get(key);
				if(temp.frequency > head.frequency)
				{
					kElements.poll();
					kElements.add(temp);
				}
			}
			j++;
		}
		List<Integer> topKFreq = new ArrayList<Integer>();
		for(Object i: kElements.toArray())
			topKFreq.add(((Pair)i).element);
		return topKFreq;
	} 
	
	public static void main(String[] args) {
		int[] numbers = {1,1,1,3,4,4,5,5,5,5,6,2,3,8,8,9,0};
		int k = 2;
		System.out.println( "Top "+ k+ " Frequent elements are: ");
		topKFrequentElements(numbers, k);
	}
}
