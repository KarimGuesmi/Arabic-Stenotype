package classes;

import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		/*
			Creating the new Individual
			Individual = caracter from the word got from the book
		*/
		Individual indiv = new Individual();
		String file="briefs.txt";
		ArrayList<String> list = new ArrayList<String>(); 
		list = indiv.wordsFromBook(file);
		
		ArrayList<Character> listChars = new ArrayList<Character>();
		ArrayList<ArrayList<Character>>listOfListsOfWords = new ArrayList<ArrayList<Character>>();
		
		// Display the list of all words from the book
		// for each word , decompose the word to set of letters and put them into a new List
		for(int i=0; i<list.size();i++){
			System.out.println(list.get(i));
		}
		System.out.println(list);
		
		listChars = indiv.decomposeWord(list.get(0));
		System.out.println(listChars);
	
	}
}
