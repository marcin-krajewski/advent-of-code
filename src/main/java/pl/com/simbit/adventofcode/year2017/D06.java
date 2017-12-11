package pl.com.simbit.adventofcode.year2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pl.com.simbit.utility.file.FileReader;

public class D06 implements Day {

	private String file = "d06-input.txt";

	public Object problem1() {
		return resolve(new P1());
	}

	public Object problem2() {
		return resolve(new P2());
	}

	private Integer resolve(ValueGetter valueGetter) {
		ArrayList<Integer> hashCodes = new ArrayList<Integer>();

		List<Integer> numbers = FileReader.readNumberMatrix(StreamReader.readFile(file)).get(0);
		Integer[] values = numbers.toArray(new Integer[0]);

		int count = 0;
		while (true) {
			List<Integer> integers = Arrays.asList(values);
			Integer value = Collections.max(integers);
			int stepIndex = integers.indexOf(value);
			values[stepIndex] = 0;
			count++;
			while (value > 0) {
				stepIndex++;
				if (stepIndex == values.length) {
					stepIndex = 0;
				}
				value--;
				values[stepIndex]++;
			}
			if (hashCodes.contains(Arrays.hashCode(values))) {
				return count - valueGetter.valueToSubtract(hashCodes, values);
			}
			hashCodes.add(Arrays.hashCode(values));
		}
	}

	interface ValueGetter {
		int valueToSubtract(ArrayList<Integer> hashCodes, Integer[] values);
	}

	class P1 implements ValueGetter {

		public int valueToSubtract(ArrayList<Integer> hashCodes, Integer[] values) {
			return 0;
		}
	}

	class P2 implements ValueGetter {

		public int valueToSubtract(ArrayList<Integer> hashCodes, Integer[] values) {
			return hashCodes.indexOf(Arrays.hashCode(values)) + 1;
		}
	}
}
