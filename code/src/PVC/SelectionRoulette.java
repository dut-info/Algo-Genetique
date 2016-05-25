package PVC;

import base.Individu;
import base.Population;
import base.Probability;
import base.Selection;

public class SelectionRoulette implements Selection {

    private double rate;

    public SelectionRoulette(double rate) {
        this.rate = rate;
    }

    public SelectionRoulette() {
        this.rate = 0.5;
    }

    @Override
    public Population select(Population p) {


        try {
            Population selectedPopulation = p.getClass().newInstance();
            int pSize = p.size();
            for (int i = 0; i < pSize*this.rate; i++) {

                double cumul = 0;
                for (Individu individu : p) {
                    cumul += (1 / individu.getFitness());
                }

                double random = Probability.between(0, cumul);
                double currentCumul = 0;

                Individu individu = null;
                int j = 0;
                while(currentCumul < random) {
                    individu = p.get(j);
                    currentCumul += (1/ individu.getFitness());

                    if(currentCumul >= random) {
                        selectedPopulation.add(individu);
                        p.remove(j);
                    }
                    j++;
                }

            }
            return selectedPopulation;

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String toString() {
        return "Selection par roulette";
    }
}
