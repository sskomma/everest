package com.questions.strings;

/**Problem:  Find the substring in the given text.
 * 
 * Algorithm: Knuth-Morris-Pratt(KMP) Pattern matching. The complexity of this algorith is O(m+n),
 * where m is the length of text and n is the length of pattern
 * 
 * Thanks to Tushar Roy, his video helped me understand this algorithm. 
 * https://www.youtube.com/watch?v=GTJr8OvyEVQ
 * 
 * @author Ram Komma
 */
public class SubStringSearch
{

    /**This private method computes the temporary array which tells, where the next comparison can occur.  
     * 
     * @param pattern that needs to be found in text
     * @return integer array, with comparision positions. 
     */
    private int[] computeTemporaryArray(char[] pattern)
    {
        int j=0, i=1;
        
        int[] tempArray = new int[pattern.length];
        tempArray[j] = 0;
        
        while(i < pattern.length)
        {
            if(pattern[j] == pattern[i])
            {
                tempArray[i] = j+1;
                i++;j++;
            }
            else 
            {
                if( j != 0)
                {
                    j = tempArray[j-1];
                }
                else
                {
                    tempArray[i] =0;
                    i++;
                }
            }
        }
        return tempArray;
    }
    
    /** Method to find the starting position of pattern in text, if exists. 
     * 
     * @param text in which pattern needs to be searched. 
     * @param pattern that needs to be searched in the text. 
     * @return -1 if text or pattern are empty, 0 if the pattern is not found, starting position of pattern in text 
     */
    public int findSubString(String text, String pattern)
    {
        if(text == null || pattern == null || text.isEmpty() || pattern.isEmpty())
            return -1;
        //Return with out search, of pattern is bigger than text.
        if(pattern.length() > text.length()) return 0;
        
        int[] tempArray = computeTemporaryArray(pattern.toCharArray());
        
        for(int t = 0, p = 0; t < text.length() && p < pattern.length(); )
        {
            if(text.charAt(t) == pattern.charAt(p))
            {
                t++; 
                p++;
                if( p == pattern.length())
                    return (t-p);
            }
            else
            {
              if(p != 0)
                  p = tempArray[p-1];
              else
              {
                  t++;
              }
            }
        }
        return 0;
    }
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        SubStringSearch finder = new SubStringSearch();
        int x = finder.findSubString("abxabcabcaby", "abcaby");
        
        if(x == -1)
            System.out.println("Either Text or pattern or both are empty");
        else if(x == 0)
            System.out.println("Pattern is not found in text");
        else
            System.out.format("Pattern found at %d th position in text", x);
    }

}
