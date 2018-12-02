package pl.com.simbit.adventofcode.year2017;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class D23 implements Day {

  private String file = "year2017/d23.txt";

  @Override
  public Object problem1() {

    List<String> lines = FileReader.readLines(StreamReader.readFile(file));

    List<D18.Operation> operations = new ArrayList<>();

    long last = -1;
    for (String line : lines) {
      operations.add(new D18().factory(line.split(" ")[0], line.split(" ")));
    }

    Map<String, Long> values = new HashMap<>();
    values.put("a", 0L);
    values.put("b", 0L);
    values.put("c", 0L);
    values.put("d", 0L);
    values.put("e", 0L);
    values.put("f", 0L);
    values.put("g", 0L);
    values.put("h", 0L);
    values.put("i", 0L);
    values.put("j", 0L);
    values.put("k", 0L);
    values.put("l", 0L);
    values.put("m", 0L);
    values.put("n", 0L);
    values.put("o", 0L);
    values.put("p", 0L);

    int currentOperation = 0;
    int c = 0;
    while (true) {
      if (currentOperation < 0 || currentOperation >= operations.size()) {
        return c;
      }
      D18.Operation o = operations.get(currentOperation);
      long value = o.setValue(values);
      if (o instanceof D18.Mul || o instanceof D18.Mulr) {
        c++;
      }
      currentOperation = o.nextOperation(values, currentOperation);
    }
  }

  @Override
  public Object problem2() {
    List<String> lines = FileReader.readLines(StreamReader.readFile(file));

    List<D18.Operation> operations = new ArrayList<>();

    long last = -1;
    for (String line : lines) {
      operations.add(new D18().factory(line.split(" ")[0], line.split(" ")));
    }

    Map<String, Long> values = new LinkedHashMap<>();
    values.put("a", 1L);
    values.put("b", 0L);
    values.put("c", 0L);
    values.put("d", 0L);
    values.put("e", 0L);
    values.put("f", 0L);
    values.put("g", 0L);
    values.put("h", 0L);
    values.put("i", 0L);
    values.put("j", 0L);
    values.put("k", 0L);
    values.put("l", 0L);
    values.put("m", 0L);
    values.put("n", 0L);
    values.put("o", 0L);
    values.put("p", 0L);

    int currentOperation = 0;
    int c = 0;
    int i = 0;
    int echanged = 0;
    int gchanged = 0;
    long prevH = -1, curH = -1;
    while (true) {

//      if (i < 1000) {

//      }

//      System.out.println("H: " + values.get("h"));
      if (currentOperation < 0 || currentOperation >= operations.size()) {
        return values.get("h");
      }
      D18.Operation o = operations.get(currentOperation);
      long value = o.setValue(values);

      if ("e".equals(o.getRegister()) || "g".equals(o.getRegister())) {
        if ("e".equals(o.getRegister())) {
          echanged++;
        }
        if ("g".equals(o.getRegister())) {
          gchanged++;
        }
      } else {
        System.out.println("G: " + gchanged + ", E: " + echanged);
        gchanged = 0;
        echanged = 0;
        String[] ar = new String[values.size()];
        int a = 0;
        String format = "";
        for (Entry<String, Long> entry : values.entrySet()) {
          format += "| " + millisToPrint(entry.getKey(), o.getRegister()) + " ";
          ar[a++] = entry.getKey() + "=" + entry.getValue();
//          System.out.print(entry.getKey() + "=" + entry.getValue() + "\t\t");
        }
        format += "|";
//        System.out.println();
        System.out.format(
            format,
            ar);
        System.out.println();

      }

      currentOperation = o.nextOperation(values, currentOperation);
      i++;
//      if (i > 5000) {
//        System.out.println(values);
//      }
    }

//    return 0;
  }

  private String millisToPrint(String current, String value) {
    return (current.equals(value) ?
        "\033[0;31m" : "\033[0;32m") + "%10s\033[0m";

  }
}
