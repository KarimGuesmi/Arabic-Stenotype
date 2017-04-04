package GeneticAlgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/*
 * This class is to Choose wheather if we're going to use
 * Single point , two points, uniform or arithmetic CROSSOVER 
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
	private Random random = new Random();
	
	
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

	/*
	 * Single point crossover List1+List2
	 */
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
	/*
	 * Single point crossover List2+List1
	 */
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

	
	/*
	 * Two point crossover
	 */
	public int choosePoints() {
		int point ;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a point to CROSSOVER");
		point = scan.nextInt();
		
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
	 * Two point crossover of starting from the list1
	 */
	public List<String> twoPointCrossover1(int point1, int point2, List<String> list1BeforeCrossover,
			List<String> list2BeforeCrossover) {
		List<String> finalList = new ArrayList<String>();
		for(int i=0; i<point1;i++){
			finalList.add(list1BeforeCrossover.get(i));
		}
		for(int i=point1; i<point2;i++){
			finalList.add(list2BeforeCrossover.get(i));
		}
		for(int i=point2; i<list1BeforeCrossover.size();i++){
			finalList.add(list1BeforeCrossover.get(i));
		}
		
		return finalList;
	}
	
	/*
	 * Two point crossover of starting from the list2
	 */
	public List<String> twoPointCrossover2(int point1, int point2, List<String> list1BeforeCrossover,
			List<String> list2BeforeCrossover) {
		List<String> finalList = new ArrayList<String>();
		for(int i=0; i<point1;i++){
			finalList.add(list2BeforeCrossover.get(i));
		}
		for(int i=point1; i<point2;i++){
			finalList.add(list1BeforeCrossover.get(i));
		}
		for(int i=point2; i<list2BeforeCrossover.size();i++){
			finalList.add(list2BeforeCrossover.get(i));
		}

		return finalList;
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
	 * Select a point for the crossover
	 * This will create a RANDOM  integer which represents the index
	 * This will be used also for two point crossover as for the single point crossover
	 */
	public int selectPointForCrossover(List<String> list1BeforeCrossover) {
		int i= random.nextInt(list1BeforeCrossover.size()) + 1;
		return i;
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
		System.out.println("* Random List1 Before Crossover :" );
		System.out.println(cr1.list1BeforeCrossover);
		System.out.println("* Random List2 Before Crossover ==>");
		System.out.println(cr2.list2BeforeCrossover);
		
		System.out.println("___________________________________________________");
		// index for the single point crossover
		int singlePointRandom = cr.selectPointForCrossover(cr1.list1BeforeCrossover);
		System.out.println("* The index if the Single point crossover ==> "+singlePointRandom);
		Crossover crSingle = new Crossover();
		crSingle.list1AfterCrossover = crSingle.crossover1(singlePointRandom, cr1.list1BeforeCrossover, cr2.list2BeforeCrossover);
		crSingle.list2AfterCrossover = crSingle.crossover2(singlePointRandom, cr1.list1BeforeCrossover, cr2.list2BeforeCrossover);
		System.out.println("The two lists After the single point Crossover");
		System.out.println("List 1 After Crossover :");
		System.out.println(crSingle.list1AfterCrossover);
		System.out.println("List 2 After Crossover :");
		System.out.println(crSingle.list2AfterCrossover);
		
		System.out.println("____________________________________________________");
		// Two indexes for the two points crossover
		int point1 = cr.selectPointForCrossover(cr1.list1BeforeCrossover);
		int point2 = cr.selectPointForCrossover(cr1.list1BeforeCrossover);
		while(point1>=point2){
			point1=cr.selectPointForCrossover(cr1.list1BeforeCrossover);
			point2=cr.selectPointForCrossover(cr1.list1BeforeCrossover);
		}
		System.out.println("* The two indexes of the two point crossover are ==> "+point1 +" & "+ point2);
		
		cr.list1AfterCrossover = cr.twoPointCrossover1(point1, point2, cr1.list1BeforeCrossover, cr2.list2BeforeCrossover);
		cr.list2AfterCrossover = cr.twoPointCrossover2(point1, point2, cr1.list1BeforeCrossover, cr2.list2BeforeCrossover);
		System.out.println("* The Two lists After the two points Crossover: ");
		System.out.println("* List1 Ather the crossover: ");
		System.out.println(cr.list1AfterCrossover);
		System.out.println("* List2 Ather the crossover: ");
		System.out.println(cr.list2AfterCrossover);
	}
	
}
