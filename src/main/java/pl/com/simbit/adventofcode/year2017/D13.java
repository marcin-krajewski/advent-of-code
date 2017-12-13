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

		public Layer(int depth, int range) {
			this.depth = depth;
			this.range = range;
			this.mod = (this.range - 1) * 2;
		}

		int index = 0;

		public Layer(Layer layer) {
			this(layer.depth, layer.range);
			this.index = layer.index;
		}

		void move(int layersToMove) {
			index = (index + layersToMove) % mod;
		}

		boolean isCaught() {
			return index == 0;
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
		int layersToMove = 0;
		for (int i = 0; i <= maxLayer; i++) {
			Layer layer = layers.get(i);
			if (layer == null) {
				layersToMove++;
			} else {
				moveAll(layers, layersToMove);
				if (layer.isCaught()) {
					if (returnIfFound) {
						return -1;
					}
					result += layer.product();
				}
				layersToMove = 1;
			}
		}

		return result;
	}

	private void moveAll(Map<Integer, Layer> layers, int layersToMove) {
		for (Map.Entry<Integer, Layer> entry : layers.entrySet()) {
			entry.getValue().move(layersToMove);
		}
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
		int delay = 0;
		Map<Integer, Layer> layers = getLayers(numbers, 0);
		// moveAll(layers);
		Integer moves = -1;// (Integer) getMoves(maxLayer, layers(layers), true);
		while (moves != 0) {
			// System.out.println("Checked: " + moves + ", delay: " + delay);
			delay++;
			moves = (Integer) getMoves(maxLayer, layers(layers), true);
		}
		return delay;
	}

	private Map<Integer, Layer> layers(Map<Integer, Layer> layers) {
		Map<Integer, Layer> newLayers = new HashMap<>();
		for (Map.Entry<Integer, Layer> entry : layers.entrySet()) {
			entry.getValue().move(1);
			newLayers.put(entry.getKey(), new Layer(entry.getValue()));
		}
		return newLayers;
	}
}
