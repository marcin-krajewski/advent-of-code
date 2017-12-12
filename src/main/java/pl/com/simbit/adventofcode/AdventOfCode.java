package pl.com.simbit.adventofcode;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

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
			System.out.println("-------------------- DAY " + entry.getKey() + " -------------------------");
			for (Map.Entry<String, Object> problemEntry : entry.getValue().entrySet()) {
				runJsonProblem(entry.getKey(), problemEntry.getKey(), problemEntry.getValue());
			}
		}
	}

	private void runJsonProblem(String className, String problem, Object expectedValue) {
		try {
			Class<?> c = Class.forName(mainClass.getPackage().getName() + "." + className);
			Object o = c.getConstructors()[0].newInstance();
			Object problemValue = c.getMethod(problem).invoke(o);

			System.out.println(className + "-" + problem + " = " + problemValue);
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
}