package pl.com.simbit.adventofcode.year2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import pl.com.simbit.utility.file.FileReader;

public class D10 implements Day {

	private int size = 256;

	private String file = "d10-input.txt";

	@Override
	public Object problem1() {
		int[] array = new int[size];
		for (int i = 0; i < array.length; i++) {
			array[i] = i;
		}
		List<Integer> order = FileReader.readNumberMatrixForSeparator(StreamReader.readFile(file), ",").get(0);

		int skipIndex = 0;
		int currentIndex = 0;
		for (int o : order) {
			array = switchArray(array, o, currentIndex);
			currentIndex = currentIndex(currentIndex, o, skipIndex);
			skipIndex++;
		}

		return array[0] * array[1];
	}

	private int currentIndex(int currentIndex, int o, int skipIndex) {
		return (currentIndex + o + skipIndex) % size;
	}

	private int reversedIndex(int currentIndex, int offset) {
		return currentIndex(currentIndex, offset, 0);
	}

	private int[] switchArray(int[] array, int o, int currentIndex) {
		int[] newArray = Arrays.copyOf(array, array.length);
		for (int i = 0; i < o; i++) {
			newArray[reversedIndex(currentIndex, i)] = array[reversedIndex(currentIndex, o - i - 1)];
		}
		return newArray;
	}

	@Override
	public Object problem2() {

		String input = FileReader.firstLine(StreamReader.readFile(file));

		int[] array = getRecalculatedArray(input);
		List<Integer> list = getEach16Xor(array);
		return hashes(list);
	}

	private List<Integer> getEach16Xor(int[] array) {
		List<Integer> list = new ArrayList<>();
		int xor = array[0];
		for (int i = 1; i < array.length; i++) {
			xor ^= array[i];
			if ((i + 1) % 16 == 0) {
				list.add(xor);
				if ((i + 1) < array.length) {
					xor = array[i + 1];
					i++;
				}
			}
		}
		return list;
	}

	private String hashes(List<Integer> list) {
		String hashes = "";
		for (Integer i : list) {
			hashes += String.format("%02x", i);
		}
		return hashes;
	}

	private int[] getRecalculatedArray(String input) {
		List<Integer> order = order(input);
		int[] array = new int[size];
		for (int i = 0; i < array.length; i++) {
			array[i] = i;
		}
		int skipIndex = 0;
		int currentIndex = 0;
		for (int i = 0; i < 64; i++) {
			for (int o : order) {
				array = switchArray(array, o, currentIndex);
				currentIndex = currentIndex(currentIndex, o, skipIndex);
				skipIndex++;
			}
		}
		return array;
	}

	private List<Integer> order(String input) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < input.length(); i++) {
			list.add(getAscii(input.charAt(i)));
		}
		list.addAll(Arrays.asList(17, 31, 73, 47, 23));
		return list;
	}

	private Integer getAscii(Character c) {
		return Integer.valueOf(c.charValue());
	}

	@Test
	public void order() {
		List<Integer> order = order("1,2,3");
		Assert.assertEquals(Integer.valueOf(49), order.get(0));
		Assert.assertEquals(Integer.valueOf(44), order.get(1));
		Assert.assertEquals(Integer.valueOf(50), order.get(2));
		Assert.assertEquals(Integer.valueOf(44), order.get(3));
		Assert.assertEquals(Integer.valueOf(51), order.get(4));
		Assert.assertEquals(Integer.valueOf(17), order.get(5));
		Assert.assertEquals(Integer.valueOf(31), order.get(6));
		Assert.assertEquals(Integer.valueOf(73), order.get(7));
		Assert.assertEquals(Integer.valueOf(47), order.get(8));
		Assert.assertEquals(Integer.valueOf(23), order.get(9));
	}

	@Test
	public void ascii() {
		Assert.assertEquals(Integer.valueOf(49), getAscii('1'));
		Assert.assertEquals(Integer.valueOf(50), getAscii('2'));
		Assert.assertEquals(Integer.valueOf(51), getAscii('3'));
		Assert.assertEquals(Integer.valueOf(44), getAscii(','));
	}

	@Test
	public void hashes() {
		Assert.assertEquals("4007ff", hashes(Arrays.asList(64, 7, 255)));
	}

	@Test
	public void xor() {
		Assert.assertEquals(new Integer(64),
				getEach16Xor(new int[] { 65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 22 }).get(0));
	}
}
