package preparation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;

/*
 * This class is implemented, to read a text from a file 
 * decompose that text into paragraphs
 * decompose each paragraph into sentences
 * decompose each sentence into words
 * decompose each word into letters 
 */
public class SplitingText {
	/*
	 * The attributes are the followings : List of paragraphs from the txtFile
	 * List of sentences for each paragraph 
	 * List of all Words for each sentence List
	 * of letters for each word
	 */
	private ArrayList<String> paragraphs = new ArrayList<String>();
	private ArrayList<String> sentencesList = new ArrayList<String>();
	private ArrayList<String> wordsList = new ArrayList<String>();
	private ArrayList<ArrayList<String>> listLettersList = new ArrayList<ArrayList<String>>();

	/*
	 * void readFileParagraphs(String fileName) Read the content of the file
	 * "fileName" split the content of the file into paragraphs add every
	 * paragraph into the ArrayList called paragraphs
	 */
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
				System.out.println("paragraph " + j + "==> " + para);
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

	/*
	 * Break all paragraphs into sentences Here it consists only to display
	 * sentences one by one
	 */
	public void paragIntoSentences() {
		System.out.println("************************************************");
		System.out.println("The sentences List is the following");
		System.out.println("-------------------------------");
		for (int i = 0; i < sentencesList.size(); i++) {
			System.out.println(sentencesList.get(i));
		}
		System.out.println("We have " + sentencesList.size() + " sentences");
		System.out.println("and " + paragraphs.size() + " paragraphs");
	}

	/*
	 * splitWords will display the list of all words of the full text Based on
	 * each sentence
	 */
	public void splitWords() {
		for (int t = 0; t < getSentencesList().size(); t++) {
			String s = getSentencesList().get(t);
			System.out.println(s);
			String[] words = s.split("\\s+");
			for (int i = 0; i < words.length; i++) {
				words[i] = words[i].replaceAll("[^\\w]", "");
				wordsList.add(words[i]);
			}
			/*
			 * To display the words FOR every sentence for(int
			 * i=0;i<words.length;i++){ System.out.print(words[i]+", "); }
			 */
		}
		System.out.println();
		System.out.println("The list of words is :");
		System.out.println(getWordsList());
	}

	/*
	 * This method is to decompose each Word from the WordList into List of List
	 * of Letters We take all words from the ArrayList<String>wordsList We
	 * decompose each word into an array of Letters We defined previously the
	 * ArrayList<ArrayList<String>> listLettersList This list will contain a
	 * list of list of letters
	 */
	public void splitLetters(String word, String[] alphabets, ArrayList<String> letters) {
		System.out.println("The List of List of Letters :");
		System.out.println("---------------------------");
		for (int d = 0; d < wordsList.size(); d++) {
			word = getWordsList().get(d);
			alphabets = word.split("");
			letters = new ArrayList<String>();
			for (String alphabet : alphabets) {
				letters.add(alphabet);
			}
			listLettersList.add(letters);

			alphabets = null;
			word = null;
		}

		System.out.println(listLettersList);

	}

	/*
	 * Getters & setters of the lists
	 */
	public ArrayList<String> getParagraphs() {
		return paragraphs;
	}

	public ArrayList<String> getSentencesList() {
		return sentencesList;
	}

	public void setParagraphs(ArrayList<String> paragraphs) {
		this.paragraphs = paragraphs;
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

	public ArrayList<ArrayList<String>> getListLettersList() {
		return listLettersList;
	}

	public void setListLettersList(ArrayList<ArrayList<String>> listLettersList) {
		this.listLettersList = listLettersList;
	}

	/*
	 * The Main program
	 */
	public static void main(String[] args) throws IOException {
		SplitingText sp = new SplitingText();
		sp.readFileParagraphs("EngTxt.txt");
		sp.paragIntoSentences();
		System.out.println("*******************************************");
		sp.splitWords();

		// Words to Letters
		System.out.println("********************************************");
		String word = "";
		String[] alphabets = null;
		ArrayList<String> letters = new ArrayList<String>();
		sp.splitLetters(word, alphabets, letters);

	}
}


/*
 *  This is for splitting a simple sentence into words
 * String s = "This is a sample sentence.";
String[] words = s.split("\\s+");
for (int i = 0; i < words.length; i++) {
    words[i] = words[i].replaceAll("[^\\w]", "");
}
for(int i=0;i<words.length;i++){
	System.out.print(words[i]+", ");
}
System.out.println("");
*/

/*
String[] result = "karim".split("");                                                                                   
for ( int x=0; x<result.length; x++) {
     System.out.print(result[x]+" ,");
}   
String str = "cat";
String[] res = new String[str.length()];
for (int i = 0; i < str.length(); i++) {
    res[i] = Character.toString(str.charAt(i));
}

for (int i = 0; i < str.length(); i++) {
    System.out.println(res[i]);
}
*/
