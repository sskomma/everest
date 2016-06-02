package com.questions.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * Subscribe to see which companies asked this question
 * 
 * @author Ram Komma
  */
public class ValidParentheses {

	public static boolean validBrackets(String s)
	{
		if(s== null || s.isEmpty())
			return false;
		Map<Character, Integer> valueMap = new HashMap<Character, Integer>();
		valueMap.put('(', 1);
		valueMap.put(')', -1);
		valueMap.put('[', 2);
		valueMap.put(']', -2);
		valueMap.put('{', 3);
		valueMap.put('}', -3);
		Deque<Integer> stack = new ArrayDeque<Integer>();

		for(Character c: s.toCharArray())
		{
			int charValue = valueMap.get(c);
			if(charValue < 0)
			{
				if(stack.peek() == null)
					return false;
				else if((stack.peek() + charValue) != 0)
					return false;
				stack.pop();
			}
			else
				stack.push(charValue);
		}
		if(stack.isEmpty())
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(validBrackets("()"));

	}

}
