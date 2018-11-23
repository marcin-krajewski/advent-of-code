package pl.com.simbit.adventofcode.year2017;

import org.junit.Assert;
import org.junit.Test;
import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class D18 implements Day {

	private String file = "year2017/d18.txt";

	@Override
	public Object problem1() {

		List<String> lines = FileReader.readLines(StreamReader.readFile(file));

		List<Operation> operations = new ArrayList<>();

		long last = -1;
		for (String line : lines) {
			operations.add(factory(line.split(" ")[0], line.split(" ")));
		}

		Map<String, Long> values = new HashMap<>();
		values.put("a", 0L);
		values.put("b", 0L);
		values.put("c", 0L);
		values.put("d", 0L);
		values.put("e", 0L);
		values.put("f", 0L);
		values.put("g", 0L);
		values.put("h", 0L);
		values.put("i", 0L);
		values.put("j", 0L);
		values.put("k", 0L);
		values.put("l", 0L);
		values.put("m", 0L);
		values.put("n", 0L);
		values.put("o", 0L);
		values.put("p", 0L);

		int currentOperation = 0;
		while (true) {
			Operation o = operations.get(currentOperation);
			long value = o.setValue(values);
			if (o instanceof Rcv && value != 0) {
				return last + "";
			}
			if (o instanceof Snd || o instanceof Sndr) {
				last = value;
			}
			currentOperation = o.nextOperation(values, currentOperation);
		}
	}

	@Override
	public Object problem2() {
		List<String> lines = FileReader.readLines(StreamReader.readFile(file));

		List<Operation> operations = new ArrayList<>();

		long last = -1;
		for (String line : lines) {
			operations.add(factory(line.split(" ")[0], line.split(" ")));
		}

		Map<String, Long> values0 = new HashMap<>();
		values0.put("a", 0L);
		values0.put("b", 0L);
		values0.put("c", 0L);
		values0.put("d", 0L);
		values0.put("e", 0L);
		values0.put("f", 0L);
		values0.put("g", 0L);
		values0.put("h", 0L);
		values0.put("i", 0L);
		values0.put("j", 0L);
		values0.put("k", 0L);
		values0.put("l", 0L);
		values0.put("m", 0L);
		values0.put("n", 0L);
		values0.put("o", 0L);
		values0.put("p", 0L);
		Map<String, Long> values1 = new HashMap<>();
		values1.put("a", 0L);
		values1.put("b", 0L);
		values1.put("c", 0L);
		values1.put("d", 0L);
		values1.put("e", 0L);
		values1.put("f", 0L);
		values1.put("g", 0L);
		values1.put("h", 0L);
		values1.put("i", 0L);
		values1.put("j", 0L);
		values1.put("k", 0L);
		values1.put("l", 0L);
		values1.put("m", 0L);
		values1.put("n", 0L);
		values1.put("o", 0L);
		values1.put("p", 1L);
		int count = 0;

		List<Long> sent0 = new ArrayList<>();
		List<Long> sent1 = new ArrayList<>();

		int currentOperation0 = 0;
		int currentOperation1 = 0;

		while (true) {
			Operation o = operations.get(currentOperation0);
			long value0 = o.setValue(values0);
			if (o instanceof Rcv) {
				while (sent1.size() == 0) {
					Operation o1 = operations.get(currentOperation1);
					long value1 = o1.setValue(values1);
					if (o1 instanceof Rcv) {
						if (sent0.size() == 0) {
							return count;
						}
						Long v = sent0.remove(0);
						((Store) o1).setValue(values1, v);
					}
					if (o1 instanceof Snd || o1 instanceof Sndr) {
						count++;
						sent1.add(value1);
					}
					currentOperation1 = o1.nextOperation(values1, currentOperation1);
				}
				Long v = sent1.remove(0);
				((Store) o).setValue(values0, v);
			}
			if (o instanceof Snd || o instanceof Sndr) {
				sent0.add(value0);
			}
			currentOperation0 = o.nextOperation(values0, currentOperation0);
		}

	}

	public Operation factory(String operationName, String[] line) {
		switch (operationName) {
		case "snd":
			try {
				Long.parseLong(line[1]);
				return new Snd(Long.parseLong(line[1]));
			} catch (NumberFormatException ex) {
				return new Sndr(line[1]);
			}
		case "set":
			try {
				Long.parseLong(line[2]);
				return new Set(line[1], Long.parseLong(line[2]));
			} catch (NumberFormatException ex) {
				return new Setr(line[1], line[2]);
			}
		case "mul":
			try {
				Long.parseLong(line[2]);
				return new Mul(line[1], Long.parseLong(line[2]));
			} catch (NumberFormatException ex) {
				return new Mulr(line[1], line[2]);
			}
		case "add":
			try {
				Long.parseLong(line[2]);
				return new Add(line[1], Long.parseLong(line[2]));
			} catch (NumberFormatException ex) {
				return new Addr(line[1], line[2]);
			}
		case "sub":
			try {
				Long.parseLong(line[2]);
				return new Sub(line[1], Long.parseLong(line[2]));
			} catch (NumberFormatException ex) {
				return new Subr(line[1], line[2]);
			}
		case "jnz":
			try {
				Long.parseLong(line[1]);
				return new Jnzi(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
			} catch (NumberFormatException ex) {
				try {
					Long.parseLong(line[2]);
					return new Jnz(line[1], Integer.parseInt(line[2]));
				} catch (NumberFormatException ex2) {
					return new Jnzr(line[1], line[2]);
				}
			}
		case "mod":
			try {
				Long.parseLong(line[2]);
				return new Mod(line[1], Long.parseLong(line[2]));
			} catch (NumberFormatException ex) {
				return new Modr(line[1], line[2]);
			}
		case "jgz":
			try {
				Integer.parseInt(line[2]);
				return new Jgz(line[1], Integer.parseInt(line[2]));
			} catch (NumberFormatException ex) {
				return new Jgzr(line[1], line[2]);
			}
		case "rcv":
			return new Rcv(line[1]);
		}
		throw new RuntimeException("Unknown operation " + operationName);
	}

	interface Operation {
		default int nextOperation(Map<String, Long> registers, int previousOperation) {
			return previousOperation + 1;
		}

		long setValue(Map<String, Long> registers);

		default long getValue(Map<String, Long> registers, String register) {
			try {
				return registers.get(register);
			} catch (NullPointerException e) {
				throw new RuntimeException();
			}
		}
	}

	interface Store {
		long setValue(Map<String, Long> registers, long value);
	}

	class Snd implements Operation {
		private final long sound;

		public Snd(long sound) {
			this.sound = sound;
		}

		@Override
		public long setValue(Map<String, Long> registers) {
			return sound;
		}
	}

	class Sndr implements Operation {
		private final String sound;

		public Sndr(String sound) {
			this.sound = sound;
		}

		@Override
		public long setValue(Map<String, Long> registers) {
			return getValue(registers, sound);
		}
	}

	@Test
	public void sndr() {
		Map<String, Long> values = new HashMap<>();
		values.put("a", 1L);
		Assert.assertEquals(0, new Sndr("b").setValue(values));
		Assert.assertEquals(1, new Sndr("a").setValue(values));
	}

	class Rcv implements Operation, Store {
		private final String sound;

		public Rcv(String sound) {
			this.sound = sound;
		}

		@Override
		public long setValue(Map<String, Long> registers) {
			return getValue(registers, sound);
		}

		@Override
		public long setValue(Map<String, Long> registers, long value) {
			registers.put(sound, value);
			return value;
		}
	}

	@Test
	public void rcv() {
		Map<String, Long> values = new HashMap<>();
		values.put("a", 1L);
		Assert.assertEquals(0, new Rcv("b").setValue(values));
		Assert.assertEquals(1, new Rcv("a").setValue(values));
	}

	class Set implements Operation {
		private final Long value;
		private final String register;

		public Set(String register, long value) {
			this.register = register;
			this.value = value;
		}

		@Override
		public long setValue(Map<String, Long> registers) {
			registers.put(register, value);
			return value;
		}
	}

	@Test
	public void set() {
		Map<String, Long> values = new HashMap<>();
		values.put("a", 1L);
		Assert.assertEquals(10, new Set("b", 10).setValue(values));
		Assert.assertEquals(5, new Set("a", 5).setValue(values));
		Assert.assertEquals(Long.valueOf(5), values.get("a"));
		Assert.assertEquals(Long.valueOf(10), values.get("b"));
	}

	class Setr implements Operation {
		private final String value;
		private final String register;

		public Setr(String register, String value) {
			this.register = register;
			this.value = value;
		}

		@Override
		public long setValue(Map<String, Long> registers) {
			registers.put(register, getValue(registers, value));
			return getValue(registers, value);
		}
	}

	@Test
	public void setr() {
		Map<String, Long> values = new HashMap<>();
		values.put("a", 1L);
		Assert.assertEquals(1, new Setr("b", "a").setValue(values));
		Assert.assertEquals(1, new Setr("a", "b").setValue(values));
		Assert.assertEquals(Long.valueOf(1), values.get("a"));
		Assert.assertEquals(0, new Setr("a", "c").setValue(values));
		Assert.assertEquals(Long.valueOf(0), values.get("a"));
		Assert.assertEquals(Long.valueOf(1), values.get("b"));
	}

	class Mul implements Operation {
		private final long value;
		private final String register;

		public Mul(String register, long value) {
			this.register = register;
			this.value = value;
		}

		@Override
		public long setValue(Map<String, Long> registers) {
			long oldValue = getValue(registers, register);
			registers.put(register, this.value * oldValue);
			return this.value * oldValue;
		}
	}

	@Test
	public void mul() {
		Map<String, Long> values = new HashMap<>();
		values.put("a", 1L);
		Assert.assertEquals(0, new Mul("b", 10).setValue(values));
		Assert.assertEquals(5, new Mul("a", 5).setValue(values));
		Assert.assertEquals(25, new Mul("a", 5).setValue(values));
		Assert.assertEquals(Long.valueOf(25), values.get("a"));
		Assert.assertEquals(Long.valueOf(0), values.get("b"));
	}

	class Mulr implements Operation {
		private final String value;
		private final String register;

		public Mulr(String register, String value) {
			this.register = register;
			this.value = value;
		}

		@Override
		public long setValue(Map<String, Long> registers) {
			long newValue = getValue(registers, value) * getValue(registers, register);
			registers.put(register, newValue);
			return newValue;
		}
	}

	@Test
	public void mulr() {
		Map<String, Long> values = new HashMap<>();
		values.put("a", 1L);
		Assert.assertEquals(0, new Mulr("b", "b").setValue(values));
		Assert.assertEquals(0, new Mulr("b", "a").setValue(values));
		values.put("a", 5L);
		Assert.assertEquals(25, new Mulr("a", "a").setValue(values));
		Assert.assertEquals(625, new Mulr("a", "a").setValue(values));
		Assert.assertEquals(Long.valueOf(625), values.get("a"));
		Assert.assertEquals(Long.valueOf(0), values.get("b"));
	}

	class Add implements Operation {
		private final long value;
		private final String register;

		public Add(String register, long value) {
			this.register = register;
			this.value = value;
		}

		@Override
		public long setValue(Map<String, Long> registers) {
			long oldValue = getValue(registers, register);
			registers.put(register, this.value + oldValue);
			return this.value + oldValue;
		}
	}

	@Test
	public void add() {
		Map<String, Long> values = new HashMap<>();
		values.put("a", 1L);
		Assert.assertEquals(10, new Add("b", 10).setValue(values));
		Assert.assertEquals(8, new Add("b", -2).setValue(values));
		Assert.assertEquals(6, new Add("a", 5).setValue(values));
		Assert.assertEquals(11, new Add("a", 5).setValue(values));
		Assert.assertEquals(Long.valueOf(11), values.get("a"));
		Assert.assertEquals(Long.valueOf(8), values.get("b"));
	}

	class Addr implements Operation {
		private final String value;
		private final String register;

		public Addr(String register, String value) {
			this.register = register;
			this.value = value;
		}

		@Override
		public long setValue(Map<String, Long> registers) {
			long newValue = getValue(registers, this.value) + getValue(registers, register);
			registers.put(register, newValue);
			return newValue;
		}
	}

	@Test
	public void addr() {
		Map<String, Long> values = new HashMap<>();
		values.put("a", 1L);
		Assert.assertEquals(0, new Addr("b", "b").setValue(values));
		Assert.assertEquals(1, new Addr("b", "a").setValue(values));
		Assert.assertEquals(2, new Addr("a", "b").setValue(values));
		Assert.assertEquals(4, new Addr("a", "a").setValue(values));
		Assert.assertEquals(4, new Addr("a", "c").setValue(values));
		Assert.assertEquals(Long.valueOf(4), values.get("a"));
		Assert.assertEquals(Long.valueOf(1), values.get("b"));
	}

	class Sub implements Operation {
		private final long value;
		private final String register;

		public Sub(String register, long value) {
			this.register = register;
			this.value = value;
		}

		@Override
		public long setValue(Map<String, Long> registers) {
			long oldValue = getValue(registers, register);
			registers.put(register, oldValue - this.value);
			return oldValue - this.value;
		}
	}

	@Test
	public void sub() {
		Map<String, Long> values = new HashMap<>();
		values.put("a", 1L);
		Assert.assertEquals(-10, new Sub("b", 10).setValue(values));
		Assert.assertEquals(-8, new Sub("b", -2).setValue(values));
		Assert.assertEquals(-13, new Sub("b", 5).setValue(values));
		Assert.assertEquals(-4, new Sub("a", 5).setValue(values));
		Assert.assertEquals(-9, new Sub("a", 5).setValue(values));
		Assert.assertEquals(Long.valueOf(-9), values.get("a"));
		Assert.assertEquals(Long.valueOf(-13), values.get("b"));
	}

	class Subr implements Operation {
		private final String value;
		private final String register;

		public Subr(String register, String value) {
			this.register = register;
			this.value = value;
		}

		@Override
		public long setValue(Map<String, Long> registers) {
			long newValue = getValue(registers, register) - getValue(registers, this.value);
			registers.put(register, newValue);
			return newValue;
		}
	}

	@Test
	public void subr() {
		Map<String, Long> values = new HashMap<>();
		values.put("a", 1L);
		Assert.assertEquals(0, new Subr("b", "b").setValue(values));
		Assert.assertEquals(-1, new Subr("b", "a").setValue(values));
		Assert.assertEquals(2, new Subr("a", "b").setValue(values));
		Assert.assertEquals(0, new Subr("a", "a").setValue(values));
		Assert.assertEquals(0, new Subr("a", "c").setValue(values));
		Assert.assertEquals(Long.valueOf(0), values.get("a"));
		Assert.assertEquals(Long.valueOf(-1), values.get("b"));
	}

	class Mod implements Operation {
		private final long value;
		private final String register;

		public Mod(String register, long value) {
			this.register = register;
			this.value = value;
		}

		@Override
		public long setValue(Map<String, Long> registers) {
			long newValue = getValue(registers, register) % this.value;
			registers.put(register, newValue);
			return newValue;
		}
	}

	@Test
	public void mod() {
		Map<String, Long> values = new HashMap<>();
		values.put("a", 1L);
		Assert.assertEquals(0, new Mod("b", 10).setValue(values));
		values.put("b", 11L);
		Assert.assertEquals(2, new Mod("b", 3).setValue(values));
		Assert.assertEquals(1, new Mod("a", 5).setValue(values));
		values.put("a", 20L);
		Assert.assertEquals(6, new Mod("a", 7).setValue(values));
		Assert.assertEquals(Long.valueOf(6), values.get("a"));
		Assert.assertEquals(Long.valueOf(2), values.get("b"));
	}

	class Modr implements Operation {
		private final String value;
		private final String register;

		public Modr(String register, String value) {
			this.register = register;
			this.value = value;
		}

		@Override
		public long setValue(Map<String, Long> registers) {
			long newValue = getValue(registers, register) % getValue(registers, this.value);
			registers.put(register, newValue);
			return newValue;
		}
	}

	@Test
	public void modr() {
		Map<String, Long> values = new HashMap<>();
		values.put("a", 3L);
		Assert.assertEquals(0, new Modr("b", "a").setValue(values));
		values.put("b", 11L);
		Assert.assertEquals(2, new Modr("b", "a").setValue(values));
		Assert.assertEquals(1, new Modr("a", "b").setValue(values));
		values.put("b", 7L);
		values.put("a", 20L);
		Assert.assertEquals(6, new Modr("a", "b").setValue(values));
		Assert.assertEquals(1, new Modr("b", "a").setValue(values));
		Assert.assertEquals(Long.valueOf(6), values.get("a"));
		Assert.assertEquals(Long.valueOf(1), values.get("b"));
	}

	class Jgz implements Operation {
		private final int offset;
		private final String register;

		public Jgz(String register, int offset) {
			this.register = register;
			this.offset = offset;
		}

		@Override
		public int nextOperation(Map<String, Long> registers, int previousOperation) {
			if (getValue(registers, register) > 0)
				return previousOperation + offset;
			return Operation.super.nextOperation(registers, previousOperation);
		}

		@Override
		public long setValue(Map<String, Long> registers) {
			return 0;
		}
	}

	@Test
	public void jgz() {
		Map<String, Long> values = new HashMap<>();
		values.put("a", 0L);
		Assert.assertSame(3, new Jgz("b", 3).nextOperation(values, 2));
		values.put("a", -1L);
		Assert.assertSame(3, new Jgz("b", 3).nextOperation(values, 2));
		values.put("a", 1L);
		Assert.assertSame(3, new Jgz("b", 3).nextOperation(values, 2));
		values.put("a", 0L);
		Assert.assertSame(3, new Jgz("a", 3).nextOperation(values, 2));
		values.put("a", -1L);
		Assert.assertSame(3, new Jgz("a", 3).nextOperation(values, 2));
		values.put("a", 1L);
		Assert.assertSame(5, new Jgz("a", 3).nextOperation(values, 2));
		values.put("a", 0L);
		Assert.assertSame(3, new Jgz("a", -3).nextOperation(values, 2));
		values.put("a", -1L);
		Assert.assertSame(3, new Jgz("a", -3).nextOperation(values, 2));
		values.put("a", 1L);
		Assert.assertSame(-1, new Jgz("a", -3).nextOperation(values, 2));
	}

	class Jgzr implements Operation {
		private final String value;
		private final String register;

		public Jgzr(String register, String value) {
			this.register = register;
			this.value = value;
		}

		@Override
		public int nextOperation(Map<String, Long> registers, int previousOperation) {
			if (getValue(registers, register) > 0)
				return previousOperation + (int) getValue(registers, value);
			return Operation.super.nextOperation(registers, previousOperation);
		}

		@Override
		public long setValue(Map<String, Long> registers) {
			return 0;
		}
	}

	@Test
	public void jgzr() {
		Map<String, Long> values = new HashMap<>();
		values.put("a", 0L);
		values.put("c", 3L);
		Assert.assertSame(3, new Jgzr("b", "c").nextOperation(values, 2));
		values.put("a", -1L);
		Assert.assertSame(3, new Jgzr("b", "c").nextOperation(values, 2));
		values.put("a", 1L);
		Assert.assertSame(3, new Jgzr("b", "c").nextOperation(values, 2));
		values.put("a", 0L);
		Assert.assertSame(3, new Jgzr("a", "c").nextOperation(values, 2));
		values.put("a", -1L);
		Assert.assertSame(3, new Jgzr("a", "c").nextOperation(values, 2));
		values.put("a", 1L);
		Assert.assertSame(5, new Jgzr("a", "c").nextOperation(values, 2));
		values.put("c", -3L);
		values.put("a", 0L);
		Assert.assertSame(3, new Jgzr("a", "c").nextOperation(values, 2));
		values.put("a", -1L);
		Assert.assertSame(3, new Jgzr("a", "c").nextOperation(values, 2));
		values.put("a", 1L);
		Assert.assertSame(-1, new Jgzr("a", "c").nextOperation(values, 2));
	}

	// TODO: jgz error to jnz
	class Jnz implements Operation {
		private final int offset;
		private final String register;

		public Jnz(String register, int offset) {
			this.register = register;
			this.offset = offset;
		}

		@Override
		public int nextOperation(Map<String, Long> registers, int previousOperation) {
			if (getValue(registers, register) != 0)
				return previousOperation + offset;
			return Operation.super.nextOperation(registers, previousOperation);
		}

		@Override
		public long setValue(Map<String, Long> registers) {
			return 0;
		}
	}

	class Jnzi implements Operation {
		private final int offset;
		private final int register;

		public Jnzi(Integer register, int offset) {
			this.register = register;
			this.offset = offset;
		}

		@Override
		public int nextOperation(Map<String, Long> registers, int previousOperation) {
			if (register != 0)
				return previousOperation + offset;
			return Operation.super.nextOperation(registers, previousOperation);
		}

		@Override
		public long setValue(Map<String, Long> registers) {
			return 0;
		}
	}

	@Test
	public void jnz() {
		Map<String, Long> values = new HashMap<>();
		values.put("a", 0L);
		Assert.assertSame(3, new Jnz("b", 3).nextOperation(values, 2));
		values.put("a", -1L);
		Assert.assertSame(3, new Jnz("b", 3).nextOperation(values, 2));
		values.put("a", 1L);
		Assert.assertSame(3, new Jnz("b", 3).nextOperation(values, 2));
		values.put("a", 0L);
		Assert.assertSame(3, new Jnz("a", 3).nextOperation(values, 2));
		values.put("a", -1L);
		Assert.assertSame(5, new Jnz("a", 3).nextOperation(values, 2));
		values.put("a", 1L);
		Assert.assertSame(5, new Jnz("a", 3).nextOperation(values, 2));
		values.put("a", 0L);
		Assert.assertSame(3, new Jnz("a", -3).nextOperation(values, 2));
		values.put("a", -1L);
		Assert.assertSame(-1, new Jnz("a", -3).nextOperation(values, 2));
		values.put("a", 1L);
		Assert.assertSame(-1, new Jnz("a", -3).nextOperation(values, 2));
	}

	class Jnzr implements Operation {
		private final String value;
		private final String register;

		public Jnzr(String register, String value) {
			this.register = register;
			this.value = value;
		}

		@Override
		public int nextOperation(Map<String, Long> registers, int previousOperation) {
			if (getValue(registers, register) != 0)
				return previousOperation + (int) getValue(registers, value);
			return Operation.super.nextOperation(registers, previousOperation);
		}

		@Override
		public long setValue(Map<String, Long> registers) {
			return 0;
		}
	}

	@Test
	public void jnzr() {
		Map<String, Long> values = new HashMap<>();
		values.put("a", 0L);
		values.put("c", 3L);
		Assert.assertSame(3, new Jnzr("b", "c").nextOperation(values, 2));
		values.put("a", -1L);
		Assert.assertSame(3, new Jnzr("b", "c").nextOperation(values, 2));
		values.put("a", 1L);
		Assert.assertSame(3, new Jnzr("b", "c").nextOperation(values, 2));
		values.put("a", 0L);
		Assert.assertSame(3, new Jnzr("a", "c").nextOperation(values, 2));
		values.put("a", -1L);
		Assert.assertSame(5, new Jnzr("a", "c").nextOperation(values, 2));
		values.put("a", 1L);
		Assert.assertSame(5, new Jnzr("a", "c").nextOperation(values, 2));
		values.put("a", -1L);
		Assert.assertSame(5, new Jnzr("a", "c").nextOperation(values, 2));
		values.put("c", -3L);
		values.put("a", 0L);
		Assert.assertSame(3, new Jnzr("a", "c").nextOperation(values, 2));
		values.put("a", -1L);
		Assert.assertSame(-1, new Jnzr("a", "c").nextOperation(values, 2));
		values.put("a", 1L);
		Assert.assertSame(-1, new Jnzr("a", "c").nextOperation(values, 2));
	}
}
