
package GeneticAlgorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Entity {
	/*
	 * The map hm will contain two parameters, Key and Value
	 * The Key parameter is the STROKE of the stenotype Keyboard 
	 * The value parameter is the corresponding translation
	 */
	private Map<String, String> hm = new HashMap<>();
	
	/*
	 * Constructor
	 * Initialize the Single and multiple Strokes with corresponding translations
	 */
	public Entity() {
		/* 
		 * Single STROKE
		 */
		
		// The Right Positions in the Stenotype Keyboard
		hm.put("-D", "ء");
		hm.put("-Z", "ل");
		hm.put("-T", "ب");
		hm.put("-S", "م");
		hm.put("-L", "ت");
		hm.put("-G", "س");
		hm.put("-P", "ع");
		hm.put("-B", "ج");
		hm.put("-F", "ك");
		hm.put("-R", "ض");
		
		// The Left Positions in the Stenotype Keyboard
		hm.put("H", "د");
		hm.put("R", "ل");
		hm.put("P", "ق");
		hm.put("W", "ر");
		hm.put("T", "س");
		hm.put("K", "م");
		hm.put("S", "ن");
		hm.put("S", "ن");

		// The Middle Positions in the Stenotype Keyboard
		hm.put("-U", "ا");
		hm.put("-E", "و");
		hm.put("O", "ي");
		hm.put("A", "ى");
		
		/*
		 * Double Stroke
		 */
		
		// Right Positions
		hm.put("-SZ", "ي");
		hm.put("-TS", "ح");
		hm.put("-LT", "د");
		hm.put("-GS", "ف");
		hm.put("-LG", "خ");
		hm.put("-PL", "ر");
		hm.put("-BG", "ن");
		hm.put("-PB", "ق");
		hm.put("-RB", "ر");
		hm.put("-FR", "ت");

		// Left Positions
		hm.put("HR", "ف");
		hm.put("PH", "ث");
		hm.put("WR", "م");
		hm.put("PW", "ة");
		hm.put("TP", "ه");
		hm.put("KW", "ض");
		hm.put("TK", "ح");
		hm.put("ST", "ب");
		hm.put("SK", "د");

		/*
		 * Triple Stroke
		 */
		
		// Right Position
		hm.put("-PLT", "ه");
		hm.put("-GSZ", "غ");

		// Left Position
		hm.put("STK", "و");
		hm.put("SKW", "ا");	
	}
	
	/*
	 * Display all (strokes : translations) of the HashMap hm
	 */
	public void displayStrokeTranslation(){
		Set keys = hm.keySet();
		for(Iterator i = keys.iterator(); i.hasNext();){
			String key = (String) i.next();
		    String value = (String) hm.get(key);
		    System.out.println(key +" : "+value);
		}
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
		Entity en = new Entity();
		en.displayStrokeTranslation();
		System.out.println("*************");
		System.out.println(en.hm);

	}

}
