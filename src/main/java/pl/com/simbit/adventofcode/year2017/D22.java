package pl.com.simbit.adventofcode.year2017;

import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.adventofcode.year2016.D01;
import pl.com.simbit.utility.file.FileReader;
import pl.com.simbit.utility.string.StringUtils;

import java.util.List;

import static pl.com.simbit.adventofcode.year2016.D01.Direction.E;
import static pl.com.simbit.adventofcode.year2016.D01.Direction.N;
import static pl.com.simbit.adventofcode.year2016.D01.Direction.S;
import static pl.com.simbit.adventofcode.year2016.D01.Direction.W;

public class D22 implements Day {

	private String file = "year2017/d22.txt";

	int size = 700;
	int mid = 12;
	private char[][] array = new char[size][size];

	@Override
	public Object problem1() {
		List<String> lines = FileReader.readLines(StreamReader.readFile(file));

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				array[i][j] = '.';
			}
		}

		mid = (lines.size() - 1) / 2;

		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < lines.get(i).length(); j++) {
				array[(size / 2) + i][(size / 2) + j] = lines.get(i).charAt(j);
			}
		}

		int[] pos = new int[] { (size / 2) + mid, (size / 2) + mid };
		D01.Direction d = N;

		int c = 0;
		for (int i = 0; i < 10000; i++) {
			// print(pos);
			char current = array[pos[0]][pos[1]];
			d = newDirection(current, d);
			array[pos[0]][pos[1]] = next1(current);
			if (array[pos[0]][pos[1]] == '#') {
				c++;
			}
			move(d, pos);
		}

		return c;
	}

	private char next1(char current) {
		if (current == '.')
			return '#';
		return '.';
	}

	private void print(int[] pos) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (i == pos[0] && j == pos[1]) {
					System.out.print("[");
				} else {
					System.out.print(" ");
				}
				System.out.print(array[i][j]);
				if (i == pos[0] && j == pos[1]) {
					System.out.print("]");
				} else {
					System.out.print(" ");
				}
			}
			// if (i != pos[0]) {
			// System.out.print(".");
			// }
			System.out.println();
		}
		System.out.println("-----------------");
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

	private D01.Direction newDirection(char move, D01.Direction oldDirection) {
		switch (oldDirection) {
		case E:
			if (move == '.') {
				return N;
			}
			if (move == '#') {
				return S;
			}
			if (move == 'F') {
				return W;
			}
		case N:
			if (move == '.') {
				return W;
			}
			if (move == '#') {
				return E;
			}
			if (move == 'F') {
				return S;
			}
		case S:
			if (move == '.') {
				return E;
			}
			if (move == '#') {
				return W;
			}
			if (move == 'F') {
				return N;
			}
		case W:
			if (move == '.') {
				return S;
			}
			if (move == '#') {
				return N;
			}
			if (move == 'F') {
				return E;
			}
		}
		return oldDirection;
		// throw new RuntimeException("Unknown direction");
	}

	@Override
	public Object problem2() {
		List<String> lines = FileReader.readLines(StreamReader.readFile(file));

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				array[i][j] = '.';
			}
		}

		mid = (lines.size() - 1) / 2;

		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < lines.get(i).length(); j++) {
				array[(size / 2) + i][(size / 2) + j] = lines.get(i).charAt(j);
			}
		}

		int[] pos = new int[] { (size / 2) + mid, (size / 2) + mid };
		D01.Direction d = N;

		int c = 0;
		for (int i = 0; i < 10000000; i++) {
//			print(pos);
			char current = array[pos[0]][pos[1]];
			d = newDirection(current, d);
			array[pos[0]][pos[1]] = next2(current);
			if (array[pos[0]][pos[1]] == '#') {
				c++;
			}
			move(d, pos);
		}
//		print(pos);

		return c;
	}

	private char next2(char current) {
		if (current == '.')
			return 'W';
		if (current == 'W')
			return '#';
		if (current == 'F')
			return '.';
		return 'F';
	}
}
