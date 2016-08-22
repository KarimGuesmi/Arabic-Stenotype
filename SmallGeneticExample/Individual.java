
public class Individual {
	static int defaultGeneLength;
	private byte[] genes = new byte[defaultGeneLength];
	private int fitness = 0;
	
	// create random individual
	public void generateIndividual(){
		for(int i=0;i<size();i++){
			byte gene = (byte) Math.round(Math.random());
			genes[i]=gene;
		}
	}

	public int size() {
		return genes.length;
	}

	public byte[] getGenes() {
		return genes;
	}

	public void setGenes(int index, byte value) {
		 genes[index] = value;
	     fitness = 0;
	}

	public int getFitness() {
		return fitness;
	}

	//use it if we want to create individuals with different length
	public static void setDefaultGeneLength(int length) {
		Individual.defaultGeneLength = length;
	}
	
	@Override
	public String toString(){
		String geneString="";
		for(int i=0;i<size();i++){
			geneString+=getGenes();
		}
		return geneString;
	}

}
