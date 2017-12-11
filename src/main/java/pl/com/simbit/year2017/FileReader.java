package pl.com.simbit.year2017;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

	public static final String TAB_SPLIT_REGEX = "\t";

	public static List<Integer> fileAsDigits(String fileName) {
		try {
			InputStream fileStream = FileReader.class.getResourceAsStream(fileName);
			String file = IOUtils.toString(fileStream);
			List<Integer> numbers = new ArrayList<Integer>();
			for (int i = 0; i < file.length(); i++) {
				numbers.add(Integer.parseInt(file.substring(i, i + 1)));
			}
			return numbers;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<String> readLines(String fileName) {
		try {
			InputStream fileStream = FileReader.class.getResourceAsStream(fileName);
			List<String> file = IOUtils.readLines(fileStream);
			return file;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String firstLine(String fileName) {
		return readLines(fileName).get(0);
	}

	public static List<Integer> readLinesAsNumbers(String fileName) {
		List<Integer> numbers = new ArrayList<Integer>();
		for (String line : readLines(fileName)) {
			numbers.add(Integer.parseInt(line));
		}
		return numbers;
	}

	public static List<List<Integer>> readNumberMatrix(String fileName) {
		return readNumberMatrixForSeparator(fileName, TAB_SPLIT_REGEX);
	}

	public static List<List<Integer>> readNumberMatrixForSeparator(String fileName, String separator) {
		List<List<Integer>> matrix = new ArrayList<List<Integer>>();
		for (List<String> singleLine : readMatrix(fileName, separator)) {
			List<Integer> singleLineNumbers = new ArrayList<Integer>();
			for (String stringNumber : singleLine) {
				singleLineNumbers.add(Integer.parseInt(stringNumber));
			}
			matrix.add(singleLineNumbers);
		}
		return matrix;
	}

	public static List<List<String>> readMatrix(String fileName, String separator) {
		List<List<String>> matrix = new ArrayList<List<String>>();
		List<String> fileLines = FileReader.readLines(fileName);
		for (String singleLine : fileLines) {
			List<String> singleLineNumbers = new ArrayList<String>();
			for (String stringNumber : singleLine.split(separator)) {
				singleLineNumbers.add(stringNumber);
			}
			matrix.add(singleLineNumbers);
		}
		return matrix;
	}
}
