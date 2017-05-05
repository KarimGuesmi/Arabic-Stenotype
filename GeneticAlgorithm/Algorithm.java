
package GeneticAlgorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Algorithm {

	private List<String> arabicDictionary = new ArrayList<String>();
	
	
	
	public Algorithm() {
		super();
		// Initialize The Arabic Dictionary
		arabicDictionary = initializeDictionary("dictionaryDemo.txt");
		System.out.println("** The Arabic Dictionary : ");
		System.out.println(arabicDictionary);
		System.out.println("________________________________________________________________________");
		
		// For every word in the (dictionary) List ==> splitting into Letters and then 
		// give to every letter a corresponding stroke in the Best Fittest Entity
		
		
	}


	/*
	 * Initialize the dictionary List
	 * Signed in the Default constructor
	 */
	private List<String> initializeDictionary(String file) {
		List<String> dictionary = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				dictionary.add(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dictionary;
	}



	public static void main(String[] args) {
		Algorithm algo = new Algorithm();
		

	}

}
