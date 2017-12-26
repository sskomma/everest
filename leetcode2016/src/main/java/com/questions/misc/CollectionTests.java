package com.questions.misc;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

public class CollectionTests {
  public static  void main(String[] args) {
    Map<String, String> result = new HashMap<String, String>(2);
    System.out.println(MapUtils.isEmpty(result));

  }
}
