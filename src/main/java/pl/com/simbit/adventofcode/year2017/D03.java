package pl.com.simbit.adventofcode.year2017;

import pl.com.simbit.adventofcode.Day;

public class D03 implements Day {

	public Object problem1() {
		return solve(new P1());
	}

	private Object solve(ValueGetter valueGetter) {
		int search = 277678;
		int[][] values = new int[600][600];

		int x = 300, y = 300;
		int xOffset = 0, yOffset = 0;
		values[x][y] = 1;

		int row = 2;
		int size;
		int count = 1;

		while (true) {
			x++;
			y--;
			size = (row++ - 1) * 2;
			for (int i = 0; i < 4; i++) {
				switch (i) {
				case 0:
					xOffset = 0;
					yOffset = 1;
					break;
				case 1:
					xOffset = -1;
					yOffset = 0;
					break;
				case 2:
					xOffset = 0;
					yOffset = -1;
					break;
				case 3:
					xOffset = 1;
					yOffset = 0;
					break;
				}
				for (int j = 0; j < size; j++) {
					try {

						x += xOffset;
						y += yOffset;

						int lastValue = valueGetter.value(x, y, values, count++);
						values[x][y] = lastValue;
						if (lastValue >= search) {
							return valueGetter.returnValue(x, y, values);
						}
					} catch (ArrayIndexOutOfBoundsException ex) {
						return 0;
					}
				}
			}
		}
	}

	public Object problem2() {
		return solve(new P2());
	}

	interface ValueGetter {
		int value(int x, int y, int[][] values, int prev);

		int returnValue(int x, int y, int[][] values);
	}

	class P1 implements ValueGetter {

		public int value(int x, int y, int[][] values, int prev) {
			return prev + 1;
		}

		public int returnValue(int x, int y, int[][] values) {
			return Math.abs(x - 300) + Math.abs(y - 300);
		}
	}

	class P2 implements ValueGetter {

		public int value(int x, int y, int[][] values, int prev) {
			int sum = 0;
			sum += values[x - 1][y];
			sum += values[x - 1][y + 1];
			sum += values[x - 1][y - 1];
			sum += values[x + 1][y];
			sum += values[x + 1][y + 1];
			sum += values[x + 1][y - 1];
			sum += values[x][y + 1];
			sum += values[x][y - 1];
			return sum;
		}

		public int returnValue(int x, int y, int[][] values) {
			return values[x][y];
		}
	}
}
