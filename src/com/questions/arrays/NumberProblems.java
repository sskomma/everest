package com.questions.arrays;

/**Set of general problems that are solved in various static methods.. 
 * 
 * @author Ram Komma
 *
 */

public class NumberProblems
{

    /**Description: https://leetcode.com/problems/reverse-integer/
     * 
     * The problem is to reverse a given integer.
     * 
     * @param number
     * @return
     */
    public static int reverseNumber(int number)
    {
        boolean isNegative = number < 0 ;
        number = isNegative ? number * -1: number;
        String numberString = new Integer(number).toString();
        StringBuffer sb = new StringBuffer("");
        char[] chararray = numberString.toCharArray();
        for(int i = numberString.length() - 1; i >= 0; i--)
        {
            sb.append(chararray[i]);
        }
        int reverseNumber;
        try{
            reverseNumber = Integer.parseInt(sb.toString());
            reverseNumber = isNegative ? reverseNumber * -1: reverseNumber;
        }
        catch(Exception e){
            return 0;
        }        
        return reverseNumber;
    }
    
    /**Description: Determine whether an integer is a Palindrome. Do this without extra space.
     * 
     * https://leetcode.com/problems/palindrome-number/
     * 
     * @param number
     * @return true if the give number is a palindrome, false otherwise.  
     */
    public static boolean isPalindrome(int number)
    {
        if(number < 0) return false;
        
        int div = 1;
        while(number / div >= 10)
            div = div * 10;
        int frontNumber, endNumber;
        
        while(div >= 10)
        {
            frontNumber = number / div;
            endNumber = number % 10;
            if(frontNumber != endNumber)
                return false;
            div = div / 10;
            number = number/10;
            number = number % div;
            div = div/10;
        }
        return true;
    }
    
    /** Find the squareroot of a given number rounded down to the nearest integer, without using the sqrt function. 
     * For example, squareroot of a number between [9, 15] should return 3, and [16, 24] should be 4.
     * 
     * http://www.ardendertat.com/2012/01/26/programming-interview-questions-27-squareroot-of-a-number/
     * 
     * @param number, for which square root is to be found. 
     * @return, squareroot of the number passed in.
     */
    public static int sqareRoot(int number)
    {
    	if(number == 0 || number == 1)
    		return number;
    	int high = 1+ (number/ 2);
    	int low = 1, mid = 1;
    	while(1+ low < high)
    	{
    		mid = (low + high)/2;
    		int square = mid * mid;
    		if(square == number)
    			return mid;
    		else if(square < number)
    			low = mid;
    		else
    			high = mid;
    	}
    	return (low+high)/2;
    }
    
    
    public static void main(String[] args)
    {
        System.out.println(sqareRoot(17));
        //System.out.println(reverseNumber(534236469));
        
    }
}
