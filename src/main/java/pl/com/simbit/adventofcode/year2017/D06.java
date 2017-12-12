package pl.com.simbit.adventofcode.year2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;
import pl.com.simbit.utility.classes.CollectionUtils;

public class D06 implements Day {

	private String file = "year2017/d06-input.txt";

	public Object problem1() {
		return resolve(new P1());
	}

	public Object problem2() {
		return resolve(new P2());
	}

	private Integer resolve(ValueGetter valueGetter) {

		Integer[] values = CollectionUtils.array(FileReader.readNumberMatrix(StreamReader.readFile(file)).get(0));

		List<Integer> hashCodes = new ArrayList<Integer>();
		int count = 0;
		while (true) {
			List<Integer> integers = Arrays.asList(values);
			Integer maxValue = Collections.max(integers);
			int stepIndex = integers.indexOf(maxValue);

			values[stepIndex] = 0;
			count++;
			while (maxValue-- > 0) {
				values[++stepIndex % values.length]++;
			}

			int hashCode = Arrays.hashCode(values);
			if (hashCodes.contains(hashCode)) {
				return count - valueGetter.valueToSubtract(hashCodes, hashCode);
			}
			hashCodes.add(hashCode);
		}
	}

	interface ValueGetter {
		default int valueToSubtract(List<Integer> listValues, int value) {
			return 0;
		}
	}

	class P1 implements ValueGetter {
	}

	class P2 implements ValueGetter {

		public int valueToSubtract(List<Integer> listValues, int value) {
			return listValues.indexOf(value) + 1;
		}
	}
}
