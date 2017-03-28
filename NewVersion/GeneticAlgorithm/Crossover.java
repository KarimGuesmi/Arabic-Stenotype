package GeneticAlgorithm;

import java.util.HashMap;
import java.util.Map;

public class Crossover {

	private RandomEntity rEN = new RandomEntity();
	private Map<String, String> hm = new HashMap<>();
	public static void main(String[] args) {

		Crossover cr = new Crossover();
		for (int i = 0; i < 2; i++) {
			Map<String, String> hm1 = cr.rEN.createOneRandomEntity();
			cr.hm=hm1;
			System.out.println(cr.hm);
			cr.hm.clear();
			
		}

	}

}
