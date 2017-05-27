package GeneticAlgorithm;

import java.awt.PointerInfo;
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
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Algorithm {

	private List<String> arabicDictionary = new ArrayList<String>();
	private List<List<String>> strokeDictionary = new ArrayList<>();
	private List<String> strokeDictionaryImproved = new ArrayList<>();

	// Additional Tools
	private Population pop;

	private Map<String, String> bestEntity = new HashMap<String, String>();

	private FitnessComputation fitness = new FitnessComputation();

	private List<String> listOfKeys = new ArrayList<>();

	private List<String> listOfStrokes;

	private List<String> strokes;

	private List<Double> penalties = new ArrayList<>();
	
	private Outline out = new Outline();
	
	private List<String> conflicts = new ArrayList<>();
	
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
		bestEntity = pop.getFittest(pop.getFitnessValues());
		//System.out.println(bestEntity);
		//System.out.println("________________________________________________________________________");

		// For every word in the (dictionary) List ==> splitting into Letters
		// and then
		// give to every letter a corresponding stroke in the Best Fittest
		// Entity

		strokeDictionary = createStrokeDictionary(arabicDictionary, bestEntity);

		// Get the list of keys
		// System.out.println("** List Of keys : ");
		listOfKeys = getListOfKeys();
		// System.out.println(listOfKeys);
		//System.out.println("__________________________________________________________________________");
		// Improve the list of strokes
		strokeDictionaryImproved = createDictionaryImproved(strokeDictionary);
		// System.out.println(strokeDictionaryImproved);

		// Check dictionary conflicts
		 Map<String, Long> counts = strokeDictionaryImproved.stream()
				.collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		java.util.Iterator<String> iterator =  counts.keySet().iterator();
		//iterator.forEachRemaining(conflicts::add);
		//System.out.println("** Dictionary Conflicts : ");
		//System.out.println("");
		//System.out.println("STROKE    ||||    APPEARENCE NUMBER");
		//System.out.println("");
		int num = 1;
		double penalty = 0.0;
		//
		FileOutputStream fosConflicts = new FileOutputStream("outlineConflicts.txt");
		PrintWriter pwConflicts = new PrintWriter(new OutputStreamWriter(fosConflicts));
		pwConflicts.println("The Best Entity");
		pwConflicts.println(bestEntity);
		pwConflicts.println("_________________________________________ ");
		pwConflicts.println("** Dictionary Conflicts ** : ");
		pwConflicts.println("List Of all conflicts : ");
		pwConflicts.println("****************-********************");
		FileOutputStream fosConf = new FileOutputStream("Conflicts.txt");
		PrintWriter pwConf = new PrintWriter(new OutputStreamWriter(fosConf));
		//
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			Long value = counts.get(key);
			if (value > 1) {
				pwConflicts.println(num+". "+key+ " ===> " + value);
				//System.out.print(num + ". " + key + " ===> " + value);
				//conflicts.add(key);
				pwConf.println(key);
				pwConf.flush();
				/*
				List<String>conflictsTranslations = new ArrayList<>(); 
				conflictsTranslations = conflictList(out.getJsonWords(),out.getJsonSTROKES() ,key);
				System.out.println(conflictsTranslations);
				*/
				if(value>3){
					penalty = value * 0.5;
					//System.out.println(" ------*----- (With Penalty ==> "+ penalty+ " )");
					pwConflicts.println("-----*------ (With Penalty ==> "+penalty+" )");
				}else{
					//System.out.println();
					pwConflicts.println();
				}
				num += 1;
				penalty += fitness.getPenalty(key, value);
				penalties.add(penalty);
				penalty = 0;
			}
		}
		//System.out.println("_______________________________________________");
		//System.out.println("----------- * Conflicts Penalties *------------");
		pwConflicts.println("_________________________________________________");
		pwConflicts.println("----------- * Conflicts Penalties *------------");
		pwConflicts.println(penalties);
		//System.out.println(penalties);
		//System.out.println(fitness.sumPenalties(penalties));
		pwConflicts.println(fitness.sumPenalties(penalties));
		pwConflicts.println("________________________________________________");
		//System.out.println("_______________________________________________");

		// System.out.println(counts);
		pwConflicts.flush();
		
		//System.out.println(conflicts);
		// Create File of conflict translations
		
		
	}

	public List<String> getConflicts() {
		return conflicts;
	}



	public void setConflicts(List<String> conflicts) {
		this.conflicts = conflicts;
	}



	public List<String> conflictList(List<String> jsonWords, List<String> jsonStrokes, String key) {
		List<String>listOfConflicts=new ArrayList<>();
		List<Integer>indexes = new ArrayList<>();
		for(int i=0; i<jsonStrokes.size();i++){
			if(jsonStrokes.get(i).equals(key)){
				indexes.add(i);
			}
		}
		for(int j=0; j<indexes.size();j++){
			listOfConflicts.add(jsonWords.get(indexes.get(j)));
		}
		return listOfConflicts;
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
		for (int i = 0; i < list.size(); i++) {
			if (stroke == "") {
				stroke = list.get(i);

			} else {
				try {
					stroke = combineStroke(stroke, list.get(i), list.get(i - 1));
				} catch (NullPointerException e) {
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return stroke;
	}

	/*
	 * Compare two strokes and combine them to respect the stenotype Order
	 * Method signed in the "createStroke()" Method
	 */
	public String combineStroke(String stroke, String nextKey, String previousKey) throws IOException {
		if (previousKey == "") {
			stroke = nextKey;
		} else {
			if (previousKey.length() == 1 && nextKey.length() == 1) {
				if (listOfKeys.indexOf(previousKey) < listOfKeys.indexOf(nextKey)) {
					stroke = stroke + nextKey;
				} else {
					stroke = stroke + "/" + nextKey;
				}
			} else if (previousKey.length() == 2 && nextKey.length() == 2 && previousKey.contains("-")
					&& nextKey.contains("-")) {
				if (listOfKeys.indexOf(previousKey) < listOfKeys.indexOf(nextKey)) {
					nextKey = nextKey.replace("-", "");
					stroke = stroke + nextKey;
				} else {
					stroke = stroke + "/" + nextKey;
				}
			} else if (previousKey.length() == 1 && nextKey.length() == 2 && nextKey.contains("-")) {
				stroke = stroke + nextKey;
			} else if (previousKey.length() >= 1 && !previousKey.contains("-") && nextKey.length() >= 2
					&& nextKey.charAt(0) == '-') {
				stroke = stroke + nextKey;
			} else if (previousKey.length() > 1 && !previousKey.contains("-") & nextKey.length() == 1) {
				if (listOfKeys.indexOf(previousKey.length() - 1) < listOfKeys.indexOf(nextKey)) {
					stroke = stroke + nextKey;
				} else {
					stroke = stroke + "/" + nextKey;
				}
			} else if (previousKey.length() > 1 && !previousKey.contains("-") && nextKey.length() > 1
					&& !nextKey.contains("-")) {
				if (listOfKeys.indexOf(previousKey.length() - 1) < listOfKeys.indexOf(nextKey.charAt(0))) {
					stroke = stroke + nextKey;
				} else {
					stroke = stroke + "/" + nextKey;
				}
			} else if (!previousKey.contains("-") && nextKey.charAt(0) == '*' && nextKey.charAt(1) == '-') {
				stroke = stroke + nextKey;
			}

			else {
				stroke = stroke + "/" + nextKey;
			}
		}
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
		// System.out.println("_______________________________________________________________________");

		// System.out.println("The List of all Strokes : ");
		// System.out.println(strokeDict);

		// System.out.println("The List of Corresponding Words : ");
		// System.out.println(arabicDictionary);
		// System.out.println("_______________________________________________________________________");

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
		// fitness.fintnessComputationLists();
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
