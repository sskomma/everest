package com.questions.misc;

public class CountPrimes
{
    public static int countPrimesNumbers(int n)
    {
    	boolean[] isPrime = new boolean[n];
    	//Initialize an boolean array to true
    	for(int i = 2;i < n; i++)
    		isPrime[i] = true;
    	
    	for(int i = 2; i*i < n; i++)
    	{
    		if( !isPrime[i]) continue;
    		for(int j = i*i; j < n; j += i )
    			isPrime[j] = false;
    	}
    	int count = 0;
    	for(int i = 2; i < n; i++)
    		if(isPrime[i])count++;
    	return count;
    }
    
    public static void main(String[] args)
    {
       System.out.println(countPrimesNumbers(15));
    }
}
