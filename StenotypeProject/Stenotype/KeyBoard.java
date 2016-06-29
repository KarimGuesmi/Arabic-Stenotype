package Stenotype;

public class KeyBoard {
	boolean showKeys;
	char[][] stenoRows = {
			{'ن','س','ق','د','*','ك','ع','ت','ب','ء'},
			{'ن','م','ر','ل','*','ض','ج','س','م','ل'},
			{'ى','ي','و','ا'}
	};
	
	String[][] queryRows = {
			{"Q","W","E","R","TY","U","I","O","P","[",},
			{"A","S","D","F","GH","J","K","L",";","'",},
			{"C","V","N","M",}
	};
	
	//State Variables
	String lastStroke="";
	boolean[][] pressedKeys;
	
	//KeyBoard Position
	int x,y;
	
	//Constructor
	public KeyBoard(int x, int y){
		super();
		this.x=x;
		this.y=y;
	}
	
	// draw keyboard
	public void draw(String stroke){
		if(lastStroke != stroke){
			lastStroke = stroke;
		}
		// get&set Pressedkeys[][]
		pressedKeys = getPressedKeys(stroke);
	}

	//Return the pressed keys corresponding to the given stroke
	public boolean[][] getPressedKeys(String stroke) {
		boolean[][] result = new boolean[3][10];
		int index = stroke.indexOf("ى");
		if(index == -1){
			index = stroke.indexOf("ي");
		}
		if(index == -1){
			index = stroke.indexOf("-");
		}
		if(index == -1){
			index = stroke.indexOf("*");
		}
		if(index == -1){
			index = stroke.indexOf("و");
		}
		if(index == -1){
			index = stroke.indexOf("ا");
		}
		//The chord contains only left side consonants
		if(index ==-1){
			setLeftConsonants(stroke, result);
		}else 
			// The chord contains both left side consonants and other keys
			if(index>0){ 
			setLeftConsonants(stroke.substring(0,index),result);
			setVowelsAndRightConsonants(stroke.substring(index, stroke.length()),result);
		}else{
			// The chord contains other keys
			setVowelsAndRightConsonants(stroke, result);
		}
		return result;
	}

	// Read all vowels, right consonants and '*' and set the corresponding pressed keys ===> In the Array Result
	public void setVowelsAndRightConsonants(String substroke, boolean[][] result) {
		for(int i=0; i<substroke.length();i++){
			char keyStroke = substroke.charAt(i);
			if(keyStroke == '*'){
				result[0][4] = true;
				result[1][4] = true;
			}else if(keyStroke == 'ى'){
				result[2][0] = true;
			}else if(keyStroke == 'ي'){
				result[2][1] = true;
			}else if(keyStroke == 'و'){
				result[2][2] = true;
			}else if(keyStroke == 'ا'){
				result[2][3] = true;
			}else if(keyStroke == 'ك'){
				result[0][5] = true;
			}else if(keyStroke == 'ض'){
				result[1][5] = true;
			}else if(keyStroke == 'ع'){
				result[0][6] = true;
			}else if(keyStroke == 'ج'){
				result[1][6] = true;
			}else if(keyStroke == 'ت'){
				result[0][7] = true;
			}else if(keyStroke == 'س'){
				result[1][7] = true;
			}else if(keyStroke == 'ب'){
				result[0][8] = true;
			}else if(keyStroke == 'م'){
				result[1][8] = true;
			}else if(keyStroke == 'ء'){
				result[0][9] = true;
			}else if(keyStroke == 'ل'){
				result[1][9] = true;
			}
			
		}
		
	}

	// Read all left consonants and set the corresponding pressed keys in the Result Array
	public void setLeftConsonants(String substroke, boolean[][] result) {
		for (int i = 0; i < substroke.length(); i++) {
		    char stenoKey = substroke.charAt(i);
		    if (stenoKey == 'ن') {
		      result[0][0] = true;
		      result[1][0] = true;
		    }
		    else if (stenoKey == 'س') {
		      result[0][1] = true;
		    }
		    else if (stenoKey == 'م') {
		      result[1][1] = true;
		    }
		    else if (stenoKey == 'ق') {
		      result[0][2] = true;
		    }
		    else if (stenoKey == 'ر') {
		      result[1][2] = true;
		    }
		    else if (stenoKey == 'د') {
		      result[0][3] = true;
		    }
		    else if (stenoKey == 'ل') {
		      result[1][3] = true;
		    }
		  }
	}
	
}
