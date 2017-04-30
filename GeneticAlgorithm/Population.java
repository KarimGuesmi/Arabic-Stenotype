package GeneticAlgorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Population {
	private Entity entity;
	private List<Map<String,String>> entities= new ArrayList<>();
	private int populationSize=5;
	
	/*
	 * Create a population
	 */
	public Population() throws IOException {
		super();
		for(int i=0; i< populationSize;i++){
			entity = new Entity();
			entities.add(entity.generateRandomEntities());
			System.out.println("Random Entity "+i+" : ");
			System.out.println(entities.get(i));
		}
	}


	public static void main(String[] args) throws IOException {
		Population pop = new Population();
	}

}
