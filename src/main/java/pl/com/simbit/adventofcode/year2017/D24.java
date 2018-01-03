package pl.com.simbit.adventofcode.year2017;

import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class D24 implements Day {

	private String file = "year2017/d24.txt";

	@Override
	public Object problem1() {
		List<List<Map.Entry<Integer, Integer>>> paths = extractBridges();

		int max = 0;
		for (List<Map.Entry<Integer, Integer>> fullBridges : paths) {
			int strength = 0;
			for (Map.Entry<Integer, Integer> entry : fullBridges) {
				strength += entry.getKey();
				strength += entry.getValue();
			}
			if (max < strength) {
				max = strength;
			}
		}

		return max;
	}

	private void addAll(Integer key, List<List<Map.Entry<Integer, Integer>>> paths,
			Map<Map.Entry<Integer, Integer>, Boolean> available, ArrayList<Map.Entry<Integer, Integer>> current) {

		for (Map.Entry<Map.Entry<Integer, Integer>, Boolean> entry : available.entrySet()) {
			if (!entry.getValue()) {
				continue;
			}

			Map.Entry<Integer, Integer> bridge = entry.getKey();

			if (bridge.getKey().equals(key) || bridge.getValue().equals(key)) {

				ArrayList<Map.Entry<Integer, Integer>> currentWithValue = new ArrayList<>(current);
				currentWithValue.add(bridge);
				paths.add(currentWithValue);
				available.put(bridge, false);
				if (bridge.getKey().equals(key)) {
					addAll(bridge.getValue(), paths, available, currentWithValue);
				} else {
					addAll(bridge.getKey(), paths, available, currentWithValue);
				}
				available.put(bridge, true);
			}
		}
	}

	@Override
	public Object problem2() {
		List<List<Map.Entry<Integer, Integer>>> paths = extractBridges();

		int maxl = 0;
		for (List<Map.Entry<Integer, Integer>> fullBridges : paths) {
			if (maxl < fullBridges.size()) {
				maxl = fullBridges.size();
			}
		}

		int max = 0;
		for (List<Map.Entry<Integer, Integer>> fullBridges : paths) {
			if (fullBridges.size() != maxl) {
				continue;
			}
			int strength = 0;
			for (Map.Entry<Integer, Integer> entry : fullBridges) {
				strength += entry.getKey();
				strength += entry.getValue();
			}
			if (max < strength) {
				max = strength;
			}
		}

		// System.out.println(paths);
		return max;
	}

	public List<List<Map.Entry<Integer, Integer>>> extractBridges() {
		List<String> lines = FileReader.readLines(StreamReader.readFile(file));

		Map<Map.Entry<Integer, Integer>, Boolean> available = new HashMap<>();
		for (String line : lines) {
			AbstractMap.SimpleEntry<Integer, Integer> key = new AbstractMap.SimpleEntry<>(
					Integer.parseInt(line.split("/")[0]), Integer.parseInt(line.split("/")[1]));

			available.put(key, true);
		}

		List<List<Map.Entry<Integer, Integer>>> paths = new ArrayList<>();
		for (Map.Entry<Map.Entry<Integer, Integer>, Boolean> entry : available.entrySet()) {
			Map.Entry<Integer, Integer> bridge = entry.getKey();
			if (bridge.getKey().equals(0) || bridge.getValue().equals(0)) {
				List<Map.Entry<Integer, Integer>> current = new ArrayList<>();
				ArrayList<Map.Entry<Integer, Integer>> currentWithValue = new ArrayList<>(current);
				currentWithValue.add(bridge);
				paths.add(currentWithValue);
				available.put(bridge, false);
				if (bridge.getKey().equals(0)) {
					addAll(bridge.getValue(), paths, available, currentWithValue);
				} else {
					addAll(bridge.getKey(), paths, available, currentWithValue);
				}
				available.put(bridge, true);
			}
		}
		return paths;
	}
}
