package pl.com.simbit.adventofcode.year2016;

import static pl.com.simbit.adventofcode.year2016.D01.Direction.E;
import static pl.com.simbit.adventofcode.year2016.D01.Direction.N;
import static pl.com.simbit.adventofcode.year2016.D01.Direction.S;
import static pl.com.simbit.adventofcode.year2016.D01.Direction.W;

import java.util.ArrayList;
import java.util.List;

import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

public class D01 implements Day {

	public enum Direction {
		N, S, W, E
	}

	private String file = "year2016/d01.txt";

	@Override
	public Object problem1() {
		Direction d = N;
		String line = FileReader.firstLine(StreamReader.readFile(file));
		Integer[] xy = new Integer[] { 0, 0 };
		for (String move : line.split(", ")) {
			d = newDirection(move, d);
			Integer moves = Integer.parseInt(move.substring(1));
			for (int i = 0; i < moves; i++) {
				move(d, xy);
			}
		}
		return Math.abs(xy[0]) + Math.abs(xy[1]);
	}

	@Override
	public Object problem2() {
		Direction d = N;
		String line = FileReader.firstLine(StreamReader.readFile(file));
		Integer[] xy = new Integer[] { 0, 0 };
		List<Integer> hashCodes = new ArrayList<Integer>();

		for (String move : line.split(", ")) {
			d = newDirection(move, d);
			Integer moves = Integer.parseInt(move.substring(1));
			for (int i = 0; i < moves; i++) {
				move(d, xy);
				int hashCode = xy[0].hashCode()*1234657+xy[1].hashCode()*123456;
				if (hashCodes.contains(hashCode)) {
					return Math.abs(xy[0]) + Math.abs(xy[1]);
				}
				hashCodes.add(hashCode);
			}

		}
		return null;
	}

	private void move(Direction d, Integer[] xy) {
		switch (d) {
		case E:
			xy[0]--;
			break;
		case N:
			xy[1]++;
			break;
		case S:
			xy[1]--;
			break;
		case W:
			xy[0]++;
			break;
		}
	}

	private Direction newDirection(String move, Direction oldDirection) {
		switch (oldDirection) {
		case E:
			return move.startsWith("L") ? S : N;
		case N:
			return move.startsWith("L") ? E : W;
		case S:
			return move.startsWith("L") ? W : E;
		case W:
			return move.startsWith("L") ? N : S;
		}
		throw new RuntimeException("Unknown direction");
	}
}
