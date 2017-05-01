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

	public static void main(String[] args) throws IOException {
		Population pop = new Population();
		System.out.println("_________________________________________________________________________");

		// do crossover for 5 entities
		for (int i = 0; i < 10; i++) {
			System.out.println(
					"----------------------------------------------------------------------------------------------");
			System.out.println("**************************** GENERATION CROSSOVER N :" + i
					+ " ***********************************");
			System.out.println(
					"----------------------------------------------------------------------------------------------");
			int index1 = pop.random.nextInt(pop.entities.size());
			int index2 = pop.random.nextInt(pop.entities.size());
			pop.crossover = new Crossover(pop.entities.get(index1), pop.entities.get(index2));
		}
	}

}
