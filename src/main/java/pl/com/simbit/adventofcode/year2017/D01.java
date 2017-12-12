package pl.com.simbit.adventofcode.year2017;

import java.util.ArrayList;
import java.util.List;

import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

public class D01 implements Day {

	private String file = "year2017/d01-input.txt";

	public Object problem1() {
		return countMatches(FileReader.fileAsDigits(StreamReader.readFile(file)), 1);
	}

	public Object problem2() {
		List<Integer> fileContent = FileReader.fileAsDigits(StreamReader.readFile(file));
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
