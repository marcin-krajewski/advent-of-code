package pl.com.simbit.adventofcode.year2016;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class D04 implements Day {

	private String file = "year2016/d04.txt";

	@Override
	public Object problem1() {
		List<String> lines = FileReader.readLines(StreamReader.readFile(file));

		int realRooms = 0;
		for (String line : lines) {
			int lastDashIndex = line.lastIndexOf("-");
			int squareBracketOpenIndex = line.indexOf("[");
			Room room = new Room(line.substring(0, lastDashIndex),
					Integer.parseInt(line.substring(lastDashIndex + 1, squareBracketOpenIndex)),
					line.substring(squareBracketOpenIndex + 1, line.length() - 1));
			if (room.isRealRoom()) {
				realRooms++;
			}
		}
		return realRooms;
	}

	@Override
	public Object problem2() {
		return null;
	}

	class Room {
		String name;
		int id;
		String checksum;

		public Room(String name, int id, String checksum) {
			this.name = name;
			this.id = id;
			this.checksum = checksum;
		}

		public boolean isRealRoom() {
			return checksum.equals(countedChecksum());
		}

		private String countedChecksum() {
			Map<Character, Integer> counts = new HashMap<>();
			for (int i = 0; i < name.length(); i++) {
				char c = name.charAt(i);
				if (c == '-') {
					continue;
				}
				incrementCountInMap(c, counts);
			}

			Map<Integer, Character> inverted = MapUtils.invertMap(counts);
			List<Integer> cList = new ArrayList<>(inverted.keySet());
			Collections.sort(cList);
			Collections.reverse(cList);

			for(Integer count : cList) {
				Character character = inverted.get(cList);
			}

			return "tofinish";
		}

		private void incrementCountInMap(Character c, Map<Character, Integer> values) {
			Integer integer = values.get(c);
			if (integer == null) {
				return;
			}
			values.put(c, integer + 1);
		}

		@Override
		public String toString() {
			return "Room{" + "name='" + name + '\'' + ", id=" + id + ", checksum='" + checksum + '\'' + '}';
		}
	}
}
