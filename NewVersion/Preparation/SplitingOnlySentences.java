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
			System.out.println("*********************************************");
			// the paragraphs to sentences
			int l = 1;
			for (int k = 0; k < paragraphs.size(); k++) {
				String park = paragraphs.get(k);
				BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
				int sentences = count(iterator, park, l);
				l++;
				System.out.println("Number of sentences: " + sentences);
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
		System.out.println("paragraph :" + l);
		System.out.println("----------------");
		while (lastIndex != BreakIterator.DONE) {
			int firstIndex = lastIndex;
			lastIndex = iterator.next();
			if (lastIndex != BreakIterator.DONE) {
				String sentence = par0.substring(firstIndex, lastIndex);
				sentencesList.add(sentence);
				setSentencesList(sentencesList);
				System.out.println("sentence " + h + "==>" + sentence);
				h += 1;
				counter++;
			}
		}
		return counter;
	}

	
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

	public static void main(String[] args) throws IOException {
		SplitingOnlySentencesForEachParagraph sp = new SplitingOnlySentencesForEachParagraph();
		sp.readFileParagraphs("book.txt");
		System.out.println("******************sentences **********");
		System.out.println(sp.getSentencesList().get(0));
		System.out.println(sp.getSentencesList().get(1));
		System.out.println(sp.getSentencesList().get(2));
		System.out.println(sp.getSentencesList().get(3));
		System.out.println(sp.getSentencesList().get(4));
	}

}
