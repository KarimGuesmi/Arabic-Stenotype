package GeneticAlgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/*
 * This class is a simple demonstration of the TreeMap 
 * I will change the Entity Representation from the HashMap to TreeMap
 * It worths because the indexing order in the Tree Map is respected 
 */
public class MapTreeDemo {

	private List<String> listLetters = new ArrayList<String>();
	private List<String> listKeys = new ArrayList<String>();
	private TreeMap<String, String> treeMap = new TreeMap<>();
	private Random random = new Random();

	public List<String> createListKeys() {
		try {
			Scanner s = new Scanner(new File("keys.txt"));
			listKeys = new ArrayList<String>();
			while (s.hasNext()) {
				listKeys.add(s.next());
			}
			s.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return listKeys;
	}

	/*
	 * Create The list Of letters
	 */
	public List<String> createListLetters() {
		try {
			Scanner s = new Scanner(new File("letters.txt"));
			listLetters = new ArrayList<String>();
			while (s.hasNext()) {
				listLetters.add(s.next());
			}
			s.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listLetters;

	}

	public static void main(String[] args) {
		MapTreeDemo mapT = new MapTreeDemo();
		mapT.listLetters=mapT.createListLetters();
		mapT.listKeys=mapT.createListKeys();
		System.out.println(mapT.listLetters);
		System.out.println(mapT.listKeys);
		mapT.treeMap = mapT.createTreeMap(mapT.listLetters, mapT.listKeys);
		System.out.println(mapT.treeMap);
		
		Set set = mapT.treeMap.entrySet();
		Iterator i = set.iterator();
		
		while(i.hasNext()){
			Map.Entry me = (Map.Entry)i.next();
			System.out.print(me.getKey() +" : ");
			System.out.println(me.getValue());
		}
		System.out.println(mapT.treeMap.get("ء"));
		
	}

	public TreeMap<String, String> createTreeMap(List<String> listLetters, List<String> listKeys) {
		for(int i=0; i<listLetters.size();i++){
			treeMap.put(listLetters.get(i), listKeys.get(i));
		}
		return treeMap;
	}

}
