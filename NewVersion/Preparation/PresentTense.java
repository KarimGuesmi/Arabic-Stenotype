package preparation;

import java.util.ArrayList;

public class PresentTense {
	/*
	 * These are lists Of :
	 * Personal pronouns List
	 * Present tense prefixes of the verbs
	 * Present tense suffixes of the verbs
	 * Present tense Prefixes/Verb/Suffixes
	 * Full sentence ==> PersonalPronoun ++ Prefixes ++ Verb ++ Suffixes
	 */
	private ArrayList<String> persPronouns = new ArrayList<String>();
	private ArrayList<String> presentPrefixes = new ArrayList<String>();
	private ArrayList<String> presentSuffixes = new ArrayList<String>();
	private ArrayList<String> presentTense = new ArrayList<String>();
	private ArrayList<String> fullPresentTense =  new ArrayList<String>();
	
	public void initializePersonalPrenouns(){
		persPronouns.add("أنا");		// ana
		persPronouns.add("نحن");		// nahnu
		persPronouns.add("أنت");		// anta 
		persPronouns.add("أنت");		// anti 
		persPronouns.add("أنتما");		// antuma
		persPronouns.add("أنتم");		// antom
		persPronouns.add("أنتن");		// antonna
		persPronouns.add("هو");			// huwa
		persPronouns.add("هي");			// hyia
		persPronouns.add("هما");		// huma
		persPronouns.add("هما");		// huma
		persPronouns.add("هم");			// hum
		persPronouns.add("هن");			// honna
		setPersPronouns(persPronouns);
	}
	
	public void initializePrefixes(){
		presentPrefixes.add("أ");		// a
		presentPrefixes.add("ن");		// na
		presentPrefixes.add("ت");		// ta 
		presentPrefixes.add("ت");		// ta 
		presentPrefixes.add("ت");		// ta
		presentPrefixes.add("ت");		// ta
		presentPrefixes.add("ت");		// ta
		presentPrefixes.add("ي");			// ya
		presentPrefixes.add("ت");			// ta
		presentPrefixes.add("ي");		// ya
		presentPrefixes.add("ت");		// ta
		presentPrefixes.add("ي");			// ya
		presentPrefixes.add("ي");			// ya
		setPresentPrefixes(presentPrefixes);
	}
	
	public void initializeSuffixes(){
		presentSuffixes.add(" ");		// 
		presentSuffixes.add(" ");		// 
		presentSuffixes.add(" ");		//  
		presentSuffixes.add("ين");		// ina 
		presentSuffixes.add("ان");		// aani
		presentSuffixes.add("ون");		// aani
		presentSuffixes.add("ن");		// una
		presentSuffixes.add("");			// na
		presentSuffixes.add("");			// 
		presentSuffixes.add("ان");		// 
		presentSuffixes.add("ان");		// aani
		presentSuffixes.add("ون");			// aani
		presentSuffixes.add("ن");			// na
		setPresentSuffixes(presentSuffixes);
	}
	
	public void thePresentTense(String verb){
		for(int i=0; i<presentPrefixes.size();i++){
			presentTense.add(presentPrefixes.get(i)+" "+verb+" "+presentSuffixes.get(i));
		}
		setPresentTense(presentTense);
	}
	
	public void theFullPresentSentence(String verb){
		for(int i=0; i<persPronouns.size();i++){
			fullPresentTense.add(persPronouns.get(i)+" "+presentPrefixes.get(i)+" "+verb+" "+presentSuffixes.get(i));
		}
	}
	
	/*
	 * Getters && Setters
	 */
	public ArrayList<String> getPersPronouns() {
		return persPronouns;
	}

	public void setPersPronouns(ArrayList<String> persPronouns) {
		this.persPronouns = persPronouns;
	}
	public ArrayList<String> getPresentPrefixes() {
		return presentPrefixes;
	}
	public void setPresentPrefixes(ArrayList<String> presentPrefixes) {
		this.presentPrefixes = presentPrefixes;
	}
	public ArrayList<String> getPresentSuffixes() {
		return presentSuffixes;
	}
	public void setPresentSuffixes(ArrayList<String> presentSuffixes) {
		this.presentSuffixes = presentSuffixes;
	}
	public ArrayList<String> getPresentTense() {
		return presentTense;
	}
	public void setPresentTense(ArrayList<String> presentTense) {
		this.presentTense = presentTense;
	}
	public ArrayList<String> getFullPresentTense() {
		return fullPresentTense;
	}

	public void setFullTense(ArrayList<String> fullPresentTense) {
		this.fullPresentTense = fullPresentTense;
	}
	
	
	
	/*
	 * Test Program
	 */
	public static void main(String[] args) {
		PresentTense pT =  new PresentTense();
		pT.initializePersonalPrenouns();
		pT.initializePrefixes();
		pT.initializeSuffixes();
		System.out.print("Personal pronouns ==> ");
		System.out.println(pT.getPersPronouns());
		System.out.print("Present prefixes  ==>  ");
		System.out.println(pT.getPresentPrefixes());
		System.out.print("Present suffixes  ==>  ");
		System.out.println(pT.getPresentSuffixes());
		System.out.println("****************************************************");
		pT.thePresentTense("جمع");
		System.out.println("The present Tense (Suffixes/Verb/Prefixes) List is :  ");
		System.out.println(pT.presentTense);
		System.out.println("****************************************************");
		pT.theFullPresentSentence("دفع");
		System.out.println("The full present Tense Sentence (PersonalPronoun/Suffixes/Verb/Prefixes) List is :  ");
		System.out.println(pT.fullPresentTense);
	}
	
}
