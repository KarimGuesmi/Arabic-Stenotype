package GeneticAlgorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;

import preparation.SplitingOnlySentencesForEachParagraph;
import preparation.SplitingText;

public class Population {

	private Entity en = new Entity();
	
	SplitingOnlySentencesForEachParagraph sp = new SplitingOnlySentencesForEachParagraph();

	/*
	 * Main program for the TEST
	 */
	public static void main(String[] args) throws IOException {
		Population pop = new Population();
		
		System.out.println(pop.en.getHm());
		pop.sp.readFileParagraphs("book.txt");
	}

}
