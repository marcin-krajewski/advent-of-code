package pl.com.simbit.adventofcode.year2017;

import java.math.BigInteger;

import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.utility.math.BinaryNumber;

public class D15 implements Day {

	public static final BigInteger MUL_A = BigInteger.valueOf(16807);
	public static final BigInteger MUL_B = BigInteger.valueOf(48271);
	public static final BigInteger MOD = BigInteger.valueOf(2147483647);
	public static final BigInteger MODA = BigInteger.valueOf(4);
	public static final BigInteger MODB = BigInteger.valueOf(8);
	private BigInteger startA = BigInteger.valueOf(618);
	private BigInteger startB = BigInteger.valueOf(814);

	@Override
	public Object problem1() {
		return resolveProblem(new P1(), 40000000);
	}

	@Override
	public Object problem2() {
		return resolveProblem(new P2(), 5000000);
	}

	public int resolveProblem(NextNumGetter valueGetter, int limit) {
		int count = 0;
		for (int i = 0; i < limit; i++) {
			startA = valueGetter.nextValueA(startA);
			startB = valueGetter.nextValueB(startB);
			if (BinaryNumber.hasTheSameLastBits(startA.intValue(), startB.intValue(), 16))
				count++;
		}
		return count;
	}

	interface NextNumGetter {
		BigInteger nextValueA(BigInteger previousValue);

		BigInteger nextValueB(BigInteger previousValue);
	}

	class P1 implements NextNumGetter {

		@Override
		public BigInteger nextValueA(BigInteger previousValue) {
			return MUL_A.multiply(previousValue).mod(MOD);
		}

		@Override
		public BigInteger nextValueB(BigInteger previousValue) {
			return MUL_B.multiply(previousValue).mod(MOD);
		}
	}

	class P2 implements NextNumGetter {

		@Override
		public BigInteger nextValueA(BigInteger previousValue) {
			previousValue = MUL_A.multiply(previousValue).mod(MOD);
			while (!(previousValue.mod(MODA) == BigInteger.ZERO))
				previousValue = MUL_A.multiply(previousValue).mod(MOD);
			return previousValue;
		}

		@Override
		public BigInteger nextValueB(BigInteger previousValue) {
			previousValue = MUL_B.multiply(previousValue).mod(MOD);
			while (!(previousValue.mod(MODB) == BigInteger.ZERO))
				previousValue = MUL_B.multiply(previousValue).mod(MOD);
			return previousValue;
		}
	}
}
