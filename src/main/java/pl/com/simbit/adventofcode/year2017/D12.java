package pl.com.simbit.adventofcode.year2017;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

public class D12 implements Day {

	private String file = "year2017/d12-input.txt";

	@Override
	public Object problem1() {
		Map<Integer, Set<Integer>> programs = readPrograms();
		return getProgramsOfGroup(programs, 0).size();
	}

	@Override
	public Object problem2() {
		Map<Integer, Set<Integer>> programs = readPrograms();
		Map<Integer, Set<Integer>> groups = new HashMap<>();

		for (Map.Entry<Integer, Set<Integer>> entry : programs.entrySet()) {
			if (anyGroupContainsCurrentId(groups, entry.getKey()))
				continue;
			groups.put(entry.getKey(), getProgramsOfGroup(programs, entry.getKey()));
		}

		return groups.size();
	}

	private boolean anyGroupContainsCurrentId(Map<Integer, Set<Integer>> groups, Integer id) {
		for (Map.Entry<Integer, Set<Integer>> groupsEntry : groups.entrySet()) {
			if (groupsEntry.getValue().contains(id)) {
				return true;
			}
		}
		return false;
	}

	private Map<Integer, Set<Integer>> readPrograms() {
		List<String> lines = FileReader.readLines(StreamReader.readFile(file));

		Map<Integer, Set<Integer>> programs = new HashMap<>();

		for (String line : lines) {
			Integer id = Integer.parseInt(line.split(" <-> ")[0]);
			Set<Integer> idSet = new HashSet<>();
			for (String dependentId : line.split(" <-> ")[1].split(", ")) {
				idSet.add(Integer.parseInt(dependentId));
			}
			programs.put(id, idSet);
		}
		return programs;
	}

	private Set<Integer> getProgramsOfGroup(Map<Integer, Set<Integer>> programs, int groupId) {
		Set<Integer> containing = new HashSet<>();
		containing.add(groupId);
		boolean nextLoop = true;
		while (nextLoop) {
			nextLoop = false;
			for (Map.Entry<Integer, Set<Integer>> entry : programs.entrySet()) {
				if (entry.getValue().contains(groupId) || CollectionUtils.containsAny(entry.getValue(), containing)) {
					if (containing.add(entry.getKey())) {
						nextLoop = true;
					}
				}
			}
		}
		return containing;
	}
}
