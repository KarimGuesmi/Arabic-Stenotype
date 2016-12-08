package Preparation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.BreakIterator;
import java.util.Locale;

public class ArabicText {
	public static void main(String[] args) throws IOException {

		
		//Import text from file , divide this text into sentences
		BufferedReader br = new BufferedReader(new FileReader("book.txt"));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		    //System.out.println(everything);
		    BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
		    iterator.setText(everything);
		    int start = iterator.first();
			for (int end = iterator.next();
			    end != BreakIterator.DONE;
			    start = end, end = iterator.next()) {
			  System.out.println(everything.substring(start,end));
			}
			
			// Dividing the imported sentences into words
			String[] words = everything.split("\\s");
			for (int i = 0; i < words.length; i++) {
			    words[i] = words[i].replaceAll("[[ ]*|[,]*|[\\.]*|[:]*|[/]*|[!]*|[?]*|[+]*]+", "");
			    System.out.println(words[i]);
			}
		} finally {
		    br.close();
		}
		
		// Dividing a simple sentence to words and put them into an ARRAY called words
		String s = "هذا تحقق, و ان لم فلا , ههه";
		String[] words = s.split("\\s");
		for (int i = 0; i < words.length; i++) {
		    words[i] = words[i].replaceAll("[[ ]*|[,]*|[\\.]*|[:]*|[/]*|[!]*|[?]*|[+]*]+", "");
		    System.out.println(words[i]);
		}
		
	}

}
