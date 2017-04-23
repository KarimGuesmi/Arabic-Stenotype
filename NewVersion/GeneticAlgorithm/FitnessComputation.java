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

public class FitnessComputation {

	/*
	 * Fitness Computation Lists
	 */
	private List<String> listLeftHand = new ArrayList<String>();
	private List<String> listRightHand = new ArrayList<String>();
	private List<String> listRow1 = new ArrayList<String>();
	private List<String> listRow2 = new ArrayList<String>();
	private List<String> listRow3 = new ArrayList<String>();
	private List<String> listKeys = new ArrayList<String>();
	private List<String> listKeysWeight = new ArrayList<String>();
	private Map<String, String> hmWeightKeys = new HashMap<>();
	private List<Integer> fingers = new ArrayList<Integer>();

	// Lists of base Efforts for all keys => list of hand,finger and
	// baselineEffort
	private List<String> keysFingers = new ArrayList<>();
	private Map<String, String> keysBaseLineEffort = new HashMap<String, String>();

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
	 * This list is to calculate the penalties Pij = W0+ Whand * Phand + Wrow *
	 * Prow + Wfinger * Pfinger w0=0 and Phand=0 because we cannot penalize the
	 * left or right hand The idea is to minimize the usage of the pinky and
	 * ring fingers and maximize the usage of the stronger fingers avoiding the
	 * movement of both hand and focus more on the right hand
	 */
	private List<Integer> penalties = new ArrayList<Integer>();

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

	/*
	 * Compute Penalty of one stroke
	 */
	public double computePenalty(String stroke) {
		// http://mkweb.bcgsc.ca/carpalx/?typing_effort
		int w0 = 0;
		double row1 = 1.2;
		double row2 = 1.1;
		double row3 = 1;

		return 0;
	}

	/*
	 * Penalty computation
	 */
	public double strokePathEffort(String stroke) {
		double s = 0;
		// Weights of Hand, Row, Finger
		int wH = 0;
		int wR = 0;
		int wF = 0;

		for (int i = 0; i < stroke.length(); i++) {

		}

		return s;
	}

	/*
	 * Compute the effort of typing a Stroke ei = kb * bi + kp * pi + ks * si
	 * kb, kp and ks factors is to weight the indidividual effort components.
	 * bi, pi and si are the base, penalty and stroke path effort components of
	 * the ith Stroke
	 */
	public double typingEffortOfStroke(String stroke) {
		// double bi = computeBaseOfStroke();
		double bI = 0, pI = 0, sI = 0;
		int longStroke = stroke.length();
		List<Double> baseAndWeight = new ArrayList<>();
		
		/*
		 * bI computation
		 */
		if (longStroke == 0) {
			bI=0;
		} else if (longStroke == 1) {
			bI = Double.parseDouble(hmWeightKeys.get(stroke)) * Double.parseDouble(keysBaseLineEffort.get(stroke));
		} else if (longStroke == 2) {
			bI = (Double.parseDouble(hmWeightKeys.get(stroke.charAt(0))) * Double.parseDouble(keysBaseLineEffort.get(stroke.charAt(0)))) 
					+ (1 + (Double.parseDouble(hmWeightKeys.get(stroke.charAt(1))) * Double.parseDouble(keysBaseLineEffort.get(stroke.charAt(1)))));
		}else if(longStroke>2){
			for(int i=0; i< longStroke;i++){
				baseAndWeight.add(Double.parseDouble(hmWeightKeys.get(stroke.charAt(i))) * Double.parseDouble(keysBaseLineEffort.get(stroke.charAt(i))));
			}
			double b=baseAndWeight.get(0);
			for(int i=1; i<baseAndWeight.size();i++){
				b = b * (1+baseAndWeight.get(i)) ;
			}
			bI = b;
		}

		double eI = bI + pI + sI;
		return eI;
	}

	/*
	 * Compute the typing Effort for the text It's given by this formula E = 1/N
	 * * Sum(ei) N : Number of strokes ; ei : effort of typing a stroke
	 */
	public double typingEffortOfText(int totalNumber, int sumEfforts) {
		return sumEfforts / totalNumber;
	}

	/*
	 * Main for TEST
	 */
	public static void main(String[] args) {
		FitnessComputation fit = new FitnessComputation();
		fit.fintnessComputationLists();
		System.out.println("Left Hand ==>  " + fit.listLeftHand);
		System.out.println("Right Hand ==>  " + fit.listRightHand);
		System.out.println("Row 1 ==>  " + fit.listRow1);
		System.out.println("Row 2 ==>  " + fit.listRow2);
		System.out.println("Row 3 ==>  " + fit.listRow3);
		System.out.println("List the keys : " + fit.listKeys);
		System.out.println("List the keys Weight : " + fit.listKeysWeight);
		System.out.println("Keys Weight ==> " + fit.hmWeightKeys);
		fit.initializeFingers();
		System.out.println("The list of fingers : " + fit.fingers);

		// Base effort Map
		fit.initializeKeysFingers();
		System.out.println("* Key's Fingers :");
		System.out.println(fit.keysFingers);

		// base line effort hash map
		fit.initializeKeyBaseLineEffort();
		System.out.println("* Key's base line effort");
		System.out.println(fit.keysBaseLineEffort);
		System.out.println("_______________________________________________________________");
	}

}
