package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Population {
	Individual[] individuals;

	// List Of lettes 
	List<Character> lettersList = new ArrayList<Character>();
	
	// List of Keys
	List<String> keysList = new ArrayList<String>();
	
	// value => translation  &&  key => character    
	private HashMap<String, String> map = new HashMap<String, String>();
	
	//Constructor
	public Population() {
		super();
	}

	public int size() {
		return individuals.length;
	}
	
	//some examples of keys => translation 
	public void addPopulation(){
		map.put("S", "is");
		map.put("T", "it");
		map.put("K", "can");
		map.put("P", "about");
		map.put("W", "with");
		map.put("H", "had");
		map.put("R", "are");
		map.put("A", "a");
		map.put("O", "to");
		map.put("*", "");
		map.put("E", "he");
		map.put("U", "you");
		map.put("F", "of");
		map.put("R", "are");
		map.put("P", "p");
		map.put("B", "Be");
		map.put("L", "L");
		map.put("G", "ing");
		map.put("T", "the");
		map.put("S", "s");
		map.put("D", "ed");
		map.put("Z", "");
		
		
		
	}
	
	
}
