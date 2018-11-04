package com.questions.misc;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.LinkedMap;

public class CollectionTests {
  public static  void main(String[] args) {
    Map<String, String> result = new HashMap<>(2);
    System.out.println(MapUtils.isEmpty(result));

    Map<String, String> testMap = new LinkedHashMap<>();
    testMap.put("Komma", "Ram");
    testMap.get("Komma");
    testMap.containsKey("Komma");
  }
}
