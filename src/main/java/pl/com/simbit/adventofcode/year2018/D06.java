package pl.com.simbit.adventofcode.year2018;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

public class D06 implements Day {

  private String file = "year2018/d06.txt";

  @Override
  public Object problem1() {

    List<List<Integer>> numbers = FileReader
        .readNumberMatrixForSeparator(StreamReader.readFile(file), ", ");

    int offset = 10;
    int size = offset + 1000 + offset;
    int[][] nums = new int[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        nums[i][j] = -1;
      }
    }
    Map<Integer, Integer> count = new HashMap<>();
    Set<Integer> toAnalyze = new HashSet<>();
    for (int i = 0; i < numbers.size(); i++) {
      List<Integer> dim = numbers.get(i);
      nums[offset + dim.get(1)][offset + dim.get(0)] = (i + 1);
      toAnalyze.add(i + 1);
      count.put(i + 1, 0);
    }

    for (int i = 0; i < size; i++) {
      String s = "";
      for (int j = 0; j < size; j++) {

        int min = Integer.MAX_VALUE;
        for (int k = 0; k < numbers.size(); k++) {
          List<Integer> dim = numbers.get(k);
          int ii = offset + dim.get(1);
          int jj = offset + dim.get(0);

          int len = Math.abs(ii - i) + Math.abs(jj - j);
          if (len < min) {
            min = len;
            nums[i][j] = (k + 1);
          } else if (len == min) {
            nums[i][j] = -1;
          }

        }
      }
    }

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (nums[i][j] > 0 && (i == 0 || j == 0 || i == size - 1
            || j == size - 1)) {
          toAnalyze.remove(nums[i][j]);
        }
      }
    }

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (nums[i][j] > 0) {
          count.put(nums[i][j], count.get(nums[i][j]) + 1);
        }
      }
    }
    int max = 0;
    for (Entry<Integer, Integer> entry : count.entrySet()) {
      if (toAnalyze.contains(entry.getKey())) {
        if (max < entry.getValue()) {
          max = entry.getValue();
        }
      }
    }

    return max;
  }

  @Override
  public Object problem2() {
    List<List<Integer>> numbers = FileReader
        .readNumberMatrixForSeparator(StreamReader.readFile(file), ", ");

    int offset = 10;
    int size = offset + 1000 + offset;
    int[][] nums = new int[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        nums[i][j] = -1;
      }
    }
    for (int i = 0; i < numbers.size(); i++) {
      List<Integer> dim = numbers.get(i);
      nums[offset + dim.get(1)][offset + dim.get(0)] = (i + 1);
    }

    int count = 0;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {

        int sum = 0;
        for (int k = 0; k < numbers.size(); k++) {
          List<Integer> dim = numbers.get(k);
          int ii = offset + dim.get(1);
          int jj = offset + dim.get(0);

          int len = Math.abs(ii - i) + Math.abs(jj - j);
          sum += len;
        }
        if (sum < 10000) {
          count++;
        }
      }
    }

    return count;
  }
}
