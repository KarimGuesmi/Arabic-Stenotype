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
	private Map<String, Integer> hmWeightKeys = new HashMap<>();


	public void fintnessComputationLists() {
		try {
			BufferedReader leftHand = new BufferedReader(new FileReader("listLeftHand.txt"));
			BufferedReader rightHand = new BufferedReader(new FileReader("listRightHand.txt"));
			BufferedReader row1 = new BufferedReader(new FileReader("listRow1.txt"));
			BufferedReader row2 = new BufferedReader(new FileReader("listRow2.txt"));
			BufferedReader row3 = new BufferedReader(new FileReader("listRow3.txt"));
			Scanner keys = new Scanner(new File("keysWeight.txt"));
			
			
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
		
		for(int i =0; i<listKeys.size();i++){
			if(listKeys.get(i).equals("-D")||listKeys.get(i).equals("-Z")){
				hmWeightKeys.put(listKeys.get(i), 0);
			}else 
				if(listKeys.get(i).equals("S")||listKeys.get(i).equals("-T")||listKeys.get(i).equals("-S")){
					hmWeightKeys.put(listKeys.get(i), 1);
				}
			else 
				if(listKeys.get(i).equals("T")||listKeys.get(i).equals("K")||listKeys.get(i).equals("-L")||listKeys.get(i).equals("-G")){
					hmWeightKeys.put(listKeys.get(i), 2);
				}
			else 
				if(listKeys.get(i).equals("A")||listKeys.get(i).equals("O")||listKeys.get(i).equals("-E")||listKeys.get(i).equals("-U")){
						hmWeightKeys.put(listKeys.get(i), 3);
				}
			else 
				if(listKeys.get(i).equals("P")||listKeys.get(i).equals("W")||listKeys.get(i).equals("-P")||listKeys.get(i).equals("-B")){
							hmWeightKeys.put(listKeys.get(i), 4);
				}
			else 
				if(listKeys.get(i).equals("H")||listKeys.get(i).equals("R")||listKeys.get(i).equals("-F")||listKeys.get(i).equals("-R")){
								hmWeightKeys.put(listKeys.get(i), 5);
				}
		}

	}

	
	public static void main(String[] args) {
		FitnessComputation fit = new FitnessComputation();
		fit.fintnessComputationLists();
		System.out.println("Left Hand ==>  " + fit.listLeftHand);
		System.out.println("Right Hand ==>  " + fit.listRightHand);
		System.out.println("Row 1 ==>  " + fit.listRow1);
		System.out.println("Row 2 ==>  " + fit.listRow2);
		System.out.println("Row 3 ==>  " + fit.listRow3);
		System.out.println("All the keys : "+fit.listKeys);
		System.out.println("Keys Weight ==> "+fit.hmWeightKeys);
		
	}

}
