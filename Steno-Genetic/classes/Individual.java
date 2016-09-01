package classes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class Individual {
	private String filePath;
	private String letterTaped;
	private ArrayList<String> listOfStrings;
	private ArrayList<String>listOfChars;
	
	public Individual() {
		super();
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	public String wordFromBook(String file){
		
		try {
			
			FileInputStream fis= new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis,"utf8"));
			String line;
			//System.out.println(line);
			ArrayList<String> listOfStrings= new ArrayList<String>();
			while((line=br.readLine()) != null){
				listOfStrings.add(line);
			}
			
			String wordFromList = listOfStrings.get(0);
			
			
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	
}
