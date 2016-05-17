package base;

import base.Gene;
import base.Individu;

import java.util.ArrayList;
import java.util.List;

public abstract class Population extends ArrayList<Individu> {

    public abstract double getDiversity();

    public double getAverageFitness()
    {
        double averageFit = 0;
        for(Individu individu : this) {
            averageFit += individu.getFitness();
        }

        averageFit /= this.size();

        return averageFit;
    }

    /**
     * Créer une population initiale aléatoire
     */
    public Population(List<Gene> genes, Class<? extends Individu> individuClass, Integer nbIndividus) throws InstantiationException, IllegalAccessException {
        super(nbIndividus);
        for (int i = 0; i < nbIndividus; i++) {
            this.add(Individu.generate(genes, individuClass));
        }
    }

    /**
     * Créer une population initiale vide
     */
    public Population()
    {
        super();
    }

    public Individu getBest()
    {
        Individu best = null;
        Double bestFitness = null;
        for(Individu individu : this) {
            Double indivFitness = individu.getFitness();
            if(bestFitness == null || bestFitness > indivFitness) {
                best = individu;
                bestFitness = indivFitness;
            }
        }

        return best;
    }
}
