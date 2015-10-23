package com.leetcode.problem.one;

import java.util.HashMap;
import java.util.Map;

/**Description: 
 * 
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target, 
 * where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
 * 
 * You may assume that each input would have exactly one solution.
 * 
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 * 
 * @author Ram Komma
 * @email sskomma@hotmail.com
 *
 */
public class TwoSums
{

    public static int[] twoSum(int[] nums, int target) {
        
        Map differencesMap = new HashMap();
        for(int i = 0; i< nums.length; i++)
        {
            differencesMap.put(target - nums[i],i);
        }
        Integer index = null;
        int j=0;
        for(  j=0; j < nums.length; j++)
        {
             index = (Integer)differencesMap.get(nums[j]);
            if(index !=null &&  index!= j)
            {
                break; 
            }
        }
        
        int[] results = {j+1,index+1};
        return results;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);
        System.out.println(result[0]);
        System.out.println(result[1]);

    }

}
