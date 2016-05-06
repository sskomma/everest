package com.questions.strings;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * 
 * Example 1:
 * Given s = "hello", return "holle".
 * 
 * Example 2:
 * Given s = "leetcode", return "leotcede".
 *
 * https://leetcode.com/problems/reverse-vowels-of-a-string/
 * 
 * @author Ram Komma
 */
public class ReverseVowelsOfString
{

    
    public static String reverseVowels(String s)
    {
        if(s == null || s.isEmpty() )
            return s;
        //Init Vowels set
        Character[] vowelArray = {'a','e','i','o','u','A','E','I','O','U'};
        Set<Character> vowelSet = new HashSet<Character>();
        Collections.addAll(vowelSet, vowelArray);
        
        StringBuffer sb = new StringBuffer(s);
        int startPtr = 0;
        int endPtr = sb.length()-1;
        while(startPtr < endPtr)
        {
            while(startPtr < endPtr)
            {
                if(vowelSet.contains(sb.charAt(startPtr)))
                {
                    break;
                }
                else
                {
                    startPtr++;
                }
            }
            while(startPtr < endPtr)
            {
                if(vowelSet.contains(sb.charAt(endPtr)))
                {
                    break;
                }
                else
                {
                    endPtr--;
                }
            }
            char temp = sb.charAt(startPtr);
            sb.setCharAt(startPtr, sb.charAt(endPtr));
            sb.setCharAt(endPtr, temp);
            startPtr++;
            endPtr--;
        }
        return sb.toString();
    }
    
    
    public static void main(String[] args)
    {
       System.out.println(reverseVowels("aA"));

    }

}
