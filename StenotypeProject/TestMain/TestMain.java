package Stenotype;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Random;

import javax.swing.Box.Filler;

// Multi-word buffer containing the next target line, uses the stenototor class
public class NextWordsBuffer {
	
	private Utils utils;
	ArrayList<Word> dictionary = new ArrayList<Word>();
	// List of integers containing all words in the line
	ArrayList<Integer> nextWords = new ArrayList<Integer>();
	
	//List of integers containing all word in the next Line
	ArrayList<Integer> nextLineWords = new ArrayList<Integer>();
	
	int wordIndex;
	int bufferSize;
	
	public NextWordsBuffer(int bufferSize){
		this.bufferSize=bufferSize;
		fillNewLine(1);
	}

	// fill a new line
	public void fillNewLine(int previousWordIndex) {
		int lastWordIndex = previousWordIndex;
		
		nextWords.clear(); // Clear Word list
		float usedBufferSize=0; // store the used space
		long[] penaltyLimits = calculatePenaltyLimits(); // calculate current min&max penalty limits
		
		for(Integer wordIndex : nextLineWords){
			nextWords.add(wordIndex);
			//usedBufferSize += dictionary.get(wordIndex).word.trim()+ " ";
			lastWordIndex = wordIndex;
		}
		
		// clear the next line
		nextLineWords.clear();
		
		//Fill the new line
		while(usedBufferSize < bufferSize){
			int nextWordIndex = getNextWordFromPool(lastWordIndex, penaltyLimits);
			nextWords.add(nextWordIndex);
			lastWordIndex = nextWordIndex;
			//usedBufferSize += dictionary.get(nextWordIndex).word.trim() + " ";
			if(isSingleWordBuffer()){
				break;
			}
		}
		if(nextWords.size()>1){
			nextWords.remove(nextWords.size()-1);
		}
		wordIndex = 0;
		
		
	}

	public boolean isSingleWordBuffer() {
		return bufferSize==1;
		
	}

	//Compute the next word
	public int getNextWordFromPool(int previousWordIndex, long[] penaltyLimits) {
		// create word pool
		ArrayList<Integer> wordPool = new ArrayList<Integer>();
		int startBaseWords=0;
		int unlockedWords=previousWordIndex;
		for(int i=0;i<startBaseWords+unlockedWords;i++){
			Component wordsBacklist;
				int penalty = (int)map(WordStats.get(i).getWordPenalty(), penaltyLimits[0],penaltyLimits[1],1,100);
				for(int j=0;j<penalty;j++){
					wordPool.add(i);
			}
		}
		return wordPool.get((int)Math.random()*wordPool.size());
	}

	

	public int map(long wordPenalty, long l, long m, int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}

	//calculate min&max penalty limits
	public long[] calculatePenaltyLimits() {
		long currentMinPenalty = 1000000000;
		long currentMaxPenalty = 0;
		
		int startBaseWords=getCurrentWordIndex();
		int unlockedWords=getNextWordIndex();
		for(int i=0; i<startBaseWords+unlockedWords;i++){
			int currentWordIndex = i;
			Component wordBackList;
			if(i==currentWordIndex ){
				continue;
			}
			
			long penalty = WordStats.get(i).getWordPenalty();
			
			if(currentMinPenalty>penalty){
				currentMinPenalty=penalty;
			}
			if(currentMaxPenalty<penalty){
				currentMaxPenalty=penalty;
			}
		}
		
		return new long[] {currentMinPenalty, currentMaxPenalty};
	}
	
	private int min(int i, int j){
		if(i>j){
			return i;
		}else{
			return j;
		}
	}
	
	
	public void addWordsToNextLine() {
	    if (isSingleWordBuffer()){ 
	    	return;
	    }
	    
	    int lastWordIndex;
	    if (nextLineWords.size() > 0) {
	      lastWordIndex = nextLineWords.get(nextLineWords.size() - 1);
	    } else {
	      lastWordIndex = nextWords.get(nextWords.size() - 1);
	    }
	    float usedBufferSize = getLine(nextLineWords);
	    long[] penaltyLimits = calculatePenaltyLimits();
	    float partialLine = getLine(nextWords, max(wordIndex - 1, 0));

	    while (usedBufferSize < partialLine) {
	      int nextWordIndex = getNextWordFromPool(lastWordIndex, penaltyLimits);
	      nextLineWords.add(nextWordIndex);
	      lastWordIndex = nextWordIndex;


	      usedBufferSize += nextWordIndex;
	    }

	    // Remove this word because it finishes too far
	    if (nextLineWords.size() > 0) {
	      nextLineWords.remove(nextLineWords.size()-1);
	    }
	  }
	
	
	
	
	private int max(int i, int j) {
		if (i>j){
			return i;
		}else{
			return j;
		}
	}

	private float getLine(ArrayList<Integer> words) {
	    float result = 0;
	    for (Integer wordIndex : words) {
	      result += wordIndex;
	    }
	    return result;
	  }
	
	
	
	 // Get partial line width
	  private float getLine(ArrayList<Integer> words, int maxWordIndex) {
	    float result = 0;
	    for (int i = 0; i < maxWordIndex; i++) {
	      result += words.get(i);
	    }
	    return result;
	  }
	
	// Get next word dictionary index
	  int getNextWordIndex() {
	    wordIndex++;
	    if (wordIndex < nextWords.size()) {
	      addWordsToNextLine();
	      return nextWords.get(wordIndex);
	    } else {
	      nextWords.get(wordIndex-1);
	      return getCurrentWordIndex();
	    }
	  }
	 
	// Get current word index
	public int getCurrentWordIndex() {
		return nextWords.get(wordIndex);
	}

	// go to the last element of the list 
	public void goToListEnd() {
	    wordIndex = nextWords.size() - 1;
	  }
	  
}
