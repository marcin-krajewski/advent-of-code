package pl.com.simbit.adventofcode.year2017;

import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.utility.math.BinaryNumber;
import pl.com.simbit.utility.string.StringUtils;

public class D14 implements Day {

	private String input = "ljoxqyyw";

	@Override
	public Object problem1() {
		int onesNum = 0;
		boolean[][] values = knotHashBinaryValues();
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values.length; j++) {
				if (values[i][j]) {
					onesNum++;
				}
			}
		}
		return onesNum;
	}

	@Override
	public Object problem2() {
		boolean[][] values = knotHashBinaryValues();
		int groups = 0;
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values.length; j++) {
				if (resetGroupAndReturnTrueIfAtLeastOneElement(values, i, j)) {
					groups++;
				}
			}
		}
		return groups;
	}

	public boolean[][] knotHashBinaryValues() {
		D10 asciiGetter = new D10();
		boolean[][] values = new boolean[128][128];
		for (int i = 0; i < 128; i++) {
			values[i] = binValues(asciiGetter.knotHash(input + "-" + i));
		}
		return values;
	}

	private boolean[] binValues(String input) {
		boolean[] values = new boolean[128];
		for (int i = 0; i < input.length(); i++) {
			String bin = BinaryNumber.hexToBinWithLeadingZeros(input.substring(i, i + 1), 4);
			for (int j = 0; j < bin.length(); j++) {
				values[bin.length() * i + j] = StringUtils.getInstance().stringAt(bin, j).equals("1");
			}
		}
		return values;
	}

	private boolean resetGroupAndReturnTrueIfAtLeastOneElement(boolean[][] values, int i, int j) {
		if (!values[i][j]) {
			return false;
		}

		values[i][j] = false;

		for (int[] ij : crossWithoutCenterCoords(i, j)) {

			int nextI = ij[0];
			int nextJ = ij[1];

			if (!isInsideArray(nextI, values.length)) {
				continue;
			}
			if (!isInsideArray(nextJ, values.length)) {
				continue;
			}
			if (values[nextI][nextJ]) {
				resetGroupAndReturnTrueIfAtLeastOneElement(values, nextI, nextJ);
			}
		}
		return true;
	}

	private boolean isInsideArray(int index, int arrayLength) {
		return index >= 0 && index < arrayLength;
	}

	private int[][] crossWithoutCenterCoords(int i, int j) {
		return new int[][] { new int[] { i - 1, j }, new int[] { i + 1, j }, new int[] { i, j - 1 },
				new int[] { i, j + 1 }, };
	}
}
