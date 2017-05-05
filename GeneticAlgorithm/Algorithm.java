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

public class Algorithm {

	private List<String> arabicDictionary = new ArrayList<String>();
	private List<String> strokeDictionary = new ArrayList<String>();

	// Additional
	private Population pop = new Population();

	private Map<String, String> bestEntity = new HashMap<String, String>();

	public Algorithm() throws IOException {
		super();
		// Initialize The Arabic Dictionary
		arabicDictionary = initializeDictionary("dictionaryDemo.txt");
		System.out.println("** The Arabic Dictionary : ");
		System.out.println(arabicDictionary);
		System.out.println("________________________________________________________________________");

		// Generate the population, AND Get the best Fittest Entity
		pop.run();
		bestEntity = pop.getFittestEntity();
		System.out.println(bestEntity);
		System.out.println("________________________________________________________________________");

		// For every word in the (dictionary) List ==> splitting into Letters
		// and then
		// give to every letter a corresponding stroke in the Best Fittest
		// Entity

		strokeDictionary = createStrokeDictionary(arabicDictionary, bestEntity);
	}

	/*
	 * Create a Stroke Dictionary based on the Arabic dictionary initialized and
	 * the fittest Entity
	 */
	private List<String> createStrokeDictionary(List<String> arabicDictionary, Map<String, String> bestEntity) {
		List<String> strokeDict = new ArrayList<>();
		
		for(int i =0; i< arabicDictionary.size(); i++){
			String word = arabicDictionary.get(i);
			//System.out.println(word);
			String stroke = "";
			for(int j=0; j<word.length(); j++){
				String letter = word.charAt(j)+"";
				if(bestEntity.get(letter).contains("-")){
					stroke+=bestEntity.get(letter);
				}else{
					stroke=bestEntity.get(letter)+stroke;
				}	
			}
			System.out.println("Old Stroke :===>:"+stroke);
			
			// Eliminate All occurences of "-"
			
			// Count how many "-" we have in the stroke
			int nbr = count(stroke);
			if(nbr>1){
				// get the first occurence of the "-" in the stroke
				int index = firstOccurence(stroke);
				//System.out.println("Index of the first occurence of - :"+index);
				
				// find the rest of "-" occurences and eliminate them
				for(int t=index+1; t< stroke.length(); t++){
					if(stroke.charAt(t)=='-'){
						stroke = removerChat(stroke, t);
					}
				}
				System.out.println("New Stroke :===>: "+stroke);
			}
			
		}
		
		return strokeDict;
	}

	
	private String removerChat(String stroke, int t) {
		return stroke.substring(0, t) + stroke.substring(t + 1);
	}

	private int firstOccurence(String stroke) {
		char firstchar = stroke.charAt(0);
		int index =0 ;
		if(firstchar=='-'){
				index=0;
		}else{
			for(int i=1; i<stroke.length();i++){
				if(stroke.charAt(i)=='-'){
					index = i;
					i=stroke.length()-1;
				}
			}
		}
		
		return index;
	}

	/*
	 * Count how many "-" we have in the Stroke
	 */
	private int count(String stroke) {
		int nbr =0;
		for(int i =0; i<stroke.length();i++){
			if(stroke.charAt(i)=='-'){
				nbr+=1;
			}
		}
		
		return nbr;
	}

	/*
	 * Initialize the dictionary List Signed in the Default constructor
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

	public static void main(String[] args) throws IOException {
		Algorithm algo = new Algorithm();

	}

}
