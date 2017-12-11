package pl.com.simbit.adventofcode.year2017;

import java.util.Collections;
import java.util.List;

import pl.com.simbit.utility.file.FileReader;

public class D02 implements Day {
	private String file = "d02-input.txt";

	public Object problem1() {
		return checksum(new NumbersProblem1());
	}

	private int checksum(MatchingNumbers matchingNumbers) {
		int checksum = 0;
		for (List<Integer> list : FileReader.readNumberMatrix(StreamReader.readFile(file))) {
			int[] numbers = matchingNumbers.numbers(list);
			checksum += matchingNumbers.expression(numbers);
		}
		return checksum;
	}

	public Object problem2() {
		return checksum(new NumbersProblem2());
	}

	interface MatchingNumbers {
		int[] numbers(List<Integer> numbers);

		int expression(int[] numbers);
	}

	class NumbersProblem1 implements MatchingNumbers {
		public int[] numbers(List<Integer> numbers) {
			return new int[] { Collections.max(numbers), Collections.min(numbers) };
		}

		public int expression(int[] numbers) {
			return numbers[0] - numbers[1];
		}
	}

	class NumbersProblem2 implements MatchingNumbers {
		public int[] numbers(List<Integer> numbers) {
			for (int i = 0; i < numbers.size(); i++) {
				for (int j = 0; j < numbers.size(); j++) {
					if (i != j && numbers.get(i) % numbers.get(j) == 0) {
						return new int[] { numbers.get(i), numbers.get(j) };
					}
				}
			}
			return new int[0];
		}

		public int expression(int[] numbers) {
			return numbers[0] / numbers[1];
		}
	}
}
