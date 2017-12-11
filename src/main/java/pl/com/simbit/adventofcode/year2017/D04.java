package pl.com.simbit.adventofcode.year2017;

import java.util.ArrayList;
import java.util.List;

import pl.com.simbit.utility.file.FileReader;
import pl.com.simbit.utility.problems.classes.CollectionUtils;
import pl.com.simbit.utility.problems.string.StringUtils;

public class D04 implements Day {

	private String file = "d04-input.txt";

	public Object problem1() {
		int count = 0;
		for (List<String> strings : FileReader.readMatrix(StreamReader.readFile(file), " ")) {
			if (CollectionUtils.hasUniqueElements(strings)) {
				count++;
			}
		}
		return count;
	}

	public Object problem2() {
		int count = 0;
		for (List<String> strings : FileReader.readMatrix(StreamReader.readFile(file), " ")) {
			if (CollectionUtils.hasUniqueElements(ordered(strings))) {
				count++;
			}
		}
		return count;
	}

	private List<String> ordered(List<String> strings) {
		List<String> newStrings = new ArrayList<String>();
		for (String s : strings) {
			newStrings.add(StringUtils.getInstance().sortCharsInStringAlphabetically(s));
		}
		return newStrings;
	}

}
