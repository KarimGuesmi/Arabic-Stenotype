package Stenotype;

import java.util.ArrayList;

// Store word speed and accuracy, and computes it's penalities

public class WordStats {
	ArrayList<Long> typeTime = new ArrayList<Long>();
	ArrayList<Boolean> isAccurate = new ArrayList<Boolean>();
	int average;
	public WordStats(int startAverage,int average) {
		super();
		this.average = average;
		typeTime.add((long) (6000.0/startAverage)); // Low performace by default
		isAccurate.add(false);
	}
	
	// Get average for this word
	
	public float getAverage(){
		long totalTime=0;
		if(typeTime.size()>0){
			for(int i=typeTime.size()-average;i<typeTime.size();i++){
				totalTime+=typeTime.get(max(i,0));
			}
			return (float) (average *1.0 / (totalTime/6000.0));
		}else{
			return (float) 1.0;
		}
	}

	private int max(int i, int j) {
		if(i>j){
			return i;
		}else{
			return j;
		}
		
	}
	
	
	// Return the word penality 
	public long getWordPenalty(){
		float wordPen=(float) 0.0;
		long timePenalty = 0;
		if(typeTime.size()>0){
			for(int i=typeTime.size()-average; i<typeTime.size();i++){
				wordPen= timePenalty * timePenalty/2000 * timePenalty;
			}
		}else{
				wordPen =  9999999999L;
			}
		return (long) wordPen;
		}
	
	public static WordStats get(int i) {
		return null;
	}
	
}
