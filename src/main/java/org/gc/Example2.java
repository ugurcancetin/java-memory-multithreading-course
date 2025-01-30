package org.gc;

import java.util.ArrayList;
import java.util.List;

public class Example2 {

  //Run this with -verbose:gc
  public static void main(String[] args) {
    List<Engineer> engineerList = new ArrayList<>();

    while (true) {
      Engineer newEngineer = new Engineer("Test");
      engineerList.add(newEngineer);
      if (engineerList.size() > 100_000_000) {
        for (int i = 0; i < 10_000; i++) {
          engineerList.remove(0);
        }
      }
    }
  }


}
