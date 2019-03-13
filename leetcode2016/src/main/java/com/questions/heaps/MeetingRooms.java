package com.questions.heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRooms {

  public static void main(String[] args) {
    Interval[] iv =
        {new Interval(2, 15), new Interval(36, 45), new Interval(9, 29), new Interval(16, 23),
            new Interval(4, 9)};
    System.out.println("Minimum number of rooms:  " + minimumMeetingRooms(iv));
  }

  /**
   * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
   * find the minimum number of conference rooms required.
   *
   * For example,
   * Given [[0, 30],[5, 10],[15, 20]],
   * return 2.
   *
   * https://leetcode.com/problems/meeting-rooms-ii/description/
   * @param intervals, intervals the meetings occur at.
   * @return minimum number of meeting rooms required to handles all the intervals.
   */
  public static int minimumMeetingRooms(Interval[] intervals) {
    if (intervals == null || intervals.length == 0) {
      return 0;
    }
    Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
    PriorityQueue<Interval> queue = new PriorityQueue<>((a, b) -> Integer.compare(a.end, b.end));
    queue.add(intervals[0]);
    for (int i = 1; i < intervals.length; i++) {
      Interval iv = queue.poll();
      if (iv.end > intervals[i].start) {
        queue.add(intervals[i]);
      } else {
        iv.end = intervals[i].end;
      }
      queue.add(iv);
    }

    return queue.size();
  }

  /**
   * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
   * determine if a person could attend all meetings.
   *
   * For example,
   * Given [[0, 30],[5, 10],[15, 20]],
   * return false
   * https://leetcode.com/problems/meeting-rooms/description/
   */
  public boolean canAttendMeetings(Interval[] intervals) {
    if (intervals == null || intervals.length == 0) {
      return false;
    }
    Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
    int endTime = 0;
    for (Interval interval : intervals) {
      if (endTime <= interval.start) {
        endTime = Math.max(endTime, interval.end);
      } else {
        return false;
      }
    }
    return true;
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
