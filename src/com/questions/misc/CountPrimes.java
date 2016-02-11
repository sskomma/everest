package com.questions.misc;

import java.util.ArrayList;
import java.util.List;

public class CountPrimes
{

    public static int countPrimes(int n)
    {
        if(n <= 1)
            return 0;
        List<Integer> primes = new ArrayList<Integer>();
        for(int i = 2; i <= n; i++)
        {
            boolean isPrime = true;
            for(int divideBy: primes)
            {
                if( i % divideBy == 0)
                {
                    isPrime = false;
                    break;
                }
            }
            if(isPrime)
                primes.add(i);
        }
        return primes.size();
    }
    
    public static void main(String[] args)
    {
       System.out.println(countPrimes(2));

    }

}
