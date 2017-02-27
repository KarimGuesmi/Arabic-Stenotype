package preparation;

import java.util.ArrayList;

public class PastTense {
	/*
	 * Each element of the arrayList "pastEndings"- 
	 * represents the endings of every verb in the past tense 
	 * The "pastTense" Array List is for the verb in the past tense
	 */
	private ArrayList<String> pastEndings = new ArrayList<String>();
	private ArrayList<String> pastTense = new ArrayList<String>();
	private ArrayList<String> fullTense =  new ArrayList<String>();
	private ArrayList<String> persPronouns = new ArrayList<String>();
	/*
	 * Initialize the list of past endings
	 */
	public void initialize(){
		pastEndings.add("ت");		// tu
		pastEndings.add("نا");		// na
		pastEndings.add("ت");		// ta
		pastEndings.add("ت");		// ti
		pastEndings.add("تما");		// tuma
		pastEndings.add("تم");		// tum
		pastEndings.add("تن");		// tunna
		pastEndings.add("");		// 
		pastEndings.add("ت");		// t
		pastEndings.add("ا");		// aa
		pastEndings.add("تا");		// ta
		pastEndings.add("وا");		// u
		pastEndings.add("ن");		// na
		setPastEndings(pastEndings);
	}
	
	/*
	 * We will provide a verb as parameter 
	 * and the program will assign for this verb all endings of the past tense
	 * and put them into a new List
	 */
	public void PastTenseList(String verb){
		for(int i=0; i<pastEndings.size();i++){
			pastTense.add(verb+" "+pastEndings.get(i));
		}
		setPastTense(pastTense);
	}
	

	public void initializePersPronouns(){
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
	
	public void fullPastSentence(){
		for(int i=0; i<persPronouns.size();i++){
			fullTense.add(persPronouns.get(i)+" "+pastTense.get(i));
		}
		setFullTense(fullTense);
	}
	
	public ArrayList<String> getPersPronouns() {
		return persPronouns;
	}

	public void setPersPronouns(ArrayList<String> persPronouns) {
		this.persPronouns = persPronouns;
	}

	public ArrayList<String> getPastEndings() {
		return pastEndings;
	}
	public void setPastEndings(ArrayList<String> pastEndings) {
		this.pastEndings = pastEndings;
	}
	public ArrayList<String> getPastTense() {
		return pastTense;
	}
	public void setPastTense(ArrayList<String> pastTense) {
		this.pastTense = pastTense;
	}

	public ArrayList<String> getFullTense() {
		return fullTense;
	}
	public void setFullTense(ArrayList<String> fullTense) {
		this.fullTense = fullTense;
	}

	/*
	 * Test Program
	 */
	public static void main(String[] args) {
		PastTense pT = new PastTense();
		pT.initialize();
		System.out.println(pT.getPastEndings());
		pT.PastTenseList("كتب");
		System.out.println(pT.getPastTense());
		pT.initializePersPronouns();
		pT.fullPastSentence();
		System.out.println(pT.getFullTense());
	}


}
