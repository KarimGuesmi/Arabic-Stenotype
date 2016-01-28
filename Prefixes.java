import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.json.simple.JSONObject;

public class Prefixes {

	public static void main(String[] args) throws IOException {
		JSONObject obj = new JSONObject();
		
		obj.put("O","{ي^}");
		obj.put("S","{ن^}");
		obj.put("-L","{ت^}");
		
			FileInputStream fstream1;
			try {
				fstream1 = new FileInputStream("prefixes.txt");
				BufferedReader br1 = new BufferedReader(new InputStreamReader(fstream1));
				
				FileInputStream fstream2 = new FileInputStream("afterPrefixes.txt");
				BufferedReader br2 = new BufferedReader(new InputStreamReader(fstream2));
				
				String strLine1;
				String strLine2;
				
				while (((strLine1 = br1.readLine()) != null) && (strLine2=br2.readLine())!=null)   {
					  System.out.print (strLine1);
					  System.out.print (" ");
					  System.out.println (strLine2);
					  obj.put(strLine1, strLine2);
					  
					  
					}

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		
			try {
				FileWriter file = new FileWriter("out.json");
				file.write(obj.toJSONString());
				
				file.flush();
				file.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		


	}

}
