package pl.com.simbit.year2017;

import java.util.ArrayList;
import java.util.List;

public class D01 implements Day {

	private String p1file = "d01-input.txt";

	public Object problem1() {
		return countMatches(FileReader.fileAsDigits(p1file), 1);
	}

	public Object problem2() {
		List<Integer> fileContent = FileReader.fileAsDigits(p1file);
		return countMatches(fileContent, fileContent.size() / 2);
	}

	private int countMatches(List<Integer> numbers, int offset) {
		List<Integer> newInput = new ArrayList<Integer>(numbers);
		newInput.addAll(numbers);

		int count = 0;
		for (int i = 0; i < numbers.size(); i++) {
			if (newInput.get(i).equals(newInput.get(i + offset))) {
				count += newInput.get(i);
			}
		}
		return count;
	}
}
