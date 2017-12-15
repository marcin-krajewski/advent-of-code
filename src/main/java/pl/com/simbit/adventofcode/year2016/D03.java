package pl.com.simbit.adventofcode.year2016;

import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

import java.util.List;

public class D03 implements Day {

	private String file = "year2016/d03.txt";

	@Override
	public Object problem1() {
		List<List<Integer>> triangles = FileReader.readNumberMatrixForSeparator(StreamReader.readFile(file), " ");
		int count = 0;
		for (List<Integer> triangle : triangles) {
			if (isTriangle(triangle.get(0), triangle.get(1), triangle.get(2))) {
				count++;
			}
		}
		return count;
	}

	@Override
	public Object problem2() {
		List<List<Integer>> triangles = FileReader.readNumberMatrixForSeparator(StreamReader.readFile(file), " ");
		int count = 0;
		for (int col = 0; col < 3; col++) {
			for (int i = 0; i < triangles.size(); i += 3) {
				if (isTriangle(triangles.get(i).get(col), triangles.get(i + 1).get(col),
						triangles.get(i + 2).get(col))) {
					count++;
				}
			}
		}
		return count;
	}

	private boolean isTriangle(int a, int b, int c) {
		return a + b > c && a + c > b && b + c > a;
	}
}
