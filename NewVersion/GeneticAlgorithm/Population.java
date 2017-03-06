package GeneticAlgorithm;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import preparation.SplitingOnlySentences;
import preparation.SplitingText;

public class Population {

	private Entity en = new Entity();
	private SplitingOnlySentences sp = new SplitingOnlySentences();
	private ArrayList<ArrayList<String>> listOfWordsList = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> listOfWordLetters = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> listOfLettersStrokes = new ArrayList<ArrayList<String>>();
	
	
	public void displayWords(String sentence) {
		String [] words = sentence.split(" ");
		ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(words));
		listOfWordsList.add(wordList);
		setListOfWordsList(listOfWordsList);
		//System.out.println(wordList);
		//System.out.println(ListOfWordsList);		
	}
	
	/*
	 * Display the list of letters 
	 */
	public void displayLetters(ArrayList<String> wordsList) {
		for(int k=0; k<wordsList.size();k++){
			String word = wordsList.get(k);
			ArrayList<String> letterList = new ArrayList<String>();
			for(int i=0; i< word.length();i++){
				letterList.add(word.substring(i, i+1));
			}
			listOfWordLetters.add(letterList);
			setListOfWordLetters(listOfWordLetters);
			
			word=null;
			letterList=null;
		}	
		System.out.println(listOfWordLetters);
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
	public ArrayList<ArrayList<String>> getListOfWordLetters() {
		return listOfWordLetters;
	}
	public void setListOfWordLetters(ArrayList<ArrayList<String>> listOfWordLetters) {
		this.listOfWordLetters = listOfWordLetters;
	}
	public ArrayList<ArrayList<String>> getListOfLettersStrokes() {
		return listOfLettersStrokes;
	}
	public void setListOfLettersStrokes(ArrayList<ArrayList<String>> listOfLettersStrokes) {
		this.listOfLettersStrokes = listOfLettersStrokes;
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
		pop.displayLetters(pop.listOfWordsList.get(0));
		
		System.out.println("**********************************************");
		System.out.println("Generation 1 :");
		//System.out.println(pop.listOfWordLetters.get(0).get(1));
		pop.affectStrokes(pop.listOfWordLetters.get(0));
		
	}

	public void affectStrokes(ArrayList<String> listLetters) {
		
		for(int i=0; i<listLetters.size();i++ ){
			
		}
		if(en.getHm().containsValue(listLetters.get(1))){
			System.out.println(true);
			System.out.println(en.getHm());
		}else 
			System.out.println(false);
	}


}
