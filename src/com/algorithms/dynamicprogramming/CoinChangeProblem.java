package com.algorithms.dynamicprogramming;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.MinimalHTMLWriter;

/**Give a dollar value, and list of denominations find the least number of denominations,
 * to make up the dollar value. 
 * 
 * @author Ram Komma
 * 
 */
public class CoinChangeProblem {

	/**The method finds out the least number of coins to use calculates the dollar amount. 
	 * 
	 * @param dollarValue, the dollar amount to make up with the denominations.
	 * @param denominations, list of coins we have 
	 * @return Map with key denomination and value, number of denominations. 
	 */
	public Map<Integer, Integer> findLeastDenominations(int dollarValue, int[] denominations){
		
		return null;
	}
	
	private Map<Integer, Integer> subProblem(int dollarValue, int[] denominations){
		
		if(dollarValue < denominations[0]) return null;
		Map<Integer, Integer> coinsCountMap = new HashMap<Integer, Integer>();
		
		return null;
	}
	
	private int numberOfCoins(Map<Integer, Integer> denomination)
	{
		int total = 0;
		for(int i:denomination.values())
			total = total + i;
		return total;
	}
	
	private Map<Integer, Integer> leastnumberOfCoins(List<Map<Integer, Integer>> list){
		Map<Integer, Integer> combinationWithLeastCoins = null;
		int leastNumberOfCoins = Integer.MAX_VALUE;
		for(Map<Integer, Integer> item: list){
			int temp = numberOfCoins(item);
			if(leastNumberOfCoins > temp ){
				leastNumberOfCoins = temp;
				combinationWithLeastCoins = item;
			}
		}
		return combinationWithLeastCoins;
	}  
	
	public static void main(String[] args) {
		CoinChangeProblem c = new CoinChangeProblem();
		int[] denominations = {1, 5, 10, 25};
		Map<Integer, Integer> denominationMap =c.findLeastDenominations(10, denominations);
		 
	}


}
