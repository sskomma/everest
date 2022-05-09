package com.komma.ik.recurssion;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Brackets {

    static String[] find_all_well_formed_brackets(int n) {
        List<String> result = new ArrayList<>();
        build(n, 0, new LinkedList<>(), 0,0, result);
        return result.toArray(new String[0]);
    }

    private static void build(int n, int index, Deque<Character> slate, int numOfOpen, int numOfclose, List<String> result) {
        if(2*n == slate.size()) {
            result.add(fromDeque(slate));
            return;
        }

        // Bracket 1 "("
        if( numOfOpen < n) {
            slate.addLast('(');
            build(n, index+1, slate, numOfOpen+1, numOfclose, result);
            slate.removeLast();
        }


        //Bracket 2 ")"
        if( numOfOpen > numOfclose &&  numOfclose < n) {
            slate.addLast(')');
            build(n, index+1, slate, numOfOpen, numOfclose +1, result);
            slate.removeLast();
        }
    }

    private static String fromDeque(Deque<Character> slate) {
        StringBuilder sb = new StringBuilder();
        Iterator<Character> it = slate.iterator();
        while(it.hasNext()) {
            sb.append(it.next());
        }
        return sb.toString();

    }

    public static void main(String[] args) {
        for(String s: find_all_well_formed_brackets(2)) {
            System.out.println(s);
        }
    }

}
