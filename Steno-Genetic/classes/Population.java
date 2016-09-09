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
		map.put("a", "A");
		map.put("b", "B");
		map.put("c", "S");
		map.put("d", "D");
		map.put("e", "E");
		map.put("f", "TP");
		map.put("g", "TKPW");
		map.put("h", "H");
		map.put("i", "EU");
		map.put("j", "G");
		map.put("k", "K");
		map.put("l", "HR");
		map.put("m", "PH");
		map.put("n", "TPH");
		map.put("o", "O");
		map.put("p", "P");
		map.put("q", "K");
		map.put("r", "R");
		map.put("s", "S");
		map.put("t", "T");
		map.put("u", "U");
		map.put("v", "SH");
		map.put("w", "SKWR");
		map.put("x", "SK");
		map.put("y", "EU");
		map.put("z", "DZ");
		
	}
	
	
	
	
}
