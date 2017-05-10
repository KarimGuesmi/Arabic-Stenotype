package GeneticAlgorithm;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Algorithm {

	private List<String> arabicDictionary = new ArrayList<String>();
	private List<List<String>> strokeDictionary = new ArrayList<>();
	private List<String> strokeDictionaryImproved = new ArrayList<>();

	// Additional Tools
	private Population pop ;

	private Map<String, String> bestEntity = new HashMap<String, String>();

	private FitnessComputation fitness = new FitnessComputation();

	private List<String> listOfKeys = new ArrayList<>();

	private List<String> listOfStrokes;

	private List<String> strokes;

	/*
	 * Constructor
	 */
	public Algorithm(String dictionaryName) throws IOException {
		super();
		// Initialize The Arabic Dictionary
		arabicDictionary = initializeDictionary(dictionaryName);
		// System.out.println("** The Arabic Dictionary : ");
		// System.out.println(arabicDictionary);
		// System.out.println("________________________________________________________________________");

		// Generate the population, AND Get the best Fittest Entity
		pop = new Population();
		pop.run();
		bestEntity = pop.getFittestEntity();
		System.out.println(bestEntity);
		System.out.println("________________________________________________________________________");

		// For every word in the (dictionary) List ==> splitting into Letters
		// and then
		// give to every letter a corresponding stroke in the Best Fittest
		// Entity

		strokeDictionary = createStrokeDictionary(arabicDictionary, bestEntity);

		// Get the list of keys
		// System.out.println("** List Of keys : ");
		listOfKeys = getListOfKeys();
		// System.out.println(listOfKeys);
		System.out.println("__________________________________________________________________________");
		// Improve the list of strokes
		strokeDictionaryImproved = createDictionaryImproved(strokeDictionary);
		//System.out.println(strokeDictionaryImproved);

	}

	public Algorithm(String wordsFromtext, Map<String, String> bestEntity) {
		arabicDictionary = initializeDictionary(wordsFromtext);
		strokeDictionary = createStrokeDictionary(arabicDictionary, bestEntity);
		// Get the list of keys
		// System.out.println("** List Of keys : ");
		listOfKeys = getListOfKeys();
		strokeDictionaryImproved = createDictionaryImproved(strokeDictionary);
	}

	/*
	 * Create An improved version of a stroke dictinary This methods is signed
	 * in the constructor
	 */
	public List<String> createDictionaryImproved(List<List<String>> strokeDictionary) {
		List<String> listOfStrokes = new ArrayList<>();

		for (int i = 0; i < strokeDictionary.size(); i++) {
			String stroke = "";
			stroke = createStroke(strokeDictionary.get(i));
			listOfStrokes.add(stroke);
		}

		return listOfStrokes;
	}

	/*
	 * Create a string representation of the improved stroke This method is
	 * signed in the createDictionaryImproved(List<List<String>>
	 * strokeDictionary) Method
	 */
	public String createStroke(List<String> list) {
		String stroke = "";
		for (int i = 0; i < list.size() - 1; i++) {
			String key1 = list.get(i);
			String key2 = list.get(i + 1);
			if(stroke.equals("")){
				stroke = combineStroke(key1, key2);
			}else{
				stroke = stroke + "/" + key2;
			}
			
		}
		return stroke;
	}

	/*
	 * Compare two strokes and combine them to respect the stenotype Order
	 * Method signed in the "createStroke()" Method
	 */
	public String combineStroke(String key1, String key2) {
		/*
		String stroke="";
		if(key1.equals(key2)){
			stroke = key1+"/"+key2;
		}else
		if(key1.length()==1 && key2.length()==1){
			stroke = key1+key2;
		}else if(key1.length()==1 && key2.length()==2){
			if(key2.contains("-")){
				stroke = key1 + key2;
			}else{
				char first = key2.charAt(0);
				if(listOfKeys.indexOf(key1) < listOfKeys.indexOf(first)){
					stroke = key1+key2;
				}else{
					stroke = key1+"/"+key2;
				}
			}
		}else if (key1.length()==2 && key2.length()==1){
			if(key1.contains("-")){
				stroke = key1 + "/" + key2 ;
			}else{
				char last = key1.charAt(key1.length()-1);
				if(listOfKeys.indexOf(last) < listOfKeys.indexOf(key2)){
					stroke = key1 + key2 ;
				}else{
					stroke = key1 + "/" + key2;
				}
			}
		}else if(key1.length()==2 && key2.length()==2 && key1.contains("-") && key2.contains("-")){
			if(listOfKeys.indexOf(key1)<listOfKeys.indexOf(key2)){
				stroke = key1+key2;
			}else{
				stroke = key1 + "/" + key2;
			}
		}else if(key1.length()==2 && key2.length()==2 && !key1.contains("-") && !key2.contains("-")){
			char last1 = key1.charAt(1);
			char first2 = key2.charAt(0);
			if(listOfKeys.indexOf(last1)<listOfKeys.indexOf(first2)){
				stroke = key1 + key2;
			}else{
				stroke = key1 + "/" + key2;
			}
		}else if(key1.length()==1 && key2.length()>1 && !key2.contains("-")){
			char first =  key2.charAt(0);
			if(listOfKeys.indexOf(key1)<listOfKeys.indexOf(first)){
				stroke = key1+key2;
			}else{
				stroke = key1+"/"+key2;
			}
		}else if(key1.length()==1 && key2.length()>1 && key2.contains("-")){
			stroke = key1+key2;
		}else if(key1.length()>1 && key2.length()==1 && !key1.contains("-")){
			char last1 = key1.charAt(key1.length()-1);
			if(listOfKeys.indexOf(last1)<listOfKeys.indexOf(key2)){
				stroke = key1+key2;
			}else{
				stroke = key1+"/"+key2;
			}
		}else if(key1.length()>1 && key2.length()==1 && key1.contains("-")){
			stroke = key1+"/"+key2;
		}else if(key1.length()==1 && key2.length()==2 && key2.contains("-")){
			stroke = key1+key2;
		}else if(key1.length()==2 && key2.length()==2 && key1.contains("-")&& key2.contains("-")){
			if(listOfKeys.indexOf(key1)<listOfKeys.indexOf(key2)){
				stroke = key1+key2;
			}else{
				stroke = key1+"/"+key2;
			}
		}else{
			stroke = key1+"/"+key2;
		}
		*/
		String stroke ="";
		stroke = key1+"/"+key2;
			
		return stroke;
	}

	/*
	 * Create a Stroke Dictionary based on the Arabic dictionary initialized and
	 * the fittest Entity
	 */
	private List<List<String>> createStrokeDictionary(List<String> arabicDictionary, Map<String, String> bestEntity) {
		List<List<String>> strokeDict = new ArrayList<>();

		for (int i = 0; i < arabicDictionary.size(); i++) {
			String word = arabicDictionary.get(i);
			// System.out.println(word);
			String stroke = "";
			listOfStrokes = new ArrayList<>();
			for (int j = 0; j < word.length(); j++) {
				String letter = word.charAt(j) + "";
				if (letter.equals(" ")) {
					j += 1;
				} else {
					listOfStrokes.add(bestEntity.get(letter));
				}
			}
			// System.out.println(listOfStrokes);
			strokeDict.add(listOfStrokes);
		}
		//System.out.println("_______________________________________________________________________");

		//System.out.println("The List of all Strokes : ");
		//System.out.println(strokeDict);

		//System.out.println("The List of Corresponding Words : ");
		//System.out.println(arabicDictionary);
		//System.out.println("_______________________________________________________________________");

		return strokeDict;
	}

	/*
	 * Remove the Character which is equal to "-" in a stroke
	 */
	private String removerChar(String stroke, int t) {
		return stroke.substring(0, t) + stroke.substring(t + 1);
	}

	private int firstOccurence(String stroke) {
		char firstchar = stroke.charAt(0);
		int index = 0;
		if (firstchar == '-') {
			index = 0;
		} else {
			for (int i = 1; i < stroke.length(); i++) {
				if (stroke.charAt(i) == '-') {
					index = i;
					i = stroke.length() - 1;
				}
			}
		}
		return index;
	}

	/*
	 * Count how many "-" we have in the Stroke
	 */
	private int count(String stroke) {
		int nbr = 0;
		for (int i = 0; i < stroke.length(); i++) {
			if (stroke.charAt(i) == '-') {
				nbr += 1;
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

	/*
	 * From the Fitness Computation class Get the list of Keys
	 */
	public List<String> getListOfKeys() {
		List<String> keys = new ArrayList<>();
		fitness.fintnessComputationLists();
		keys = fitness.getListKeys();
		return keys;
	}

	public Map<String, String> getBestEntity() {
		return bestEntity;
	}

	public List<String> getStrokeDictionaryImproved() {
		return strokeDictionaryImproved;
	}

	/*
	 * Main Program for the Test
	 */
	public static void main(String[] args) throws IOException {
		Algorithm algo = new Algorithm("dictionaryDemo.txt");
	}

}
