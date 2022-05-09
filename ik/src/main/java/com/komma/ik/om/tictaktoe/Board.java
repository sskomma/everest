package com.komma.ik.om.tictaktoe;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

public class Board {

    private static final String CIRCLE = " O ";
    private static final String CROSS = " X ";
    private boolean toggle;

    String[][] board = new String[3][3];
    private Integer turns = 9;
    public Board() {
        for(int i = 0; i< 3; i++) {
            for(int j = 0; j< 3; j++) {
                board[i][j] = "   ";
            }
        }
    }

    void printBoard() {
        Function<String[], String> printer = row -> StringUtils.join(row, "|");
        String x = StringUtils.join(Arrays.stream(board).map(printer).collect(Collectors.toList()), "\n___ ___ ___\n");
        System.out.println(x);
    }

    public void play() throws IOException {

        while(turns != 0) {
            Pair<Integer, Integer> xy = readInput();
            if(!"   ".equals(board[xy.getLeft()][xy.getRight()])) {
                System.out.println("Invalid input");
                continue;
            }
            board[xy.getLeft()][xy.getRight()] = toggle ? CIRCLE: CROSS;
            turns--;
            toggle = !toggle;
            Runtime.getRuntime().exec("cls");
            printBoard();
        }

    }
    Pair<Integer, Integer> readInput() throws IOException {
        System.out.println("x:");
        int x = System.in.read();
        System.out.println("y:");
        int y = System.in.read();
        return Pair.of(x, y);
    }

    public static void main(String[] args) throws IOException {
        Board b = new Board();
        b.play();

    }

}
