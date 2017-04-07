package GeneticAlgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/*
 * This class is to mutate
 * Order changing : two selected values of keys are  exchanged
 */
public class Mutation {

	private RandomEntity ra = new RandomEntity();
	private Map<String, String> hmFinal = new HashMap<>();
	private List<String> listLetters = new ArrayList<String>();
	private List<String> listKeys = new ArrayList<String>();
	private Random random = new Random();
	private List<Integer> binaryKeyboard = new ArrayList<Integer>();
	private List<String> ploverKeyboard = new ArrayList<String>();

	/*
	 * Default Constructor
	 */
	public Mutation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Initialize the keys representation of the plover Keyboard
	 */
	public void initializePloverKeyboard() {
		try {
			Scanner sc = new Scanner(new File("ploverkeys.txt"));
			while (sc.hasNext()) {
				ploverKeyboard.add(sc.next());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Inialize the binary representation if the plover keyboard
	 */
	public void initializeKeyboard() {
		for (int i = 0; i < 24; i++) {
			binaryKeyboard.add(0);
		}
	}

	/*
	 * Select a random letter (Represented as a Key in the HashMap)
	 */
	public String selectRandomLetter(Map<String, String> hm1, List<String> listLetters) {
		int i = random.nextInt(listLetters.size()) + 1;
		String letter = listLetters.get(i);
		return letter;
	}

	/*
	 * Create the list of letters
	 */
	public List<String> createListLetters() {
		Scanner s;
		try {
			s = new Scanner(new File("letters.txt"));
			listLetters = new ArrayList<String>();
			while (s.hasNext()) {
				listLetters.add(s.next());
			}
			s.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listLetters;
	}

	public List<String> createListOfKeys(Map<String, String> hm1) {
		List<String> list = new ArrayList<String>();
		for (String key : hm1.keySet()) {
			list.add(hm1.get(key));
		}
		return list;
	}


	public void updateListBinary(String key) {
		if(key.length()==1 || key.length()==2 && key.contains("-")){
			binaryKeyboard.set(ploverKeyboard.indexOf(key), 1);
		}else if(key.length()==2 && !key.contains("-")){
			String c1 = String.valueOf(key.charAt(0));
			String c2 = String.valueOf(key.charAt(1));
			binaryKeyboard.set(ploverKeyboard.indexOf(c1), 1);
			binaryKeyboard.set(ploverKeyboard.indexOf(c2), 1);
		}else if(key.substring(0, 1).equals("-") && key.length()==3){
			String c1 = "-"+String.valueOf(key.charAt(1));
			String c2 = "-"+String.valueOf(key.charAt(2));
			binaryKeyboard.set(ploverKeyboard.indexOf(c1), 1);
			binaryKeyboard.set(ploverKeyboard.indexOf(c2), 1);
		}else if(key.length()==3 && !key.contains("-")){
			String c1 = String.valueOf(key.charAt(0));
			String c2 = String.valueOf(key.charAt(1));
			String c3 = String.valueOf(key.charAt(2));
			binaryKeyboard.set(ploverKeyboard.indexOf(c1), 1);
			binaryKeyboard.set(ploverKeyboard.indexOf(c2), 1);
			binaryKeyboard.set(ploverKeyboard.indexOf(c3), 1);
		}else if (key.contains("-") &&  String.valueOf(key.charAt(0))!="-"){
			String part1 = key.substring(0, key.indexOf("-"));
			String part2 = key.substring(key.indexOf("-")+1);
			System.out.println(part1 + " "+part2);
			int lenghtP1 = part1.length();
			int lengthP2 = part2.length();
			List<String> part1List = new ArrayList<String>();
			List<String> part2List = new ArrayList<String>();
			for(int i=0; i<lenghtP1;i++){
				part1List.add(String.valueOf(part1.charAt(i)));
				binaryKeyboard.set(ploverKeyboard.indexOf(part1List.get(i)), 1);
			}
			for(int i=0; i<lengthP2;i++){
				part2List.add("-"+String.valueOf(part2.charAt(i)));
				binaryKeyboard.set(ploverKeyboard.indexOf(part2List.get(i)), 1);
			}
			System.out.println(part1List);System.out.println(part2List);

			//Update the binary keyboard
		}	
	}
	
	
	/*
	 * Convert the binary list representation into a Key 
	 */
	public String convertBinaryToKey(List<Integer> binaryKeyboard2) {
		String c = "";
		for(int i=0;i<binaryKeyboard2.size();i++){
			if(binaryKeyboard2.get(i).equals(1)){
				if(c.contains("-") && ploverKeyboard.get(i).contains("-")){
					c=c+ploverKeyboard.get(i).replace("-", "");
				}else{
					c=c+ploverKeyboard.get(i);
				}
			}
		}
		return c;
	}

	/*
	 * Modify the binary list representation of the key
	 * and replace a 0 by 1
	 * The index is choosen randomly
	 */
	public void modifyTheKey(int randomIndex) {
		binaryKeyboard.set(randomIndex, 1);	
	}

	
	
	/*
	 * Getters && Setters
	 */

	public RandomEntity getRa() {
		return ra;
	}

	public void setRa(RandomEntity ra) {
		this.ra = ra;
	}

	public Map<String, String> getHmFinal() {
		return hmFinal;
	}

	public void setHmFinal(Map<String, String> hmFinal) {
		this.hmFinal = hmFinal;
	}

	public List<String> getListLetters() {
		return listLetters;
	}

	public void setListLetters(List<String> listLetters) {
		this.listLetters = listLetters;
	}

	public List<String> getListKeys() {
		return listKeys;
	}

	public void setListKeys(List<String> listKeys) {
		this.listKeys = listKeys;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public List<Integer> getBinaryKeyboard() {
		return binaryKeyboard;
	}

	public void setBinaryKeyboard(List<Integer> binaryKeyboard) {
		this.binaryKeyboard = binaryKeyboard;
	}

	public List<String> getPloverKeyboard() {
		return ploverKeyboard;
	}

	public void setPloverKeyboard(List<String> ploverKeyboard) {
		this.ploverKeyboard = ploverKeyboard;
	}

	/*
	 * Main Program for the Test
	 */
	public static void main(String[] args) {
		// Get the random entity
		System.out.println("* The Random entity before the Mutation : ");
		Mutation m1 = new Mutation();
		Map<String, String> hm1 = m1.ra.createOneRandomEntity();
		System.out.println(hm1);

		// Create the list of letters
		m1.listLetters = m1.createListLetters();
		System.out.println(m1.listLetters);

		// Create list of keys
		m1.listKeys = m1.createListOfKeys(hm1);
		System.out.println(m1.listKeys);

		System.out.println("____________________________________________________________________________________");

		// Initialize the binary Plover keyboard
		m1.initializeKeyboard();
		System.out.println("* The Binary KeyBoard");
		System.out.println(m1.binaryKeyboard);

		// Initialize the Plover Keys
		m1.initializePloverKeyboard();
		System.out.println("* The Plover Keyboard");
		System.out.println(m1.ploverKeyboard);

		System.out.println("____________________________________________________________________________________");

		// Select A random Entity key+Value
		String randomLetter = m1.selectRandomLetter(hm1, m1.listLetters);
		System.out.println("* The random letter from The Entity ==>   " + randomLetter);
		String key = hm1.get(randomLetter);
		System.out.println("* The corresponding Key ==>    " + key);
		// Binary representation of the keyboard
		m1.updateListBinary(key);
		System.out.println("* The binary representation of that KEY ");
		System.out.println(m1.binaryKeyboard);
		
		// Analyse the Stroke
		/* for every stroke i will change the bits in the list of the binaryKeyBoard
		 * to 1 if the corresponding index of the key is in 
		 * and keeping 0 if none
		 *  index < =11 ==> it's on the left side
		 *  idex >11 ==> it's on the right side
		 */
		System.out.println("____________________________________________________________________________________");
		
		// Make some modifications
		// Generate a random index of the binary representation of the keyboard 
		int randomIndex = m1.random.nextInt(m1.binaryKeyboard.size());
		System.out.println(randomIndex);

		//Modify the binary keyboard 
		m1.modifyTheKey(randomIndex);
		System.out.println(m1.binaryKeyboard);
		String keyy = m1.convertBinaryToKey(m1.binaryKeyboard);
		
		// convert the binary representation of the key into a String ==> It means the key
		System.out.println(keyy);
	}

}
