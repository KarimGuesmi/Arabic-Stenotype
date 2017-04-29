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

public class Entity {
	
	/*
	 * Base data structure of the Entity
	 */
	private Random random = new Random();
	private int entityLength = 5;
	private List<Map<String,String>>entities = new ArrayList<>();
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

	

	private void defaultEntityHashMap(List<String> listLetters2, List<String> listKeys2) {
		for (int i = 0; i < listLetters.size(); i++) {
			hmEntity.put(listLetters.get(i), listKeys.get(i));
		}
		
	}


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

	public void generateRandomEntities() {
		for(int i=0; i<entityLength;i++){
			randomEntityKeys(listKeys);
			System.out.println("* Random Entity  : " + i);
			//System.out.println(listKeysRandom);
			randomHashMap(listKeysRandom);
			System.out.println(hmRandomEntity);
			listKeysRandom.clear();
		}
	}

	
	public void randomHashMap(List<String> listKeysRandom2) {
		for (int i = 0; i < listKeysRandom.size(); i++) {
			hmRandomEntity.put(listLetters.get(i), listKeysRandom.get(i));
		}
		
	}

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

	public static void main(String[] args) throws IOException {
		Entity en = new Entity();
		System.out.println("The Default Entity");
		//System.out.println(en.listKeys);
		//System.out.println(en.listLetters);
		System.out.println(en.hmEntity);
		System.out.println("____________________________________________________________");
		
		// Generate some random entities
		en.generateRandomEntities();
	}



	


}
