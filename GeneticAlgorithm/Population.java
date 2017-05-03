package GeneticAlgorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
	private List<Double>fitnessValues= new ArrayList<>();
	
	/*
	 * Create a population
	 */
	public Population() throws IOException {
		super();
		for (int i = 0; i < populationSize; i++) {
			entity = new Entity();
			entities.add(entity.generateRandomEntities());
			System.out.println("===> Random Entity " + i + " : ");
			System.out.println(entities.get(i));
			fitness = new FitnessComputation(entities.get(i));
			System.out.println("** The fitness value of the Entity "+ i+" : "+fitness.getEffort());
			fitnessValues.add(fitness.getEffort());
		}
	}

	/*
	 * Take two random entities and make a crossover between them
	 */
	public void run() throws IOException {
		// List of all fitness values
		 
		for (int i = 0; i < 10; i++) {
			System.out.println(
					"----------------------------------------------------------------------------------------------");
			System.out.println("**************************** GENERATION CROSSOVER N :" + i
					+ " ***********************************");
			System.out.println(
					"----------------------------------------------------------------------------------------------");
			int index1 = random.nextInt(entities.size());
			int index2 = random.nextInt(entities.size());
			crossover = new Crossover(entities.get(index1), entities.get(index2));
			
			Map<String,String> entity1 = crossover.getEntity1SingleCrossed();
			
			System.out.println("** The Entity After the crossover :");
			System.out.println(entity1);
			
			Mutation mutation1 = new Mutation(entity1);
			System.out.println("** The Entity after the mutation");
			System.out.println(mutation1.getFinalEntityMutated());
			entities.set(index1, mutation1.getFinalEntityMutated());
			
			// Get the fitness value for all entities
			fitness = new FitnessComputation(mutation1.getFinalEntityMutated());
			System.out.println("** The fitness value of the Entity :"+index1+" : "+fitness.getEffort());
			fitnessValues.set(index1, fitness.getEffort());
			
			
		}
		System.out.println("_____________________________________________");
		System.out.println("** All Fitness values : ");
		System.out.println(fitnessValues);
		
		// Select the highest fitness value
		double highestFitness = fitnessValues.get(0) ;
		for(int i=1; i< fitnessValues.size();i++){
			if(fitnessValues.get(i)>highestFitness){
				highestFitness = fitnessValues.get(i);
			}
		}
		// Get the index of the highest Fitness Value
		int indexHighestFitness = fitnessValues.indexOf(highestFitness);
		System.out.println(indexHighestFitness);
		
		// Throw Away the corresponding entity 
		// And generate a new Entity instead of it

		// Generate a new Entity
		Map<String,String> newEntity = new HashMap<String,String>();
		newEntity = entity.generateRandomEntities();
		entities.set(indexHighestFitness, newEntity);
		System.out.println("___________________________________________________");
		
		// Display the entities after the generations
		for(int i=0; i<entities.size();i++){
			System.out.println(entities.get(i));
			System.out.println(fitnessValues.get(i));
		}
	}

	/*
	 * Take a random entity and do the mutation for it
	 */
	public void doMutation() {
		for (int i = 0; i < 4; i++) {
			System.out.println(
					"----------------------------------------------------------------------------------------------");
			System.out.println("**************************** GENERATION Mutation N :" + i
					+ " ***********************************");
			System.out.println(
					"----------------------------------------------------------------------------------------------");

			mutation = new Mutation(entities.get(i));
		}

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

		// do mutation
		//System.out.println("///////////////////////// THE MUTATION ///////////////////////////////");
		//pop.doMutation();

	}

}
