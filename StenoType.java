
import java.io.*;
import java.util.Properties;
import java.util.Arrays;


String lessonName;
int startBaseWords;
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
boolean showKeyboard;
boolean showKeyboardQwerty;
boolean showKeyboardChord;

Utils utils = new Utils();

BufferedReader logReader = null;

final PFont font = createFont("Arial",30,true);

final String winLogBasePath = "";
final String xLogBasePath = "";

String logFilePath;

String lesDictionaryFilePath;
String chdDictionaryFilePath;
String blkDictionaryFilePath;

Keyboard keyboard;

String buffer = "";


NextWordsBuffer nextWordsBuffer;
TTS tts;

ArrayList<Word> dictionary;
ArrayList<WordStats> wordStats = new ArrayList<WordStats>();

ArrayList<String> wordsBlacklist = new ArrayList<String>();

int currentLevel = 0;

boolean isLessonCompleted = false;
int unlockedWords = 0;
int currentWordIndex = 0;
boolean isLessonStarted = false;
boolean isLessonPaused = false;
long lessonStartTime;
long lastTypedWordTime;
long lastPauseTime;
int typedWords = 0;
int worstWordWpm = 0;
String worstWord = "";
Stroke previousStroke = new Stroke();
boolean ctrlKeyReleased = false;
boolean tabKeyReleased = false;

boolean debug = false;

int frameSizeX = 700;
int frameSizeY = 480;
int defaultFontSize = 20;
int mainTextFontSize = 24;
int baseX = 60;
int baseY = 70;
int labelValueSpace = 20;
int nextWordX = baseX + 120;
int nextWordY = baseY;
int nextChordX = baseX + 120;
int nextChordY = baseY + -35;
int lastChordX = baseX + 120;
int lastChordY = baseY + 80;
int bufferX = baseX + 120;
int bufferY = baseY + 50;
int wpmX = baseX + 120;
int wpmY = baseY + 140;
int timerX = baseX + 270;
int timerY = baseY + 140;
int wordWpmX = baseX + 120;
int wordWpmY = baseY + 170;
int levelX = baseX + 270;
int levelY = baseY + 170;
int unlockedWordsX = baseX + 470;
int unlockedWordsY = baseY + 140;
int totalWordsX = baseX + 470;
int totalWordsY = baseY + 170;
int worstWordWpmX = baseX + 120;
int worstWordWpmY = baseY + 200;
int worstWordX = baseX + 270;
int worstWordY = baseY + 200;
int keyboardX = baseX - 10;
int keyboardY = baseY + 230;


void setup() {

  readSessionConfig();

  findPloverLog();
  logReader = utils.readEndOfFile(logFilePath);

  lesDictionaryFilePath = sketchPath + "" + lessonName + ".txt";
  chdDictionaryFilePath = sketchPath + "" + lessonName + ".txt";
  blkDictionaryFilePath = sketchPath + "" + lessonName + ".txt";
  dictionary = utils.readDictionary(lesDictionaryFilePath, chdDictionaryFilePath, debug);
  wordsBlacklist = utils.readBlacklist(blkDictionaryFilePath);
  applyStartBlacklist();

  for (int i = 0; i < dictionary.size(); i++) {
    wordStats.add(new WordStats(wordStartAvgWpm, wordAvgSamples));
  }

  nextWordsBuffer = new NextWordsBuffer(frameSizeX - nextWordX);
  currentWordIndex = nextWordsBuffer.getCurrentWordIndex();

  keyboard = new Keyboard(keyboardX, keyboardY, showKeyboardQwerty);

  size(frameSizeX, frameSizeY);

  tts = new TTS();
  tts.setPitchRange(7);
  background(25);
  Stroke stroke = new Stroke();
  showTextInfo(stroke);
  drawKeyboard();

  if (isWordDictationEnabled) {
    sayCurrentWord();
  }
}

void draw() {

  if (ctrlKeyReleased) {
    blacklistCurrentWord();
  }

  if (tabKeyReleased) {
    togglePause();
    tabKeyReleased = false;
  }

  Stroke stroke = utils.getNextStroke(logReader);

  if (stroke != null) {
    previousStroke = stroke;
  }

  if (!isLessonStarted && buffer.length() > 0) {
    isLessonStarted = true;
    lessonStartTime = System.currentTimeMillis();
    lastTypedWordTime = lessonStartTime - ((long) 60000.0 / wordStartAvgWpm);
    announceCurrentLevel();
    if (isSoundEnabled && wpmReportingPeriod > 0) {
      WpmReporter wpmReporter = new WpmReporter((long) wpmReportingPeriod * 1000, tts);
      wpmReporter.start();
    }
  }

  checkBuffer(false);

  background(25);
  showTextInfo(stroke == null ? previousStroke : stroke);
  drawKeyboard();
}

void keyPressed() {
  if (keyCode == BACKSPACE) {
      buffer = buffer.substring(0, max(0, buffer.length() - 1));
  }

  if (key != CODED) {
    if (isLessonPaused) {
      tabKeyReleased = true;
    }

    switch (key) {
    case TAB:
      tabKeyReleased = true;
      break;
    case BACKSPACE:
    case ESC:
    case DELETE:
    case ENTER:
    case RETURN:
      break;
    default:
      buffer += key;
    }
  }
}

void keyReleased() {
  if (keyCode == CONTROL) ctrlKeyReleased = true;
}

void togglePause() {
  if (!isLessonStarted) return;
  if (isLessonPaused) {
    long now = System.currentTimeMillis();
    long pauseTime = now - lastPauseTime;
    lessonStartTime += pauseTime;
    lastTypedWordTime += pauseTime;
    isLessonPaused = false;
  } else {
    lastPauseTime = System.currentTimeMillis();
    isLessonPaused = true;
  }
}

void applyStartBlacklist() {
  int totalWords = 0;
  int i = 0;
  while (totalWords < startBaseWords && i < dictionary.size()) {
    if (wordsBlacklist.contains(dictionary.get(i).word.trim())) {
      startBaseWords++;
    }
    totalWords++;
    i++;
  }
}

void readSessionConfig() {
  Properties properties = new Properties();
  try {
    properties.load(openStream(sketchPath + ""));
  } catch (Exception e ) {
    println("Cannot read session" + e.getMessage());
  }
  logFilePath = properties.getProperty("session.logFilePath", "");
  lessonName = properties.getProperty("session.lessonName", "common_words");
  startBaseWords = Integer.valueOf(properties.getProperty("session.startBaseWords", "" + 5));
  incrementWords = Integer.valueOf(properties.getProperty("session.incrementWords", "" + 5));
  minLevelUpWordWpm = Integer.valueOf(properties.getProperty("session.minLevelUpWordWpm", "" + 30));
  minLevelUpTotalWpm = Integer.valueOf(properties.getProperty("session.minLevelUpTotalWpm", "" + 20));
  wordAvgSamples = Integer.valueOf(properties.getProperty("session.wordAvgSamples", "" + 10));
  wordStartAvgWpm = Integer.valueOf(properties.getProperty("session.wordStartAvgWpm", "" + 20));
  isSingleWordBuffer = Boolean.valueOf(properties.getProperty("session.isSingleWordBuffer", "false"));
  isSoundEnabled = Boolean.valueOf(properties.getProperty("session.isSoundEnabled", "true"));
  isAnnounceLevels = Boolean.valueOf(properties.getProperty("session.isAnnounceLevels", "true"));
  wpmReportingPeriod = Integer.valueOf(properties.getProperty("session.wpmReportingPeriod", "" + 60));
  isWordDictationEnabled = Boolean.valueOf(properties.getProperty("session.isWordDictationEnabled", "false"));
  showKeyboard = Boolean.valueOf(properties.getProperty("session.showKeyboard", "true"));
  showKeyboardQwerty = Boolean.valueOf(properties.getProperty("session.showKeyboardQwerty", "true"));
  showKeyboardChord = Boolean.valueOf(properties.getProperty("session.showKeyboardChord", "true"));
}

void findPloverLog() {
  if(!logFilePath.equals("")) return;
  String userHome = System.getProperty("user.home");
  String userOs = System.getProperty("os.name");
  if (userOs.startsWith("Windows")) {
    logFilePath = userHome + winLogBasePath;
  } else {
    logFilePath = userHome + xLogBasePath;
  }
}

void blacklistCurrentWord() {
  ctrlKeyReleased = false;

  if (isLessonStarted && !isLessonPaused) {
    wordsBlacklist.add(dictionary.get(currentWordIndex).word);
    utils.writeBlacklist(wordsBlacklist, blkDictionaryFilePath);
    unlockedWords++;

    while (wordsBlacklist.contains(dictionary.get(startBaseWords + unlockedWords - 1).word)) unlockedWords++;

    nextWordsBuffer.goToListEnd();
    checkBuffer(true);
  }
}

long getElapsedTime() {
  return isLessonPaused ? (lastPauseTime - lessonStartTime) : (System.currentTimeMillis() - lessonStartTime);
}

void drawKeyboard() {
  if (!showKeyboard) {
    return;
  }
  
  if (showKeyboardChord) {
    String[] chords = dictionary.get(currentWordIndex).stroke.split("/");
    keyboard.draw(chords[0]);
  } else {
    keyboard.draw("-");
  }
}

void showTextInfo(Stroke stroke) {
  textAlign(RIGHT);
  fill(isLessonPaused ? 200 : 250);
  textFont(font,mainTextFontSize);
  text("Target words:", nextWordX - labelValueSpace, nextWordY);
  text("Input:", bufferX - labelValueSpace, bufferY);
  fill(200);
  textFont(font,defaultFontSize);
  text("Next chord:", nextChordX - labelValueSpace, nextChordY);
  text("Typed chord:", lastChordX - labelValueSpace, lastChordY);
  text("WPM:", wpmX - labelValueSpace, wpmY);
  text("Time:", timerX - labelValueSpace, timerY);
  text("Current w WPM:", wordWpmX - labelValueSpace, wordWpmY);
  text("Level:", levelX - labelValueSpace, levelY);
  text("Unlocked w:", unlockedWordsX - labelValueSpace, unlockedWordsY);
  text("Total w:", totalWordsX - labelValueSpace, totalWordsY);
  text("Worst w WPM:", worstWordWpmX - labelValueSpace, worstWordWpmY);
  text("Worst w:", worstWordX - labelValueSpace, worstWordY);
  textAlign(LEFT);
  fill(isLessonPaused ? 200 : 250);
  textFont(font,mainTextFontSize);
  nextWordsBuffer.showText(nextWordX, nextWordY);
  text(buffer.trim() + (System.currentTimeMillis() % 1000 < 500 ? "_" : ""), bufferX, bufferY);
  fill(200);
  textFont(font, defaultFontSize);
  text(dictionary.get(currentWordIndex).stroke, nextChordX, nextChordY);
  text(stroke.isDelete ? "*" : buffer.equals("") ? "" : stroke.stroke, lastChordX, lastChordY);
  text((int) getAverageWpm(), wpmX, wpmY);
  long timerValue = isLessonStarted ? getElapsedTime() : 0;
  text((int) timerValue/1000, timerX, timerY);
  text(isLessonStarted ? (int) wordStats.get(currentWordIndex).getAvgWpm() : 0, wordWpmX, wordWpmY);
  text(currentLevel, levelX, levelY);
  text(getActualUnlockedWords(), unlockedWordsX, unlockedWordsY);
  text(dictionary.size() - wordsBlacklist.size(), totalWordsX, totalWordsY);
  text(worstWordWpm, worstWordWpmX, worstWordWpmY);
  text(worstWord, worstWordX, worstWordY);
}

float getAverageWpm() {
  return isLessonStarted ? (typedWords / (getElapsedTime() / 60000.0)) : 0.0;
}

void checkBuffer(boolean forceNextWord) {
  if (buffer.trim().equals(dictionary.get(currentWordIndex).word) || forceNextWord) {
    buffer = ""; // Clear input buffer
    long typeTime = System.currentTimeMillis();
    wordStats.get(currentWordIndex).typeTime.add(typeTime - lastTypedWordTime);
    lastTypedWordTime = typeTime;
    typedWords++;
    checkLevelUp();
    currentWordIndex = nextWordsBuffer.getNextWordIndex();
    updateWorstWord();

    if (isWordDictationEnabled) {
      sayCurrentWord();
    }
  }
}

void updateWorstWord() {
  int worstWordIndex = 0;
  int tempWorstWordWpm = 500;
  for (int i = 0; i < startBaseWords + unlockedWords; i++) {
    if (wordsBlacklist.contains(dictionary.get(i).word)) {
      continue;
    }
    WordStats stats = wordStats.get(i);
    int wpm = (int) stats.getAvgWpm();
    if (wpm < tempWorstWordWpm) {
      worstWordIndex = i;
      tempWorstWordWpm = wpm;
    }
  }
  worstWordWpm = tempWorstWordWpm;
  worstWord = dictionary.get(worstWordIndex).word;
}

void checkLevelUp() {
  if ((int) (typedWords / (getElapsedTime() / 60000.0)) < minLevelUpTotalWpm) {
    return;
  }
  for (int i = 0; i < startBaseWords + unlockedWords; i++) {
    if (wordsBlacklist.contains(dictionary.get(i).word)) {
      continue;
    }
    if (wordStats.get(i).getAvgWpm() < minLevelUpWordWpm) {
      return;
    }
  }
  levelUp(); 
}

void levelUp() {
  int totalWords = startBaseWords + unlockedWords;
  if (totalWords == dictionary.size()) {
    if(isLessonCompleted == false) {
      announceLessonCompleted();
      isLessonCompleted = true;
    }
    return;
  }
  int i = totalWords;
  unlockedWords += incrementWords;
  if(startBaseWords + unlockedWords > dictionary.size()) unlockedWords = dictionary.size() - startBaseWords;
  while (totalWords < startBaseWords + unlockedWords && i < dictionary.size()) {
    if (wordsBlacklist.contains(dictionary.get(i).word.trim())) {
      unlockedWords++;
    }
    totalWords++;
    i++;
  }
  currentLevel++;


  announceCurrentLevel();
}


void announceCurrentLevel() {
  if (isSoundEnabled && isAnnounceLevels) {
    Speaker speaker = new Speaker("Level " + currentLevel, tts);
    speaker.start();
  }
}


void announceLessonCompleted() {
  if (isSoundEnabled && isAnnounceLevels) {
    Speaker speaker = new Speaker("Lesson completed", tts);
    speaker.start();
  }
}

void sayCurrentWord() {
  Speaker speaker = new Speaker(dictionary.get(currentWordIndex).word, tts);
  speaker.start();
}


int getActualUnlockedWords() {
  int result = 0;
  for (int i = 0; i < startBaseWords + unlockedWords; i++) {
    if (!wordsBlacklist.contains(dictionary.get(i).word)) {
      result++;
    }
  }
  return result;
}

void updateBuffer(Stroke stroke) {
  if (stroke.isDelete) buffer = buffer.substring(0, max(0, buffer.length() - stroke.word.length()));
  else buffer += stroke.word;
}
