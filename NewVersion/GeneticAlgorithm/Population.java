package GeneticAlgorithm;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import preparation.SplitingOnlySentences;
import preparation.SplitingText;

public class Population {

	private Entity en = new Entity();
	private SplitingOnlySentences sp = new SplitingOnlySentences();
	private ArrayList<ArrayList<String>> ListOfWordsList = new ArrayList<ArrayList<String>>();

	
	public void displayWords(String sentence) {
		String [] words = sentence.split(" ");
		ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(words));
		ListOfWordsList.add(wordList);
		setListOfWordsList(ListOfWordsList);
		//System.out.println(wordList);
		//System.out.println(ListOfWordsList);
		
	}
	
	/*
	 * Getters && Setters
	 */
	public ArrayList<ArrayList<String>> getListOfWordsList() {
		return ListOfWordsList;
	}
	public void setListOfWordsList(ArrayList<ArrayList<String>> listOfWordsList) {
		ListOfWordsList = listOfWordsList;
	}
	
	
	
	/*
	 * Main program for the TEST
	 */
	public static void main(String[] args) throws IOException {
		Population pop = new Population();
		
		System.out.println(pop.en.getHm());
		pop.sp.readFileParagraphs("book.txt");
		//System.out.println(pop.sp.getSentencesList().get(0));
		for(int i=0; i<pop.sp.getSentencesList().size();i++){
			pop.displayWords(pop.sp.getSentencesList().get(i));
		}
		
	}
	
}
