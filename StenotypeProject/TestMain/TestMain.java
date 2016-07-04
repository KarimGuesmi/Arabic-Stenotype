package TestMain;
import Stenotype.*;

public class TestMain {


	public static void main(String[] args) {
		String lessonName="briefs";
		StenoTutor steno = new StenoTutor();
		steno.setup();
		steno.draw();
		
		int x=1;
		int y=2;
		String stroke = "M";
		
		
		KeyBoard kb = new KeyBoard(x, y);
		kb.draw(stroke);
		System.out.println(kb.getPressedKeys(stroke));

		int size=4;
		NextWordsBuffer nextWordB = new NextWordsBuffer(size);
		nextWordB.fillNewLine(0);
		long[] penaltyLimit = nextWordB.calculatePenaltyLimits();
	}

}
