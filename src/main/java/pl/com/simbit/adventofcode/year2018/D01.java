package pl.com.simbit.adventofcode.year2018;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

public class D01 implements Day {

  private String file = "year2018/d01.txt";

  @Override
  public Object problem1() {
    List<Integer> fileContent = FileReader.readLinesAsNumbers(StreamReader.readFile(file));

    int c = 0;
    for (int num : fileContent) {
      c += num;
    }
    return c;
  }

  @Override
  public Object problem2() {
    List<Integer> fileContent = FileReader.readLinesAsNumbers(StreamReader.readFile(file));

    Integer c = 0;
    Set<Integer> set = new LinkedHashSet<>();
    set.add(c);
    while (true) {
      for (Integer num : fileContent) {
        c = new Integer(c + num);
        if (set.contains(c)) {
          System.out.println(set
          );
          return c;
        }
        set.add(c);
      }
    }
//    return -1;
  }
}
