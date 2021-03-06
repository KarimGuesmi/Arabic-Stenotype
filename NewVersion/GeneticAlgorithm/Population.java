package GeneticAlgorithm;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import preparation.SplitingOnlySentences;
import preparation.SplitingText;

public class Population {

	private Entity en = new Entity("letters.txt","keys.txt");
	private RandomEntity rEN = new RandomEntity();
	private SplitingOnlySentences sp = new SplitingOnlySentences();
	private ArrayList<ArrayList<String>> listOfWordsList = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> listOfWordLetters = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> listOfLettersStrokes = new ArrayList<ArrayList<String>>();
	private ArrayList<String> listofStrokes = new ArrayList<String>();
	
	
	public void displayWords(String sentence) {
		String [] words = sentence.split(" ");
		ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(words));
		listOfWordsList.add(wordList);
		setListOfWordsList(listOfWordsList);
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
	
		for (int l = 0; l < 5; l++) {
			System.out.println("******************* Generation : "+(l+1)+" *******************");
			Population pop = new Population();
			System.out.println("The Letters : Keys");
			// System.out.println(pop.en.getHm());

			pop.rEN.createOneRandomEntity();
			pop.sp.readFileParagraphs("bookk.txt");

			for (int i = 0; i < pop.sp.getSentencesList().size(); i++) {
				pop.displayWords(pop.sp.getSentencesList().get(i));
			}

			System.out.println("The sentence 1 :");
			System.out.println("-------------------------------");
			pop.displayLetters(pop.listOfWordsList.get(0));
			System.out.println(pop.listOfWordsList.get(0));
			for (int i = 0; i < pop.listOfWordLetters.size(); i++) {
				String word = pop.listOfWordsList.get(0).get(i);
				System.out.println("Word " + i + " : " + word);
				for (int j = 0; j < pop.listOfWordLetters.get(i).size(); j++) {
					String let = pop.listOfWordLetters.get(i).get(j);
					if (pop.rEN.getHmRandomEntity().get(let) == null) {

					} else {
						System.out.println(let + " : " + pop.rEN.getHmRandomEntity().get(let));
						pop.listofStrokes.add(pop.rEN.getHmRandomEntity().get(let));
						
					}
					/*
					 * if(pop.en.gethm().get(let)==null){
					 * 
					 * }else{ System.out.println(let +" : "
					 * +pop.en.getHm().get(let)); }
					 */
				}
				//List of stroke of the word
				System.out.println(pop.listofStrokes); pop.listofStrokes.clear();
				
				System.out.println("****************************************");
			}
		}
	}

}
