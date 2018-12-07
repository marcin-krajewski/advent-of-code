package pl.com.simbit.adventofcode.year2018;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

public class D04 implements Day {

  private String file = "year2018/d04.txt";

  @Override
  public Object problem1() {

    List<String> lines = FileReader.readLines(StreamReader.readFile(file));
    List<GuardEntry> entries = new ArrayList<>();

    Map<Integer, int[]> map = new HashMap<>();

    for (String line : lines) {
      GuardEntry guard = create(line);
      entries.add(guard);
      if (guard.id != null && map.get(guard.id) == null) {
        map.put(guard.id, new int[60]);
      }
    }

    Collections.sort(entries, new Comparator<GuardEntry>() {
      @Override
      public int compare(GuardEntry o1, GuardEntry o2) {
        if (o1.year.compareTo(o2.year) != 0) {
          return o1.year.compareTo(o2.year);
        }
        if (o1.month.compareTo(o2.month) != 0) {
          return o1.month.compareTo(o2.month);
        }
        if (o1.day.compareTo(o2.day) != 0) {
          return o1.day.compareTo(o2.day);
        }
        if (o1.m.compareTo(o2.m) != 0) {
          return o1.m.compareTo(o2.m);
        }
        if (o1.h.compareTo(o2.h) != 0) {
          return o1.h.compareTo(o2.h);
        }
        return 0;
      }
    });

    String si = "            ";
    String sj = "            ";
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 10; j++) {
        si += i;
        sj += j;
      }
    }

    System.out.println(si);
    System.out.println(sj);

    GuardEntry first = entries.get(0);
    GuardEntry last = entries.get(entries.size() - 1);

    Integer currentId = -1;
    int index = 0;

    for (int m = first.month; m <= last.month; m++) {
      for (int d = m == first.month ? first.day : 1;
          (m != last.month && d <= 31) || (m == last.month && d <= last.day);
          d++) {

        String line = "%2s-%2s %5s ";
        boolean sleep = false;
        for (int min = 0; min < 60; min++) {

          if (index < entries.size()) {
            GuardEntry current = entries.get(index);
            if (current.id == null && current.same2(m, d, min)) {
              sleep = current.o == Operation.sleep;
              index++;
            }
            if (sleep) {
              try {

                map.get(currentId)[min]++;
              } catch (NullPointerException e) {
                int a = 0;
              }
            }

            if (index < entries.size()) {
              current = entries.get(index);
              if (current.id != null && current.same2(m, d, min)) {
                currentId = current.id;
                index++;
              }
            }
          }

          line += sleep ? "#" : ".";

        }
        System.out.format(line, m, d, currentId);
        System.out.println();

      }
    }

    int max = 0;
    int maxI = -1;
    for (Entry<Integer, int[]> entry : map.entrySet()) {
      int c = 0;
      for (int i = 0; i < 60; i++) {
        c += entry.getValue()[i];
      }
      if (c > max) {
        max = c;
        maxI = entry.getKey();
      }
    }
    int max2 = 0;
    int max2i = -1;
    for (int i = 0; i < 60; i++) {
      int val = map.get(maxI)[i];
      if (val > max2) {
        max2 = val;
        max2i = i;
      }
    }
    for (int val : map.get(maxI)) {
      if (val > max2) {
        max2 = val;
        max2i = val;
      }
    }

    return maxI * max2i;

  }

  @Override
  public Object problem2() {
    List<String> lines = FileReader.readLines(StreamReader.readFile(file));
    List<GuardEntry> entries = new ArrayList<>();

    Map<Integer, int[]> map = new HashMap<>();

    for (String line : lines) {
      GuardEntry guard = create(line);
      entries.add(guard);
      if (guard.id != null && map.get(guard.id) == null) {
        map.put(guard.id, new int[60]);
      }
    }

    Collections.sort(entries, new Comparator<GuardEntry>() {
      @Override
      public int compare(GuardEntry o1, GuardEntry o2) {
        if (o1.year.compareTo(o2.year) != 0) {
          return o1.year.compareTo(o2.year);
        }
        if (o1.month.compareTo(o2.month) != 0) {
          return o1.month.compareTo(o2.month);
        }
        if (o1.day.compareTo(o2.day) != 0) {
          return o1.day.compareTo(o2.day);
        }
        if (o1.m.compareTo(o2.m) != 0) {
          return o1.m.compareTo(o2.m);
        }
        if (o1.h.compareTo(o2.h) != 0) {
          return o1.h.compareTo(o2.h);
        }
        return 0;
      }
    });

    String si = "            ";
    String sj = "            ";
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 10; j++) {
        si += i;
        sj += j;
      }
    }

    System.out.println(si);
    System.out.println(sj);

    GuardEntry first = entries.get(0);
    GuardEntry last = entries.get(entries.size() - 1);

    Integer currentId = -1;
    int index = 0;

    for (int m = first.month; m <= last.month; m++) {
      for (int d = m == first.month ? first.day : 1;
          (m != last.month && d <= 31) || (m == last.month && d <= last.day);
          d++) {

        String line = "%2s-%2s %5s ";
        boolean sleep = false;
        for (int min = 0; min < 60; min++) {

          if (index < entries.size()) {
            GuardEntry current = entries.get(index);
            if (current.id == null && current.same2(m, d, min)) {
              sleep = current.o == Operation.sleep;
              index++;
            }
            if (sleep) {
              try {

                map.get(currentId)[min]++;
              } catch (NullPointerException e) {
                int a = 0;
              }
            }

            if (index < entries.size()) {
              current = entries.get(index);
              if (current.id != null && current.same2(m, d, min)) {
                currentId = current.id;
                index++;
              }
            }
          }

          line += sleep ? "#" : ".";

        }
        System.out.format(line, m, d, currentId);
        System.out.println();

      }
    }

    int max = 0;
    int maxM = -1;
    int maxI = -1;
    for (Entry<Integer, int[]> entry : map.entrySet()) {
      for (int i = 0; i < 60; i++) {
        if (entry.getValue()[i] > max) {
          max = entry.getValue()[i];
          maxI = entry.getKey();
          maxM = i;
        }
      }
    }
    return maxI * maxM;
  }

  private GuardEntry create(String line) {
    GuardEntry g = new GuardEntry();

    if (line.contains("shift")) {
      g.o = Operation.begin;
      g.id = Integer.parseInt(line.substring(line.indexOf("#") + 1, line.indexOf(" begin")));
    } else if (line.contains("wakes")) {
      g.o = Operation.wake;
    } else {
      g.o = Operation.sleep;
    }
    g.year = Integer.parseInt(line.substring(1, 5));
    g.month = Integer.parseInt(line.substring(6, 8));
    g.day = Integer.parseInt(line.substring(9, 11));

    g.h = Integer.parseInt(line.substring(12, 14));
    g.m = Integer.parseInt(line.substring(15, 17));

    return g;
  }

  class GuardEntry {

    Integer year;
    Integer month;
    Integer day;
    Integer h;
    Integer m;
    Operation o;
    Integer id;

    boolean same(Integer id) {
      return this.id != null && !id.equals(this.id);
    }

    public boolean same2(Integer mm, Integer dd, Integer min) {
      return mm.equals(month) && dd.equals(day) && min.equals(m);
    }
  }

  enum Operation {
    begin, wake, sleep
  }
}
