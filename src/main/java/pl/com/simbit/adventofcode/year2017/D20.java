package pl.com.simbit.adventofcode.year2017;

import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

import java.util.ArrayList;
import java.util.List;

public class D20 implements Day {

  private String file = "year2017/d20.txt";

  @Override
  public Object problem1() {
    List<Points> points = readPoints();

    long min = Long.MAX_VALUE;
    Points minInd = null;
    for (int j = 0; j < points.size(); j++) {
      Points p = points.get(j);
      int i = 0;

      while (true) {
        if (i == 3000) {
          long distance = p.distance();
          if (distance < min && distance > 0) {
            min = distance;
            minInd = p;
          }
          break;
        }
        p.increaseDistance();
        if (i++ > 5000000) {
          int b = i;
        }
      }

    }

    return minInd.ind;
  }

  Point getPoint(String coords) {
    Point point = new Point();
    point.x = Integer.parseInt(coords.split(",")[0]);
    point.y = Integer.parseInt(coords.split(",")[1]);
    point.z = Integer.parseInt(coords.split(",")[2]);
    return point;
  }

  class Point {

    long x;
    long y;
    long z;

    long distance() {
      return Math.abs(x) + Math.abs(y) + Math.abs(z);
    }

    void increase(Point p) {
      x += p.x;
      y += p.y;
      z += p.z;
    }

    @Override
    public String toString() {
      return "<" + x + ", " + y + ", " + z + ">";
    }
  }

  class Points {

    boolean collides = false;

    Long d = Long.MAX_VALUE;

    Point p;
    Point v;
    Point a;

    int ind = -1;

    public void parse(String line) {
      String[] pointsLine = line.split(", ");
      p = getPoint(
          pointsLine[0].substring(pointsLine[0].indexOf("<") + 1, pointsLine[0].length() - 1));
      v = getPoint(
          pointsLine[1].substring(pointsLine[1].indexOf("<") + 1, pointsLine[1].length() - 1));
      a = getPoint(
          pointsLine[2].substring(pointsLine[2].indexOf("<") + 1, pointsLine[2].length() - 1));
      d = p.distance();
    }

    public long distance() {
      return d;
    }

    public void increaseDistance() {
      increaseVelocity();
      p.increase(v);
      d = p.distance();
    }

    public void increaseVelocity() {
      v.increase(a);
    }

    public void collides(List<Points> pp) {
      if (this.collides == true) {
        return;
      }
      for (Points p : pp) {
        if (p.ind == ind || p.collides == true) {
          continue;
        }
        if (p.p.x == this.p.x && p.p.z == this.p.z && p.p.y == this.p.y) {
          this.collides = true;
          p.collides = true;
        }
      }
    }

    public String String() {
      return "p=<" + p.x + "\t" + p.y + "\t" + p.z + ">, v=<" + v.x + "\t" + v.y + "\t" + v.z
          + ">, a=<" + a.x + "\t" + a.y + "\t" + a.z + ">" + "\t\t\t\t" + p.x + "\t" + p.y + "\t"
          + p.z + "\t" + v.x + "\t" + v.y + "\t" + v.z
          + "\t" + a.x + "\t" + a.y + "\t" + a.z;
    }
  }

  @Override
  public Object problem2() {
    List<Points> points = readPoints();

    int i = 0;
    while (true) {

      for (int j = 0; j < points.size(); j++) {
        Points p = points.get(j);
        p.collides(points);
      }

      for (int j = 0; j < points.size(); j++) {
        Points p = points.get(j);
        p.increaseDistance();
      }
      i++;
      if (i == 50) {
        break;
      }

    }
    i = 0;
    for (int j = 0; j < points.size(); j++) {
      Points p = points.get(j);
      if (!p.collides) {
        i++;
      }
    }

    return i;
  }

  public List<Points> readPoints() {
    List<String> strings = FileReader.readLines(StreamReader.readFile(file));

    List<Points> points = new ArrayList<>();

    int ii = 0;
    for (String line : strings) {
      String[] pointsLine = line.split(", ");

      Points p = new Points();
      p.parse(line);
      p.ind = ii;
      points.add(p);
      ii++;
    }
    return points;
  }
}
