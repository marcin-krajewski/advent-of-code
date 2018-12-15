package pl.com.simbit.adventofcode.year2018;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

public class D08 implements Day {

  private String file = "year2018/d08.txt";

  @Override
  public Object problem1() {

    List<Integer> numbers = FileReader
        .readNumberMatrixForSeparator(StreamReader.readFile(file), " ").get(0);

    Counter c = new Counter();
    Counter index = new Counter();

    readNode(c, numbers, index);

    return c.value();
  }

  private void readNode(Counter c, List<Integer> numbers, Counter index) {
    if (index.value() >= numbers.size()) {
      return;
    }

    int nodes = numbers.get(index.value());
    index.increment();
    int metadata = numbers.get(index.value());
    index.increment();
    for (int i = 0; i < nodes; i++) {
      readNode(c, numbers, index);
//      index.increment();
    }
    for (int i = 0; i < metadata; i++) {
      c.add(numbers.get(index.value()));
      index.increment();
    }
  }

  class Structure {

    List<Structure> children = new ArrayList<>();
    List<Integer> metadata = new ArrayList<>();

    public int value() {
      int value = 0;
      if (CollectionUtils.isEmpty(children)) {
        for (Integer metadataValue : metadata) {
          value += metadataValue;
        }
        return value;
      }
      for (Integer metadataValue : metadata) {
        int index = metadataValue - 1;
        if (index >= 0 && index < children.size()) {
          value += children.get(index).value();
        }
      }
      return value;
    }
  }

  class Counter {

    int count;

    Counter() {
      this(0);
    }

    public Counter(int initValue) {
      count = initValue;
    }

    void increment() {
      count++;
    }

    void add(int toAdd) {
      count += toAdd;
    }

    int value() {
      return count;
    }

    @Override
    public String toString() {
      return "Counter{" +
          "count=" + count +
          '}';
    }
  }

  @Override
  public Object problem2() {
    List<Integer> numbers = FileReader
        .readNumberMatrixForSeparator(StreamReader.readFile(file), " ").get(0);

    Counter c = new Counter();
    Counter index = new Counter();

    Structure parent = new Structure();

    readNode(c, numbers, index, parent);

    return parent.children.get(0).value();
  }

  private void readNode(Counter c, List<Integer> numbers, Counter index, Structure parent) {
    if (index.value() >= numbers.size()) {
      return;
    }

    Structure s = new Structure();
    parent.children.add(s);
    int nodes = numbers.get(index.value());
    index.increment();
    int metadata = numbers.get(index.value());
    index.increment();
    for (int i = 0; i < nodes; i++) {
      readNode(c, numbers, index, s);
//      index.increment();
    }
    for (int i = 0; i < metadata; i++) {
      Integer metadataValue = numbers.get(index.value());
      c.add(metadataValue);
      index.increment();
      s.metadata.add(metadataValue);
    }

  }


}
