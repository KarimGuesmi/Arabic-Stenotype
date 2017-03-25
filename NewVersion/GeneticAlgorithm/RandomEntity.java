package GeneticAlgorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class RandomEntity {

	private  List<String> listLetters = new ArrayList<String>();
	private  List<String> listKeys = new ArrayList<String>();
	private  List<String> listKeysRandom = new ArrayList<String>();
	
	private Map<String, String> hmEntity = new HashMap<>();
	private Map<String, String> hmRandomEntity = new HashMap<>();
	
	/*
	 * Constructor
	 */
	public RandomEntity(String letterFile, String keyFile) throws IOException{
		defalutEntityLists(letterFile, keyFile);
		defaultEntityHashMap(listLetters, listKeys);	
	}
	
	/*
	 * This constructor is to define only one Random Entity
	 * This will be used next in the population class top generate some Entities
	 */
	public RandomEntity()   {
		try {
			defalutEntityLists("letters.txt","keys.txt");
			randomEntityKeys(listKeys);
			randomHashMap(listKeysRandom);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(hmRandomEntity);
	}

	/*
	 * Create  a Random List of Keys
	 */
	public void randomEntityKeys(List<String>listKeys) {
		Random random = new Random();
		int index;
		while(listKeysRandom.size()!=listKeys.size()){
			for(int i =0; i< listKeys.size();i++){
				index=random.nextInt(listKeys.size());
				if(!listKeysRandom.contains(listKeys.get(index))){
					listKeysRandom.add(listKeys.get(index));
				}
			}
		}
		
		
	}



	/*
	 * create default entity HashMap of letters:keys
	 */
	private void defaultEntityHashMap(List<String> listLetters, List<String> listKeys) {
		for(int i=0; i<listLetters.size();i++){
			hmEntity.put(listLetters.get(i), listKeys.get(i));
		}
		
	}



	/*
	 * Create a default entity List of letters and Keys
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
	 * Create randomHashMap List of letters:Keys
	 */
	private void randomHashMap(List<String> listKeysRandom) {
		for(int i=0; i<listKeysRandom.size();i++){
			hmRandomEntity.put(listLetters.get(i), listKeysRandom.get(i));
		}
		
	}
	
	/*
	 * Display Default Entities
	 */
	private  void display() {
		System.out.println("************ Default Entity *************");
		System.out.println(listLetters);
		System.out.println(listKeys);
		System.out.println(hmEntity);
		System.out.println("************ Random Entities *************");
		
		
	}


	/*
	 * Main Method for test
	 */
	public static void main(String[] args) throws IOException  {
		RandomEntity re = new RandomEntity("letters.txt", "keys.txt");
		
		// Display Only the default entity
		re.display();
		
		// Generate Some Random Entities Lists
		for(int i=0; i<5; i++){
			re.randomEntityKeys(re.listKeys);
			System.out.println("Generation : "+i);
			System.out.println(re.listKeysRandom);
			re.randomHashMap(re.listKeysRandom);
			System.out.println(re.hmRandomEntity);
			re.listKeysRandom.clear();
		}
	// This is to create only one Random Entity
	System.out.println("*************");
	RandomEntity ree = new RandomEntity();
	
	}
}
