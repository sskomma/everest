package com.questions.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AnagramsInList
{

    /**
     * This method fails in case with words like "Nana", "papa".. because both end up having xor of 0.  
     * */
    public static void findAnagram(String[] words)
    {
        Map<Integer, List<Integer>> xorMap = new HashMap<Integer, List<Integer>>();
        
        for(int i = 0; i < words.length; i++)
        {
            int xor = 0;
            for(char c: words[i].toCharArray())
            {
                xor = xor ^ c;
            }
            List<Integer> list;

            if(xorMap.get(xor) == null)
                list= new ArrayList<Integer>();
            else
                list =  xorMap.get(xor);
            
            list.add(i);
            xorMap.put(xor, list);
        }
        for(int key :xorMap.keySet())
        {
            if( ((List<Integer>)xorMap.get(key)).size() > 1 )
            {
                System.out.println("Anagrams words are");
                for(int i:xorMap.get(key))
                {
                    System.out.println(words[i]);
                }
            }
        }
    }
    
    public static void findAnagramsInList(String[] words)
    {
        Map<String,List<String>> anagramMap = new HashMap<String, List<String>>();
        for(String word: words)
        {
            char[] arrayHolder = word.toCharArray();
            Arrays.sort(arrayHolder);
            String sortedWord = String.valueOf(arrayHolder);
            
            List<String> anagramList;
            if(!anagramMap.containsKey(sortedWord))
            {
                anagramList = new LinkedList<String>();
            }
            else
            {
                anagramList = anagramMap.get(sortedWord);
            }
            anagramList.add(word);  
            anagramMap.put(sortedWord, anagramList);
        }
        
        for(String key: anagramMap.keySet())
        {
            List<String> anagramList = anagramMap.get(key);
            if (anagramList.size() > 1)
            {
                System.out.println("Set of anagrams");
                for(String word: anagramList)
                    System.out.println(word);
            }
        }
    }
    
    
    public static void main(String[] args)
    {
        String[] words = {"iceman","cinema","mary","army","cat","baba","nana","abba","nnaa"};
        findAnagramsInList(words);
    }

}
