package GeneticAlgorithm;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Entity {
	/*
	 * The map hm will contain two parameters, Key and Value
	 * The Key parameter is the STROKE of the stenotype Keyboard 
	 * The value parameter is the corresponding translation
	 */
	private Map<String, String> hm = new HashMap<>();
	
	/*
	 * Constructor
	 * Takes two parameters (File of arabic letters AND File of corresponding KEYS)
	 */
	public Entity(String lettersFile, String keysFile)  {
		File letters = new File(lettersFile);
		File keys = new File(keysFile);
		Scanner scanerLetters;
		try {
			scanerLetters = new Scanner(letters);
			Scanner scanerKeys = new Scanner(keys);
			while(scanerLetters.hasNext()&&scanerLetters.hasNext()){
	            String lettersScan = scanerLetters.nextLine();
	            String keysScan = scanerKeys.nextLine();
	            hm.put(lettersScan, keysScan);
	        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/*
	 * Display the HASHMAP of letters and keys
	 */
	public void displayEntity() {
		System.out.println(hm);
		System.out.println(hm.size());

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

	
	/*
	 * Main Class of the test
	 */
	public static void main(String[] args) {
		Entity en = new Entity("letters.txt","keys.txt");
		en.displayEntity();
	}


}
