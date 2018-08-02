package com.questions.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * For example:
 *
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 *
 * https://leetcode.com/problems/merge-intervals/description/
 */
public class MergeIntervals {
  public static void main(String[] args) {
    List<Interval> iv = new ArrayList<>();
    /*iv.add(new Interval(1, 3));
    iv.add(new Interval(2, 6));
    iv.add(new Interval(8, 10));
    iv.add(new Interval(15, 18));*/
    iv.add(new Interval(1, 7));
    iv.add(new Interval(6, 8));
    iv.add(new Interval(2, 4));
    System.out.println(merge(iv));
  }

  public static List<Interval> merge(List<Interval> intervals) {
    if (intervals == null || intervals.isEmpty()) {
      return Collections.emptyList();
    }
    Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
    List<Interval> result = new ArrayList<>();
    Interval current = null;
    for (Interval interval : intervals) {
      if (current == null) {
        current = interval;
      } else if (current.end >= interval.start) {
        current.end = Math.max(interval.end, current.end);
      } else {
        result.add(current);
        current = interval;
      }
    }
    result.add(current);

    return result;
  }

  public static class Interval {
    int start;
    int end;

    public Interval() {
      start = 0;
      end = 0;
    }

    public Interval(int s, int e) {
      start = s;
      end = e;
    }

    @Override
    public String toString() {
      return "Interval{" + "start=" + start + ", end=" + end + '}';
    }
  }
}
