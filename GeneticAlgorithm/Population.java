package GeneticAlgorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Population {
	private Entity entity;
	private List<Map<String, String>> entities = new ArrayList<>();
	private int populationSize = 5;
	private Random random = new Random();
	private Crossover crossover;
	private Mutation mutation;
	private FitnessComputation fitness;
	private List<Double> fitnessValues = new ArrayList<>();
	
	/*
	 * Create a population
	 */
	public Population() throws IOException {
		super();
		for (int i = 0; i < populationSize; i++) {
			entity = new Entity();
			entities.add(entity.generateRandomEntities());
		//	System.out.println("===> Random Entity " + i + " : ");
		//	System.out.println(entities.get(i));
			fitness = new FitnessComputation(entities.get(i));
		//	System.out.println("** The fitness value of the Entity " + i + " : " + fitness.getEffort());
			fitnessValues.add(fitness.getEffort());
		}
		//System.out.println("===========================================");
		//System.out.println("** The List of our fitness values : ");
		//System.out.println(fitnessValues);
	}

	/*
	 * Take two random entities and make a crossover between them
	 */
	public void run() throws IOException {
		loopThroughGenerations();
	}

	/*
	 * This method is signed in the run() void 
	 */
	private void loopThroughGenerations() throws IOException {
		for (int i = 0; i < 10000; i++) {
			
			int index1 = random.nextInt(entities.size());
			int index2 = random.nextInt(entities.size());
			crossover = new Crossover(entities.get(index1), entities.get(index2));

			Map<String, String> entity1 = crossover.getEntity1SingleCrossed();

			Mutation mutation1 = new Mutation(entity1);
			
			entities.set(index1, mutation1.getFinalEntityMutated());

			// Get the fitness value for all entities
			fitness = new FitnessComputation(mutation1.getFinalEntityMutated());
			//System.out.println("** The fitness value of the Entity :" + index1 + " : " + fitness.getEffort());
			fitnessValues.set(index1, fitness.getEffort());

			//System.out.println("_____________________________________________");
			//System.out.println("** All Fitness values : ");
			//System.out.println(fitnessValues);

			// Select the highest fitness value
			double highestFitness = fitnessValues.get(0);
			for (int k = 1; k < fitnessValues.size(); k++) {
				if (fitnessValues.get(k) > highestFitness) {
					highestFitness = fitnessValues.get(k);
				}
			}

			// Get the index of the highest Fitness Value
			int indexHighestFitness = fitnessValues.indexOf(highestFitness);
			//System.out.println(indexHighestFitness);

			// Throw Away the corresponding entity
			// And generate a new Entity instead of it

			// Generate a new Entity
			Map<String, String> newEntity = new HashMap<String, String>();
			newEntity = entity.generateRandomEntities();
			entities.set(indexHighestFitness, newEntity);
			//System.out.println("___________________________________________________");

			// Display the entities after the generations
			//System.out.println("////////******* THE NEW ENTITIES & FITNESS *********////////////");
			for (int k = 0; k < entities.size(); k++) {
				//System.out.println(entities.get(k));
				//System.out.println(fitnessValues.get(k));
			}
		}
	}

	/*
	 * Get The Best Entity
	 * The best entity is defined as an entity which has the lowest fitness value
	 */
	public Map<String, String> getFittest(List<Double> fitnessValues){
		Double Lowest = getLowest(fitnessValues);
		
		return entities.get(fitnessValues.indexOf(Lowest));
	}
	
	public List<Double> getFitnessValues() {
		return fitnessValues;
	}

	public void setFitnessValues(List<Double> fitnessValues) {
		this.fitnessValues = fitnessValues;
	}

	/*
	 * Signature of this method in the function getFittest()
	 */
	public Double getLowest(List<Double> fitnessValues2) {
		Double lowest = fitnessValues2.get(0);
		for(int i=1; i< fitnessValues2.size();i++){
			if(fitnessValues2.get(i) > lowest){
				lowest = fitnessValues2.get(i);
			}
		}
		return lowest;
	}

	/*
	 * Main program for the Testing
	 */
	public static void main(String[] args) throws IOException {
		Population pop = new Population();
		System.out.println("_________________________________________________________________________");

		// do crossover for 5 entities
		System.out.println("///////////////////////// THE CROSSOVER ///////////////////////////////");
		pop.run();
	}
}
