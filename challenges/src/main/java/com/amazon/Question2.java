package com.amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * #amazon
 */
public class Question2 {
  public List<Integer> minWindow(List<String> targetList, List<String> availableTagList) {
    if (targetList.size() > availableTagList.size()) {
      return Collections.emptyList();
    }
    Map<String, Integer> targetListMap = new HashMap<>();
    for (String tag : targetList) {
      targetListMap.put(tag, targetListMap.getOrDefault(tag, 0) + 1);
    }
    int counter = targetListMap.size();

    int begin = 0, end = 0;
    int head = 0;
    int len = Integer.MAX_VALUE;

    while (end < availableTagList.size()) {
      String c = availableTagList.get(end);
      if (targetListMap.containsKey(c)) {
        targetListMap.put(c, targetListMap.get(c) - 1);
        if (targetListMap.get(c) == 0) {
          counter--;
        }
      }
      end++;

      while (counter == 0) {
        String tempc = availableTagList.get(begin);
        if (targetListMap.containsKey(tempc)) {
          targetListMap.put(tempc, targetListMap.get(tempc) + 1);
          if (targetListMap.get(tempc) > 0) {
            counter++;
          }
        }
        if (end - begin < len) {
          len = end - begin;
          head = begin;
        }
        begin++;
      }
    }
    if (len == Integer.MAX_VALUE) {
      return Collections.emptyList();
    }
    List<Integer> result = new ArrayList<>();
    result.add(head);
    result.add(head + len);
    return result;
  }
}
