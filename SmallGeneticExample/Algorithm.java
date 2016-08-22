
public class Algorithm {
	//GA Parameters
	private static final double uniformRate=0.5;
	private static final double mutationRate = 0.015;
	private static final int tournamentSize = 5;
	private static final boolean elitism = true;
	
	// Evolve a population
	public static Population evolvePopulation(Population pop){
		Population newPopulation = new Population(pop.size(), false);
		
		// Keep our best individual
		if(elitism){
			newPopulation.saveIndividual(0, pop.getFittest());
		}
		
		// crossover population
		int elitismOffSet;
		if(elitism){
			elitismOffSet=1;
		}else{
			elitismOffSet=0;
		}
		
		// Loop over the population size and create new individuals with crossover
		for(int i=elitismOffSet; i<pop.size();i++){
			Individual indiv1 = tournamentSelection(pop);
			Individual indiv2 = tournamentSelection(pop);
			Individual newIndiv = crossover(indiv1,indiv2);
			newPopulation.saveIndividual(i, newIndiv);
		}

		//mutation population
		for(int i=elitismOffSet;i<newPopulation.size();i++){
			mutate(newPopulation.getIndividual(i));
		}
		return newPopulation;
	}

	//mutate an individual
	private static void mutate(Individual indiv) {
		// Loop through genes
		for(int i=0;i<indiv.size();i++){
			if(Math.random()<=mutationRate){
				//create random gene
				byte gene = (byte) Math.round(Math.random());
				indiv.setGenes(i, gene);
			}
		}
	}

	//Crossover individuals
	private static Individual crossover(Individual indiv1, Individual indiv2) {
		Individual newSol = new Individual();
		//Loop through genes
		for(int i=0;i<indiv1.size();i++){
			//crossover
			if(Math.random()<=uniformRate){
				newSol.setGenes(i,indiv1.getGenes(i));
			}else{
				newSol.setGenes(i, indiv2.getGenes(i));
			}
		}
		return newSol;
	}
	
	//select individual for crossover
	public static Individual tournamentSelection(Population pop) {
		// create tournament population
		Population tournament = new Population(tournamentSize, false);
		//for each place in the tournament get a random individual
		for(int i=0;i<tournamentSize;i++){
			int randomID = (int)(Math.random()*pop.size());
			tournament.saveIndividual(i, pop.getIndividual(randomID));
		}
		//get the fittest
		Individual fittest = tournament.getFittest();
		return fittest;
	}
}
