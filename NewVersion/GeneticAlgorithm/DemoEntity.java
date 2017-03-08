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
	private List<String> lettersWithStar = new ArrayList<String>();
	
	/*
	 * Some Arabic letters need a double stroke 
	 * One Key + "*"
	 */
	private List<String> strokesWithStar = new ArrayList<String>();
	
	
	/*
	 * Constructor
	 * Initialize the Arabic Keyboard
	 */
	public DemoEntity()   {
		super();
		// Initialize the List of Arabic letters for single Strokes
		Scanner s;
		try {
			s = new Scanner(new File("lettersSingle.txt"));
			while (s.hasNext()){
			    lettersSingle.add(s.next());
			}
			s.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		
		// Initialize the List of Single Strokes
		Scanner sL;
		try {
			sL = new Scanner(new File("strokesSingle.txt"));
			while (sL.hasNext()){
			    strokesSingle.add(sL.next());
			}
			sL.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		// Initialize the List of Arabic letters for strokes with Stars
		Scanner sLS;
		try {
			sLS = new Scanner(new File("lettersWithStar.txt"));
			while (sLS.hasNext()){
			    lettersWithStar.add(sLS.next());
			}
			sLS.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		// Initialize the List of Strokes with Star
		Scanner sS;
		try {
			sS = new Scanner(new File("strokesWithStar.txt"));
			while (sS.hasNext()){
			    strokesWithStar.add(sS.next());
			}
			sS.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	/*
	 * Getters And Setters for the lists
	 */
	
	public List<String> getLettersSingle() {
		return lettersSingle;
	}

	public void setLettersSingle(List<String> lettersSingle) {
		this.lettersSingle = lettersSingle;
	}

	public List<String> getStrokesSingle() {
		return strokesSingle;
	}

	public void setStrokesSingle(List<String> strokesSingle) {
		this.strokesSingle = strokesSingle;
	}

	public List<String> getLettersWithStar() {
		return lettersWithStar;
	}

	public void setLettersWithStar(List<String> lettersWithStar) {
		this.lettersWithStar = lettersWithStar;
	}

	public List<String> getStrokesWithStar() {
		return strokesWithStar;
	}

	public void setStrokesWithStar(List<String> strokesWithStar) {
		this.strokesWithStar = strokesWithStar;
	}
	
	/*
	 * Main For Test
	 */
	public static void main(String[] args) throws FileNotFoundException  {
		DemoEntity en = new DemoEntity();
		
		System.out.println("Arabic letters for Single Stroke : ");
		System.out.println(en.getLettersSingle());
	
		System.out.println("Single Strokes for Arabic letters : ");
		System.out.println(en.getStrokesSingle());
	
		System.out.println("Arabic letters for  Strokes with Star : ");
		System.out.println(en.getLettersWithStar());
	
		System.out.println("Strokes with Star : ");
		System.out.println(en.getStrokesWithStar());
	
	}

	
}
