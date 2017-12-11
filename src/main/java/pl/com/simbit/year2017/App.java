package pl.com.simbit.year2017;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
	public static void main(String[] args) throws IOException {

		if (args.length > 0) {
			System.out.println("-------------------- DAY " + args[0] + " -------------------------");
			runJsonProblem(args[0], args[1], null);
			return;
		}

		LinkedHashMap<String, LinkedHashMap<String, Object>> result = new ObjectMapper()
				.readValue(App.class.getResourceAsStream("answers.json"), LinkedHashMap.class);

		for (Map.Entry<String, LinkedHashMap<String, Object>> entry : result.entrySet()) {
			System.out.println("-------------------- DAY " + entry.getKey() + " -------------------------");
			for (Map.Entry<String, Object> problemEntry : entry.getValue().entrySet()) {
				runJsonProblem(entry.getKey(), problemEntry.getKey(), problemEntry.getValue());
			}
		}
	}

	private static void runJsonProblem(String className, String problem, Object expectedValue) {
		try {
			Class<?> c = Class.forName(App.class.getPackage().getName() + "." + className);
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