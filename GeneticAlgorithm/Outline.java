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
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import preparation.SplitingText;

public class Outline {
	
	private static Algorithm algo;
	private SplitingText words = new SplitingText();
	
	public void createOutlineFile(String file, List<String> strokeDictionaryImproved) {
		try{
		    PrintWriter writer = new PrintWriter(file, "UTF-8");
		    for(int i=0; i<strokeDictionaryImproved.size();i++){
		    	writer.println(strokeDictionaryImproved.get(i));
		    }
		    writer.close();
		} catch (IOException e) {
		}
	}
	
	/*
	 * 
	 */
	/*
	public void createJSONOutline(String outline, String dictionary, String jsonDict) throws IOException {
		JSONObject json = new JSONObject();
		
		FileInputStream fis1 =new FileInputStream(dictionary);
		BufferedReader br1 = new BufferedReader(new InputStreamReader(fis1));
		
		FileInputStream fis2 =new FileInputStream(outline);
		BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));
		
		String strLine1;
		String strLine2;
		
		while(((strLine1 = br1.readLine()) != null) && ((strLine2 = br2.readLine()) != null)){
			json.put(strLine1, strLine2);
		}
		
		FileWriter fw = new FileWriter(jsonDict);
		fw.write(json.toJSONString());
		
		fw.flush();
		fw.close();
	}
	*/
	
	private void createJSONOutline(String outline, String dictionary, String jsonDic) throws IOException {
		FileInputStream fis1 =new FileInputStream(dictionary);
		BufferedReader br1 = new BufferedReader(new InputStreamReader(fis1));
		
		FileInputStream fis2 =new FileInputStream(outline);
		BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));
		
		String strLine1;
		String strLine2;
		
		//FileWriter fw = new FileWriter(jsonDic);
		FileOutputStream fos = new FileOutputStream(jsonDic);
		BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(fos));
		fw.write("{");
		while(((strLine1 = br1.readLine()) != null) && ((strLine2 = br2.readLine()) != null)){
			fw.write("\"");fw.write(strLine1);fw.write("\"");fw.write(":");
			fw.write("\"");fw.write(strLine2);fw.write("\"");fw.write(",");fw.newLine();
		}
		// to avoid the last "," in the last line in the file
		fw.write("\"");fw.write("رررر");fw.write("\"");fw.write(":");
		fw.write("\"");fw.write("STKPWHRAO");fw.write("\"");
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
		//System.out.println(algo.getStrokeDictionaryImproved());
		out.createOutlineFile("outline.txt", algo.getStrokeDictionaryImproved());
		
		// Create A JSON FILE Containing all the words and it's corresponding strokes
		out.createJSONOutline("outline.txt", "dictionary.txt", "jsonDictionary.json");
		
		// Use the splittingWords class, get the text and the words ==> 
		// The translate the text into words
		out.words.readFileParagraphs("bookk.txt");
		out.words.paragIntoSentences();
		out.words.splitWords();
		//System.out.println(out.words.getWordsList());
		
		// Insert all words into a file
		out.insetWords(out.words.getWordsList());

		//Translate the text into strokes
		List<String>strokes = out.translateTextIntoStrokes(out.words.getWordsList());
		out.createOutlineFile("translatedText.txt", strokes);
	}

	private void insetWords(ArrayList<String> wordsList) throws IOException {
		File fout = new File("wordsFromText.txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		for(int i=0; i<wordsList.size();i++){
			if(wordsList.get(i)!=null){
				bw.write(wordsList.get(i));
				bw.newLine();
			}
		}
		
	}

	private List<String> translateTextIntoStrokes(ArrayList<String> words) throws IOException {
		Algorithm algo = new Algorithm("wordsFromText.txt");
		List<String> strokes = new ArrayList<>();
		
		strokes = algo.getStrokeDictionaryImproved();
		
		return strokes;
	}

	

}