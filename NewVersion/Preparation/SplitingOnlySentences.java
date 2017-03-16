package preparation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;

public class SplitingOnlySentences {

	private ArrayList<String> paragraphs = new ArrayList<String>();
	private ArrayList<String> sentencesList = new ArrayList<String>();
	private ArrayList<String> wordsList = new ArrayList<String>();
	
	
	public void readFileParagraphs(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			// Split the content of the file into an array of paragraphs
			String parags[] = sb.toString().split("\n\n");
			// Add every paragraph into an ArrayList called paragraphs
			for (int i = 0; i < parags.length; i++) {
				paragraphs.add(parags[i]);
			}
			int j = 1;
			for (String para : paragraphs) {
				j += 1;
			}
			// the paragraphs to sentences
			int l = 1;
			for (int k = 0; k < paragraphs.size(); k++) {
				String park = paragraphs.get(k);
				BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
				int sentences = count(iterator, park, l);
				l++;
			}

		} finally {
			br.close();
		}
	}

	/*
	 * Count the number of sentence in each paragraph
	 */
	public int count(BreakIterator iterator, String par0, int l) {
		int counter = 0;

		iterator.setText(par0);
		int lastIndex = iterator.first();
		int h = 1;
		while (lastIndex != BreakIterator.DONE) {
			int firstIndex = lastIndex;
			lastIndex = iterator.next();
			if (lastIndex != BreakIterator.DONE) {
				String sentence = par0.substring(firstIndex, lastIndex);
				sentencesList.add(sentence);
				setSentencesList(sentencesList);
				h += 1;
				counter++;
			}
		}
		return counter;
	}

	public void displayAllSentences(){
		for(int i=0; i<sentencesList.size();i++){
			System.out.println(getSentencesList().get(i));
		}
	}
	
	
	/*
	 * Setters and getters
	 */
	public ArrayList<String> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(ArrayList<String> paragraphs) {
		this.paragraphs = paragraphs;
	}

	public ArrayList<String> getSentencesList() {
		return sentencesList;
	}

	public void setSentencesList(ArrayList<String> sentencesList) {
		this.sentencesList = sentencesList;
	}


	/*
	 * Main
	 */
	public static void main(String[] args) throws IOException {
		SplitingOnlySentences sp = new SplitingOnlySentences();
		sp.readFileParagraphs("bookk.txt");
		System.out.println("******************sentences of the full text******************");System.out.println();
		sp.displayAllSentences();
		System.out.println();
		
	}

	

	

}
