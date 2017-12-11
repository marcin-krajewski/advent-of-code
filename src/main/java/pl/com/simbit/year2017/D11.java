package pl.com.simbit.year2017;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class D11 implements Day {

	private String file = "d11-input.txt";

	@Override
	public Object problem1() {
		List<String> directions = FileReader.readMatrix(file, ",").get(0);

		int currx = 0, curry = 0;
		for (String direction : directions) {
			switch (direction) {
			case "n":
				curry += 2;
				break;
			case "s":
				curry -= 2;
				break;
			case "ne":
				curry++;
				currx++;
				break;
			case "nw":
				curry++;
				currx--;
				break;
			case "sw":
				curry--;
				currx--;
				break;
			case "se":
				curry--;
				currx++;
				break;
			}
		}

		return distance(currx, curry);
	}

	private Integer distance(int x, int y) {

		int yDiff = Math.abs(y);
		if (Math.abs(x) < yDiff) {
			return Math.abs(x) + (yDiff - Math.abs(x)) / 2;
		}

		return Math.abs(x);

	}

	@Override
	public Object problem2() {
		int max = Integer.MIN_VALUE;
		List<String> directions = FileReader.readMatrix(file, ",").get(0);

		int currx = 0, curry = 0;
		for (String direction : directions) {
			switch (direction) {
			case "n":
				curry += 2;
				break;
			case "s":
				curry -= 2;
				break;
			case "ne":
				curry++;
				currx++;
				break;
			case "nw":
				curry++;
				currx--;
				break;
			case "sw":
				curry--;
				currx--;
				break;
			case "se":
				curry--;
				currx++;
				break;
			}
			int distance = distance(currx, curry);
			if (max < distance) {
				max = distance;
			}
		}

		return max;
	}

	@Test
	public void distance() {
		Assert.assertEquals(new Integer(5), distance(-5, 1));
		Assert.assertEquals(new Integer(6), distance(-6, 2));
		Assert.assertEquals(new Integer(5), distance(5, 1));
		Assert.assertEquals(new Integer(6), distance(6, 2));
		Assert.assertEquals(new Integer(5), distance(-5, -1));
		Assert.assertEquals(new Integer(6), distance(-6, -2));
		Assert.assertEquals(new Integer(5), distance(5, -1));
		Assert.assertEquals(new Integer(6), distance(6, -2));

		Assert.assertEquals(new Integer(2), distance(0, 4));
		Assert.assertEquals(new Integer(3), distance(1, 5));
		Assert.assertEquals(new Integer(2), distance(0, -4));
		Assert.assertEquals(new Integer(3), distance(1, -5));
		Assert.assertEquals(new Integer(3), distance(-1, -5));
		Assert.assertEquals(new Integer(3), distance(-1, 5));
	}
}
