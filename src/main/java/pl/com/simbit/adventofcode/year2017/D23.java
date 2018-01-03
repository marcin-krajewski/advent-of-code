package pl.com.simbit.adventofcode.year2017;

import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class D23 implements Day {
	private String file = "year2017/d23.txt";

	@Override
	public Object problem1() {

		List<String> lines = FileReader.readLines(StreamReader.readFile(file));

		List<D18.Operation> operations = new ArrayList<>();

		long last = -1;
		for (String line : lines) {
			operations.add(new D18().factory(line.split(" ")[0], line.split(" ")));
		}

		Map<String, Long> values = new HashMap<>();

		int currentOperation = 0;
		int c = 0;
		while (true) {
			if (currentOperation < 0 || currentOperation >= operations.size()) {
				return c;
			}
			D18.Operation o = operations.get(currentOperation);
			long value = o.setValue(values);
			if (o instanceof D18.Mul || o instanceof D18.Mulr) {
				c++;
			}
			currentOperation = o.nextOperation(values, currentOperation);
		}
	}

	@Override
	public Object problem2() {
		return null;
	}
}
