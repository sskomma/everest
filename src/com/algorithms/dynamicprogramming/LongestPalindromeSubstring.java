package com.algorithms.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/** 
 * Description: https://leetcode.com/problems/longest-palindromic-substring/
 * 
 * Given a string S, find the longest palindromic substring in S. 
 * You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 * 
 * @author Ram Komma
 */

public class LongestPalindromeSubstring
{

	private Map<String,Boolean> palindromInfo = new HashMap<String, Boolean>();
    /**
     * @param args
     */
	public static String isSubStringPalindrome(String string)
	{
		int maxLength =1; 
		int start = 0;
		int begin, end =0;
		for(int i = 1 ; i < string.length(); i++)
		{
			//Even numbered palindromic string
			begin = i - 1;
			end = i;
			while(begin >= 0 && end < string.length() && string.charAt(begin) == string.charAt(end))
			{
				if(end - begin + 1 > maxLength){
					start = begin;
					maxLength = end - begin +1;
				}
				begin--;
				end++;
			}
			//Odd lengthed palindromic string
			begin = i-1;
			end = i+1;
			while(begin >= 0 && end < string.length() && string.charAt(begin) == string.charAt(end))
			{
				if(end - begin + 1 > maxLength){
					start = begin;
					maxLength = end - begin +1;
				}
				begin--;
				end++;
			}
		}
		System.out.format("Largest Palindromic string occurs at :%d and is %d long \n", start,maxLength );
		return string.substring(start, start+maxLength);
	}
	
    public static void main(String[] args)
    {
    	System.out.println(isSubStringPalindrome("forgeeksskeegfor"));

    }

}
