import base.Population;
import base.Selection;

import java.util.Collections;

import base.Individu;


public class SelectionElitisme implements Selection {

	@Override
	public Population select(Population p) {
		
		Collections.sort(p);
		double average = p.getAverageFitness();
		p.subList((int) average/2, p.size()-1).clear();

		return p;
	}

}
