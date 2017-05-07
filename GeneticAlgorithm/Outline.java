
package GeneticAlgorithm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Outline {
	
	private static Algorithm algo;
	
	public void createOutlineFile(String file, List<String> strokeDictionaryImproved) {
		try{
		    PrintWriter writer = new PrintWriter(file, "UTF-8");
		    for(int i=0; i<strokeDictionaryImproved.size();i++){
		    	writer.println(strokeDictionaryImproved.get(i));
		    }
		    writer.close();
		} catch (IOException e) {
		}
	}
	
	public static void main(String[] args) throws IOException {
		Outline out = new Outline();
		algo = new Algorithm();
		//System.out.println(algo.getStrokeDictionaryImproved());
		out.createOutlineFile("outline.txt", algo.getStrokeDictionaryImproved());
	}

}
