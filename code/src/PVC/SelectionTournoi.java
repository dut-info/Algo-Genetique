package PVC;

import base.Population;
import base.Probability;
import base.Selection;

public class SelectionTournoi implements Selection
{	
	private Double rate;

	public SelectionTournoi(Double rate) {
		if(rate < 0 || rate > 1) throw new IllegalArgumentException("First argument should be between 0 and 1");
		this.rate = rate;
	}

	public SelectionTournoi() {
		this.rate = 0.5;
	}
	
	@Override
	public Population select(Population p)
	{
		int nbOfGroups = (int) (p.size() * this.rate);
		int nbOfIndPerGroups = p.size () / nbOfGroups;

		Population group;
		Population newPopulation = new Chemins();

		try {
			for(int i=0; i < nbOfGroups; i++)
			{
				group = p.getClass().newInstance();
				for(int j=0; j < nbOfIndPerGroups; j++)
				{
					int indice = Probability.between(0, p.size() - 1);

					group.add(p.remove(indice));
				}
				newPopulation.add(group.getBest());
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		if(p.size() > 0) newPopulation.add(p.getBest());
		
		return newPopulation;
	}

	public String toString() {
		return "Selection par tournoi";
	}
}