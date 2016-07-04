package Stenotype;

import java.io.BufferedReader;
import java.util.ArrayList;

public class StenoTutor {
	String lessonName;
	int startBaseWord;
	int incrementWords;
	int minLevelUpWordWpm;
	int minLevelUpTotalWpm;
	int wordAvgSamples;
	int wordStartAvgWpm;
	boolean isSingleWordBuffer;
	boolean isSoundEnabled;
	boolean isAnnounceLevels;
	int wpmReportingPeriod;
	boolean isWordDictationEnabled;
	boolean showKeyBoard;
	boolean showKeyBoardQuerty;
	boolean showKeyBoardChord;
	
	Utils utils = new Utils();
	BufferedReader logReader = null;
	
	//
	final String winLogBasePath = "/AppData/Local/plover/plover/plover.log";
	final String xLogBasePath = "/.config/plover/plover.log";
	//
	
	// Path to Log file
	String logFilePath;
	
	// Path to Lessons
	String lesDictionaryFilePath;
	String chdDictionaryFilePath;
	String blkDictionaryFilePath;
	
	KeyBoard keyBoard;
	
	String buffer = "";
	NextWordsBuffer nextWordBuffer;
	
	// Dictionary of current Lesson
	ArrayList<Word> dictionary;
	// stats of current lesson for each word
	ArrayList<WordStats> wordStats = new ArrayList<WordStats>();
	
	ArrayList<String> wordsBlackList = new ArrayList<String>();
	
	int currentLevel=0;
	int unlockedWords=0;
	int currentWordIndex=0;
	boolean isLessonStarted=false;
	boolean isLessonPaused=false;
	long lessonStartTime;
	long lastTypeWordTime;
	long lastPauseTime;
	int typeWords=0;
	int wortsWordWpm=0;
	String worstWord;
	
	// Storing the previous strokes
	Stroke previousStroke = new Stroke();
	boolean ctrlKeyRelease=false;
	boolean tabKeyReleased=false;
	boolean debug =false;

	
	public void setup(){
		readSessionConfig();
		findPloverLog();
		lesDictionaryFilePath = "/lessons/" + lessonName + ".les";
		chdDictionaryFilePath = "/lessons/" + lessonName + ".chd";
		blkDictionaryFilePath = "/lessons/" + lessonName + ".blk";
		dictionary = utils.readDictionary(lesDictionaryFilePath, chdDictionaryFilePath, debug);
		wordsBlackList = utils.readBackList(blkDictionaryFilePath);
		applyStartBacklist();
		
		// Initialize word stats
		for(int i=0; i<dictionary.size();i++){
			wordStats.add(new WordStats(wordStartAvgWpm, wordAvgSamples));
		}
		
		// initialise target line buffer and set the next word index
		nextWordBuffer =  new NextWordsBuffer(700-180);
		currentWordIndex = nextWordBuffer.getCurrentWordIndex();
		keyBoard = new KeyBoard(50, 300);
		Stroke stroke = new Stroke();
		showTextInfo(stroke);
	}
	
	// Draw cycle
	public void draw(){
		if(ctrlKeyRelease){
			blackListCurrentWord();
		}
		//If tab Key released, pause & resume this session
		if(tabKeyReleased){
			tabKeyReleased=false;
		}
		// Read the next stroke from plover log
		Stroke stroke = utils.getNextStroke(logReader);
		
		// If stroke is not null, store it
		if(stroke!=null){
			previousStroke = null;
		}
		
		// If lesson started, add word start AVG time
		// In this case, The first word will not start with low penalty
		if(!isLessonStarted && buffer.length()>0){
			isLessonStarted=true;
			lessonStartTime = System.currentTimeMillis();
			lastTypeWordTime = lessonStartTime - ((long)60000.0f/wordStartAvgWpm);
			// Announce level 0
			announceCurrentLevel();
			
			// if WPM reporting is enabled , start it
			if(isSoundEnabled && wpmReportingPeriod>0){
				WpmReporter wpmReporter = new WpmReporter((long)wpmReportingPeriod*1000);
				wpmReporter.start();
			}
			checkBuffer(false);
			showTextInfo(stroke==null ? previousStroke:stroke);
			drawKeyboard();
			
		}
		
	
		// Line290
		
	}


	private void drawKeyboard() {
		// TODO Auto-generated method stub
		
	}

	private void checkBuffer(boolean b) {
		// TODO Auto-generated method stub
		
	}

	private void announceCurrentLevel() {
		// TODO Auto-generated method stub
		
	}

	private void blackListCurrentWord() {
		// TODO Auto-generated method stub
		
	}

	private void showTextInfo(Stroke stroke) {
		// TODO Auto-generated method stub
		
	}


	private void applyStartBacklist() {
		// TODO Auto-generated method stub
		
	}


	private void findPloverLog() {
		// TODO Auto-generated method stub
		
	}


	private void readSessionConfig() {
		// TODO Auto-generated method stub
		
	}
}
