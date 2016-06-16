package com.questions.strings;

import java.util.HashSet;
import java.util.Set;

/**Remove duplicate characters in a given string keeping only the first occurrences. 
 * For example, if the input is ‘tree traversal’ the output will be ‘tre avsl’
 * 
 * http://www.ardendertat.com/2012/01/06/programming-interview-questions-25-remove-duplicate-characters-in-string/
 * @author Ram Komma
 *
 */
public class RemoveDupChars {

	public static String removeDupChars(String word)
	{
		if(word == null || word.length() == 0)
			return word;
		StringBuffer uniqeCharStr = new StringBuffer();
		Set<Character> seenChars = new HashSet<Character>();
		for(Character c: word.toCharArray())
		{
			if( !seenChars.contains(c) )
			{
				seenChars.add(c);
				uniqeCharStr.append(c);
			}
		}
		return uniqeCharStr.toString();
	}
	public static void main(String[] args) {
		System.out.println(removeDupChars("tree traversal"));
	}

}
