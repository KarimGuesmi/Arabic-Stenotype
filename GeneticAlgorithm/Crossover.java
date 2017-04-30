package GeneticAlgorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Crossover {

	private Entity entity;
	private Map<String, String> entity1 = new HashMap<String, String>();
	private Map<String, String> entity2 = new HashMap<String, String>();
	private Random random = new Random();

	// Two Final Entities After the Single Point Crossover
	private Map<String, String> entity1SingleCrossed = new HashMap<String, String>();
	private Map<String, String> entity2SingleCrossed = new HashMap<String, String>();

	// Two Final Entities After The Double Point Crossover
	private Map<String, String> entity1DoubleCrossed = new HashMap<String, String>();
	private Map<String, String> entity2DoubleCrossed = new HashMap<String, String>();

	/*
	 * Constructor
	 */
	public Crossover() throws IOException {
		super();
		operate();
	}

	/*
	 * The void operate() contains all the operations, Tmp lists, Tmp Maps and random indexes
	 * For the both single point and double point Crossover
	 */
	public void operate() throws IOException {
		entity = new Entity();
		entity1 = entity.generateRandomEntities();
		entity = new Entity();
		entity2 = entity.generateRandomEntities();

		System.out.println("** Two Random Entities : ");
		System.out.println(entity1);
		System.out.println(entity2);

		System.out.println("_____________________________________");

		System.out.println("** Two lists of keys from two random entities : ");
		List<String> keys1BeforeCrossover = createListKeys(entity1);
		List<String> keys2BeforeCrossover = createListKeys(entity2);
		System.out.println(keys1BeforeCrossover);
		System.out.println(keys2BeforeCrossover);

		System.out.println("_____________________________________");

		int indexSinglePoint = random.nextInt(keys1BeforeCrossover.size()) + 1;
		List<String> keys1AfterCrossover = createListKeysCrossovered(indexSinglePoint, keys1BeforeCrossover,
				keys2BeforeCrossover);
		List<String> keys2AfterCrossover = createListKeysCrossovered(indexSinglePoint, keys2BeforeCrossover,
				keys1BeforeCrossover);

		System.out.println("** The index for the single point Crossover :==> " + indexSinglePoint);
		System.out.println("** The two lists of keys After The crossover");
		System.out.println(keys1AfterCrossover);
		System.out.println(keys2AfterCrossover);

		System.out.println("_____________________________________");
		entity1SingleCrossed = createMapSingleCrossed(entity1, keys1AfterCrossover);
		entity2SingleCrossed = createMapSingleCrossed(entity2, keys2AfterCrossover);

		System.out.println("** The Two entities After The Single point crossover :");
		System.out.println(entity1SingleCrossed);
		System.out.println(entity2SingleCrossed);

		System.out.println("_____________________________________");

		// Two indexes for the two points crossover
		int index1 = random.nextInt(keys1BeforeCrossover.size()) + 1;
		int index2 = random.nextInt(keys1BeforeCrossover.size()) + 1;
		while (index1 >= index2) {
			index1 = random.nextInt(keys1BeforeCrossover.size()) + 1;
			index2 = random.nextInt(keys1BeforeCrossover.size()) + 1;
		}
		List<String> keys1AfterDoubleCrossover = createListKeysDoubleCrossovered(index1, index2, keys1BeforeCrossover,
				keys2BeforeCrossover);
		List<String> keys2AfterDoubleCrossover = createListKeysDoubleCrossovered(index1, index2, keys2BeforeCrossover,
				keys1BeforeCrossover);
		System.out.println("** The two lists After The Two point Crossover :");
		System.out.println(index1 +" && "+index2);
		System.out.println(keys1AfterDoubleCrossover);
		System.out.println(keys2AfterDoubleCrossover);
		System.out.println("_____________________________________");
		entity1DoubleCrossed = createMapSingleCrossed(entity1, keys1AfterDoubleCrossover);
		entity2DoubleCrossed = createMapSingleCrossed(entity1, keys2AfterDoubleCrossover);
		System.out.println("** The Two entities After the Double Point Crossover : ");
		System.out.println(entity1DoubleCrossed);
		System.out.println(entity2DoubleCrossed);
	}

	/*
	 * Create a Tmp List of the keys for the double point crossover
	 */
	public List<String> createListKeysDoubleCrossovered(int index1, int index2, List<String> keys1BeforeCrossover,
			List<String> keys2BeforeCrossover) {

		List<String> tmp = new ArrayList<>();
		for(int i=0;i<index1;i++){
			tmp.add(keys1BeforeCrossover.get(i));
		}
		for(int i=index1;i<index2;i++){
			tmp.add(keys2BeforeCrossover.get(i));
		}
		for(int i=index2;i<keys1BeforeCrossover.size();i++){
			tmp.add(keys1BeforeCrossover.get(i));
		}
		return tmp;
	}

	/*
	 * Create the final entity for the single point crossover
	 */
	public Map<String, String> createMapSingleCrossed(Map<String, String> entity, List<String> keys1AfterCrossover) {
		Map<String, String> tmp = new HashMap<>();
		int i = 0;
		for (String key : entity.keySet()) {
			tmp.put(key, keys1AfterCrossover.get(i));
			i++;
		}
		return tmp;
	}

	/*
	 * Create the new tmp List after the crossover
	 */
	public List<String> createListKeysCrossovered(int indexSinglePoint, List<String> keys1BeforeCrossover,
			List<String> keys2BeforeCrossover) {
		List<String> tmp = new ArrayList<>();
		for (int i = 0; i < indexSinglePoint; i++) {
			tmp.add(keys1BeforeCrossover.get(i));
		}
		for (int i = indexSinglePoint; i < keys1BeforeCrossover.size(); i++) {
			tmp.add(keys2BeforeCrossover.get(i));
		}
		return tmp;
	}

	/*
	 * Create s list of keys from the random entity
	 * Get all values(stenotupe keys) from the hashMap and put them into a Tmp List
	 */
	public List<String> createListKeys(Map<String, String> entity) {
		List<String> keys = new ArrayList<>();
		for (String key : entity.keySet()) {
			keys.add(entity.get(key));
		}
		return keys;
	}

	/*
	 * Main program for the Test
	 */
	public static void main(String[] args) throws IOException {
		Crossover crossover = new Crossover();

	}

}
