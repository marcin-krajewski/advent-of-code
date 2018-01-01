package pl.com.simbit.adventofcode.year2017;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

public class D09 implements Day {

	private String file = "year2017/d09.txt";

	private String selfResetted = "([^!])((!!)+)([^!])";
	private String garbageIncorrectEndRegex = "(!!)?!>";
	private String resetted = "(!!)?!.";
	private String garbageRegex = "<[^>]*>";

	@Override
	public Object problem1() {

		String input = inputWithCorrectGarbages().replaceAll(garbageRegex, "");

		int level = 0;
		int sum = 0;
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if (ch == '{') {
				level++;
			}
			if (ch == '}') {
				sum += level;
				level--;
			}
		}

		return sum;
	}

	@Override
	public Object problem2() {

		String input = inputWithCorrectGarbages();

		int sum = 0;

		Pattern p = Pattern.compile(garbageRegex);
		Matcher m = p.matcher(input);
		while (m.find()) {
			sum += m.group().length() - 2;
		}

		return sum;
	}

	private String inputWithCorrectGarbages() {
		String input = FileReader.readLines(StreamReader.readFile(file)).get(0);

		Pattern p = Pattern.compile(selfResetted);
		Matcher m = p.matcher(input);
		while (m.find()) {
			input = m.replaceFirst("$1$4");
			m = p.matcher(input);
		}

		input = input.replaceAll(garbageIncorrectEndRegex, "");
		return input.replaceAll(resetted, "");
	}
}
