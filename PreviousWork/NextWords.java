public class NextWordsBuffer {
  
  ArrayList<Integer> nextWords = new ArrayList<Integer>();
  
  ArrayList<Integer> nextLineWords = new ArrayList<Integer>();

  int highlightedWordIndex;
  int bufferSize;
  
  NextWordsBuffer(int bufferSize) {
    this.bufferSize = bufferSize;
    fillNewLine(1);
  }


  void goToListEnd() {
    highlightedWordIndex = nextWords.size() - 1;
  }

  int getCurrentWordIndex() {
    return nextWords.get(highlightedWordIndex);
  }

  int getNextWordIndex() {
    highlightedWordIndex++;
    if (highlightedWordIndex < nextWords.size()) {
      addWordsToNextLine();
      return nextWords.get(highlightedWordIndex);
    } else {
      fillNewLine(nextWords.get(highlightedWordIndex-1));
      return getCurrentWordIndex();
    }
  }

  
  void addWordsToNextLine() {
    if (isSingleWordBuffer) return;
    int lastWordIndex;
    if (nextLineWords.size() > 0) {
      lastWordIndex = nextLineWords.get(nextLineWords.size() - 1);
    } else {
      lastWordIndex = nextWords.get(nextWords.size() - 1);
    }
    float usedBufferSize = getLineWidth(nextLineWords);
    long[] penaltyLimits = calculatePenaltyLimits();
    float partialLineWidth = getLineWidth(nextWords, max(highlightedWordIndex - 1, 0));

    while (usedBufferSize < partialLineWidth) {
      int nextWordIndex = getNextWordFromPool(lastWordIndex, penaltyLimits);
      nextLineWords.add(nextWordIndex);
      lastWordIndex = nextWordIndex;

      textFont(font, mainTextFontSize);
      usedBufferSize += textWidth(dictionary.get(nextWordIndex).word.trim() + " ");
    }

    
    if (nextLineWords.size() > 0) {
      nextLineWords.remove(nextLineWords.size()-1);
    }
  }

  
  float getLineWidth(ArrayList<Integer> words) {
    float result = 0;
    for (Integer wordIndex : words) {
      result += textWidth(dictionary.get(wordIndex).word.trim() + " ");
    }
    return result;
  }

  
  float getLineWidth(ArrayList<Integer> words, int maxWordIndex) {
    float result = 0;
    for (int i = 0; i < maxWordIndex; i++) {
      result += textWidth(dictionary.get(words.get(i)).word.trim() + " ");
    }
    return result;
  }

  
  void fillNewLine(int previousWordIndex) {
    int lastWordIndex = previousWordIndex;

  
    nextWords.clear();

  
    float usedBufferSize = 0;

  
    long[] penaltyLimits = calculatePenaltyLimits();

  
    for (Integer wordIndex : nextLineWords) {
      nextWords.add(wordIndex);

      textFont(font, mainTextFontSize);
      usedBufferSize += textWidth(dictionary.get(wordIndex).word.trim() + " ");
      lastWordIndex = wordIndex;
    }

  
    nextLineWords.clear();

  
    while (usedBufferSize < bufferSize) {
      int nextWordIndex = getNextWordFromPool(lastWordIndex, penaltyLimits);
      nextWords.add(nextWordIndex);
      lastWordIndex = nextWordIndex;

      textFont(font, mainTextFontSize);
      usedBufferSize += textWidth(dictionary.get(nextWordIndex).word.trim() + " ");

  
      if(isSingleWordBuffer) break;
    }

  
    if(nextWords.size() > 1) nextWords.remove(nextWords.size()-1);

  
    highlightedWordIndex = 0;
  }

  
  int getNextWordFromPool(int previousWordIndex, long[] penaltyLimits) {
  
    ArrayList<Integer> wordPool = new ArrayList<Integer>();

  
    for (int i = 0; i < startBaseWords + unlockedWords; i++) {
      if (i == previousWordIndex || wordsBlacklist.contains(dictionary.get(i).word)) continue;
      else {
        int penalty = (int) map(wordStats.get(i).getWordPenalty(), penaltyLimits[0], penaltyLimits[1], 1, 100);
        for (int j = 0; j < penalty; j++) wordPool.add(i);
      }
    }
    return wordPool.get((int) random(0, wordPool.size()));
  }

  long[] calculatePenaltyLimits() {
    long currentMinPenalty = 1000000000;
    long currentMaxPenalty = 0;
    for (int i = 0; i < min(dictionary.size(), startBaseWords + unlockedWords); i++) {
      if (i == currentWordIndex || wordsBlacklist.contains(dictionary.get(i).word)) continue;
      long penalty = wordStats.get(i).getWordPenalty();
      if (currentMinPenalty > penalty) currentMinPenalty = penalty;
      if (currentMaxPenalty < penalty) currentMaxPenalty = penalty;
    }
    return new long[] {currentMinPenalty, currentMaxPenalty};
  }

  void showText(int x, int y) {
    float currentX = x;
    textFont(font, mainTextFontSize);
    for (int i = 0; i < nextWords.size(); i++) {
      int index = nextWords.get(i);
      String word = dictionary.get(index).word;
      if (i == highlightedWordIndex) {
        noFill();
        stroke(250, 200, 100);
        line(currentX, y + mainTextFontSize / 5, currentX + textWidth(word), y + mainTextFontSize / 5);
        fill(250, 200, 100);
      }
      text(word, currentX, y);
      if (i == highlightedWordIndex) fill(isLessonPaused ? 200 : 250);
      currentX += textWidth(word + " ");
    }

    currentX = x;
    for (int i = 0; i < nextLineWords.size(); i++) {
      if (nextLineWords.size() < 3) {
        fill(25);
      } else {
        fill(min(250, 25 * (nextLineWords.size() - i)));
      }
      int index = nextLineWords.get(i);
      String word = dictionary.get(index).word;
      text(word, currentX, y + mainTextFontSize);
      fill(isLessonPaused ? 200 : 250);
      currentX += textWidth(word + " ");
    }
  }
}
