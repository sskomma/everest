package com.algorithms.dynamicprogramming;

import java.util.ArrayList;

/**Give a dollar value, and list of denominations find the least number of denominations,
 * to make up the dollar value. 
 * 
 * @author Ram Komma
 * 
 */
public class CoinChangeProblem {

	/**The method finds out the least number of coins to make up the dollar amount. 
	 * 
	 * @param total, the total dollar amount to make up with the denominations.
	 * @param denominations, list of coins we have.. we have these in infinite supply 
	 * @return Map with key denomination and value, number of denominations. 
	 */
	public int findLeastDenominations(int total, int[] denominations)
	{
		int[] numOfCoins = new int[total+1];
		int[] coinUsed = new int[total+1];
		for(int i = 0; i <= total; i++)
		{
			numOfCoins[i] = 60000; //Random Maximum number
			coinUsed[i] = -1;
		}
		numOfCoins[0] = 0;
		for(int i = 0; i < denominations.length; i++)
		{
			for(int j = 1; j <= total; j++)
			{
				if( j == denominations[i] )
				{
					numOfCoins[j] = 1;
					coinUsed[j] = i;
				}
				else if(denominations[i] < j)
				{
					numOfCoins[j] = Math.min(numOfCoins[j], 1 + numOfCoins[j - denominations[i]]);
					coinUsed[j] = numOfCoins[j] == (1 + numOfCoins[j - denominations[i]]) ? i:coinUsed[j];
				}
			}
		}
		//Identify all coins used to make up the total
		ArrayList<Integer> coins = new ArrayList<Integer>();
		int i = total;
		while(i>0)
		{
			int coin = coinUsed[i];
			i = i - denominations[coin];
			coins.add(denominations[coin]);
		}
		System.out.println("Coins used to make total with least number");
		for(int coin:coins)
			System.out.print(coin + " ");
		System.out.println("");
		return numOfCoins[total];
	}
	
	public static void main(String[] args) {
		CoinChangeProblem c = new CoinChangeProblem();
		int[] denominations = {7,2,3,6};
		int total = 13;
		System.out.println("Least number of coins to make total of "+total + " are :"+c.findLeastDenominations(total, denominations));
	}
}
