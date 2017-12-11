package pl.com.simbit.year2017;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class D08 implements Day {

	class Values {
		Map<String, Integer> values = new HashMap<>();
		private Integer maxValueEver = Integer.MIN_VALUE;

		void makeOperation(String line) {
			String key = line.split(" ")[0];
			String operator = line.split(" ")[1];
			Integer value = Integer.parseInt(line.split(" ")[2]);

			Integer newValue = getValue(key) + (operator.equals("inc") ? value : -value);
			if (newValue > maxValueEver) {
				maxValueEver = newValue;
			}
			values.put(key, newValue);
		}

		Integer getValue(String key) {
			return values.get(key) == null ? 0 : values.get(key);
		}

		public Integer maxValue() {
			return Collections.max(values.values());
		}

		public Integer maxValueEver() {
			return maxValueEver;
		}
	}

	class Condition {
		private final String first;
		private final Integer second;
		private final String condition;

		Condition(String line) {
			this.first = line.split(" ")[0];
			this.condition = line.split(" ")[1];
			this.second = Integer.parseInt(line.split(" ")[2]);
		}

		boolean isFulfilled(Values values) {
			switch (condition) {
			case ">=":
				return values.getValue(first) >= second;
			case ">":
				return values.getValue(first) > second;
			case "<":
				return values.getValue(first) < second;
			case "<=":
				return values.getValue(first) <= second;
			case "!=":
				return !values.getValue(first).equals(second);
			case "==":
				return values.getValue(first).equals(second);
			default:
				throw new RuntimeException("Unknown condition");
			}
		}
	}

	private String file = "d08-input.txt";

	@Override
	public Object problem1() {

		List<String> lines = FileReader.readLines(file);
		Values values = new Values();
		for (String line : lines) {
			Condition c = new Condition(line.split(" if ")[1].trim());
			if (c.isFulfilled(values)) {
				values.makeOperation(line.split(" if ")[0]);
			}
		}
		return values.maxValue();
	}

	@Override
	public Object problem2() {
		List<String> lines = FileReader.readLines(file);
		Values values = new Values();
		for (String line : lines) {
			Condition c = new Condition(line.split(" if ")[1].trim());
			if (c.isFulfilled(values)) {
				values.makeOperation(line.split(" if ")[0]);
			}
		}
		return values.maxValueEver();
	}
}
