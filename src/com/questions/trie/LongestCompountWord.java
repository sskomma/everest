package com.questions.trie;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**Given a sorted list of words, find the longest compound word in the list that is constructed by concatenating the words in the list. 
 * For example, if the input list is: 
 * [‘cat’, ‘cats’, ‘catsdogcats’, ‘catxdogcatsrat’, ‘dog’, ‘dogcatsdog’, ‘hippopotamuses’, ‘rat’, ‘ratcat’, ‘ratcatdog’, * ‘ratcatdogcat’]. 
 * Then the longest compound word is ‘ratcatdogcat’ with 12 letters. 
 * Note that the longest individual words are ‘catxdogcatsrat’ and ‘hippopotamuses’ with 14 letters, 
 * but they’re not fully constructed by other words. Former one has an extra ‘x’ letter, and latter is an individual word by itself not a compound word.
 * 
 * http://www.ardendertat.com/2012/06/15/programming-interview-questions-28-longest-compound-word/
 * @author Ram Komma
 *
 */
public class LongestCompountWord {

	public String findLongestCompountWord(String[] words)
	{
		Trie trie = new Trie();
		Arrays.sort(words);
		Queue<Pair> pairs = new LinkedList<Pair>();
		String largestCompountWord = "";
		for(String s: words)
		{
			List<String> prefix = trie.insertAndGetPrefixes(s);
			for(String pre: prefix)
				pairs.add(new Pair(s, s.substring(pre.length())));
		}
		while(!pairs.isEmpty())
		{
			Pair p = pairs.remove();
			List<String> prefixes = trie.getPrefixWords(p.suffix);
			if(prefixes == null)
			{
				continue;
			}
			if(prefixes.isEmpty())
			{
				largestCompountWord = 
						largestCompountWord.length() < p.key.length() ? p.key:largestCompountWord;
			}
			else
			{
				for(String pre: prefixes)
					pairs.add(new Pair(p.key, p.suffix.substring(pre.length())));
			}
		}
		return largestCompountWord;
	}
	class Pair
	{
		String key;
		String suffix;
		Pair(String key, String prefix)
		{
			this.key = key;
			this.suffix = prefix;
		}
		@Override
		public String toString() {
			return "Pair [key=" + key + ", suffix=" + suffix + "]";
		}
		
	}
	
	public static void main(String[] args) {
		LongestCompountWord finder = new LongestCompountWord();
		String[] words = {"amazon","retail","apple","pie","applepie","amazonapplepieretailx"};
		System.out.println(finder.findLongestCompountWord(words));
	}

}
