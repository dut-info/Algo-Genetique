package PVC;

import base.Population;
import base.Selection;

import java.util.Collections;

import base.Individu;

/**
 * Selectionne les n/2 individus les
 */
public class SelectionElitisme implements Selection {

	private Double rate;

	public SelectionElitisme(Double rate) {
		if(rate < 0 || rate > 1) throw new IllegalArgumentException("First argument should be between 0 and 1");
		this.rate = rate;
	}

	public SelectionElitisme() {
		this.rate = 0.5;
	}

	@Override
	public Population select(Population p) {
		Collections.sort(p);
		p.subList((int) (p.size()*this.rate), p.size()-1).clear();

		return p;
	}

}
