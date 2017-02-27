package preparation;

import java.util.ArrayList;

public class PersonalPronouns {
	/*
	 * 12 Personal Pronouns in Arabic language
	 * We will put them into aa Array List called "PersPronouns"
	 */
	private ArrayList<String> persPronouns = new ArrayList<String>();
	
	public void initializePersPronouns(){
		persPronouns.add("أنا");		// ana
		persPronouns.add("نحن");		// nahnu
		persPronouns.add("أنت");		// anta (OR) anti
		persPronouns.add("أنتما");		// antuma
		persPronouns.add("أنتم");		// antom
		persPronouns.add("أنتن");		// antonna
		persPronouns.add("هو");			// huwa
		persPronouns.add("هي");			// hyia
		persPronouns.add("هما");		// huma
		persPronouns.add("هم");			// hum
		persPronouns.add("هن");			// honna
		setPersPronouns(persPronouns);
	}
	
	public ArrayList<String> getPersPronouns() {
		return persPronouns;
	}
	public void setPersPronouns(ArrayList<String> persPronouns) {
		persPronouns = persPronouns;
	}

	public static void main(String[] args) {
		PersonalPronouns pP=new PersonalPronouns();
		pP.initializePersPronouns();
		System.out.println(pP.getPersPronouns());

	}

}
