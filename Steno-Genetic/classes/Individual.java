package classes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class Individual {
	private String filePath;
	private String letterTaped;
	private ArrayList<String> listOfStrings= new ArrayList<String>();
	private ArrayList<Character>listOfChars = new ArrayList<Character>();
	// This is a list of lists_of_caracters 
	private ArrayList<ArrayList<Character>> listOLists = new ArrayList<ArrayList<Character>>();
	
	public Individual() {
		super();
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	//get all words from the book and put them into a list of words
	public ArrayList<String> wordsFromBook(String file){
		try {
			FileInputStream fis= new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis,"utf8"));
			String line;
			
			while((line=br.readLine()) != null){
				listOfStrings.add(line);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listOfStrings;
	}
	
	// Decompose each word from The list of words to a list of Chars(letters)
	
	public ArrayList<Character> decomposeWord(String word){
		for(int i=0; i<word.length();i++){
			listOfChars.add(i,word.charAt(i));
		}
		return listOfChars;
	}
	
	
}
