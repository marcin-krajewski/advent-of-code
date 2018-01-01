package pl.com.simbit.adventofcode.year2017;

import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

import java.util.ArrayList;
import java.util.List;

public class D20 implements Day {

	private String file = "year2017/d20-input.txt";

	@Override
	public Object problem1() {
		List<String> strings = FileReader.readLines(StreamReader.readFile(file));

		List<Point[]> points = new ArrayList<>();
		for (String line : strings) {
			String[] pointsLine = line.split(", ");

			Point[] p = new Point[3];
			for (int i = 0; i < pointsLine.length; i++) {
				p[i] = getPoint(pointsLine[i].substring(pointsLine[i].indexOf("<") + 1, pointsLine[i].length() - 1));
			}
			points.add(p);
		}

		long min = Long.MAX_VALUE;
		int minInd = -1;
		for (int j = 0; j < points.size(); j++) {
			Point[] p = points.get(j);
			int i = 0;
			while (true) {
				// System.out.println("Ind: " + j);
				// System.out.println("\tp: " + p[0]);
				// System.out.println("\tv: " + p[1]);
				// System.out.println("\ta: " + p[2]);
				long distance = p[0].distance();
				if (distance < min && distance > 0) {
					// System.out.println("CHANGING MIN TO " + distance + ", point: " + p[0]);
					min = distance;
					minInd = j;
				}
				if (allPointsTheSameSign(p)) {
					// System.out.println("Break pos: " + j);
					break;
				}
				p[1].increase(p[2]);
				p[0].increase(p[1]);
				if (i++ > 5000000) {
					int b = i;
				}
			}
		}
		System.out.println(min);

		return minInd;
	}

	private boolean allPointsTheSameSign(Point[] pp) {
		Point p = pp[0];
		Point v = pp[1];
		Point a = pp[2];
		if (p.x >= 0 && v.x >= 0 && a.x >= 0) {
			return true;
		}
		if (p.x <= 0 && v.x <= 0 && a.x <= 0) {
			return true;
		}
		if (p.y >= 0 && v.y >= 0 && a.y >= 0) {
			return true;
		}
		if (p.y <= 0 && v.y <= 0 && a.y <= 0) {
			return true;
		}

		if (p.z >= 0 && v.z >= 0 && a.z >= 0) {
			return true;
		}
		if (p.z <= 0 && v.z <= 0 && a.z <= 0) {
			return true;
		}
		return false;
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

	@Override
	public Object problem2() {
		return null;
	}
}
