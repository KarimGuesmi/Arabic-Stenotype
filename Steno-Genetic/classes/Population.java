package classes;

public class Population {
	Individual[] individuals;

	// List Of lettes 
	
	// List of Keys
	
	public Population(int populationSize) {
		super();
		individuals = new Individual[populationSize];
	}

	public int size() {
		return individuals.length;
	}
	
	
}
