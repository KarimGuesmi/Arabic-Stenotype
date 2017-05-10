package preparation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrequencyTable {
	private String filename;
	private LinkedList<String> keywordsList;
	private TreeMap<String, Integer> freqMap;

	FrequencyTable(String filename) {
		this.filename = filename;
		freqMap = new TreeMap<String, Integer>();
		keywordsList = new LinkedList<String>();
	}

	public void readWords() {
		Pattern pattern = Pattern.compile("\\w");
		try {

			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String strLine;
			while ((strLine = br.readLine()) != null) {
				// split a line by spaces so we get words
				String[] words = strLine.split("[[ ]*|[,]*|[\\.]*|[:]*|[/]*|[!]*|[?]*|[+]*]+");
				for (String word : words) {
					// remove all symbols except underscore
					Matcher mat = pattern.matcher(word);
					word = mat.replaceAll("");
					// add words to the list
					keywordsList.add(word.toLowerCase());
				}
			}

			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void countWords() {
		int count = 1;
		String word = "";
		for (int i = 0; i < keywordsList.size(); i++) {
			word = keywordsList.get(i);
			for (int j = i + 1; j < keywordsList.size(); j++) {
				if (word.equals(keywordsList.get(j))) {
					count++; // increase the number of duplicate words
				}
			}
			// add the word and its frequency to the TreeMap
			addToMap(word, count);
			// reset the count variable
			count = 1;
		}

	}

	public void addToMap(String word, int count) {
		// place keyword and its frequency in TreeMap
		if (!freqMap.containsKey(word) && word.length() >= 1) {
			freqMap.put(word, count);
		}

	}

	public void showResult() {
		Set<String> keys = freqMap.keySet();
		int numWord = keys.size();
		Iterator<Map.Entry<String, Integer>> iterator = freqMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Integer> word = iterator.next();
			System.out.format("%-20s%-5d%-2s\n", word.getKey(), word.getValue(), 100 * word.getValue()
					/ numWord + "%");
		}
	}

	public void processCounting() {
		Thread backprocess = new Thread() {
			public void run() {
				readWords();
				System.out.println("Done reading.");
				countWords();
				System.out.println("Done counting.");
				showResult();
			}
		};
		backprocess.start();
	}

	public static void main(String[] args) {
			FrequencyTable FT = new FrequencyTable("Bookk.txt");
			FT.processCounting();
	}
}
