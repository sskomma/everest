package com.questions.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**Interval problems
 * 
 * @author Ram Komma
 */
public class IntervalProblems
{
    
    /**Given a collection of intervals, merge all overlapping intervals.
     * 
     * For example,
     * Given [1,3],[2,6],[8,10],[15,18],
     * return [1,6],[8,10],[15,18].
     *
     * https://leetcode.com/problems/merge-intervals/
     * 
     * @param intervals, List of intervals to merge, if there exists a mapping
     * @return List of merged intervals. 
     */
    public static List<Interval> merge(List<Interval> intervals)
    {
        if(intervals == null || intervals.isEmpty())
            return Collections.emptyList();
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval o1, Interval o2)
            {
                Integer i1 = o1.start;
                Integer i2 = o2.start;
                return i1.compareTo(i2);
            }
        });
        List<Interval> mergedList = new ArrayList<Interval>();
        Interval current = intervals.get(0);
        for(int i = 1; i < intervals.size(); i++)
        {
            Interval c = intervals.get(i);
            if(c.start <= current.end)
            {
                current.end = Math.max(current.end, c.end);
            }
            else
            {
                mergedList.add(current);
                current = c; 
            }
        }
        mergedList.add(current);
        return mergedList;
    }
    
    /**
     * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
     * You may assume that the intervals were initially sorted according to their start times.
     * 
     * Example 1:
     * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
     *
     * Example 2: 
     * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
     * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
     * 
     * https://leetcode.com/problems/insert-interval/
     */
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval)
    {
        intervals.add(newInterval);
        return merge(intervals);
    }
    
    public static void main(String[] args)
    {
        Interval i1 = new Interval(1,3);
        Interval i2 = new Interval(2,6);
        Interval i3 = new Interval(8,10);
        Interval i4 = new Interval(15,18);
        List<Interval> intervals = new ArrayList<Interval>();
        intervals.add(i1);
        intervals.add(i2);
        intervals.add(i3);
        intervals.add(i4);
        for(Interval i:merge(intervals))
        {
            System.out.println(i);
        }
    }
}

class Interval 
{
     int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }
    @Override
    public String toString()
    {
        return "Interval [start=" + start + ", end=" + end + "]";
    }
 }

