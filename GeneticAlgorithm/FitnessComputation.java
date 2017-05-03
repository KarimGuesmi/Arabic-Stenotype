package GeneticAlgorithm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FitnessComputation {
	
	static Entity entite;
	private static Map<String,String>entitee = new HashMap<String, String>();
	
	/*
	 * Compute the fitness value from a given random generated entity
	 */
	public FitnessComputation(Map<String,String> entity) {
		super();
		double effort = computeEffort(entity); 
		double penalty = computePenalty(entity);
		
		double fitness = effort - penalty;
	}


	public double computePenalty(Map<String, String> entity) {
		
		return 0;
	}


	public double computeEffort(Map<String, String> entity) {
		
		return 0;
	}


	/*
	 * Main Program for the TEST
	 */
	public static void main(String[] args) throws IOException {
		entite = new Entity();
		entitee = entite.generateRandomEntities();
		FitnessComputation fitness = new FitnessComputation(entitee);
		System.out.println(fitness);
	}

}
