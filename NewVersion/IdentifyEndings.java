package preparation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class IdentifyEndings {

	private ArrayList<String> paragraphs = new ArrayList<String>();
	private ArrayList<String> sentencesList = new ArrayList<String>();
	private ArrayList<String> wordsList = new ArrayList<String>();
	
	public void readFileParagraphs(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),
				"UTF-8"));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			String parags[] = sb.toString().split("\n\n");
			for (int i = 0; i < parags.length; i++) {
				paragraphs.add(parags[i]);
			}
			int j = 1;
			for (String para : paragraphs) {
				j += 1;
			}
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

	/*
	 * splitWords will display the list of all words of the full text Based on
	 * each sentence
	 */
	public void splitWords() {
		for (int t = 0; t < getSentencesList().size(); t++) {
			String s = getSentencesList().get(t);
			String[] words = s.split("\\s+");
			for (int i = 0; i < words.length; i++) {
				words[i] = words[i].replaceAll("", "");
				wordsList.add(words[i]);
			}
		}
		
		System.out.println("The list of words is :");
		System.out.println(getWordsList());
		System.out.println("****************************************************");
	}
	
	public static boolean contains( String str1, String str2 ) {
		  str1 = str1 == null ? "" : str1;
		  str2 = str2 == null ? "" : str2;
		  return str1.contains( str2 );
		}
	

	/*
	 * Gettes && Setters
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

	public ArrayList<String> getWordsList() {
		return wordsList;
	}

	public void setWordsList(ArrayList<String> wordsList) {
		this.wordsList = wordsList;
	}

	/*
	 * Main program for the Test
	 */
	public static void main(String[] args) throws IOException {
		// preparation for the list of words
		IdentifyEndings iE = new IdentifyEndings();
		iE.readFileParagraphs("bookEng.txt");
		System.out.println("****************************************************");
		iE.splitWords();
		
		// preparation for the list of verbs
		IdentifyEndings iEVerbs = new IdentifyEndings();
		iEVerbs.readFileParagraphs("verbs.txt");
		System.out.println("****************************************************");
		iEVerbs.splitWords();
		
		// Identifying the endings 
		System.out.println("Similarity identified :");
		System.out.println("-------");
		String str1=iE.getWordsList().get(2);
		System.out.println(str1);
		String str2=iEVerbs.getWordsList().get(1);
		System.out.println(str2);
		System.out.println("-------");
		System.out.println("***********");
		if( contains( str1, str2 ) ) {
			 System.out.println("The word in the text is :"+str1);
			 System.out.println("The normal Verb is :"+str2);
			 String str3 = str1.substring(str2.length(),str1.length());
			 System.out.println("ENding is : "+str3);
			}
	}
}
