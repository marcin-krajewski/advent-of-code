package pl.com.simbit.adventofcode.year2017;

import org.junit.Assert;
import org.junit.Test;

import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;
import pl.com.simbit.utility.string.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class D16 implements Day {

	private String file = "year2017/d16-input.txt";
	private String start = "abcdefghijklmnop";
	private Factory factory = new Factory();

	@Override
	public Object problem1() {
		String[] operations = FileReader.firstLine(StreamReader.readFile(file)).split(",");
		return danceForAllOperations(operations, start);
	}

	@Override
	public Object problem2() {
		List<String> dances = new ArrayList<>();
		String[] operations = FileReader.firstLine(StreamReader.readFile(file)).split(",");
		while (true) {
			start = danceForAllOperations(operations, start);
			if (dances.contains(start)) {
				return dances.get(1000000000 % dances.size() - 1);
			}
			dances.add(start);
		}
	}

	public String danceForAllOperations(String[] operations, String start) {
		for (String operation : operations) {
			start = factory.instance(operation).make(operation.substring(1), start);
		}
		return start;
	}

	interface OperationMaker {
		String make(String input, String phrase);

		Object[] args(String input);
	}

	class Spin implements OperationMaker {

		@Override
		public String make(String input, String phrase) {
			Integer index = phrase.length() - args(input)[0];
			return phrase.substring(index) + phrase.substring(0, index);
		}

		@Override
		public Integer[] args(String input) {
			return new Integer[] { Integer.parseInt(input) };
		}
	}

	class Exchange implements OperationMaker {

		@Override
		public String make(String input, String phrase) {
			int index1 = args(input)[0];
			int index2 = args(input)[1];
			return StringUtils.getInstance().swapIndex(phrase, index1, index2);
		}

		@Override
		public Integer[] args(String input) {
			return new Integer[] { Integer.parseInt(input.split("/")[0]), Integer.parseInt(input.split("/")[1]) };
		}
	}

	class Partner implements OperationMaker {

		@Override
		public String make(String input, String phrase) {
			int index1 = phrase.indexOf(args(input)[0]);
			int index2 = phrase.indexOf(args(input)[1]);
			return StringUtils.getInstance().swapIndex(phrase, index1, index2);
		}

		@Override
		public String[] args(String input) {
			return new String[] { input.split("/")[0], input.split("/")[1] };
		}
	}

	class Factory {
		OperationMaker spin = new Spin();
		OperationMaker exchange = new Exchange();
		OperationMaker partner = new Partner();

		OperationMaker instance(String input) {
			if (input.startsWith("s")) {
				return spin;
			}
			if (input.startsWith("x")) {
				return exchange;
			}
			if (input.startsWith("p")) {
				return partner;
			}
			throw new RuntimeException("Unknown operation");
		}
	}

	@Test
	public void spin() {
		Assert.assertEquals("eabcd", new Spin().make("1", "abcde"));
	}

	@Test
	public void exchange() {
		Assert.assertEquals("eabdc", new Exchange().make("3/4", "eabcd"));
	}

	@Test
	public void partner() {
		Assert.assertEquals("baedc", new Partner().make("e/b", "eabdc"));
	}

}
