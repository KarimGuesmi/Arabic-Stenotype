import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;


public class Briefs {

	public static void main(String[] args) throws IOException {
		JSONObject obj = new JSONObject();
		
		FileInputStream fis1 =new FileInputStream("KeysOfKeys.txt");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(fis1));
		
		FileInputStream fis2 =new FileInputStream("TranslationOfKeys.txt");
		BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));
		
		String strLine1;
		String strLine2;
		
		while(((strLine1 = br1.readLine()) != null) && ((strLine2 = br2.readLine()) != null)){
			System.out.print(strLine1);
			System.out.print(" ");
			System.out.println(strLine2);
			obj.put(strLine1, strLine2);
		}
		
		FileWriter fw = new FileWriter("Keys.json.json");
		fw.write(obj.toJSONString());
		
		fw.flush();
		fw.close();
		
		
	}

}
