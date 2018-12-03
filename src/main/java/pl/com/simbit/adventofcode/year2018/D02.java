package pl.com.simbit.adventofcode.year2018;

import java.util.List;
import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;
import pl.com.simbit.utility.string.StringUtils;

public class D02 implements Day {

  private String file = "year2018/d02.txt";

  @Override
  public Object problem1() {

    List<String> fileContent = FileReader.readLines(StreamReader.readFile(file));

    int c2 = 0, c3 = 0;

    for (String s : fileContent) {
      boolean b2 = false;
      boolean b3 = false;
      for (int i = 0; i < s.length(); i++) {
        if (!b2 && StringUtils.getInstance().count(s, s.charAt(i)) == 2) {
          b2 = true;
          c2++;
        }
        if (!b3 && StringUtils.getInstance().count(s, s.charAt(i)) == 3) {
          b3 = true;
          c3++;
        }
        if (b2 && b3) {
          break;
        }
      }
    }

    return c2 * c3;
  }

  @Override
  public Object problem2() {

    List<String> fileContent = FileReader.readLines(StreamReader.readFile(file));

    int max = 0;

    StringBuilder temp;
    String ss2 = "";

    for (String s1 : fileContent) {
      for (String s2 : fileContent) {
        if (s1.equals(s2)) {
          continue;
        }
        temp = new StringBuilder();
        for (int i = 0; i < s1.length(); i++) {
          if (s1.charAt(i) == s2.charAt(i)) {
            temp.append(s1.charAt(i));
          }
        }
        if (temp.length() > max) {
          max = temp.length();
          ss2 = temp.toString();
        }
      }
    }

    return ss2;
  }
}
