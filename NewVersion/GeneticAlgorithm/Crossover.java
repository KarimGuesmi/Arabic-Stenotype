package GeneticAlgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * This class is to Choose wheather if we're going to crossover two entities over only one point or more
 */
public class Crossover {

	private RandomEntity rEN = new RandomEntity();
	private List<String> list1BeforeCrossover = new ArrayList<String>();
	private List<String> list2BeforeCrossover = new ArrayList<String>();
	private List<String> list1AfterCrossover = new ArrayList<String>();
	private List<String> list2AfterCrossover = new ArrayList<String>();
	private List<String> listLetters = new ArrayList<String>();
	private HashMap<String, String> hm1AfterCrossover = new HashMap<>();
	private HashMap<String, String> hm2AfterCrossover = new HashMap<>();

	
	
	public  HashMap<String, String> crossoverMap(List<String> listLetters1, List<String> listAfterCrossover) {
		HashMap<String, String> hm = new HashMap<>();
		for (int i = 0; i < listLetters1.size(); i++) {
			hm.put(listLetters.get(i), listAfterCrossover.get(i));
		}
		return hm;
	}

	public List<String> createListLetters() {
		List<String> list = new ArrayList<String>();
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

	public List<String> crossover1(int point, List<String> list1BeforeCrossover, List<String> list2BeforeCrossover) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < point; i++) {
			list.add(list1BeforeCrossover.get(i));
		}
		for (int i = point; i < list2BeforeCrossover.size(); i++) {
			list.add(list2BeforeCrossover.get(i));
		}
		return list;
	}

	public List<String> crossover2(int point, List<String> list1BeforeCrossover, List<String> list2BeforeCrossover) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < point; i++) {
			list.add(list2BeforeCrossover.get(i));
		}
		for (int i = point ; i < list1BeforeCrossover.size(); i++) {
			list.add(list1BeforeCrossover.get(i));
		}
		return list;
	}

	public List<Integer> choosePoint(int numberOfPoints) {
		return null;
	}

	public int chooseNumberOfPoints() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a number of points");
		int n = scan.nextInt();
		return n;
	}

	public int choosePoint() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a point to CROSSOVER");
		int point = scan.nextInt();
		System.out.println("Loading Crossover ...");
		return point;
	}

	public List<String> mapIntolist1(Map<String, String> hm1) {
		List<String> keys1 = new ArrayList<String>();
		List<String> values1 = new ArrayList<String>();
		for (String key : hm1.keySet()) {
			keys1.add(key);
			values1.add(hm1.get(key));
		}
		return values1;
	}

	public List<String> mapIntolist2(Map<String, String> hm2) {
		List<String> keys2 = new ArrayList<String>();
		List<String> values2 = new ArrayList<String>();
		for (String key : hm2.keySet()) {
			keys2.add(key);
			values2.add(hm2.get(key));
		}
		return values2;
	}

	public void mapIntolist(Map<String, String> hm1, Map<String, String> hm2) {
		List<String> keys1 = new ArrayList<String>();
		List<String> values1 = new ArrayList<String>();
		for (String key : hm1.keySet()) {
			keys1.add(key);
			values1.add(hm1.get(key));
		}

		System.out.println("List of Random Entity 1 :");
		System.out.println(values1);

		// Entity 2
		List<String> keys2 = new ArrayList<String>();
		List<String> values2 = new ArrayList<String>();
		for (String key : hm2.keySet()) {
			keys2.add(key);
			values2.add(hm2.get(key));
		}
		System.out.println("List of Random Entity 2 :");
		System.out.println(values2);

	}

	
	/*
	 * Getters & Setters
	 */
	public RandomEntity getrEN() {
		return rEN;
	}

	public void setrEN(RandomEntity rEN) {
		this.rEN = rEN;
	}

	public List<String> getList1BeforeCrossover() {
		return list1BeforeCrossover;
	}

	public void setList1BeforeCrossover(List<String> list1BeforeCrossover) {
		this.list1BeforeCrossover = list1BeforeCrossover;
	}

	public List<String> getList2BeforeCrossover() {
		return list2BeforeCrossover;
	}

	public void setList2BeforeCrossover(List<String> list2BeforeCrossover) {
		this.list2BeforeCrossover = list2BeforeCrossover;
	}

	public List<String> getList1AfterCrossover() {
		return list1AfterCrossover;
	}

	public void setList1AfterCrossover(List<String> list1AfterCrossover) {
		this.list1AfterCrossover = list1AfterCrossover;
	}

	public List<String> getList2AfterCrossover() {
		return list2AfterCrossover;
	}

	public void setList2AfterCrossover(List<String> list2AfterCrossover) {
		this.list2AfterCrossover = list2AfterCrossover;
	}

	public List<String> getListLetters() {
		return listLetters;
	}

	public void setListLetters(List<String> listLetters) {
		this.listLetters = listLetters;
	}

	public HashMap<String, String> getHm1AfterCrossover() {
		return hm1AfterCrossover;
	}

	public void setHm1AfterCrossover(HashMap<String, String> hm1AfterCrossover) {
		this.hm1AfterCrossover = hm1AfterCrossover;
	}

	public HashMap<String, String> getHm2AfterCrossover() {
		return hm2AfterCrossover;
	}

	public void setHm2AfterCrossover(HashMap<String, String> hm2AfterCrossover) {
		this.hm2AfterCrossover = hm2AfterCrossover;
	}

	
	
	/*
	 * Main Program for the TEST
	 */
	public static void main(String[] args) {
		System.out.println("* The two entities before CROSSOVER");
		Crossover cr1 = new Crossover();
		Map<String, String> hm1 = cr1.rEN.createOneRandomEntity();
		System.out.println("* Random Entity 1 ==> " + hm1);

		Crossover cr2 = new Crossover();
		Map<String, String> hm2 = cr2.rEN.createOneRandomEntity();
		System.out.println("* Random Entity 2 ==> " + hm2);

		Crossover cr = new Crossover();
		// cr.mapIntolist(hm1,hm2);

		cr1.list1BeforeCrossover = cr.mapIntolist1(hm1);
		cr2.list2BeforeCrossover = cr.mapIntolist2(hm2);
		System.out.println("* Random List1 Before Crossover ==>" + cr1.list1BeforeCrossover);
		System.out.println("* Random List2 Before Crossover ==>" + cr2.list2BeforeCrossover);

		// The crossover points
		System.out.println("*****************************");
		int numberOfPoints = cr.chooseNumberOfPoints();
		if (numberOfPoints == 1) {
			int point = cr.choosePoint();
			System.out.println("--------------------------------------------------");
			System.out.println("* Lists After The Crossover");
			cr.list1AfterCrossover = cr.crossover1(point, cr1.list1BeforeCrossover, cr2.list2BeforeCrossover);
			System.out.println("* List1 After Crossover ==> " + cr.list1AfterCrossover);
			cr.list2AfterCrossover = cr.crossover2(point, cr1.list1BeforeCrossover, cr2.list2BeforeCrossover);
			System.out.println("* List2 After Crossover ==> " + cr.list2AfterCrossover);
			System.out.println("--------------------------------------------------");
			cr.listLetters = cr.createListLetters();
			System.out.println(cr.listLetters);
		
			System.out.println("* Two Entities After The Crossover : ");
			cr.hm1AfterCrossover = cr.crossoverMap(cr.listLetters,cr.list1AfterCrossover);
			System.out.println("Entity 1 ==> "+cr.hm1AfterCrossover);
			cr.hm2AfterCrossover = cr.crossoverMap(cr.listLetters, cr.list2AfterCrossover);
			System.out.println("Entity 2 ==> "+cr.hm2AfterCrossover);
		} else if (numberOfPoints > 1) {
			List<Integer> points = new ArrayList<Integer>();
			points = cr.choosePoint(numberOfPoints);
		}

	}

	
}
