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
	private List<String> letterList = new ArrayList<String>();
	private List<String> strokesOneWord = new ArrayList<String>();
	private List<String> strokesSentence = new ArrayList<String>();
	
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
		
		System.out.println("Word 1: "+words.get(0));
		String word = words.get(0);
		for(int i=0; i<word.length();i++){
			String in = String.valueOf(word.charAt(i));
			int index = dP.dEN.getLettersWithStar().indexOf(in);
			if(index!=-1){
				System.out.println(in+" : "+dP.dEN.getStrokesWithStar().get(index)+" : index : "+dP.dEN.getStrokesWithStar().indexOf(dP.dEN.getStrokesWithStar().get(index)));
				dP.strokesOneWord.add(dP.dEN.getStrokesWithStar().get(index));
			}else{
				int index2 = dP.dEN.getLettersSingle().indexOf(in);
				System.out.println(in+" : "+dP.dEN.getStrokesSingle().get(index2)+" : index : "+ dP.dEN.getStrokesSingle().indexOf(dP.dEN.getStrokesSingle().get(index2)));
				dP.strokesOneWord.add(dP.dEN.getStrokesSingle().get(index2));
			}
		}
		System.out.println("The first Word :  '"+ word +"' -> "+dP.strokesOneWord);
		String stroke=dP.strokesOneWord.get(0);
		for(int i=0; i<dP.strokesOneWord.size()-1;i++){
			if(dP.strokesOneWord.get(i).contains("*")){
				stroke =stroke.concat("/").concat(dP.strokesOneWord.get(i+1));
			}else
			if(dP.strokesOneWord.indexOf(dP.strokesOneWord.get(i))>dP.strokesOneWord.indexOf(dP.strokesOneWord.get(i+1))){
				stroke =stroke.concat(dP.strokesOneWord.get(i+1));
			}else{
				stroke =stroke.concat("/").concat(dP.strokesOneWord.get(i+1));
			}
			
		}
		System.out.println(stroke);
		dP.strokesSentence.add(stroke);
		System.out.println(dP.strokesSentence);
		
	}

}
