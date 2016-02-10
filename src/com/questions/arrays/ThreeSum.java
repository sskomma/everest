package com.questions.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.<br>
 * Note: <br>
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c) <br>
 * The solution set must not contain duplicate triplets. <br>
 *  For example, given array S = {-1 0 1 2 -1 -4},<br>
 *  A solution set is: <br>
 *        (-1, 0, 1) <br>
 *        (-1, -1, 2)<br>
 * 
 * https://leetcode.com/problems/3sum/
 * http://www.geeksforgeeks.org/find-a-triplet-that-sum-to-a-given-value/
 * 
 * @author Ram Komma
 *
 */
public class ThreeSum
{
    /**
     * @param args
     */
    public static List<List<Integer>> threeSum(int[] nums)
    {
        if(nums == null || nums.length == 0 ) return null;
        Arrays.sort(nums);
        
        Set<Triplet> setOfTriplets = new HashSet<Triplet>();
        
        for(int i = 0; i < nums.length; i++ )
        {
            int a = nums[i];
            int si = i+1;
            int ei = nums.length -1;
            while(si < ei)
            {
                if( a + nums[si] + nums[ei] == 0)
                {
                    Triplet t = new Triplet(a, nums[si++], nums[ei--]);
                    setOfTriplets.add(t);
                }
                else if( a + nums[si] + nums[ei] > 0)
                    ei--;
                else
                    si++;
            }
        }
        
        return convertSetToList(setOfTriplets);
    }

    private static List<List<Integer>> convertSetToList(Set<Triplet> setOfTriplets)
    {
        List<List<Integer>> listOfTriplets = new ArrayList<List<Integer>>();
        for(Triplet t: setOfTriplets)
            listOfTriplets.add(t.getListOfNumbers());
        return listOfTriplets;
    }
    
    public static void main(String[] args)
    {
        int[] S = {7,-1,14,-12,-8,7,2,-15,8,8,-8,-14,-4,-5,7,9,11,-4,-15,-6,1,-14,4,3,10,-5,2,1,6,11,2,-2,-5,-7,-6,2,-15,11,-6,8,-4,2,1,-1,4,-6,-15,1,5,-15,10,14,9,-8,-6,4,-6,11,12,-15,7,-1,-9,9,-1,0,-4,-1,-12,-2,14,-9,7,0,-3,-4,1,-2,12,14,-10,0,5,14,-1,14,3,8,10,-8,8,-5,-2,6,-11,12,13,-7,-12,8,6,-13,14,-2,-5,-11,1,3,-6};
        for(List<Integer> i:threeSum(S))
        {
            System.out.print("[");
            for(Integer j:i )
                System.out.print(j + " ");
            System.out.println("],");
        }
        
    }

    static class Triplet
    {
        int a, b, c;
        public Triplet(int x, int y, int z)
        {
            a= x; b= y; c= z;
        }
        public List<Integer> getListOfNumbers()
        {
            List<Integer> triplet = new ArrayList<Integer>();
            triplet.add(a);
            triplet.add(b);
            triplet.add(b);
            return triplet;
        }
        public boolean eqals(Object obj)
        {
            Triplet secondObj = (Triplet)obj;
            return this.a == secondObj.a && this.b == secondObj.b && this.c == secondObj.c;
        }
        @Override
        public int hashCode()
        {
            int primeNumber = 31;
            int primeNumber2 = 37;
            int primeNumber3 = 41;
            int primeNumber4 = 43;
            return ((a+b+c) * primeNumber );//;+ (a+b-c) * primeNumber2+ (a+c-b) * primeNumber3 + (c+b-a) * primeNumber4);
        }
        
    }
    
}
