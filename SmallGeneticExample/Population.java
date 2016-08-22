import javax.print.attribute.Size2DSyntax;


public class Population {
	Individual[] individuals;
	
	public Population(int populationSize,  boolean initialize){
		individuals = new Individual[populationSize];
		//initialize the population
		if(initialize){
			//Loop and create individuals
			for(int i=0;i<size();i++){
				Individual newIndividual = new Individual();
				newIndividual.generateIndividual();
				saveIndividual(i, newIndividual);
			}
		}
	}

	
	public void saveIndividual(int index, Individual indiv) {
		individuals[index]=indiv;
		
	}

	public int size() {
		return individuals.length;
	}

	public Individual getIndividual(int index){
		return individuals[index];
	}
	
	public Individual getFittest(){
		Individual fittest = individuals[0];
		// loop through individuals to find fittest
		for(int i=0;i<size();i++){
			if(fittest.getFitness()<=getIndividual(i).getFitness()){
				fittest = getIndividual(i);
			}
		}
		return fittest;
	}
}

