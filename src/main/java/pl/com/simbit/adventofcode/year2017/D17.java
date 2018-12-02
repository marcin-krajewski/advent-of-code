package pl.com.simbit.adventofcode.year2017;

import pl.com.simbit.adventofcode.Day;

import java.util.ArrayList;
import java.util.List;

public class D17 implements Day {

	private int steps = 367;

	@Override
	public Object problem1() {
		List<Integer> numbers = new ArrayList<>();
		numbers.add(0);
		int oldIndex = 0;
		for (int position = 1; position <= 2017; position++) {
			oldIndex = index(oldIndex, position);
			numbers.add(oldIndex, position);
		}
		return numbers.get(numbers.indexOf(2017) + 1);
	}

	private int index(int oldIndex, int size) {
		return (oldIndex + steps) % size + 1;
	}

	@Override
	public Object problem2() {
		List<Integer> numbers = new ArrayList<>();
		numbers.add(0);
		int oldIndex = 0;
		int remembered = 0;
		for (int position = 1; position <= 50000000; position++) {
			oldIndex = index(oldIndex, position);
			if (oldIndex == 1) {
				remembered = position;
			}
		}
		return remembered;
	}
}
