package pl.com.simbit.adventofcode.year2016;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.junit.Assert;
import org.junit.Test;
import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.com.simbit.utility.string.StringUtils;

public class D04 implements Day {

  private String file = "year2016/d04.txt";

  @Override
  public Object problem1() {
    List<String> lines = FileReader.readLines(StreamReader.readFile(file));

    int realRooms = 0;
    for (String line : lines) {
      int lastDashIndex = line.lastIndexOf("-");
      int squareBracketOpenIndex = line.indexOf("[");
      Room room = new Room(line.substring(0, lastDashIndex),
          Integer.parseInt(line.substring(lastDashIndex + 1, squareBracketOpenIndex)),
          line.substring(squareBracketOpenIndex + 1, line.length() - 1));
      if (room.isRealRoom()) {
        realRooms += room.id;
      }
    }
    return realRooms;
  }

  @Override
  public Object problem2() {
    List<String> lines = FileReader.readLines(StreamReader.readFile(file));

    int realRooms = 0;
    for (String line : lines) {
      int lastDashIndex = line.lastIndexOf("-");
      int squareBracketOpenIndex = line.indexOf("[");
      Room room = new Room(line.substring(0, lastDashIndex),
          Integer.parseInt(line.substring(lastDashIndex + 1, squareBracketOpenIndex)),
          line.substring(squareBracketOpenIndex + 1, line.length() - 1));

      String decrypted = room.decryptedName();

      if (decrypted.contains("pole")) {
        System.out.println(decrypted);
        return room.id;
      }
    }
    return "not found";
  }

  class Room {

    String name;
    int id;
    String checksum;

    public Room(String name, int id, String checksum) {
      this.name = name;
      this.id = id;
      this.checksum = checksum;
    }

    public boolean isRealRoom() {
      return countedChecksum().startsWith(checksum);
    }

    private String countedChecksum() {
      Map<Character, Integer> counts = new HashMap<>();
      for (int i = 0; i < name.length(); i++) {
        char c = name.charAt(i);
        if (c == '-') {
          continue;
        }
        counts.put(c, StringUtils.getInstance().count(name, c));
//        incrementCountInMap(c, counts);
      }

      List<Character> c = new ArrayList<>(counts.keySet());
      c.sort(new Comparator<Character>() {
        @Override
        public int compare(Character o1, Character o2) {
          Integer cc1 = counts.get(o1);
          Integer cc2 = counts.get(o2);
          if (cc1.equals(cc2)) {
            return o1.compareTo(o2);
          }
          return cc1 > cc2 ? -1 : 1;
        }
      });

      StringBuilder sb = new StringBuilder();
      for (Character cc : c) {
        sb.append(cc);
      }
      return sb.toString();
    }

    private void incrementCountInMap(Character c, Map<Character, Integer> values) {
      Integer integer = values.get(c);
      if (integer == null) {
        return;
      }
      values.put(c, integer + 1);
    }

    @Override
    public String toString() {
      return "Room{" + "name='" + name + '\'' + ", id=" + id + ", checksum='" + checksum + '\''
          + '}';
    }

    public String decryptedName() {

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < name.length(); i++) {
        char c = name.charAt(i);
        if (c == '-') {
          c = ' ';
        } else {
          for (int j = 0; j < id; j++) {
            c++;
            if (c > 'z') {
              c = 'a';
            }
          }
        }
        sb.append(c);
      }

      return sb.toString();
    }
  }

  @Test
  public void test() {
    Room r = new Room("qzmt-zixmtkozy-ivhz", 343, "");

    assertEquals("very encrypted name", r.decryptedName());
  }
}
