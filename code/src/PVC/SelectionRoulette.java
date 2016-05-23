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

        double cumul = 0;
        for (Individu individu : p) {
            cumul += 1 / individu.getFitness();
        }

        try {
            Population selectedPopulation = p.getClass().newInstance();
            int pSize = p.size();
            for (int i = 0; i < pSize*this.rate; i++) {
                double random = Probability.between(0, cumul);
                double currentCumul = 0;

                /*
                for(Individu individu : p) {
                    currentCumul += (cumul - individu.getFitness());
                    System.out.println(i + " : " + individu.getFitness()+" : " + currentCumul + " : " + random + " : " + cumul);
                    if(currentCumul > random) {
                        selectedPopulation.add(individu);
                        break;
                    }
                }
                */

                Individu individu = null;
                int j = 0;
                while(currentCumul < random && j < pSize) {
                    individu = p.get(j);
                    currentCumul += (1/ individu.getFitness());
/*
                    System.out.println("----------------------");
                    System.out.println("random : " + random);
                    System.out.println("j      : " + j);
                    System.out.println("current: " + currentCumul);
                    System.out.println("cumul  : " + cumul);
                    System.out.println("fitness: " + individu.getFitness());
                    System.out.println("inv fit: " + (1/individu.getFitness()));
*/
                    if(random <= currentCumul) {
                        selectedPopulation.add(individu);
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
}
