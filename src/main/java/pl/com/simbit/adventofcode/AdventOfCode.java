package pl.com.simbit.adventofcode;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Stopwatch;

public class AdventOfCode {

	private final Class mainClass;

	public AdventOfCode(Class mainClass) {
		this.mainClass = mainClass;
	}

	public void runSingle(String day, String problem) {
		System.out.println("-------------------- DAY " + day + " -------------------------");
		runJsonProblem(day, problem, null);
	}

	public void runAll() throws IOException {

		LinkedHashMap<String, LinkedHashMap<String, Object>> result = new ObjectMapper().readValue(
				AdventOfCode.class.getResourceAsStream(mainClass.getPackage().getName()
						.substring(mainClass.getPackage().getName().lastIndexOf(".") + 1) + "/answers.json"),
				LinkedHashMap.class);

		for (Map.Entry<String, LinkedHashMap<String, Object>> entry : result.entrySet()) {
			if (entry.getValue().containsKey("avoid") && entry.getValue().get("avoid").equals(true)) {
				System.out
						.println(" OMITTING ---------- DAY " + entry.getKey() + " -------------------------");
				System.out.println();
				continue;
			}
			System.out.println("-------------------- DAY " + entry.getKey() + " -------------------------");
			for (Map.Entry<String, Object> problemEntry : entry.getValue().entrySet()) {
				runJsonProblem(entry.getKey(), problemEntry.getKey(), problemEntry.getValue());
			}
			System.out.println();
		}
	}

	private void runJsonProblem(String className, String problem, Object expectedValue) {
		try {
			Class<?> c = Class.forName(mainClass.getPackage().getName() + "." + className);
			Object o = c.getConstructors()[0].newInstance();

			Stopwatch stopwatch = Stopwatch.createStarted();
			Object problemValue = c.getMethod(problem).invoke(o);

			System.out.format("%20s%40s%30s", className + "-" + problem, problemValue,
					millisToPrint(stopwatch.elapsed(TimeUnit.MILLISECONDS)));
			System.out.println();
			assert problemValue.equals(expectedValue) : "Incorrect problem result";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	private String millisToPrint(long millis) {
		return (millis > 2000 ? " \033[0;31m" : "\033[0;32m") + millis + " ms" + "\033[0m";
	}
}