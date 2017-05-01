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
	private static Map<String, String> initialEntity = new HashMap<String,String>();
	private static Map<String, String> finalEntityMutated = new HashMap<String,String>();
	
	// Additional tools
	private List<String> ploverKeyboard = new ArrayList<String>();
	private List<Integer> binaryKeyboard = new ArrayList<Integer>();
	private List<String> listLetters = new ArrayList<String>();
	
	
	public Mutation(Map<String, String> initialEntity) {
		super();
		// Create the list of letters
		List<String>letterList = createListLetters();
		System.out.println(letterList);
		
		// Create list of keys
		List<String>keyList = createListOfKeys(initialEntity);
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
		
	}


	public void updateListBinary(String key) {
		// TODO Auto-generated method stub
		
	}


	public String selectRandomLetter(Map<String, String> initialEntity, List<String> listLetters) {
		int i = random.nextInt(listLetters.size()) + 1;
		String letter = listLetters.get(i);
		return letter;
	}


	public List<String> createListOfKeys(Map<String, String> initialEntity) {
		List<String> list = new ArrayList<String>();
		for (String key : initialEntity.keySet()) {
			list.add(initialEntity.get(key));
		}
		return list;
	}


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


	public void initializeKeyboard() {
		for (int i = 0; i < 24; i++) {
			binaryKeyboard.add(0);
		}
	}


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
