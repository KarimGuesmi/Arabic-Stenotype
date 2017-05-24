package GeneticAlgorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import GeneticAlgorithmOld.RandomEntity;

/*
 * Create firstly a default entity
 * Then Create 5 random entities 
 * All entities are represented as HashMap data structure Letter(Arabic) ==> Key(Stenotype)
 */
public class Entity {
	/*
	 * Base data structure of the Entity
	 */
	private Random random = new Random();
	private int entityLength = 5;

	private int fitness = 0;

	// Additional tools to save our data
	private List<String> listLetters = new ArrayList<String>();
	private List<String> listKeys = new ArrayList<String>();
	private List<String> listKeysRandom = new ArrayList<String>();

	private Map<String, String> hmEntity = new HashMap<>();
	private Map<String, String> hmRandomEntity = new HashMap<>();

	/*
	 * Create A Random Individual
	 */
	public Entity() throws IOException {
		super();
		defalutEntityLists("letters.txt", "keys.txt");
		defaultEntityHashMap(listLetters, listKeys);
	}

	/*
	 * Create a default hash map from the default two lists keys and letters
	 */
	private void defaultEntityHashMap(List<String> listLetters2, List<String> listKeys2) {
		for (int i = 0; i < listLetters.size(); i++) {
			hmEntity.put(listLetters.get(i), listKeys.get(i));
		}
	}

	/*
	 * Default entity by using a default list of letters and keys without Random
	 * java class
	 */
	private void defalutEntityLists(String letterFile, String keyFile) throws IOException {
		// Create List of Arabic Letters
		BufferedReader reader1 = new BufferedReader(new FileReader(letterFile));
		String line1;
		while ((line1 = reader1.readLine()) != null) {
			listLetters.add(line1);
		}
		reader1.close();

		// Create List of Keys
		BufferedReader reader2 = new BufferedReader(new FileReader(keyFile));
		String line2;
		while ((line2 = reader2.readLine()) != null) {
			listKeys.add(line2);
		}
		reader2.close();
	}

	/*
	 * Generate some random entities In our case Random entities should be
	 * created and putted into a hashMap data structure
	 */
	public Map<String, String> generateRandomEntities() {
		// for(int i=0; i<entityLength;i++){
		randomEntityKeys(listKeys);

		// System.out.println(listKeysRandom);
		randomHashMap(listKeysRandom);
		// System.out.println(hmRandomEntity);
		listKeysRandom.clear();
		// }
		return hmRandomEntity;
	}

	/*
	 * Random hash map of arabic letters corresponds to stenotype keys
	 */
	public void randomHashMap(List<String> listKeysRandom2) {
		for (int i = 0; i < listKeysRandom.size(); i++) {
			hmRandomEntity.put(listLetters.get(i), listKeysRandom.get(i));
		}
	}
	
	/*
	 * Generate randomly the list of keys in different order 
	 * This will serve lately for the last HashMap
	 * Where we will put all the keys to its corresponding Arabic letter 
	 */
	public void randomEntityKeys(List<String> listKeys2) {
		int index;
		while (listKeysRandom.size() != listKeys.size()) {
			for (int i = 0; i < listKeys.size(); i++) {
				index = random.nextInt(listKeys.size());
				if (!listKeysRandom.contains(listKeys.get(index))) {
					listKeysRandom.add(listKeys.get(index));
				}
			}
		}
	}
	
	/*
	 * Main Program for a test
	 */
	public static void main(String[] args) throws IOException {
		Entity en = new Entity();
		System.out.println("The Default Entity");
		// System.out.println(en.listKeys);
		// System.out.println(en.listLetters);
		System.out.println(en.hmEntity);
		System.out.println("____________________________________________________________");

		// Generate some random entities
		System.out.println("The Random Entity : ");
		System.out.println(en.generateRandomEntities());
		System.out.println("____________________________________________________________");
	}
}
