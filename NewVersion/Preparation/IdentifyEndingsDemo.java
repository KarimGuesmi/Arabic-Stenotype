package preparation;

import java.util.Arrays;

public class IdentifyEndingsDemo {

	private String verb;
	private String noun;
	
	public static boolean contains( String str1, String str2 ) {
		  str1 = str1 == null ? "" : str1;
		  str2 = str2 == null ? "" : str2;
		  return str1.contains( str2 );
		}
	
	
	public static void main(String[] args) {
		String str1="karim";
		String str2="ar";
		if( contains( str1, str2 ) ) {
			 System.out.println("The word in the text is :"+str1);
			 System.out.println("The normal word is : "+str1+""+str2);
			 System.out.println("the ending is : "+str2);
			 System.out.println( "Found (" + str2 + ") within (" + str1 + ") ." );
			}
	}

}
