package com.questions.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class DecodeString {
  public static String decodeString(String s) {
    if(s == null || s == "") {
      return s;
    }
    String result = "";
    Deque<Integer> numStack = new LinkedList<>();
    Deque<String> resultStack = new LinkedList<>();
    int i = 0;
    while(i < s.length()) {
      if(Character.isDigit(s.charAt(i))) {
        int k = 0;
        while(Character.isDigit(s.charAt(i))) {
          k = k*10 + (s.charAt(i) - '0');
          i++;
        }
        numStack.push(k);
      } else if (s.charAt(i) == '[') {
        resultStack.push(result);
        result = "";
        i++;
      } else if( s.charAt(i) == ']' ) {
        StringBuilder temp = new StringBuilder(resultStack.pop());
        int freq = numStack.pop();
        for(int j = 0; j < freq; j++) {
          temp.append(result);
        }
        result = temp.toString();
        i++;
      }else {
        result = result + s.charAt(i);
        i++;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(decodeString("3[a2[c]]"));
    System.out.println(decodeString("2[abc]3[cd]e"));
  }
}
