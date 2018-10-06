package com.questions.trees;

import java.util.Comparator;

public class TestComparator {
    static class Person{
        String name;
        String getName(){return name;}
        void setName(String name){
            this.name = name;
        }
    }


    public static void main(String[] args) {
        Comparator<String> nameComparator = (personNameLeft, personNameRight) -> {
            personNameLeft = personNameLeft+" ";
          return personNameLeft.compareTo(personNameRight);
        };
        Comparator<Person> c = Comparator.comparing(Person::getName,nameComparator);
    }
}
