package GeneticAlgorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.json.simple.JSONObject;

import preparation.SplitingText;

public class Outline {

	private static Algorithm algo;
	private SplitingText words = new SplitingText();

	// After The Final JSON Dictionary
	private List<String> jSONStrokes = new ArrayList<>();
	private List<String> jSONWords =  new ArrayList<>();
	private List<String> conflicts = new ArrayList<>();
	private List<String> outlineDict = new ArrayList<>();
	private List<String> dictionary = new ArrayList<>();
	
	public void createOutlineFile(String file, List<String> strokeDictionaryImproved) {
		try {
			PrintWriter writer = new PrintWriter(file, "UTF-8");
			for (int i = 0; i < strokeDictionaryImproved.size(); i++) {
				writer.println(strokeDictionaryImproved.get(i));
			}
			writer.close();
		} catch (IOException e) {
		}
	}

	private void createJSONOutline(String outline, String dictionary, String jsonDic) throws IOException {
		FileInputStream fis1 = new FileInputStream(dictionary);
		BufferedReader br1 = new BufferedReader(new InputStreamReader(fis1));

		FileInputStream fis2 = new FileInputStream(outline);
		BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));

		String strLine1;
		String strLine2;

		// FileWriter fw = new FileWriter(jsonDic);
		FileOutputStream fos = new FileOutputStream(jsonDic);
		BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(fos));
		fw.write("{");
		while (((strLine1 = br1.readLine()) != null) && ((strLine2 = br2.readLine()) != null)) {
			fw.write("\"");
			fw.write(strLine2);
			fw.write("\"");
			fw.write(":");
			fw.write("\"");
			fw.write(strLine1);
			fw.write("\"");
			fw.write(",");
			fw.newLine();
		}
		// to avoid the last "," in the last line in the file
		fw.write("\"");
		fw.write("STKPWHRAOE*");
		fw.write("\"");
		fw.write(":");
		fw.write("\"");
		fw.write("ههههه");
		fw.write("\"");
		fw.newLine();

		fw.write("}");
		fw.close();

	}

	/*
	 * Main Program for the Text
	 */

	public static void main(String[] args) throws IOException {
		Outline out = new Outline();
		algo = new Algorithm("dictionary.txt");
		// System.out.println(algo.getStrokeDictionaryImproved());
		out.createOutlineFile("outline.txt", algo.getStrokeDictionaryImproved());

		// Write into a file, the number of strokes for every word
		List<Integer> nbrStrokes = out.computeNumberOfStrokes("outline.txt");
		out.writeIntoFileNbrStrokes(nbrStrokes);
		
		/*
		 * Writing Counting data into a FILE
		 */
		FileOutputStream fosCounting = new FileOutputStream("strokesAppearenceCounting.txt");
		PrintWriter pwCounting = new PrintWriter(new OutputStreamWriter(fosCounting));
		pwCounting.println("***** Dictionary Strokes Number Counting : *******");
		
		// Count from that list (nbrStrokes) the apprearence of all numbers
		//System.out.println("***** Dictionary Strokes Number Counting : *******");
		Set<Integer> mySet = new HashSet<Integer>(nbrStrokes);
		for (Integer s : mySet) {
			//System.out.println(	"[" + s + " Strokes]" + " Is Appearing : " + Collections.frequency(nbrStrokes, s) + " Times.");
			pwCounting.println("[" + s + " Strokes]" + " Is Appearing : " + Collections.frequency(nbrStrokes, s) + " Times.");
		}
		pwCounting.flush();
		//System.out.println("______________________________________________");
		// Create A JSON FILE Containing all the words and it's corresponding
		// strokes
		out.createJSONOutline("outline.txt", "dictionary.txt", "jsonDictionary.json");

		// Use the splittingWords class, get the text and the words ==>
		// The translate the text into words
		out.words.readFileParagraphs("bookk.txt");
		out.words.paragIntoSentences();
		out.words.splitWords();
		// System.out.println(out.words.getWordsList());

		// Insert all words into a file
		out.insetWords(out.words.getWordsList());

		// Translate the text into strokes
		List<String> strokes = out.translateTextIntoStrokes(out.words.getWordsList());
		out.createOutlineFile("translatedText.txt", strokes);

		// Delete all the "null" string in the translated text into strokes
		out.deleteNULL("translatedText.txt");

		// Save the best entity into a file
		try (FileOutputStream fos = new FileOutputStream("bestEntity.txt");
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));) {
			pw.println(algo.getBestEntity());
			pw.flush();
		}
		
		
		// all conflicts From File into a List
		Scanner s = new Scanner(new File("Conflicts.txt"));
		while (s.hasNext()){
		    out.conflicts.add(s.next());
		}
		s.close();
		// All Outline dictionary from FILE into List
		Scanner s1 = new Scanner(new File("outline.txt"));
		while (s1.hasNext()){
		    out.outlineDict.add(s1.next());
		}
		s1.close();
		// All Arabic translations from dictionary from fil into List
		Scanner s2 = new Scanner(new File("dictionary.txt"));
		while (s2.hasNext()){
			out.dictionary.add(s2.next());
		}
		s2.close();
		// Write conflicts and translations into file
		FileOutputStream fosTranslation = new FileOutputStream("conflictsTranslations.txt");
		PrintWriter pwTranslation = new PrintWriter(new OutputStreamWriter(fosTranslation));
		List<Integer>indexes;
		for(int i=0;i<out.conflicts.size();i++){
			String stroke = out.conflicts.get(i);
			indexes=new ArrayList<>();
			for(int j=0;j<out.outlineDict.size();j++){
				if(out.outlineDict.get(j).equals(stroke)){
					indexes.add(j);
				}
			}
			pwTranslation.println("["+stroke+"], has Translation conflict with :");
			for(int k=0;k<indexes.size();k++){
				pwTranslation.println(out.dictionary.get(indexes.get(k)));
			}
			pwTranslation.println("_________________________________");
		}
		
		/*
		String stroke = out.conflicts.get(0);
		for(int i=0; i<out.outlineDict.size();i++){
			if(out.outlineDict.get(i).equals(stroke)){
				System.out.print((i+1)+" , ");
			}
		}
		*/
		
	}

	/*
	 * Delete all the "null" string values in the textfile of the strokes
	 * representation
	 */
	public void deleteNULL(String fileText) {
		File file = new File(fileText);
		File temp;
		try {
			temp = File.createTempFile("fileTemp", ".txt", file.getParentFile());
			String delete = "null";
			String delete1 = "null/";
			String charset = "UTF-8";
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(temp), charset));
			for (String line; (line = reader.readLine()) != null;) {
				line = line.replace(delete, "");
				line = line.replace(delete1, "");
				writer.println(line);

			}
			reader.close();
			writer.close();
			file.delete();
			temp.renameTo(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void writeIntoFileNbrStrokes(List<Integer> nbrStrokes) throws IOException {
		PrintWriter pw = new PrintWriter(new FileOutputStream("strokesNumbers.txt"));
		for (Integer nbr : nbrStrokes)
			pw.println(nbr); // call toString() on club, like club.toString()
		pw.close();
	}

	public List<Integer> computeNumberOfStrokes(String file) throws IOException {
		List<String> listOfStrokes = new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		listOfStrokes = Files.readAllLines(new File(file).toPath(), Charset.defaultCharset());
		for (int i = 0; i < listOfStrokes.size(); i++) {
			int nbr = 0;
			for (int j = 0; j < listOfStrokes.get(i).length(); j++) {
				if (listOfStrokes.get(i).charAt(j) == '/') {
					nbr += 1;
				}
			}
			list.add(nbr + 1);
		}

		return list;
	}
	
	public List<String> getJsonSTROKES() throws FileNotFoundException{
		List<String>jSONStrokes = new ArrayList<>();
		Scanner s = new Scanner(new File("outline.txt"));
		while (s.hasNext()){
			jSONStrokes.add(s.next());
		}
		s.close();
		return jSONStrokes;
	}
	
	public List<String> getJsonWords() throws FileNotFoundException{
		List<String>jsonWords = new ArrayList<>();
		Scanner s = new Scanner(new File("dictionary.txt"));
		while (s.hasNext()){
			jsonWords.add(s.next());
		}
		s.close();
		return jsonWords;
	}

	private void insetWords(ArrayList<String> wordsList) throws IOException {
		File fout = new File("wordsFromText.txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

		for (int i = 0; i < wordsList.size(); i++) {
			if (wordsList.get(i) != null) {
				bw.write(wordsList.get(i));
				bw.newLine();
			}
		}

	}

	private List<String> translateTextIntoStrokes(ArrayList<String> words) throws IOException {
		Algorithm algoText = new Algorithm("wordsFromText.txt", algo.getBestEntity());

		List<String> strokes = new ArrayList<>();

		strokes = algoText.getStrokeDictionaryImproved();

		return strokes;
	}
	//////////////////////////////////////////////////////////////
	/*
	 * Other way of creating the JSON file
	 */
	/*
	 * public void createJSONOutline(String outline, String dictionary, String
	 * jsonDict) throws IOException { JSONObject json = new JSONObject();
	 * 
	 * FileInputStream fis1 =new FileInputStream(dictionary); BufferedReader br1
	 * = new BufferedReader(new InputStreamReader(fis1));
	 * 
	 * FileInputStream fis2 =new FileInputStream(outline); BufferedReader br2 =
	 * new BufferedReader(new InputStreamReader(fis2));
	 * 
	 * String strLine1; String strLine2;
	 * 
	 * while(((strLine1 = br1.readLine()) != null) && ((strLine2 =
	 * br2.readLine()) != null)){ json.put(strLine1, strLine2); }
	 * 
	 * FileWriter fw = new FileWriter(jsonDict); fw.write(json.toJSONString());
	 * 
	 * fw.flush(); fw.close(); }
	 */

}
