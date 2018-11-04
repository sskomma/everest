package com.algorithms.design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/***
 * Bloomberg interview question:
 * https://www.glassdoor.com/Interview/You-have-a-million-people-running-a-marathon-The-progress-of-the-race-is-tracked-via-a-series-of-check-points-A-through-Z-QTN_1897302.htm
 */
public class MarathonLeaderBoard {

  private int[] nextRank;
  private Map<Integer, Integer> ranking;
  private LinkedList<Integer> leaderBoard = new LinkedList<>();

  public MarathonLeaderBoard(int markers, int runners) {
    this.nextRank = new int[markers + 1];
    ranking = new HashMap<>(runners);
  }

  public void updateRunner(int runnerId, int marker) {
    int rank = nextRank[marker];
    nextRank[marker] = rank + 1;
    ranking.put(runnerId, rank);

    if(rank < 10) {
      int index = leaderBoard.indexOf(runnerId);
      if(index != -1) {
        leaderBoard.remove(index);
      } else {
        leaderBoard.removeLast();
      }
      leaderBoard.add(rank, runnerId);
    }
  }

  public void getLeaderBoard() {
    for(Integer runner: leaderBoard) {
      System.out.println("Rank:" + runner);
    }
  }
}
