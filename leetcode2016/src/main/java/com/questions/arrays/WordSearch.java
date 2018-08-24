package com.questions.arrays;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells
 * are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example:
 *board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 *
 * https://leetcode.com/problems/word-search/description/
 */
class WordSearch {
  public static void main(String[] args) {
    //char[][] array = {{'C', 'A', 'A'}, {'A', 'A', 'A'}, {'B', 'C', 'D'}};
/*    char[][] array = {
        {'A', 'B', 'C', 'E'},
        {'S', 'F', 'C', 'S'},
        {'A', 'D', 'E', 'E'}
    };*/
    char[][] array = {{'A', 'B'}, {'C', 'D'}};
    WordSearch ws = new WordSearch();
    System.out.println(ws.exist(array, "ABDC"));
  }

  public boolean exist(char[][] board, String word) {
    if (board == null || board.length == 0 || board[0].length == 0) {
      return false;
    }

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        Set<Integer> visited = new HashSet<>();
        if (board[i][j] == word.charAt(0) && dfs(board, word, i, j, 0, visited)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean dfs(char[][] board, String word, int x, int y) {
    System.out.println("\nDFS");
    Set<Integer> visited = new HashSet<>();
    Deque<Pair<Integer, Integer>> stack = new LinkedList<>();
    stack.push(Pair.of((x * 10) + y, 1));
    while (!stack.isEmpty()) {

      Pair<Integer, Integer> pair = stack.pop();
      System.out.println(pair);
      int coord = pair.getLeft();
      int length = pair.getRight();
      if (length == word.length()) {
        return true;
      }
      if (visited.contains(coord)) {
        return false;
      }
      x = coord / 10;
      y = coord % 10;
      visited.add((x * 10) + y);
      char character = word.charAt(length);
      if (!visited.contains(((x + 1) * 10) + y) && checkElement(board, character, x + 1, y)) {
        stack.push(Pair.of(((x + 1) * 10) + y, length + 1));
      }
      if (!visited.contains(((x - 1) * 10) + y) && checkElement(board, character, x - 1, y)) {
        stack.push(Pair.of((x - 1) * 10 + y, length + 1));
      }
      if (!visited.contains((x * 10) + y + 1) && checkElement(board, character, x, y + 1)) {
        stack.push(Pair.of((x * 10) + y + 1, length + 1));
      }
      if (!visited.contains((x * 10) + y - 1) && checkElement(board, character, x, y - 1)) {
        stack.push(Pair.of((x * 10) + y - 1, length + 1));
      }
    }
    return false;
  }

  private boolean dfs(char[][] board, String word, int x, int y, int length, Set<Integer> visited) {
    System.out.println("X: " + x + ", Y: " + y + ", length: " + length);
    if (word.length() == length) {
      return true;
    }
    if (visited.contains((x * 100) + y)) {
      return false;
    }

    if (!checkElement(board, word.charAt(length), x, y)) {
      return false;
    }
    visited.add((x * 100) + y);
    if (dfs(board, word, x + 1, y, length + 1, visited) ||
        dfs(board, word, x - 1, y, length + 1, visited) ||
        dfs(board, word, x, y + 1, length + 1, visited) ||
        dfs(board, word, x, y - 1, length + 1, visited)) {
      return true;
    }
    visited.remove((x * 100) + y);
    return false;
  }

  private boolean checkElement(char[][] board, char nxtChar, int x, int y) {
    if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
      return false;
    }
    return board[x][y] == nxtChar;
  }
}