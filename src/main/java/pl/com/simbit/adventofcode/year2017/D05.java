package pl.com.simbit.adventofcode.year2017;

import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

public class D05 implements Day {

	interface NextValue {
		default int next(int previous) {
			return 1;
		}
	}

	class P1 implements NextValue {
	}

	class P2 implements NextValue {
		public int next(int previous) {
			return previous >= 3 ? -1 : 1;
		}
	}

	private String file = "year2017/d05-input.txt";

	public Object problem1() {
		return count(new P1());
	}

	public Object problem2() {
		return count(new P2());
	}

	private Object count(NextValue valueGetter) {
		Integer[] array = FileReader.readLinesAsNumbers(StreamReader.readFile(file)).toArray(new Integer[0]);
		int count = 0, currentIndex = 0, numbersSize = array.length;
		while (currentIndex >= 0 && currentIndex < numbersSize) {
			Integer oldValue = array[currentIndex];
			array[currentIndex] += valueGetter.next(oldValue);
			currentIndex += oldValue;
			count++;
		}
		return count;
	}
}
