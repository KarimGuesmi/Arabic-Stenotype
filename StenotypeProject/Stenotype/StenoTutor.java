package Stenotype;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.logging.Level;

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
	int worstWordWpm=0;
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
			
			
			// if WPM reporting is enabled , start it
			if(isSoundEnabled && wpmReportingPeriod>0){
				WpmReporter wpmReporter = new WpmReporter((long)wpmReportingPeriod*1000);
				wpmReporter.start();
			}
			checkBuffer(false);
			showTextInfo(stroke==null ? previousStroke:stroke);
			drawKeyboard();
			
		}
		
		
	}
	
	// Pause , resume session
	// This is the timing pause and resume when pressing keys
	public void tooglePause(){
		if(!isLessonStarted)return;
		if(isLessonPaused){
			long now = System.currentTimeMillis();
			long pauseTime = now - lastPauseTime;
			lastTypeWordTime +=pauseTime;
			isLessonPaused=false;
			
		}else{
			lastPauseTime = System.currentTimeMillis();
			isLessonPaused=true;
		}
	}
	

	private int max(int i, int j) {
		if(i>j){
		return i;}
		else{
			return j;
		}
	}

	private void drawKeyboard() {
		if(!showKeyBoard){
			return;
		}
		// If show chord is enabled ===> show the forst chord
		if(showKeyBoardChord){
			String[] chords = dictionary.get(currentWordIndex).stroke.split("/");
			keyBoard.draw(chords[0]);
		}else{
			keyBoard.draw("-");
		}
		
	}

	// IF the Input buffer match the current word store word stats  delegate to setnextwordindex()
	// Or If forcenextword is true
	// And compute the next word based on word stats
	// If levelup ===> unlock new word
	
	private void checkBuffer(boolean forceNextWord) {
		if(buffer.trim().equals(dictionary.get(currentWordIndex).word) || forceNextWord){
			buffer = ""; // Clear input buffer
			long typeTime = System.currentTimeMillis();
			wordStats.get(currentWordIndex).typeTime.add(typeTime-lastTypeWordTime);
			lastTypeWordTime = typeTime;
			typeWords++;
			checkLevelUp();
			currentWordIndex = nextWordBuffer.getNextWordIndex();
			updateWorstWord();
			
		}
		
	}
	
	
	
	// Update worst word WPM & String value
	private void updateWorstWord() {
		int worstWordIndex=0;
		int tempWorstWordWpm= 500;
		for(int i=0; i<startBaseWord+unlockedWords;i++){
			if(wordsBlackList.contains(dictionary.get(i).word)){
				continue;
			}
			WordStats stats = WordStats.get(i);
			int wpm = (int)stats.getAvgWmp();
			if(wpm<tempWorstWordWpm){
				worstWordIndex=i;
				tempWorstWordWpm=wpm;
			}
		}
		
		worstWordWpm=tempWorstWordWpm;
		worstWord=dictionary.get(worstWordIndex).word;
		
	}

	
	// If Level up  =====>  unlock New Words
	private void checkLevelUp() {
		if((int)(typeWords/(getElapsedTime()/60000.0f))<minLevelUpTotalWpm){
			return;
		}
		for(int i=0; i<startBaseWord+unlockedWords; i++){
			if(wordsBlackList.contains(dictionary.get(i).word)){
				continue;
			}
			if(wordStats.get(i).getAvgWmp()<minLevelUpWordWpm){
				return;
			}
		}
		LevelUp();
		
	}

	// Level up, unlock new word
	private void LevelUp() {
		int totalWords = startBaseWord + unlockedWords;
		int i = totalWords;
		unlockedWords += incrementWords;
		while(totalWords < startBaseWord+unlockedWords &&  i<dictionary.size()){
			if(wordsBlackList.contains(dictionary.get(i).word.trim())){
				unlockedWords++;
			}
			totalWords++;
			i++;
		}
		currentLevel++;
		
	}

	// 
	private void blackListCurrentWord() {
		// Reset Control Key State
		ctrlKeyRelease = false;
		// If  the lesson has started and not paused => add current word to backlist
		// then save backlist to a file and unlock a new word
		// Finally move to next word
		if(isLessonStarted && !isLessonPaused){
			wordsBlackList.add(dictionary.get(currentWordIndex).word);
			utils.writeBackList(wordsBlackList, blkDictionaryFilePath);
			unlockedWords++;
			// Making sure that the unlocked word isn't another backlisted word
			while(wordsBlackList.contains(dictionary.get(startBaseWord+unlockedWords-1).word)){
				unlockedWords++;
			}
			// Clear and refresh next words buffer
			nextWordBuffer.goToListEnd();
			checkBuffer(true);
		}
		
	}

	private void showTextInfo(Stroke stroke) {
		// TODO Auto-generated method stub
		
	}


	// Apply start backlist
	private void applyStartBacklist() {
		int totalWords = 0;
		int i =0;
		while (totalWords<startBaseWord  &&  i<dictionary.size()){
			if(wordsBlackList.contains(dictionary.get(i).word.trim())){
				startBaseWord++;
			}
			totalWords--;
			i++;
		}
		
	}

	// Automatically find plover log file path
	private void findPloverLog() {
		if(!logFilePath.equals(""))return;
		String userHome = System.getProperty("user.home");
		String userOs = System.getProperty("os.name");
		if(userOs.startsWith("Windows")){
			logFilePath = userHome + winLogBasePath;
		}else{
			logFilePath = userHome + xLogBasePath;
		}
		
	}


	// Get total unlocked words
	public int getActualUnlockedWords(){
		int result =0;
		for(int i=0; i<startBaseWord+unlockedWords;i++){
			if(!wordsBlackList.contains(dictionary.get(i).word)){
				result++;
			}
		}
		return result;
	}
	
	private void readSessionConfig() {
		// TODO Auto-generated method stub
		
	}
	
	// return Temps ecoulee(Elapsed)
	public long getElapsedTime(){
		if(isLessonPaused){
			return lastPauseTime-lessonStartTime;
		}else{
			return System.currentTimeMillis()-lessonStartTime;
		}
	}
	
	
	// get session average wpm 
	public float getAverageWpm(){
		if(isLessonStarted){
			return (typeWords/getElapsedTime()/60000.0f);
		}else{
			return 0.0f;
		}
	}
	
	public void updateBuffer(Stroke stroke){
		if(stroke.isDelete){
			buffer = buffer.substring(0, max(0, buffer.length()-stroke.word.length()));
		}else{
			buffer+=stroke.word;
		}
	}
	
	
	
}
