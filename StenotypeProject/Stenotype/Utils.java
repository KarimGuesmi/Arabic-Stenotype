package Stenotype;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;


// Read Txt files, and add WORDS to the corresponding STROKES
public class Utils {
	public ArrayList<Word> readDictionary(String lesDictionaryFilePath, String chdDictionaryFilePath,boolean debug){
		String tempLine=null;
		BufferedReader lesReader=null;
		BufferedReader chdReader=null;
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<String> strokes = new ArrayList<String>();
		ArrayList<Word> dictionary = new ArrayList<Word>();
		
		//////// Read & Store WORDS
		try {
			Reader reader = new FileReader(lesDictionaryFilePath);
			lesReader = new BufferedReader(reader);
			while((tempLine = lesReader.readLine())!=null){
				if(tempLine.length() != 0 && tempLine.charAt(0)=='<' || tempLine.trim().length() == 0){
					continue;
				}
				String[] newWords = tempLine.split(" ");
				for(String word:newWords){
					words.add(word);
				}
			}
		} catch (Exception e) {
			System.out.println("Error while Reading .lesDictionary "+e.getMessage());
		}
		
		
		
		if(lesReader !=null){
			try{
				lesReader.close();
			}catch(Exception e){
				
			}
		}
		
		
		//// Read & Store STROKES
		try {
			Reader reader = new FileReader(chdDictionaryFilePath);
			while((tempLine=chdReader.readLine())!=null){
				if(tempLine.length()!=0 && tempLine.charAt(0)=='<' || tempLine.trim().length()==0){
					continue;
				}
				String[] newStrokes = tempLine.split(" ");
				for(String stroke:newStrokes){
					strokes.add(stroke);
				}
			}
		} catch (Exception e) {
			System.out.println("Error while reading .chd Dicitonary"+e.getMessage());
		}
		
		if(chdReader!=null){
			try{
				chdReader.close();
			}catch(Exception e){
				
			}
		}
		
		
		///////// Store WORDS & STROKES in a dictionary List
		if(words !=null && strokes!=null){
			for(int i=0;i<words.size();i++){
				Word word = new Word();
				word.word = words.get(i);
				word.stroke = strokes.get(i);
				dictionary.add(word);
			}
		}
		
		// debug info
		if(debug){
			System.out.println("Current lesson contains "+ words.size()+" words and "+strokes.size()+" chords.");
		}
		
		
		return dictionary;
	}
	
	////////////////////////////////////////////////////////
	// Read lesson backlist and adding backlist words to the returned list
	public ArrayList<String> readBackList(String blkDictionaryFilePath){
		ArrayList<String> wordBackList=new ArrayList<String>();
		String tempLine = null;
		BufferedReader blkReader = null;
		try{
			Reader reader = new FileReader(blkDictionaryFilePath);
			blkReader = new BufferedReader(reader);
			while((tempLine=blkReader.readLine())!=null){
				if(tempLine.trim().length()==0){
					continue;
				}
				String[] words =tempLine.split(" ");
				for(String word:words){
					wordBackList.add(word);
				}
			}
		}catch(Exception e){
			System.out.println("Warning "+e.getMessage());
		}
		
		if(blkReader!=null){
			try{
				blkReader.close();
			}catch(Exception e){
				
			}
		}
		
		return wordBackList;
		
	}
	
	/////////////////////////////////
	// Store backlist data in a given file
	public void writeBackList(ArrayList<String>wordsBacklist, String blkDictionaryFilePath){
		BufferedWriter blkWriter = null;
		StringBuilder  backList = new StringBuilder();
		for(String word:wordsBacklist){
			backList.append(word+" ");
		}
		String fileContent = backList.toString();
		fileContent = fileContent.substring(0,fileContent.length()-1);
		try{
			Writer writer = new FileWriter(blkDictionaryFilePath);
			blkWriter = new BufferedWriter(writer);
			blkWriter.write(fileContent);
		}catch(Exception e){
			System.out.println("Error while writing backlist file "+e.getMessage());
		}
		if(blkWriter!=null){
			try{
				blkWriter.close();
			}catch(Exception e){
				
			}
		}
	}
	
	
	/////////////////////////////
	// Initialize plover  log Reader and go to the End of file
	
	public BufferedReader readEndOfFile(String logFilePath){
		BufferedReader logReader=null;
		String line=null;
		String tempLine = null;
		
		try{
			Reader reader = new FileReader(logFilePath);
			logReader  = new BufferedReader(reader);
			while((tempLine = logReader.readLine()) != null){
				line = tempLine;
			}
		}catch(Exception e){
			System.out.println("Error while reading plover log file "+e.getMessage());
		}
		
		
		return logReader;
		
	}
	
	/////////////////////////////////
	//Get Next Stroke from plover logFile
	public Stroke getNextStroke(BufferedReader logReader){
		Stroke stroke = new Stroke();
		String line = null;
		try{
			line = logReader.readLine();
			int indexOfTrans1 = -1;
			if(line!= null){
				indexOfTrans1 = line.indexOf("Translation");
			}if(line!=null && indexOfTrans1>-1){
				boolean isMultipleWorld  = false;
				int indexOfLast = 1 + line.indexOf(",) : ");
				if(indexOfLast<1){
					isMultipleWorld = true;
					indexOfLast = line.indexOf(" : ");
				}
				if(indexOfLast==24){
					stroke.isDelete=false;
				}else{
					stroke.isDelete=true;
				}
				stroke.stroke = getStroke(line, indexOfTrans1+14, indexOfLast-2);
				stroke.word = line.substring(indexOfLast+(isMultipleWorld?2:3),line.length()-1 );
				return stroke;
			}else{
				return null;
			}
		}catch(Exception e){
			System.out.println("Error while reading stroke from plover log file"+e.getMessage());
		}
		return null;
	}

	
	//////////////////////////
	//Format Strokes and multiples strokes for a single word
	private String getStroke(String line, int start, int end) {
		String result = "";
		String strokeLine = line.substring(start,end);
		String[] strokes = strokeLine.split("', '");
		for(String stroke:strokes){
			result+=stroke+"/";
		}
		return result.substring(0, result.length()-1);
	}
	
	
}
