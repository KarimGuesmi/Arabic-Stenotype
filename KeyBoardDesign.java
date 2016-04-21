
public class KeyBoardDesign {

	int x, y;
	boolean showQuerty;
	
	char[][] stenoRows = {
			{'ن','س','ق','د','*','ك','ع','ت','ب','ء'},
			{'ن','م','ر','ل','*','ض','ج','س','م','ل'},
			{'ى','ي','و','ا'}
	};
	
	String quertyRows[][] ={
			{"Q","W","E","R","TY","U","I","O","P","[",},
			{"A","S","D","F","GH","J","K","L",";","'",},
			{"C","V","N","M",}
	};
	
	int keySizeX = 50;
	int keySizeY = 50;
	
	String lastStroke = "";
	
	boolean [][] pressedKeys;

	public Keyboard(int x, int y, boolean showKeeboardQuerty) {
		super();
		this.x = x;
		this.y = y;
		this.showQuerty = showKeeboardQuerty;
	}
	
	
	void draw(String stroke){
		if(lastStroke != stroke){
			lastStroke = stroke;
			pressedKeys = getPressedKeys(stroke);
		}
		drawRow(0,10,x,y);	//top row
		drawRow(1,10,x,y+(keySizeY+10));	//bottom row
		drawRow(4,2, (int)(x+(keySizeX+10)*2.5), y+(keySizeY+10)*2);	//vowels row
	}
	
	void drawRow(int rowIndex, int rowSize, int rowX, int rowY){
		for(int i=0; i< rowSize; i++){
			fill(pressedKeys[rowIndex][i] ? 0 : 75);
			rect(rowX + (keySizeX+10)*i, rowY, keySizeX, keySizeY, 5);
			fill(255);
			textAlign(CENTER);
			text(stenoRows[rowIndex][i], rowX + keySizeX / 2 + (keySizeX + 10) * i, rowY + (keySizeY / 2 + 10));
			if(showQuerty){
				fill(40);
				text(qwertyRows[rowIndex][i], rowX + keySizeX / 2 + (keySizeX + 10) * i - 15, rowY + (keySizeY / 2 + 10) - 15);
			}
		}
	}
	
	boolean[][] getPressedKeys(String stroke){
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
		
		if(index ==-1){
			setLeftConsonants(stroke, result);
		}else if(index>0){
			setLeftConsonants(stroke.substring(0,index),result);
			setVowelsAndRightConsonants(stroke.substring(index, stroke.length()),result);
		}else{
			setVowelsAndRightConsonants(stroke, result);
		}
		return result;
	}
	
	
	void setVowelsAndRightConsonants(String substroke, boolean[][]result){
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
	
	
	
	void setLeftConsonants(String substroke, boolean[][] result){
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
