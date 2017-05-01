package GeneticAlgorithm;

import java.io.IOException;
import java.util.ArrayList;
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

	/*
	 * Create a population
	 */
	public Population() throws IOException {
		super();
		for (int i = 0; i < populationSize; i++) {
			entity = new Entity();
			entities.add(entity.generateRandomEntities());
			System.out.println("Random Entity " + i + " : ");
			System.out.println(entities.get(i));
		}
	}

	/*
	 * Take two random entities and make a crossover between them
	 */
	public void doCrossover() throws IOException {
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
		}

	}

	/*
	 * Main program for the Testing
	 */
	public static void main(String[] args) throws IOException {
		Population pop = new Population();
		System.out.println("_________________________________________________________________________");

		// do crossover for 5 entities
		pop.doCrossover();

	}

}
