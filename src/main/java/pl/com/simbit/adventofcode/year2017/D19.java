package pl.com.simbit.adventofcode.year2017;

import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.adventofcode.year2016.D01;
import pl.com.simbit.utility.file.FileReader;

import java.util.List;

public class D19 implements Day {

	private String file = "year2017/d19.txt";

	@Override
	public Object problem1() {

		List<List<String>> chars = FileReader.readMatrix(StreamReader.readFile(file), "");

		int[] xy = new int[] { 0, 0 };
		D01.Direction d = D01.Direction.S;

		List<String> firstLine = chars.get(0);
		for (int i = 0; i < firstLine.size(); i++) {
			if (firstLine.get(i).equals("|")) {
				xy[1] = i;
			}
		}

		StringBuilder sb = new StringBuilder();
		while (true) {
			// try {
			// Thread.sleep(250);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
			move(d, xy);
			// printChars(xy, chars);
			if (!isValid(xy, chars)) {
				return sb.toString();
			}
			String current = chars.get(xy[0]).get(xy[1]);
			if (current.equals("+")) {
				d = changeDirection(xy, d, chars);
			}
			if (current.matches("[A-Z]")) {
				sb.append(current);
			}
		}
	}

	private void printChars(int[] xy, List<List<String>> chars) {
		System.out.println();
		for (int i = Math.max(0, xy[0] - 5); i < chars.size() && i < xy[0] + 5; i++) {
			List<String> line = chars.get(i);
			for (int j = 0; j < line.size(); j++) {
				if (i == xy[0] && j == xy[1]) {
					System.out.print("\u001B[43m" + line.get(j) + "\033[0m");
				} else {
					System.out.print(line.get(j));
				}
			}
			System.out.println();
		}
	}

	private D01.Direction changeDirection(int[] xy, D01.Direction d, List<List<String>> chars) {
		switch (d) {
		case S:
		case N:
			if (isValid(new int[] { xy[0], xy[1] + 1 }, chars) && (chars.get(xy[0]).get(xy[1] + 1).matches("[A-Z]")
					|| chars.get(xy[0]).get(xy[1] + 1).equals("-"))) {
				return D01.Direction.E;
			}
			if (isValid(new int[] { xy[0], xy[1] - 1 }, chars) && (chars.get(xy[0]).get(xy[1] - 1).matches("[A-Z]")
					|| chars.get(xy[0]).get(xy[1] - 1).equals("-"))) {
				return D01.Direction.W;
			}
			break;
		case E:
		case W:
			if (isValid(new int[] { xy[0] + 1, xy[1] }, chars) && (chars.get(xy[0] + 1).get(xy[1]).matches("[A-Z]")
					|| chars.get(xy[0] + 1).get(xy[1]).equals("|"))) {
				return D01.Direction.S;
			}
			if (isValid(new int[] { xy[0] - 1, xy[1] }, chars) && (chars.get(xy[0] - 1).get(xy[1]).matches("[A-Z]")
					|| chars.get(xy[0] - 1).get(xy[1]).equals("|"))) {
				return D01.Direction.N;
			}
			break;
		}
		throw new RuntimeException("Incorrect move " + xy[0] + ", " + xy[1] + ", D: " + d);
	}

	private boolean isValid(int[] xy, List<List<String>> chars) {
		int x = xy[0];
		int y = xy[1];
		if (x < 0 || x >= chars.size()) {
			return false;
		}
		List<String> line = chars.get(x);
		if (y < 0 | y >= line.size()) {
			return false;
		}
		String current = chars.get(x).get(y);
		if (current.equals("+") || current.equals("|") || current.equals("-") || current.matches("[A-Z]")) {
			return true;
		}
		return false;
	}

	private void move(D01.Direction d, int[] xy) {
		switch (d) {
		case E:
			xy[1]++;
			break;
		case N:
			xy[0]--;
			break;
		case S:
			xy[0]++;
			break;
		case W:
			xy[1]--;
			break;
		}
	}

	@Override
	public Object problem2() {
		List<List<String>> chars = FileReader.readMatrix(StreamReader.readFile(file), "");

		int[] xy = new int[] { 0, 0 };
		D01.Direction d = D01.Direction.S;

		List<String> firstLine = chars.get(0);
		for (int i = 0; i < firstLine.size(); i++) {
			if (firstLine.get(i).equals("|")) {
				xy[1] = i;
			}
		}

		StringBuilder sb = new StringBuilder();
		int count = 1;
		while (true) {
			// try {
			// Thread.sleep(250);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
			move(d, xy);
			// printChars(xy, chars);
			if (!isValid(xy, chars)) {
				return count;
			}
			String current = chars.get(xy[0]).get(xy[1]);
			if (current.equals("+")) {
				d = changeDirection(xy, d, chars);
			}
			if (current.matches("[A-Z]")) {
				sb.append(current);
			}
			count++;
		}
	}
}
