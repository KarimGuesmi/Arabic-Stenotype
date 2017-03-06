package GeneticAlgorithm;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import preparation.SplitingOnlySentences;
import preparation.SplitingText;

public class Population {

	private Entity en = new Entity();
	private SplitingOnlySentences sp = new SplitingOnlySentences();
	private ArrayList<ArrayList<String>> listOfWordsList = new ArrayList<ArrayList<String>>();
	private ArrayList<char[][]> listOfWordsLetters = new ArrayList<char[][]>();

	
	public void displayWords(String sentence) {
		String [] words = sentence.split(" ");
		ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(words));
		listOfWordsList.add(wordList);
		setListOfWordsList(listOfWordsList);
		//System.out.println(wordList);
		//System.out.println(ListOfWordsList);
		
	}
	
	/*
	 * Getters && Setters
	 */
	public ArrayList<ArrayList<String>> getListOfWordsList() {
		return listOfWordsList;
	}
	public void setListOfWordsList(ArrayList<ArrayList<String>> listOfWordsList) {
		listOfWordsList = listOfWordsList;
	}
	public ArrayList<char[][]> getListOfWordsLetters() {
		return listOfWordsLetters;
	}	
	public void setListOfWordsLetters(ArrayList<char[][]> listOfWordsLetters) {
		this.listOfWordsLetters = listOfWordsLetters;
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
		
		System.out.println(pop.listOfWordsList.get(0));
		pop.displayLetters(pop.listOfWordsList.get(0));
	}

	public void displayLetters(ArrayList<String> wordsList) {
		char[][] lettersByWord = new char[wordsList.size()][];
		for(int i = 0; i < lettersByWord.length; i++){
		    lettersByWord[i] = wordsList.get(i).toCharArray();
		    listOfWordsLetters.add(lettersByWord);    
		}
		setListOfWordsLetters(listOfWordsLetters);
		
		System.out.println(Arrays.deepToString(lettersByWord));
		
	}
	
}
