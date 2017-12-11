package pl.com.simbit.adventofcode.year2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import pl.com.simbit.utility.file.FileReader;

public class D04 implements Day {

	private String file = "d04-input.txt";

	public Object problem1() {
		int count = 0;
		for (List<String> strings : FileReader.readMatrix(StreamReader.readFile(file), " ")) {
			if (strings.size() == new HashSet<String>(strings).size()) {
				count++;
			}
		}
		return count;
	}

	public Object problem2() {
		int count = 0;
		for (List<String> strings : FileReader.readMatrix(StreamReader.readFile(file), " ")) {

			List<String> newStrings = new ArrayList<String>();
			for (String s : strings) {
				char[] chars = s.toCharArray();
				Arrays.sort(chars);
				newStrings.add(new String(chars));
			}
			if (newStrings.size() == new HashSet<String>(newStrings).size()) {
				count++;
			}
		}
		return count;
	}

}
