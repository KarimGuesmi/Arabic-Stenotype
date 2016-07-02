package TestMain;
import Stenotype.*;

public class TestMain {


	public static void main(String[] args) {
		int x=1;
		int y=2;
		String stroke = "M";
		
		
		KeyBoard kb = new KeyBoard(x, y);
		kb.draw(stroke);
		System.out.println(kb.getPressedKeys(stroke));

	}

}
