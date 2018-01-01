package pl.com.simbit.adventofcode.year2017;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

@Builder
@Getter
@Setter
class Tower {
	String key;
	Integer weight;
	List<Tower> children = new ArrayList<>();
	Tower parent;

	void addChild(Tower tower) {
		this.children.add(tower);
	}

	int sum() {
		return weight + childSum();
	}

	private Integer childSum() {
		Integer sum = 0;
		for (Tower child : this.children) {
			sum += child.sum();
		}
		return sum;
	}

	public boolean hasIncorrect() {
		return getIncorrect() != null;
	}

	public Tower getIncorrect() {

		for (int i = 0; i < this.children.size() - 2; i++) {
			Tower t1 = this.children.get(i);
			Tower t2 = this.children.get(i + 1);
			Tower t3 = this.children.get(i + 2);
			if (t1.sum() == t2.sum() && t1.sum() != t3.sum()) {
				return t3;
			}
			if (t1.sum() == t3.sum() && t1.sum() != t2.sum()) {
				return t2;
			}
			if (t2.sum() == t3.sum() && t1.sum() != t2.sum()) {
				return t1;
			}
		}
		return null;
	}

	public Tower getSingleCorrect() {
		Tower incorrect = getIncorrect();
		for (int i = 0; i < this.children.size() - 3; i++) {
			Tower t1 = this.children.get(i);
			if (!t1.equals(incorrect)) {
				return t1;
			}
		}
		return null;
	}
}

public class D07 implements Day {

	private String file = "year2017/d07.txt";

	public Object problem1() {

		List<String> lines = FileReader.readLines(StreamReader.readFile(file));

		Map<String, Integer> counts = new HashMap<>();

		for (String s : lines) {
			String s1 = s.substring(0, s.indexOf(" "));
			incrementCount(s1, counts);
			s = s.substring(s.indexOf(" "));
			if (s.contains("->")) {
				s = s.substring(s.indexOf("-> ") + 3);
				for (String ss : s.split(", ")) {

					incrementCount(ss, counts);
				}
			}
		}

		return MapUtils.invertMap(counts).get(1);
	}

	static <T> void incrementCount(T key, Map<T, Integer> counts) {
		Integer value = counts.get(key);
		if (value == null) {
			value = 0;
		}
		value++;
		counts.put(key, value);
	}

	public Object problem2() {
		Map<String, String> towers = new HashMap<>();

		List<String> lines = FileReader.readLines(StreamReader.readFile(file));
		for (String s : lines) {
			towers.put(s.substring(0, s.indexOf(" ")), s);
		}

		String startKey = (String) problem1();
		Tower tower = Tower.builder().children(new ArrayList<>()).build();
		fillChildren(startKey, towers, tower);

		Tower current = tower;
		while (current.hasIncorrect()) {
			current = current.getIncorrect();
		}

		return current.getParent().getIncorrect().getWeight()
				- (current.getParent().getIncorrect().sum() - current.getParent().getSingleCorrect().sum());
	}

	private void fillChildren(String key, Map<String, String> towers, Tower tower) {
		String line = towers.get(key);

		Integer weight = Integer.valueOf(line.substring(line.indexOf("(") + 1, line.indexOf(")")));
		tower.setKey(key);
		tower.setWeight(weight);

		if (line.contains("->")) {
			line = line.substring(line.indexOf("-> ") + 3);
			for (String childKey : line.split(", ")) {
				Tower childTower = Tower.builder().children(new ArrayList<>()).parent(tower).build();
				tower.addChild(childTower);
				fillChildren(childKey, towers, childTower);
			}
		}
	}

}
