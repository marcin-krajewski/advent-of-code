package pl.com.simbit.adventofcode.year2018;

import java.io.IOException;
import pl.com.simbit.adventofcode.AdventOfCode;

public class AdventOfCode2018 {

  public static void main(String[] args) throws IOException {
    if (args.length > 0) {
      new AdventOfCode(AdventOfCode2018.class).runSingle(args[0], args[1]);
    } else {
      new AdventOfCode(AdventOfCode2018.class).runAll();
    }

  }

}
