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
		Population groupe;
		Population newPopulation = new Chemins();
		
		for(int i=0; p.size() >= Math.floor(1/rate)/*p.size()*rate*/; i++)
		{
			groupe = new Chemins();
			
			for(int j=0; j < Math.floor(1/rate); j++) // Savoir si le int prend l'arrondi à l'inférieur
			{
				int indice = Probability.between(0, p.size() - 1);
				groupe.add(p.get(indice));
				p.remove(indice);
			}
			newPopulation.add(groupe.getBest());
		}
		
		if(p.size() > 0) newPopulation.add(p.getBest());
		
		return newPopulation;
		
		
		
		
		
		
		
		
		
		
		
		/*
		int i1, i2;
		boolean checkSup;
		Population newPopulation = new Chemins();
		
		while(p.size() >= 2)
		{
			i1 = Probability.between(1, p.size());
			i2 = Probability.between(1, p.size());
			while(i2 == i1) i2 = Probability.between(1, p.size());
			
			int maxFitness;
			if (p.get(i1 - 1).getFitness() < p.get(i2 - 1).getFitness()) maxFitness = i1;
			else if (p.get(i1 - 1).getFitness() > p.get(i2 - 1).getFitness()) maxFitness = i2;
			else
			{
				if(Probability.between(0, 1) < 0.5) maxFitness = i1;
				else maxFitness = i2;
			}
			
			newPopulation.add(p.get(maxFitness - 1));
			p.remove(i1 - 1);
			if(i1 > i2) p.remove(i2 - 1);
			else p.remove(i2 - 2); 
		}
		
		return newPopulation;
		
		*/
	}
}