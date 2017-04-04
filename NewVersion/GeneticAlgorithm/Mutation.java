package GeneticAlgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * This class is to mutate
 * Order changing : two selected values of keys are  exchanged
 */
public class Mutation {

	private RandomEntity ra = new RandomEntity();
	private Map<String, String> hmFinal = new HashMap<>();
	private List<String> listLetters = new ArrayList<String>();
	
	public static void main(String[] args) {
		// Get the random entity
		System.out.println("* The Random entity before the Mutation : ");
		Mutation m1 = new Mutation();
		Map<String, String> hm1 = m1.ra.createOneRandomEntity();
		System.out.println(hm1);
		
		// select the two keys to exchange
		String key1 = m1.selectValue();
		String key2 = m1.selectValue();
		//System.out.println(value1+" "+value2);
		
		// get the two values of the specified keys
		String val1 = hm1.get(key1);
		String val2 = hm1.get(key2);
		
		List<String> listOfKeys = m1.createListOfKeys(hm1, val1, val2);
		System.out.println("Before Mutation : ");
		System.out.println(listOfKeys);
		// exchange the two points in the Array List
		List<String> listOfKeysExchanged = m1.exchangeList(listOfKeys, val1, val2);
		System.out.println("After Mutation : ");
		System.out.println(listOfKeysExchanged);
		System.out.println("****************************");
		
		// Create the list of letters
		m1.listLetters = m1.createListLetters();
		// Create the final Map of the Entity
		m1.hmFinal = m1.finalEntityAfterMutation(hm1, listOfKeysExchanged, m1.listLetters);
		
		//Display the final RandomEntity
		System.out.println(m1.hmFinal);
	}

	public Map<String, String> finalEntityAfterMutation(Map<String, String> hm1, List<String> listOfKeysExchanged, List<String> listLetters2) {
		Map<String , String> hm = new HashMap<>();
		for(int i=0; i<listLetters2.size(); i++){
			hm.put(listLetters.get(i), listOfKeysExchanged.get(i));
		}
		
		return hm;
	}
	
	/*
	 * Create the list of letters
	 */
	public List<String> createListLetters(){
		List<String> list= new ArrayList<>();
		Scanner s;
		try {
			s = new Scanner(new File("letters.txt"));
			list = new ArrayList<String>();
			while (s.hasNext()) {
				list.add(s.next());
			}
			s.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	/*
	 * Exchange the two list of values 
	 * values are the keys representation of the plover Keyboard
	 */
	public List<String> exchangeList(List<String> listOfKeys, String val1, String val2) {
		listOfKeys.set(listOfKeys.indexOf(val1), val2);
		listOfKeys.set(listOfKeys.indexOf(val2), val1);
		
		return listOfKeys;
	}

	public  List<String> createListOfKeys(Map<String,String>hm1, String val1, String val2) {
		List<String>list=new ArrayList<String>();
		for (String key : hm1.keySet()) {
			list.add(hm1.get(key));
		}
		
		return list;
	}

	public String selectValue() {
		String val="";
		Scanner scan = new Scanner(System.in);
		System.out.println("* Enter the key that you want to exchange : ");
		val = scan.next();
		
		return val;
	}

}
