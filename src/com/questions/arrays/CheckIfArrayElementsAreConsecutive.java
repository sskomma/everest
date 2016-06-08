package com.questions.arrays;

import java.util.HashSet;
import java.util.Set;

/**Description: 
 * 
 * Identify, if the given array has all unique consecutive elements.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * 
 * @author Ram Komma
 *
 */
public class CheckIfArrayElementsAreConsecutive
{
    
    /**
     * Method determines if the given list of integers are consecutive. 
     * 
     * @param elements list of integers to be checked for consecutiveness.
     * @return true if consecutive, false if not. 
     */
    
    public static boolean areElementsConsecutive(int[] elements)
    {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        Set<Integer> visited = new HashSet<Integer>();
        for(int element: elements)
        {
            if(visited.contains(element))
                return false;
            visited.add(element);
            min = element < min ? element: min;
            max = element > max ? element: max;
        }
        
        return ( max - min + 1 == elements.length );
    }

    public static void main(String[] args)
    {
        int[] elements =  {5, 3, 2, 1, 4};
        System.out.println(areElementsConsecutive(elements));

    }

}
