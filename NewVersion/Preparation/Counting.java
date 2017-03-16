package preparation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Couting {
	public static void main(String[] args) throws IOException {
	
		//String text = "the quick brown fox jumps fox fox over the lazy dog brown";
		BufferedReader br = new BufferedReader(new FileReader("bookk.txt"));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		
		while (line != null) {
			sb.append(line);
			sb.append(System.lineSeparator());
			line = br.readLine();
			
		}
		
		String text = sb.toString();
		
        String[] keys = text.split(" ");
        String[] uniqueKeys;
        int count = 0;
        //System.out.println(text);
        uniqueKeys = getUniqueKeys(keys);

        for(String key: uniqueKeys)
        {
            if(null == key)
            {
                break;
            }           
            for(String s : keys)
            {
                if(key.equals(s))
                {
                    count++;
                }               
            }
            System.out.println("Number of ["+key+"] is : "+count);
            count=0;
        }
	}
	
	private static String[] getUniqueKeys(String[] keys)
    {
        String[] uniqueKeys = new String[keys.length];

        uniqueKeys[0] = keys[0];
        int uniqueKeyIndex = 1;
        boolean keyAlreadyExists = false;

        for(int i=1; i<keys.length ; i++)
        {
            for(int j=0; j<=uniqueKeyIndex; j++)
            {
                if(keys[i].equals(uniqueKeys[j]))
                {
                    keyAlreadyExists = true;
                }
            }           

            if(!keyAlreadyExists)
            {
                uniqueKeys[uniqueKeyIndex] = keys[i];
                uniqueKeyIndex++;               
            }
            keyAlreadyExists = false;
        }       
        return uniqueKeys;
    }
}
