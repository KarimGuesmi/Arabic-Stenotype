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
import java.util.Scanner;

public class RandomEntity {

	private  List<String> listLetters = new ArrayList<String>();
	private  List<String> listKeys = new ArrayList<String>();
	private Map<String, String> hmEntity = new HashMap<>();
	
	
	public RandomEntity(String letterFile, String keyFile) throws IOException{
		defalutEntityLists(letterFile, keyFile);
		defaultEntityHashMap(listLetters, listKeys);
		
	}
	

	

	private void defaultEntityHashMap(List<String> listLetters, List<String> listKeys) {
		for(int i=0; i<listLetters.size();i++){
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




	/*
	 * Main Method for test
	 */
	public static void main(String[] args) throws IOException  {
		RandomEntity re = new RandomEntity("letters.txt", "keys.txt");
		System.out.println("************ Default Entity *************");
		System.out.println(re.listLetters);
		System.out.println(re.listKeys);
		System.out.println(re.hmEntity);
		System.out.println("************ Random Entities *************");
		
	}

}
