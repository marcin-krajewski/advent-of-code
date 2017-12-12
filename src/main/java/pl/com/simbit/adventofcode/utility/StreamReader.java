package pl.com.simbit.adventofcode.utility;

import java.io.InputStream;

public class StreamReader {
	public static final InputStream readFile(String file) {
		return StreamReader.class.getResourceAsStream("../" + file);
	}
}
