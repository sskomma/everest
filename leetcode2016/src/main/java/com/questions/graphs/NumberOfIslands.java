package com.questions.graphs;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 *
 * Example 2:
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 *
 * https://leetcode.com/problems/number-of-islands/description/
 * #leetcode200
 *
 * @author Ram Komma
 */
public class NumberOfIslands {

  public static void main(String[] args) {
    NumberOfIslands numberOfIslands = new NumberOfIslands();
    char[][] grid =
        {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}};
    char[][] grid2 =
        {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}};
    char[][] grid3 = {{'1', '1', '1'}, {'0', '1', '0'}, {'1', '1', '1'}};
    //System.out.println(numberOfIslands.numIslands(grid));
    System.out.println(numberOfIslands.numIslands(grid3));
  }

  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int count = 0;
    int rows = grid.length;
    int columns = grid[0].length;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (grid[i][j] == '1') {
          dfs(grid, i, j, rows, columns);
          count++;
        }
      }
    }
    return count;
  }

  private void dfs(char[][] grid, int x, int y, int rows, int columns) {
    if (x >= rows || y >= columns || x < 0 || y < 0 || grid[x][y] == '0') {
      return;
    }
    if (grid[x][y] == '1') {
      grid[x][y] = '0';
      dfs(grid, x + 1, y, rows, columns);
      dfs(grid, x - 1, y, rows, columns);
      dfs(grid, x, y + 1, rows, columns);
      dfs(grid, x, y - 1, rows, columns);
    }
  }
}
