package GeneticAlgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

	private static Entity entity;
	private Random random = new Random();
	// Initial Entity before the Mutation
	private static Map<String, String> initialEntity = new HashMap<String, String>();
	// Final Entity After The Mutation
	private static Map<String, String> finalEntityMutated = new HashMap<String, String>();

	// Additional tools
	private List<String> ploverKeyboard = new ArrayList<String>();
	private List<Integer> binaryKeyboard = new ArrayList<Integer>();
	private List<String> listLetters = new ArrayList<String>();

	/*
	 * Constructor The parameter will be the initial Random Entity Created from
	 * Entity.java Class All Operations are done in this constructor
	 */
	public Mutation(Map<String, String> initialEntity) {
		super();
		// Create the list of letters
		List<String> letterList = createListLetters();
		System.out.println(letterList);

		// Create list of keys
		List<String> keyList = createListOfKeys(initialEntity);
		System.out.println(keyList);
		System.out.println("____________________________________________________________________________________");

		// Initialize the binary Plover keyboard
		initializeKeyboard();
		System.out.println("* The Binary KeyBoard");
		System.out.println(binaryKeyboard);

		// Initialize the Plover Keys
		initializePloverKeyboard();
		System.out.println("* The Plover Keyboard");
		System.out.println(ploverKeyboard);

		System.out.println("____________________________________________________________________________________");

		// Select A random Entity key+Value
		String randomLetter = selectRandomLetter(initialEntity, listLetters);
		System.out.println("* The random letter from The Entity ==>   " + randomLetter);
		String key = initialEntity.get(randomLetter);
		System.out.println("* The corresponding Key ==>    " + key);

		// Binary representation of the keyboard
		updateListBinary(key);

		// Analyse the Stroke
		/*
		 * for every stroke i will change the bits in the list of the
		 * binaryKeyBoard to 1 if the corresponding index of the key is in and
		 * keeping 0 if none index < =11 ==> it's on the left side idex >11 ==>
		 * it's on the right side
		 */
		System.out.println("____________________________________________________________________________________");

		// Make some modifications
		// Generate a random index of the binary representation of the keyboard
		int randomIndex = random.nextInt(binaryKeyboard.size());
		System.out.println("* The Random index of the binaryKeyboard is ==> " + randomIndex);

		// Modify the binary keyboard
		modifyTheKey(randomIndex);
		System.out.println("* The New Binary Keyboard list representation is :");
		System.out.println(binaryKeyboard);

		// Conversion
		String keyy = convertBinaryToKey(binaryKeyboard);

		// convert the binary representation of the key into a String ==> It
		// means the key
		System.out.println("* The New Key modified after the mutation is :");
		System.out.println(keyy);

		System.out.println("____________________________________________________________________________________");

		// Modify the final entity HashMap
		System.out.println("* The final Entity after the mutation");
		initialEntity.put(randomLetter, keyy);
		finalEntityMutated = initialEntity;
		System.out.println(finalEntityMutated);
	}

	/*
	 * Convert The binary list into a String(Key)
	 */
	public String convertBinaryToKey(List<Integer> binaryKeyboard2) {
		String c = "";
		for (int i = 0; i < binaryKeyboard2.size(); i++) {
			if (binaryKeyboard2.get(i).equals(1)) {
				if (c.contains("-") && ploverKeyboard.get(i).contains("-")) {
					c = c + ploverKeyboard.get(i).replace("-", "");
				} else {
					c = c + ploverKeyboard.get(i);
				}
			}
		}
		return c;
	}

	/*
	 * Modify The String(Key) by inserting some other Keys
	 */
	public void modifyTheKey(int randomIndex) {
		binaryKeyboard.set(randomIndex, 1);
	}

	/*
	 * Update the binary representation of the Key And change some value of "0"
	 * and "1"
	 */
	public void updateListBinary(String key) {
		if (key.length() == 1 || key.length() == 2 && key.contains("-")) {
			binaryKeyboard.set(ploverKeyboard.indexOf(key), 1);
		} else if (key.length() == 2 && !key.contains("-")) {
			String c1 = String.valueOf(key.charAt(0));
			String c2 = String.valueOf(key.charAt(1));
			binaryKeyboard.set(ploverKeyboard.indexOf(c1), 1);
			binaryKeyboard.set(ploverKeyboard.indexOf(c2), 1);
		} else if (key.substring(0, 1).equals("-") && key.length() == 3) {
			String c1 = "-" + String.valueOf(key.charAt(1));
			String c2 = "-" + String.valueOf(key.charAt(2));
			binaryKeyboard.set(ploverKeyboard.indexOf(c1), 1);
			binaryKeyboard.set(ploverKeyboard.indexOf(c2), 1);
		} else if (key.length() == 3 && !key.contains("-")) {
			String c1 = String.valueOf(key.charAt(0));
			String c2 = String.valueOf(key.charAt(1));
			String c3 = String.valueOf(key.charAt(2));
			binaryKeyboard.set(ploverKeyboard.indexOf(c1), 1);
			binaryKeyboard.set(ploverKeyboard.indexOf(c2), 1);
			binaryKeyboard.set(ploverKeyboard.indexOf(c3), 1);
		} else if (key.contains("-") && String.valueOf(key.charAt(0)) != "-") {
			String part1 = key.substring(0, key.indexOf("-"));
			String part2 = key.substring(key.indexOf("-") + 1);
			System.out.println(part1 + " " + part2);
			int lenghtP1 = part1.length();
			int lengthP2 = part2.length();
			List<String> part1List = new ArrayList<String>();
			List<String> part2List = new ArrayList<String>();
			for (int i = 0; i < lenghtP1; i++) {
				part1List.add(String.valueOf(part1.charAt(i)));
				binaryKeyboard.set(ploverKeyboard.indexOf(part1List.get(i)), 1);
			}
			for (int i = 0; i < lengthP2; i++) {
				part2List.add("-" + String.valueOf(part2.charAt(i)));
				binaryKeyboard.set(ploverKeyboard.indexOf(part2List.get(i)), 1);
			}
			System.out.println(part1List);
			System.out.println(part2List);

			// Update the binary keyboard
		}
	}

	/*
	 * Select a Random a value (Arabic letter) from the entity (HashMap)
	 */
	public String selectRandomLetter(Map<String, String> initialEntity, List<String> listLetters) {
		int i = random.nextInt(listLetters.size()) + 1;
		String letter = listLetters.get(i);
		return letter;
	}

	/*
	 * Get all the key from the HashMap and create with it a List of all those
	 * keys
	 */
	public List<String> createListOfKeys(Map<String, String> initialEntity) {
		List<String> list = new ArrayList<String>();
		for (String key : initialEntity.keySet()) {
			list.add(initialEntity.get(key));
		}
		return list;
	}

	/*
	 * Create a list of letter It means to get all letters from the HashMap and
	 * create with it a list
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

	/*
	 * Initialize all the values of the Lit to "0"
	 */
	public void initializeKeyboard() {
		for (int i = 0; i < 24; i++) {
			binaryKeyboard.add(0);
		}
	}

	/*
	 * Initialize the Plover Keyboard representation of a given String(stenotype
	 * Key)
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
	 * Main Program for the Test
	 */
	public static void main(String[] args) throws IOException {
		entity = new Entity();
		initialEntity = entity.generateRandomEntities();
		System.out.println("* The Initial entity before the Mutation : ");
		System.out.println(initialEntity);
		Mutation mutation = new Mutation(initialEntity);

	}

}
