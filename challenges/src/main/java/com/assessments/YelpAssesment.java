package com.assessments;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class YelpAssesment {

  public static void main(String[] args) {
    String[] s1 = {"El Farolito", "Japa Curry", "Eatsa"};
    String[] s2 = {"Japa Curry", "Eatsa", "Ayola", "Working Girls"};
    System.out.println(favoriteRestaurant(Arrays.asList(s1), Arrays.asList(s2)));
  }

  public static String favoriteRestaurant(List<String> restaurants_1, List<String> restaurants_2) {
    if ((restaurants_1 == null && restaurants_2 == null) || (restaurants_1.size() == 0
        && restaurants_2.size() == 0)) {
      return "Yelpwich";
    }
    if (restaurants_1 == null || restaurants_1.size() == 0) {
      return restaurants_2.get(0);
    }
    if (restaurants_2 == null || restaurants_2.size() == 0) {
      return restaurants_1.get(0);
    }
    Map<String, Integer> rankMap = new HashMap<>();
    Set<String> common = new HashSet<>();

    int rank = 1;
    for (String restaurant : restaurants_1) {
      rankMap.put(restaurant, rank++);
    }
    rank = 1;
    for (String restaurant : restaurants_2) {
      if (rankMap.containsKey(restaurant)) {
        common.add(restaurant);
        rankMap.put(restaurant, rankMap.get(restaurant) + rank);
        rank++;
      }
    }
    if (common.isEmpty()) {
      return "Yelpwich";
    }
    String result = "Yelpwich";
    int combinedRank = Integer.MAX_VALUE;
    for (String restaurant : common) {
      rank = rankMap.get(restaurant);
      if(rank < combinedRank) {
        combinedRank = rank;
        result = restaurant;
      }
    }
    return result;
  }
}
