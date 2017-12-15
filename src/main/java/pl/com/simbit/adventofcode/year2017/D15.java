package pl.com.simbit.adventofcode.year2017;

import java.math.BigInteger;

import pl.com.simbit.adventofcode.Day;

public class D15 implements Day {

	public static final BigInteger MUL_A = BigInteger.valueOf(16807);
	public static final BigInteger MUL_B = BigInteger.valueOf(48271);
	public static final BigInteger MOD = BigInteger.valueOf(2147483647);
	public static final int MOD2 = 65536;
	private int startA = 618;
	private int startB = 814;

	@Override
	public Object problem1() {
		int count = 0;
		for (int i = 0; i < 40000000; i++) {
			startA = MUL_A.multiply(BigInteger.valueOf(startA)).mod(MOD).intValue();
			startB = MUL_B.multiply(BigInteger.valueOf(startB)).mod(MOD).intValue();
			if (startA % MOD2 == startB % MOD2)
				count++;
		}
		return count;
	}

	@Override
	public Object problem2() {
		int count = 0;
		for (int i = 0; i < 5000000; i++) {
			startA = MUL_A.multiply(BigInteger.valueOf(startA)).mod(MOD).intValue();
			while (!(startA % 4 == 0))
				startA = MUL_A.multiply(BigInteger.valueOf(startA)).mod(MOD).intValue();
			startB = MUL_B.multiply(BigInteger.valueOf(startB)).mod(MOD).intValue();
			while (!(startB % 8 == 0))
				startB = MUL_B.multiply(BigInteger.valueOf(startB)).mod(MOD).intValue();
			if (startA % MOD2 == startB % MOD2)
				count++;
		}
		return count;
	}
}
