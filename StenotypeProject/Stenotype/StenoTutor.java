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
	boolean debud =false;

}
