package GeneticAlgorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FitnessComputation {

	static Entity entite;
	private static Map<String, String> entitee = new HashMap<String, String>();
	private double fitness;
	private double effort, penalty;
	
	// Tool for the Stroke path Effort && Penalty Computation
	private Map<String, String> keysBaseLineEffort = new HashMap<String, String>();
	private List<String> listKeysWeight = new ArrayList<String>();
	private Map<String, String> hmWeightKeys = new HashMap<>();
	private List<Integer> fingers = new ArrayList<Integer>();
	private List<String> keysFingers = new ArrayList<>();
	private List<String> listLeftHand = new ArrayList<String>();
	private List<String> listRightHand = new ArrayList<String>();
	private List<String> listRow1 = new ArrayList<String>();
	private List<String> listRow2 = new ArrayList<String>();
	private List<String> listRow3 = new ArrayList<String>();
	private List<String> listKeys = new ArrayList<String>();

	/*
	 * Constructor Compute the fitness value from a given random generated
	 * entity
	 */
	public FitnessComputation(Map<String, String> entity) {
		super();
		fintnessComputationLists();
		initializeFingers();
		initializeKeysFingers();
		initializeKeyBaseLineEffort();

		effort = computeEffort(entity);
		
		//double penalty = computePenalty(entity);

		//fitness = effort - penalty;
	}

	/*
	 * Compute the stroke path Effort
	 */
	public double computeEffort(Map<String, String> entity) {
		List<String>listOfKeys = new ArrayList<String>();
		List<Double>listofEffortValues = new ArrayList<Double>();
		double somme = 0.0;
		
		listOfKeys = entity.values().stream().collect(Collectors.toList());
		for(int i=0; i< listOfKeys.size();i++){
			if(listOfKeys.get(i).length()==1 || (listOfKeys.get(i).length()==2 && listOfKeys.get(i).contains("-"))){
				effort = 0.0;//Double.parseDouble(keysBaseLineEffort.get(listOfKeys.get(i)));
				listofEffortValues.add(effort); // No special Effort
			}else{
				if(listOfKeys.get(i).length()==3 && listOfKeys.get(i).charAt(0)=='-'){
					String key1 = "-"+listOfKeys.get(i).charAt(1);
					String key2 = "-"+listOfKeys.get(i).charAt(2);
					double effort1 = Double.parseDouble(keysBaseLineEffort.get(key1));
					double effort2 = Double.parseDouble(keysBaseLineEffort.get(key2));
					effort = effort1+effort2;
					listofEffortValues.add(effort);
				}
				
			}
		}
		
		for(int i=0; i<listofEffortValues.size();i++){
			somme = somme + listofEffortValues.get(i);
		}
		return somme;
	}

	/*
	 * Compute the penalty
	 */
	public double computePenalty(Map<String, String> entity) {

		return 0;
	}

	//////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Data structures and tools initializations The tools for the Stroke path
	 * effort and penalty computation.
	 */

	/*
	 * Initialize the hash map of the key 's Base Line Effort Each key ==> base
	 * line Effort(Double)
	 */
	public void initializeKeyBaseLineEffort() {
		try {
			Scanner scan1 = new Scanner(new File("keysWeight.txt"));
			Scanner scan2 = new Scanner(new File("keysBaseLineEffort.txt"));
			while (scan1.hasNext() && scan2.hasNext()) {
				keysBaseLineEffort.put(scan1.next(), scan2.next());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Initialize the list of finger's numbers for every Key
	 */
	public void initializeKeysFingers() {
		try {
			Scanner scan = new Scanner(new File("keysFingers.txt"));
			while (scan.hasNext()) {
				keysFingers.add(scan.next());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * The list of fingers number The left hand is from 0 to 4 The right hand is
	 * from 5 to 9 We start counting from the Pinky
	 */
	public void initializeFingers() {
		for (int i = 0; i < 10; i++) {
			fingers.add(i);
		}
	}

	public void fintnessComputationLists() {
		try {
			BufferedReader leftHand = new BufferedReader(new FileReader("listLeftHand.txt"));
			BufferedReader rightHand = new BufferedReader(new FileReader("listRightHand.txt"));
			BufferedReader row1 = new BufferedReader(new FileReader("listRow1.txt"));
			BufferedReader row2 = new BufferedReader(new FileReader("listRow2.txt"));
			BufferedReader row3 = new BufferedReader(new FileReader("listRow3.txt"));
			Scanner keys = new Scanner(new File("keysWeight.txt"));
			Scanner keysWeight = new Scanner(new File("keysWeightValues.txt"));

			String line1, line2, lineR1, lineR2, lineR3;

			while ((line1 = leftHand.readLine()) != null) {
				listLeftHand.add(line1);
			}
			while ((line2 = rightHand.readLine()) != null) {
				listRightHand.add(line2);
			}
			while ((lineR1 = row1.readLine()) != null) {
				listRow1.add(lineR1);
			}
			while ((lineR2 = row2.readLine()) != null) {
				listRow2.add(lineR2);
			}
			while ((lineR3 = row3.readLine()) != null) {
				listRow3.add(lineR3);
			}
			while (keys.hasNext()) {
				listKeys.add(keys.next());
			}

			while (keysWeight.hasNext()) {
				listKeysWeight.add(keysWeight.next());
			}

			leftHand.close();
			rightHand.close();
			row1.close();
			row2.close();
			row3.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < listKeys.size(); i++) {
			hmWeightKeys.put(listKeys.get(i), listKeysWeight.get(i));
		}

	}
	///////////////////////////////////////////////////////////////////////////////////

	/*
	 * Getters And Setters
	 */

	public double getEffort() {
		return effort;
	}

	
	
	/*
	 * Main Program for the TEST
	 */
	public static void main(String[] args) throws IOException {
		entite = new Entity();
		entitee = entite.generateRandomEntities();
		System.out.println(entitee);
		FitnessComputation fitness = new FitnessComputation(entitee);
		System.out.println(fitness);
		// fitness.fintnessComputationLists();
		System.out.println("Left Hand ==>  " + fitness.listLeftHand);
		System.out.println("Right Hand ==>  " + fitness.listRightHand);
		System.out.println("Row 1 ==>  " + fitness.listRow1);
		System.out.println("Row 2 ==>  " + fitness.listRow2);
		System.out.println("Row 3 ==>  " + fitness.listRow3);
		System.out.println("List the keys : " + fitness.listKeys);
		System.out.println("List the keys Weight : " + fitness.listKeysWeight);
		System.out.println("Keys Weight ==> " + fitness.hmWeightKeys);

		// fitness.initializeFingers();
		System.out.println("The list of fingers : " + fitness.fingers);

		// fitness.initializeKeysFingers();
		System.out.println("* Key's Fingers :");
		System.out.println(fitness.keysFingers);

		// fitness.initializeKeyBaseLineEffort();
		System.out.println("* Key's base line effort");
		System.out.println(fitness.keysBaseLineEffort);
		System.out.println("____________________________________________________________________________");
		System.out.println(fitness.getEffort());
		
	}

}
