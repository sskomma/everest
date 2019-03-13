package com.questions.graphs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * #TopologicalSort
 */
public class CourseSchedule {

  /**There are a total of n courses you have to take, labeled from 0 to n-1.
   * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
   *  Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
   *
   * Example 1:
   *  Input: 2, [[1,0]]
   *  Output: true
   *  Explanation: There are a total of 2 courses to take.
   *  To take course 1 you should have finished course 0. So it is possible.
   *
   * Example 2:
   *  Input: 2, [[1,0],[0,1]]
   *  Output: false
   *  Explanation: There are a total of 2 courses to take.
   *  To take course 1 you should have finished course 0, and to take course 0 you should
   *  also have finished course 1. So it is impossible.
   *
   * Note:
   *  The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
   *  You may assume that there are no duplicate edges in the input prerequisites.
   *
   * https://leetcode.com/problems/course-schedule/description/
   * @param numCourses number of courses.
   * @param prerequisites a list of edges.
   * @return
   */
  public static boolean canFinish(int numCourses, int[][] prerequisites) {
    if (prerequisites == null || prerequisites.length == 0) {
      return true;
    }
    // Input is given in Edge List format. Which is less easy to navigate, so creating a adj List.
    // Here are other formats:https://www.khanacademy.org/computing/computer-science/algorithms/graph-representation/a/representing-graphs
    Map<Integer, List<Integer>> adjList = new HashMap<>();
    Map<Integer, String> status = new HashMap<>();
    for (int i = 0; i < prerequisites.length; i++) {
      List<Integer> adjNodes = adjList.getOrDefault(prerequisites[i][0], new ArrayList<>());
      adjNodes.add(prerequisites[i][1]);
      status.putIfAbsent(prerequisites[i][1], "NOT_VISITED");
      status.putIfAbsent(prerequisites[i][0], "NOT_VISITED");
      adjList.putIfAbsent(prerequisites[i][0], adjNodes);
    }
    //NOTE: Notice that the above adjList is constructed with inward degree. i.e, each vertex will have a list of all upstream vertices.

    Deque<Integer> stack = new LinkedList<>();
    for (Integer vertex : adjList.keySet()) {
      if (status.get(vertex).equals("VISITED")) {
        continue;
      }
      stack.push(vertex);
      status.put(vertex, "VISITING");

      while (!stack.isEmpty()) {
        Integer course = stack.peek();
        boolean noPreReqsExist = true;
        if (adjList.get(course) != null) {
          for (Integer preReq : adjList.get(course)) {
            if (status.get(preReq).equals("VISITING")) {
              // Loop exists
              return false;
            } else if (status.get(preReq).equals("NOT_VISITED")) {
              noPreReqsExist = false;
              stack.push(preReq);
              status.put(preReq, "VISITING");
            }
          }
        }
        if (noPreReqsExist) {
          status.put(course, "VISITED");
          stack.pop();
        }
      }
    }
    return true;
  }

  /**
   * https://leetcode.com/problems/course-schedule-ii/description/
   * There are a total of n courses you have to take, labeled from 0 to n-1.
   * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
   * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
   * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
   *
   * @param numCourses number of courses.
   * @param prerequisites a list of edges.
   * @return
   */
  public static int[] findOrder(int numCourses, int[][] prerequisites) {
    if (prerequisites == null || prerequisites.length == 0) {
      int[] courses = new int[numCourses];
      for (int i = 0; i < numCourses; i++) {
        courses[i] = i;
      }
      return courses;
    }
    //Adjacency Map.
    //Unlike the above solution this is not an inward map. Trying with outward map.
    Map<Integer, List<Integer>> adjMap = new HashMap<>();
    Map<Integer, String> status = new HashMap<>();
    for (int i = 0; i < prerequisites.length; i++) {
      List<Integer> adjNodes = adjMap.getOrDefault(prerequisites[i][1], new ArrayList<>());
      adjNodes.add(prerequisites[i][0]);
      adjMap.putIfAbsent(prerequisites[i][1], adjNodes);
      status.putIfAbsent(prerequisites[i][0], "NOT_VISITED");
      status.putIfAbsent(prerequisites[i][1], "NOT_VISITED");
    }

    Deque<Integer> orderStack = new LinkedList<>();
    Deque<Integer> dfsStack = new LinkedList<>();
    for (Integer course : adjMap.keySet()) {
      if (status.get(course).equals("VISITED")) {
        continue;
      }
      dfsStack.push(course);
      status.put(course, "VISITING");
      while (!dfsStack.isEmpty()) {
        Integer vertex = dfsStack.peek();
        boolean noNxtCoursesExists = true;
        if (adjMap.get(vertex) != null) {
          for (Integer nextCourse : adjMap.get(vertex)) {
            if (status.get(nextCourse).equals("NOT_VISITED")) {
              noNxtCoursesExists = false;
              dfsStack.push(nextCourse);
              status.put(nextCourse, "VISITING");
            } else if (status.get(nextCourse).equals("VISITING")) {
              return new int[0];
            }
          }
        }
        if (noNxtCoursesExists) {
          status.put(vertex, "VISITED");
          dfsStack.pop();
          orderStack.push(vertex);
        }
      }
    }
    int[] order = new int[orderStack.size()];
    for (int i = 0; !orderStack.isEmpty(); i++) {
      order[i] = orderStack.pop();
    }
    return order;
  }

  public static void main(String[] args) {
    int[][] edges = {{1, 2}, {1, 3}, {1, 6}, {2, 3}, {2, 4}, {5, 6}, {5, 2}};
    int[][] edges2 = {{1, 0}};
    //System.out.println(canFinish(2, edges2));
    for (Integer course : findOrder(2, edges)) {
      System.out.println(course);
    }
  }
}
