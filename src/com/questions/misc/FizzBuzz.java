package com.questions.misc;

public class FizzBuzz
{

    public static void fizzBuzz(int number)
    {
        int threeReminder = number % 3;
        int fiveReminder = number % 5;
        
        if(threeReminder == 0 && fiveReminder == 0)
            System.out.println("FizzBuzz");
        else if(threeReminder == 0)
            System.out.println("Fizz");
        else if(fiveReminder == 0)
            System.out.println("Buzz");
        else
            System.out.println("No Fizz, no Buzz");
    }

    public static void main(String[] args)
    {
        fizzBuzz(-30);

    }

}
