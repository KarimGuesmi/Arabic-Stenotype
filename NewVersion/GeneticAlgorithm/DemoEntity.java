package GeneticAlgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DemoEntity {

	/*
	 * The Arabic Letters ordered by plover Keyboard positions from 0 to 21 
	 * Every letter here is for a single STROKE
	 */
	private List<String> lettersSingle = new ArrayList<String>();
	
	/*
	 * Single Strokes for every Arabic letter
	 * Single Stroke = single arabic letter 
	 */
	private List<String> strokesSingle = new ArrayList<String>();
	
	/*
	 * Missing Arabic letters from the first List of lettersSingle
	 */
	private List<String> lettersDouble = new ArrayList<String>();
	
	/*
	 * Some Arabic letters need a double stroke 
	 * One Key + "*"
	 */
	private List<String> strokesDouble = new ArrayList<String>();
	
	
	/*
	 * Constructor
	 * Initialize the Arabic Keyboard
	 */
	public DemoEntity() throws FileNotFoundException  {
		super();
		// Initialize the List of Arabic letters for single Strokes
		Scanner s = new Scanner(new File("lettersSingle.txt"));
		while (s.hasNext()){
		    lettersSingle.add(s.next());
		}
		s.close();
		System.out.println(lettersSingle);
	
		// Initialize the List of Single Strokes
		Scanner sL = new Scanner(new File("strokesSingle.txt"));
		while (sL.hasNext()){
		    strokesSingle.add(sL.next());
		}
		s.close();
		System.out.println(strokesSingle);
	
	}



	/*
	 * Main For Test
	 */
	public static void main(String[] args) throws FileNotFoundException  {
		DemoEntity en = new DemoEntity();
	

}
}