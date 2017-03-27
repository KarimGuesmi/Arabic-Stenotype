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

import javax.xml.crypto.dsig.spec.HMACParameterSpec;

public class RandomEntity {

	private List<String> listLetters = new ArrayList<String>();
	private List<String> listKeys = new ArrayList<String>();
	private List<String> listKeysRandom = new ArrayList<String>();

	private Map<String, String> hmEntity = new HashMap<>();
	private Map<String, String> hmRandomEntity = new HashMap<>();

	/*
	 * Fitness Computation Lists
	 */
	private List<String> listLeftHand = new ArrayList<String>();
	private List<String> listRightHand = new ArrayList<String>();
	private List<String> listRow1 = new ArrayList<String>();
	private List<String> listRow2 = new ArrayList<String>();
	private List<String> listRow3 = new ArrayList<String>();
	private Map<String, Integer> hmWeightKeys = new HashMap<>();

	/*
	 * Constructor
	 */
	public RandomEntity(String letterFile, String keyFile) throws IOException {
		defalutEntityLists(letterFile, keyFile);
		defaultEntityHashMap(listLetters, listKeys);
	}

	/*
	 * This constructor is to define only one Random Entity This will be used
	 * next in the population class top generate some Entities
	 */
	public RandomEntity() {

	}

	public void createOneRandomEntity() {
		try {
			defalutEntityLists("letters.txt", "keys.txt");
			randomEntityKeys(listKeys);
			randomHashMap(listKeysRandom);

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(hmRandomEntity);
	}

	/*
	 * Create a Random List of Keys
	 */
	public void randomEntityKeys(List<String> listKeys) {
		Random random = new Random();
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
	 * create default entity HashMap of letters:keys
	 */
	private void defaultEntityHashMap(List<String> listLetters, List<String> listKeys) {
		for (int i = 0; i < listLetters.size(); i++) {
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
		for (int i = 0; i < listKeysRandom.size(); i++) {
			hmRandomEntity.put(listLetters.get(i), listKeysRandom.get(i));
		}
	}

	/*
	 * Display Default Entities
	 */
	private void display() {
		System.out.println("************ Default Entity *************");
		System.out.println(listLetters);
		System.out.println(listKeys);
		System.out.println(hmEntity);
		System.out.println("************ Random Entities *************");
	}

	/*
	 * This void is to setUp all the lists For the fitness value computaiton
	 */
	public void fintnessComputationLists() {
		try {
			BufferedReader leftHand = new BufferedReader(new FileReader("listLeftHand.txt"));
			BufferedReader rightHand = new BufferedReader(new FileReader("listRightHand.txt"));
			BufferedReader row1 = new BufferedReader(new FileReader("listRow1.txt"));
			BufferedReader row2 = new BufferedReader(new FileReader("listRow2.txt"));
			BufferedReader row3 = new BufferedReader(new FileReader("listRow3.txt"));
			BufferedReader weightKeys = new BufferedReader(new FileReader("keysWeight.txt"));
			
			
			String line1, line2, lineR1, lineR2, lineR3, weight;

			while ((line1 = leftHand.readLine()) != null) {
				listLeftHand.add(line1);
			}
			while ((line2 = rightHand.readLine()) != null) {
				listRightHand.add(line2);
			}
			while ((lineR1 = row1.readLine()) != null) {
				listRow1.add(lineR1);
			}
			while ((lineR2 = row2.readLine()) != null) {
				listRow2.add(lineR2);
			}
			while ((lineR3 = row3.readLine()) != null) {
				listRow3.add(lineR3);
			}
			while ((weight = weightKeys.readLine()) != null) {
				if(weight.equals("-D")||weight.equals("-Z")){
					hmWeightKeys.put(weight, 0);
				}else 
					if(weight.equals("S")||weight.equals("-T")||weight.equals("-S")){
						hmWeightKeys.put(weight, 1);
					}
				else 
					if(weight.equals("T")||weight.equals("K")||weight.equals("-L")||weight.equals("-G")){
						hmWeightKeys.put(weight, 2);
					}
				else 
					if(weight.equals("A")||weight.equals("O")||weight.equals("-E")||weight.equals("-U")){
							hmWeightKeys.put(weight, 3);
					}
				else 
					if(weight.equals("P")||weight.equals("W")||weight.equals("-P")||weight.equals("-B")){
								hmWeightKeys.put(weight, 4);
					}
				else 
					if(weight.equals("H")||weight.equals("R")||weight.equals("-F")||weight.equals("-R")){
									hmWeightKeys.put(weight, 5);
					}
			}
			
			leftHand.close();
			rightHand.close();
			row1.close();
			row2.close();
			row3.close();
			weightKeys.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * Getters && Settres
	 */
	public List<String> getListLetters() {
		return listLetters;
	}

	public void setListLetters(List<String> listLetters) {
		this.listLetters = listLetters;
	}

	public List<String> getListKeys() {
		return listKeys;
	}

	public void setListKeys(List<String> listKeys) {
		this.listKeys = listKeys;
	}

	public List<String> getListKeysRandom() {
		return listKeysRandom;
	}

	public void setListKeysRandom(List<String> listKeysRandom) {
		this.listKeysRandom = listKeysRandom;
	}

	public Map<String, String> getHmEntity() {
		return hmEntity;
	}

	public void setHmEntity(Map<String, String> hmEntity) {
		this.hmEntity = hmEntity;
	}

	public Map<String, String> getHmRandomEntity() {
		return hmRandomEntity;
	}

	public void setHmRandomEntity(Map<String, String> hmRandomEntity) {
		this.hmRandomEntity = hmRandomEntity;
	}

	public List<String> getListLeftHand() {
		return listLeftHand;
	}

	public void setListLeftHand(List<String> listLeftHand) {
		this.listLeftHand = listLeftHand;
	}

	public List<String> getListRightHand() {
		return listRightHand;
	}

	public void setListRightHand(List<String> listRightHand) {
		this.listRightHand = listRightHand;
	}

	public List<String> getListRow1() {
		return listRow1;
	}

	public void setListRow1(List<String> listRow1) {
		this.listRow1 = listRow1;
	}

	public List<String> getListRow2() {
		return listRow2;
	}

	public void setListRow2(List<String> listRow2) {
		this.listRow2 = listRow2;
	}

	public List<String> getListRow3() {
		return listRow3;
	}

	public void setListRow3(List<String> listRow3) {
		this.listRow3 = listRow3;
	}
	public Map<String, Integer> getHmWeightKeys() {
		return hmWeightKeys;
	}

	public void setHmWeightKeys(Map<String, Integer> hmWeightKeys) {
		this.hmWeightKeys = hmWeightKeys;
	}

	/*
	 * Main Method for test
	 */
	public static void main(String[] args) throws IOException {
		RandomEntity re = new RandomEntity("letters.txt", "keys.txt");

		// Display Only the default entity
		re.display();

		// Generate Some Random Entities Lists
		for (int i = 0; i < 5; i++) {
			re.randomEntityKeys(re.listKeys);
			System.out.println("Generation : " + i);
			System.out.println(re.listKeysRandom);
			re.randomHashMap(re.listKeysRandom);
			System.out.println(re.hmRandomEntity);
			re.listKeysRandom.clear();
		}
		// This is to create only one Random Entity
		System.out.println("*************");
		RandomEntity ree = new RandomEntity();
		ree.createOneRandomEntity();
		// Display The fitness Computation lists
		ree.fintnessComputationLists();
		System.out.println("Left Hand ==>  " + ree.listLeftHand);
		System.out.println("Right Hand ==>  " + ree.listRightHand);
		System.out.println("Row 1 ==>  " + ree.listRow1);
		System.out.println("Row 2 ==>  " + ree.listRow2);
		System.out.println("Row 3 ==>  " + ree.listRow3);
		System.out.println("Keys Weight ==> "+ree.hmWeightKeys);
	}

}
