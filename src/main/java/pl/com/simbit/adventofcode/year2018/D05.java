package pl.com.simbit.adventofcode.year2018;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

public class D05 implements Day {

  private String file = "year2018/d05.txt";

  int a = Character.valueOf('a');
  int z = Character.valueOf('z');
  int A = Character.valueOf('A');
  int Z = Character.valueOf('Z');

  @Test
  public void testCharDiff() {
    Assert.assertEquals(32, a - A);
    Assert.assertEquals(32, z - Z);
  }

  @Override
  public Object problem1() {

    String line = FileReader.firstLine(StreamReader.readFile(file));

    return length(line);
  }

  public int length(String line) {
    boolean found = true;
    while (found) {
      found = false;
      int firstIndex = -1;
      int lastIndex = -1;
      for (int i = 0; i < line.length(); i++) {
        firstIndex = i;
        if (found(i, line)) {
//          i++;
          lastIndex = i + 1;
          found = true;
        }
        if (found) {
          break;
        }
      }
      if (found) {
        line = line.substring(0, firstIndex) + line.substring(lastIndex + 1, line.length());
      }

    }

    return line.length();
  }

  private boolean found(int i, String line) {
    if (i + 1 == line.length()) {
      return false;
    }
    if (line.charAt(i) <= Z && line.charAt(i) >= A) {
      return line.charAt(i + 1) - line.charAt(i) == 32;
    }
    if (line.charAt(i) <= z && line.charAt(i) >= a) {
      return line.charAt(i) - line.charAt(i + 1) == 32;
    }
    return false;
  }

  @Override
  public Object problem2() {
    String line = FileReader.firstLine(StreamReader.readFile(file));

    int min = Integer.MAX_VALUE;
    for (int i = A; i <= Z; i++) {
      String line2 = line.replaceAll(Character.toString((char) i), "");
      line2 = line2.replaceAll(Character.toString((char) (i + 32)), "");
      int len = length(line2);
      if (len < min) {
        min = len;
      }
    }
    return min;
  }
}
