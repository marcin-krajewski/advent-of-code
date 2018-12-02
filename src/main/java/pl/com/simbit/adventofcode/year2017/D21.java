package pl.com.simbit.adventofcode.year2017;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;
import pl.com.simbit.utility.string.StringUtils;

public class D21 implements Day {

  private String[][] input = new String[][]{new String[]{".", "#", "."},
      new String[]{".", ".", "#"},
      new String[]{"#", "#", "#"}};

  private String file = "year2017/d21.txt";

  @Override
  public Object problem1() {
    Image image = new Image();
    image.images = input;

    Map<String, String> flips = new HashMap<>();

    List<String> lines = FileReader.readLines(StreamReader.readFile(file));
    for (String line : lines) {
      flips.put(line.split(" => ")[0], line.split(" => ")[1]);
    }

    for (int i = 0; i < 5; i++) {
      image.split(flips);
    }

    int c = 0;
    for (int i = 0; i < image.images.length; i++) {
      for (int j = 0; j < image.images.length; j++) {
        if (image.images[i][j].equals("#")) {
          c++;
        }
      }
    }

    return c;
  }

  @Override
  public Object problem2() {
    Image image = new Image();
    image.images = input;

    Map<String, String> flips = new HashMap<>();

    List<String> lines = FileReader.readLines(StreamReader.readFile(file));
    for (String line : lines) {
      flips.put(line.split(" => ")[0], line.split(" => ")[1]);
    }

    for (int i = 0; i < 18; i++) {
      image.split(flips);
    }

    int c = 0;
    for (int i = 0; i < image.images.length; i++) {
      for (int j = 0; j < image.images.length; j++) {
        if (image.images[i][j].equals("#")) {
          c++;
        }
      }
    }

    return c;
  }

  class Image {

    String[][] images;

    void split(Map<String, String> flips) {

      int flipSize = 3;
      double mul;
      if (this.images.length % 2 == 0) {
        flipSize = 2;
        mul = 3.0 / 2.0;
      } else {
        mul = 4.0 / 3.0;
      }
      int newSize = (int) ((double) this.images.length * mul);
      String[][] newImages = new String[newSize][newSize];

      int inti, intj;
      for (int i = 0; i < images.length; i += flipSize) {
        for (int j = 0; j < images.length; j += flipSize) {
          inti = (int) (i * mul);
          intj = (int) (j * mul);
          String n = flip(flips, subarray(images, i, j, flipSize));
          for (String s : n.split("/")) {
            for (int k = 0; k < s.length(); k++) {
              newImages[inti][intj + k] = StringUtils.getInstance().stringAt(s, k);
            }
            inti++;
          }
        }
      }

      this.images = newImages;
    }

    private String flip(Map<String, String> flips, String[][] subarray) {
      return flipString(flips, subarray);
    }

    private String flipString(Map<String, String> flips, String[][] subarray) {
      for (String option : flipOptions(subarray)) {
        if (flips.get(option) != null) {
          return flips.get(option);
        }
      }
      throw new RuntimeException("Not found option! " + flips);
    }

    private Set<String> flipOptions(String[][] subarray) {
      Set<String> flips = new HashSet<>();

      StringBuilder sr0 = new StringBuilder();
      StringBuilder sr1 = new StringBuilder();
      StringBuilder sr2 = new StringBuilder();
      StringBuilder sr3 = new StringBuilder();
      StringBuilder sr4 = new StringBuilder();
      StringBuilder sr5 = new StringBuilder();
      StringBuilder sr6 = new StringBuilder();
      StringBuilder sr7 = new StringBuilder();

      int subLen = subarray.length;
      int subLenM1 = subLen - 1;

      for (int i = 0; i < subLen; i++) {
        for (int j = 0; j < subLen; j++) {
          sr0.append(subarray[i][j]);
          sr5.append(subarray[subLenM1 - i][j]);
          sr7.append(subarray[subLenM1 - i][subLenM1 - j]);
          sr6.append(subarray[i][subLenM1 - j]);

          sr1.append(subarray[j][subLenM1 - i]);
          sr2.append(subarray[j][i]);
          sr3.append(subarray[subLenM1 - j][i]);
          sr4.append(subarray[subLenM1 - j][subLenM1 - i]);
        }
        sr0.append("/");
        sr1.append("/");
        sr2.append("/");
        sr3.append("/");
        sr4.append("/");
        sr5.append("/");
        sr6.append("/");
        sr7.append("/");
      }
      int end = sr0.length() - 1;
      flips.add(sr0.substring(0, end));
      flips.add(sr1.substring(0, end));
      flips.add(sr2.substring(0, end));
      flips.add(sr4.substring(0, end));
      flips.add(sr3.substring(0, end));
      flips.add(sr5.substring(0, end));
      flips.add(sr6.substring(0, end));
      flips.add(sr7.substring(0, end));

      return flips;
    }

    private String[][] subarray(String[][] images, int i, int j, int flipSize) {
      if (flipSize == 2) {
        return new String[][]{new String[]{images[i][j], images[i][j + 1]},
            new String[]{images[i + 1][j], images[i + 1][j + 1]},};
      }
      return new String[][]{new String[]{images[i][j], images[i][j + 1], images[i][j + 2]},
          new String[]{images[i + 1][j], images[i + 1][j + 1], images[i + 1][j + 2]},
          new String[]{images[i + 2][j], images[i + 2][j + 1], images[i + 2][j + 2]}};
    }
  }
}
