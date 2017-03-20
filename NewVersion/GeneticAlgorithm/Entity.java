package GeneticAlgorithm;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Entity {
	/*
	 * The map hm will contain two parameters, Key and Value
	 * The Key parameter is the STROKE of the stenotype Keyboard 
	 * The value parameter is the corresستؤponding translation
	 */
	private Map<String, String> hm = new HashMap<>();
	
	/*
	 * This MapList is th set the positions of the Keys
	 * It will be useful to compute the fitness value
	 * For every stroke we will need an index  
	 */
	private Map<Integer, String> hmIndices = new HashMap<>();
	
	/*
	 * This is for the strokes of the past tense Endings
	 */
	private Map<String, String> hmPastEndings = new HashMap<>();
	
	/*
	 * This HashMap is for the present tense Prefixes
	 */
	private Map<String, String> hmPresentPrefixes = new HashMap<>();
	 
	 /*
	  * This HashMap is for the present tense Suffixes 
	  */
	private Map<String, String> hmPresentSuffixes = new HashMap<>();
	
	/*
	 * List of the most common used verbs
	 */
	private ArrayList<String>verbs = new ArrayList<String>();
	
	/*
	 * List of Strokes Of the common verbs
	 */
	private ArrayList<String>verbStrokes = new ArrayList<String>();
	
	
	/*
	 * Constructor
	 * Takes two parameters (File of arabic letters AND File of corresponding KEYS)
	 */
	public Entity(String lettersFile, String keysFile)  {
		constructLetterKeys(lettersFile,keysFile);
		// HASHMAP :  indexes of KEYS
		constructIndexesOfKeys();
		// HASHMAP :  Past tense Endings
		constructPastTenseEndings();
		//HASHMAP : Present tense prefixes
		constructPresentTensePrefixes();
		//HASHMAP : Present tense Suffixes
		constructPresentTenseSuffixes();
		// ArrayList Verbs 
		constructVerbs();
	}
	
	
	public void constructVerbs(){
		try {
			Scanner s = new Scanner(new File("verbs.txt"));
			while (s.hasNext()){
			    verbs.add(s.next());
			}
			s.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void constructPresentTenseSuffixes() {
		File lettersPresentSuffixes = new File("presentTenseSuffixesLetters.txt");
		File keysPresentSuffixes = new File("presentTenseSuffixesKeys.txt");
		try {
			Scanner scanerLettersSuffixes = new Scanner(lettersPresentSuffixes);
			Scanner scanerKeysSuffixes = new Scanner(keysPresentSuffixes);
			while(scanerLettersSuffixes.hasNext()&& scanerKeysSuffixes.hasNext()){
				String letterScan = scanerLettersSuffixes.nextLine();
				String keyyScan = scanerKeysSuffixes.nextLine();	            
	            hmPresentSuffixes.put(letterScan,keyyScan);
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public void constructPresentTensePrefixes() {
		File lettersPresentPrefixes = new File("presentTensePrefixesLetters.txt");
		File keysPresentPrefixes = new File("presentTensePrefixesKeys.txt");
		try {
			Scanner scanerLettersPrefixes = new Scanner(lettersPresentPrefixes);
			Scanner scanerKeysPrefixes = new Scanner(keysPresentPrefixes);
			while(scanerLettersPrefixes.hasNext()&& scanerKeysPrefixes.hasNext()){
				String letterScan = scanerLettersPrefixes.nextLine();
				String keyyScan = scanerKeysPrefixes.nextLine();	            
	            hmPresentPrefixes.put(letterScan,keyyScan);
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}



	public void constructLetterKeys(String lettersFile, String keysFile){
		File letters = new File(lettersFile);
		File keys = new File(keysFile);
		Scanner scanerLetters;
		try {
			scanerLetters = new Scanner(letters);
			Scanner scanerKeys = new Scanner(keys);
			while(scanerLetters.hasNext()&&scanerKeys.hasNext()){
	            String lettersScan = scanerLetters.nextLine();
	            String keysScan = scanerKeys.nextLine();
	            hm.put(lettersScan, keysScan);
	        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public void constructIndexesOfKeys(){
		File keysToIndex = new File("keysIn.txt");
		try {
			Scanner scanerKeysToIndex = new Scanner(keysToIndex);
			int i=0;
			while(scanerKeysToIndex.hasNext()){
	            String keyyScan = scanerKeysToIndex.nextLine();	            
	            hmIndices.put(i,keyyScan);
	            i=i+1;
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void constructPastTenseEndings(){
		File lettersPast = new File("pastTenseEndingsLetters.txt");
		File keysPast = new File("pastTenseEndingsKeys.txt");
		try {
			Scanner scanerLettersEndings = new Scanner(lettersPast);
			Scanner scanerKeys = new Scanner(keysPast);
			while(scanerKeys.hasNext()&& scanerLettersEndings.hasNext()){
				String letterScan = scanerLettersEndings.nextLine();
				String keyyScan = scanerKeys.nextLine();	            
	            hmPastEndings.put(letterScan,keyyScan);
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Display the HASHMAP of letters and keys
	 */
	public void displayEntity() {
		System.out.println("* Arabic letter : Key");
		System.out.println(hm);
		System.out.print("* Size of the LIST ==> ");
		System.out.println(hm.size());
		System.out.println("* Indice : KEY");
		System.out.println(hmIndices);
		System.out.print("* Size of the LIST ==> ");
		System.out.println(hmIndices.size());
		System.out.println("* The past Endings Are :");
		System.out.println(hmPastEndings);
		System.out.println("* The present tense prefixes Are :");
		System.out.println(hmPresentPrefixes);
		System.out.println("* The present tense Suffixes Are :");
		System.out.println(hmPresentSuffixes);
		System.out.println("* The list of common verbs");
		System.out.println(verbs);
		System.out.println(verbStrokes);
	}

	
	
	/*
	 * Getters && Setters
	 */
	public Map<String, String> getHm() {
		return hm;
	}
	public void setHm(Map<String, String> hm) {
		this.hm = hm;
	}
	
	
	public Map<Integer, String> getHmIndices() {
		return hmIndices;
	}


	public void setHmIndices(Map<Integer, String> hmIndices) {
		this.hmIndices = hmIndices;
	}


	public Map<String, String> getHmPastEndings() {
		return hmPastEndings;
	}


	public void setHmPastEndings(Map<String, String> hmPastEndings) {
		this.hmPastEndings = hmPastEndings;
	}


	public Map<String, String> getHmPresentPrefixes() {
		return hmPresentPrefixes;
	}


	public void setHmPresentPrefixes(Map<String, String> hmPresentPrefixes) {
		this.hmPresentPrefixes = hmPresentPrefixes;
	}


	public Map<String, String> getHmPresentSuffixes() {
		return hmPresentSuffixes;
	}


	public void setHmPresentSuffixes(Map<String, String> hmPresentSuffixes) {
		this.hmPresentSuffixes = hmPresentSuffixes;
	}


	public ArrayList<String> getVerbs() {
		return verbs;
	}


	public void setVerbs(ArrayList<String> verbs) {
		this.verbs = verbs;
	}
	
	
	
	public ArrayList<String> getVerbStrokes() {
		return verbStrokes;
	}


	public void setVerbStrokes(ArrayList<String> verbStrokes) {
		this.verbStrokes = verbStrokes;
	}


	public void strokesOfVerbs() {
		
	for(int i=0; i<verbs.size();i++){
		String stroke="";
		String verb1 = verbs.get(i);
		System.out.println("Verb "+i+" : "+verb1);
		for(int j=0; j< verb1.length();j++){
			if(hm.get(String.valueOf(verb1.charAt(j))) != null){
				System.out.print(verb1.charAt(j));System.out.println(" : "+hm.get(String.valueOf(verb1.charAt(j))));
				stroke=stroke+hm.get(String.valueOf(verb1.charAt(j)));
			}
		}
		System.out.println("The corresponding stroke of the verb ("+verb1+") ==> "+stroke);
		verbStrokes.add(stroke);
		System.out.println(verbStrokes);
		
	}	
		
		
	}

	/*
	 * Main Class of the test
	 */
	public static void main(String[] args) {
		Entity en = new Entity("letters.txt","keys.txt");
		en.displayEntity();
		en.strokesOfVerbs();
	}


}
