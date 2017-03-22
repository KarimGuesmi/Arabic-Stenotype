package GeneticAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDemo {
	private List<String> list = new ArrayList<String>();
	private List<String> listRand = new ArrayList<String>();
	
	public RandomDemo(){
		list.add("One");
		list.add("Two");
		list.add("Three");
		list.add("Four");
	}
	
	private void newRandom(List<String> list)
	{
		Random random = new Random();
		int index;
		while(listRand.size()!=list.size()){
			for(int i =0; i< list.size();i++){
				index=random.nextInt(list.size());
				if(!listRand.contains(list.get(index))){
					listRand.add(list.get(index));
				}
			}
		}
		
	    
	}
	
	public static void main(String[] args) {
		RandomDemo rd = new RandomDemo();
		rd.newRandom(rd.list);
		System.out.println(rd.list);
		System.out.println(rd.listRand);
	}
	

}
