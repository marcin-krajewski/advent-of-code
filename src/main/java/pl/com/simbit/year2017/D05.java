package pl.com.simbit.year2017;

import java.util.List;

public class D05 implements Day {

	private String file = "d05-input.txt";

	public Object problem1() {
		List<Integer> numbers = FileReader.readLinesAsNumbers(file);
		Integer[] array = numbers.toArray(new Integer[0]);
		int count = 0, currentIndex = 0, numbersSize = numbers.size();
		while (currentIndex >= 0 && currentIndex < numbersSize) {
			Integer number = array[currentIndex];
			array[currentIndex]++;
			currentIndex += number;
			count++;
		}
		return count;
	}

	public Object problem2() {
		List<Integer> numbers = FileReader.readLinesAsNumbers(file);
		Integer[] array = numbers.toArray(new Integer[0]);
		int count = 0, currentIndex = 0, numbersSize = numbers.size();
		while (currentIndex >= 0 && currentIndex < numbersSize) {
			Integer number = array[currentIndex];
			array[currentIndex] = number >= 3 ? number - 1 : number + 1;
			currentIndex += number;
			count++;
		}
		return count;
	}
}
