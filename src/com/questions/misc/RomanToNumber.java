package com.questions.misc;

import java.util.HashMap;
import java.util.Map;

public class RomanToNumber {

    private static int[] numbers = {1,4,5,9,10,40,50,90,100,400,500,900,1000};
    private static Map<Integer,String> numberToRomanMap;
    private static Map<String,Integer> romanToNumberMap;
    private static Map<Character,Integer> romans;
    
    static
    {
        numberToRomanMap = new HashMap<Integer, String>();
        numberToRomanMap.put(1, "I");
        numberToRomanMap.put(4, "IV");
        numberToRomanMap.put(5, "V");
        numberToRomanMap.put(9, "IX");
        numberToRomanMap.put(10, "X");
        numberToRomanMap.put(40, "XL");
        numberToRomanMap.put(50, "L");
        numberToRomanMap.put(90, "XC");
        numberToRomanMap.put(100, "C");
        numberToRomanMap.put(400, "CD");
        numberToRomanMap.put(500, "D");
        numberToRomanMap.put(900, "CM");
        numberToRomanMap.put(1000, "M");
        
        romans = new HashMap<Character, Integer>();
        romans.put('M', 1000);
        romans.put('D', 500);
        romans.put('C', 100);
        romans.put('L', 50);
        romans.put('X', 10);
        romans.put('V', 5);
        romans.put('I', 1);
        
        romanToNumberMap = new HashMap<String, Integer>();
        romanToNumberMap.put("I", 1);
        romanToNumberMap.put("IV", 4);
        romanToNumberMap.put("V", 5);
        romanToNumberMap.put("IX",9);
        romanToNumberMap.put("X", 10);
        romanToNumberMap.put("XL", 40);
        romanToNumberMap.put("L", 50);
        romanToNumberMap.put("XC", 90);
        romanToNumberMap.put("C", 100);
        romanToNumberMap.put("CD", 400);
        romanToNumberMap.put("D", 500);
        romanToNumberMap.put("CM",900);
        romanToNumberMap.put("M", 1000);
        
        
    }
    
    public String convertNumberToRoman(int number)
    {
        StringBuffer sb = new StringBuffer();
        return convertNumberToRoman(number, sb).toString();
    }

    private StringBuffer convertNumberToRoman(int number, StringBuffer sb)
    {
        if(number <=0) return sb;
        for(int i = numbers.length -1; i>= 0 ; i--)
        {
            if(number >= numbers[i])
            {
                sb.append(numberToRomanMap.get(numbers[i]));
                number -= numbers[i];
                break;
            }
        }
        convertNumberToRoman(number,sb);
        return sb;
    }
    
    public int convertRomanToNumber(String roman)
    {
        char[] chars = roman.toCharArray();
        int number = 0;
        for(int i = 0; i < chars.length ; i++)
        {
            if( i < chars.length-1  && romans.get(chars[i]) < romans.get(chars[i+1]))
            {
                number += romanToNumberMap.get(String.valueOf(chars[i]) + String.valueOf(chars[i+1]));
                i++;
            }
            else
            {
                number += romanToNumberMap.get(String.valueOf(chars[i]));
            }
        }
        return number;
    }

    
    public static void main(String[] args) {
        RomanToNumber converter = new RomanToNumber();
        String s = converter.convertNumberToRoman(3999);
        System.out.println(s);
        System.out.println(converter.convertRomanToNumber(s));

    }

}
