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
		map.put("S", "");
		map.put("T", "");
		map.put("K", "");
		map.put("P", "");
		map.put("W", "");
		map.put("H", "");
		map.put("R", "");
		map.put("A", "");
		map.put("O", "");
		map.put("*", "");
		map.put("E", "");
		map.put("U", "");
		map.put("F", "");
		map.put("R", "");
		map.put("P", "");
		map.put("B", "");
		map.put("L", "");
		map.put("G", "");
		map.put("T", "");
		map.put("S", "");
		map.put("D", "");
		map.put("Z", "");
		
		
		
	}
	
	
}
