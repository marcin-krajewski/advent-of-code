package pl.com.simbit.adventofcode.year2017;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.com.simbit.adventofcode.Day;
import pl.com.simbit.adventofcode.utility.StreamReader;
import pl.com.simbit.utility.file.FileReader;

public class D13 implements Day {

	class Layer {
		int depth;
		int range;
		int mod;
		int offset = 0;

		public Layer(int depth, int range) {
			this.depth = depth;
			this.range = range;
			this.mod = (this.range - 1) * 2;
		}

		void incrementOffset() {
			this.offset++;
		}

		boolean isCaught(int step) {
			return (step + offset) % mod == 0;
		}

		int product() {
			return depth * range;
		}
	}

	private String file = "year2017/d13-input.txt";

	@Override
	public Object problem1() {
		List<List<Integer>> numbers = FileReader.readNumberMatrixForSeparator(StreamReader.readFile(file), ": ");
		int maxLayer = numbers.get(numbers.size() - 1).get(0);
		return getMoves(maxLayer, getLayers(numbers, 0), false);
	}

	private Object getMoves(int maxLayer, Map<Integer, Layer> layers, boolean returnIfFound) {
		int result = 0;
		for (int i = 0; i <= maxLayer; i++) {
			Layer layer = layers.get(i);
			if (layer != null && layer.isCaught(i)) {
				if (returnIfFound) {
					return -1;
				}
				result += layer.product();
			}
		}
		return result;

	}

	private Map<Integer, Layer> getLayers(List<List<Integer>> numbers, int moves) {
		Map<Integer, Layer> layers = new HashMap<>();
		for (List<Integer> layerNumbers : numbers) {
			Integer depth = layerNumbers.get(0);
			layers.put(depth, new Layer(depth, layerNumbers.get(1)));
		}
		return layers;
	}

	@Override
	public Object problem2() {
		List<List<Integer>> numbers = FileReader.readNumberMatrixForSeparator(StreamReader.readFile(file), ": ");
		int maxLayer = numbers.get(numbers.size() - 1).get(0);
		int delay = 1;
		Map<Integer, Layer> layers = getLayers(numbers, 0);
		while ((Integer) getMoves(maxLayer, layers(layers), true) != 0) {
			delay++;
		}
		return delay;
	}

	private Map<Integer, Layer> layers(Map<Integer, Layer> layers) {
		for (Map.Entry<Integer, Layer> entry : layers.entrySet()) {
			entry.getValue().incrementOffset();
		}
		return layers;
	}
}
