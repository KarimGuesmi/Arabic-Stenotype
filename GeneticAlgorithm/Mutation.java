package GeneticAlgorithm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 * This class is to mutate
 * Order changing : two selected values of keys are  exchanged
 */
public class Mutation {

	private static Entity entity;
	private Random random = new Random();
	private static Map<String, String> initialEntity = new HashMap<String,String>();
	
	
	
	public Mutation(Map<String, String> initialEntity) {
		super();
		
	}


	/*
	 * Main Program for the Test
	 */
	public static void main(String[] args) throws IOException {
		entity = new Entity();
		initialEntity = entity.generateRandomEntities();
		System.out.println(initialEntity);
		Mutation mutation = new Mutation(initialEntity);

	}

}
