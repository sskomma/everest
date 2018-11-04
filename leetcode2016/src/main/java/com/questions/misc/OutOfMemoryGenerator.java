package com.questions.misc;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryGenerator {
  public static void main(String[] args) {
    List<ObjectForLeak> list = new ArrayList<>();
    while (true) {
      list.add(new ObjectForLeak());
    }
  }

  static class ObjectForLeak {
    void display() {
      System.out.println("Inside the leak method");
    }
  }
}
