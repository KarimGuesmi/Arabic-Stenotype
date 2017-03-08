package GeneticAlgorithm;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import preparation.SplitingOnlySentences;

public class DemoPopulation {

	private DemoEntity dEN = new DemoEntity();
	private SplitingOnlySentences sPS = new SplitingOnlySentences();
	private List<String> wordsList = new ArrayList<String>();
	
	/*
	 * Split sentence into words
	 */
	public List<String> getWords(String text) {
	    BreakIterator breakIterator = BreakIterator.getWordInstance();
	    breakIterator.setText(text);
	    int lastIndex = breakIterator.first();
	    while (BreakIterator.DONE != lastIndex) {
	        int firstIndex = lastIndex;
	        lastIndex = breakIterator.next();
	        if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
	            wordsList.add(text.substring(firstIndex, lastIndex));
	        }
	    }
	    return wordsList;
	}
	
	
	/*
	 * Main Program for the test
	 */
	public static void main(String[] args) throws IOException {
		DemoPopulation dP= new DemoPopulation();
		dP.sPS.readFileParagraphs("book.txt");
		String sentence1 = dP.sPS.getSentencesList().get(0);
		System.out.println(sentence1);
		List<String>words= dP.getWords(sentence1);
		System.out.println(words);
	}

}
