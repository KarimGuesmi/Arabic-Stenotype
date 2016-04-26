import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Word {
	
	static int defaultGeneLength = 500; 
	
	Map<String, String> genes = new HashMap<>();
	
	public void generateWords() throws IOException{
		Random rng=new Random();
		for(int i=0; i<=defaultGeneLength; i++){
			//byte gene  = (byte)Math.round(Math.random());			
			String translation = translate();
			int length = 6;
			String stroke = generateString(rng, translation, length);
			genes.put(stroke, translation);
		}
	}

	public static String generateString(Random rng, String characters, int length)
	{
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(rng.nextInt(characters.length()));
	    }
	    return new String(text);
	}
	

	private String translate() throws IOException {
			BufferedReader br = new BufferedReader(new FileReader("file.txt")); 
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    while(line != null){
		    	
		    }
		    return line;
	}
	
}
