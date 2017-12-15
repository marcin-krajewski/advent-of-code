package pl.com.simbit.adventofcode.year2016;

import java.util.List;

import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

public class D02 implements Day {

	private String file = "year2016/d02.txt";
	int size = 3;

	@Override
	public Object problem1() {

		String[][] numbers = new String[][] { new String[] { "1", "2", "3" }, new String[] { "4", "5", "6" },
				new String[] { "7", "8", "9" } };
		int[] xy = new int[] { 1, 1 };

		List<String> lines = FileReader.readLines(StreamReader.readFile(file));

		String code = "";
		for (String line : lines) {
			for (int i = 0; i < line.length(); i++) {
				move(xy, line.charAt(i), numbers);
			}
			code += numbers[xy[1]][xy[0]] + "";
		}

		return code;
	}

	@Override
	public Object problem2() {
		String[][] numbers = new String[][] { new String[] { "", "", "1", "", "" },
				new String[] { "", "2", "3", "4", "" }, new String[] { "5", "6", "7", "8", "9" },
				new String[] { "", "A", "B", "C", "" }, new String[] { "", "", "D", "", "" } };

		int[] xy = new int[] { 0, 2 };

		List<String> lines = FileReader.readLines(StreamReader.readFile(file));

		String code = "";
		for (String line : lines) {
			for (int i = 0; i < line.length(); i++) {
				move(xy, line.charAt(i), numbers);
			}
			code += numbers[xy[1]][xy[0]];
		}

		return code;
	}

	private void move(int[] xy, char c, String[][] numbers) {
		switch (c) {
		case 'U':
			xy[1] = newIndex(-1, xy, true, numbers);
			break;
		case 'D':
			xy[1] = newIndex(1, xy, true, numbers);
			break;
		case 'L':
			xy[0] = newIndex(-1, xy, false, numbers);
			break;
		case 'R':
			xy[0] = newIndex(1, xy, false, numbers);
			break;
		}
	}

	private int newIndex(int valueToAdd, int[] xy, boolean yInd, String[][] values) {
		int newInd = xy[yInd ? 1 : 0] + valueToAdd;
		if (newInd >= values[0].length) {
			return xy[yInd ? 1 : 0];
		}
		if (newInd < 0) {
			return 0;
		}
		if (values[yInd ? newInd : xy[1]][yInd ? xy[0] : newInd].equals("")) {
			return xy[yInd ? 1 : 0];
		}
		return xy[yInd ? 1 : 0] + valueToAdd;
	}
}
