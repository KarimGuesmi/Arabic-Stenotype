import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;

public class Suffixes {

	public static void main(String[] args) throws IOException {
		JSONObject obj = new JSONObject();
		
		obj.put("-UZ","{^ا}");
		obj.put("-SU","{^نا}");
		obj.put("L","{^ت}");
		obj.put("-ULS","{^تما}");
		obj.put("K-L","{^تم}");
		obj.put("S-L","{^تن}");
		obj.put("-EU","{^وا}");
		obj.put("S","{^ن}");
		
			FileInputStream fstream1;
			try {
				fstream1 = new FileInputStream("Suffixes.txt");
				BufferedReader br1 = new BufferedReader(new InputStreamReader(fstream1));
				
				FileInputStream fstream2 = new FileInputStream("BeforeSuffixes.txt");
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
				FileWriter file = new FileWriter("outSuffixes.json");
				file.write(obj.toJSONString());
				
				file.flush();
				file.close();

			} catch (IOException e) {
				e.printStackTrace();
			}


	}

}
