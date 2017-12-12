package pl.com.simbit.adventofcode.year2016;

import java.io.IOException;

import pl.com.simbit.adventofcode.AdventOfCode;

public class AdventOfCode2016 {

	public static void main(String[] args) throws IOException {
		if (args.length > 0) {
			new AdventOfCode(AdventOfCode2016.class).runSingle(args[0], args[1]);
		} else {
			new AdventOfCode(AdventOfCode2016.class).runAll();
		}

	}
}
