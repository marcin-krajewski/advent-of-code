package pl.com.simbit.adventofcode.year2018;

import java.util.List;
import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

public class D03 implements Day {

  private String file = "year2018/d03.txt";

  @Override
  public Object problem1() {
    List<String> lines = FileReader.readLines(StreamReader.readFile(file));

    int[][] arr = new int[2000][2000];

    for (String line : lines) {
      String[] inches = line.substring(line.indexOf("@ ") + 2, line.indexOf(":")).split(",");
      String[] dim = line.substring(line.indexOf(": ") + 2).split("x");

      for (int i = Integer.parseInt(inches[0]);
          i < Integer.parseInt(dim[0]) + Integer.parseInt(inches[0]); i++) {
        for (int j = Integer.parseInt(inches[1]);
            j < Integer.parseInt(dim[1]) + Integer.parseInt(inches[1]); j++) {
          arr[i][j]++;
        }
      }

    }

    int c = 0;
    for (int i = 0; i < 2000; i++) {
      for (int j = 0; j < 2000; j++) {
        if (arr[i][j] > 1) {
          c++;
        }
      }
    }

    return c;
  }

  @Override
  public Object problem2() {
    List<String> lines = FileReader.readLines(StreamReader.readFile(file));

    int[][] arr = new int[2000][2000];

    for (String line : lines) {
      String[] inches = line.substring(line.indexOf("@ ") + 2, line.indexOf(":")).split(",");
      String[] dim = line.substring(line.indexOf(": ") + 2).split("x");

      boolean over = true;
      for (int i = Integer.parseInt(inches[0]);
          i < Integer.parseInt(dim[0]) + Integer.parseInt(inches[0]); i++) {
        for (int j = Integer.parseInt(inches[1]);
            j < Integer.parseInt(dim[1]) + Integer.parseInt(inches[1]); j++) {
          arr[i][j]++;
        }
      }

    }

    for (String line : lines) {
      String[] inches = line.substring(line.indexOf("@ ") + 2, line.indexOf(":")).split(",");
      String[] dim = line.substring(line.indexOf(": ") + 2).split("x");

      boolean over = true;
      for (int i = Integer.parseInt(inches[0]);
          i < Integer.parseInt(dim[0]) + Integer.parseInt(inches[0]); i++) {
        for (int j = Integer.parseInt(inches[1]);
            j < Integer.parseInt(dim[1]) + Integer.parseInt(inches[1]); j++) {
          if (arr[i][j] > 1) {
            over = false;
          }
        }
      }
      if (over) {
        return line.substring(1, line.indexOf(" @"));
      }

    }

    return null;
  }
}
