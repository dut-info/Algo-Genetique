package PVC;

import base.Gene;
import base.Individu;
import base.Probability;

import java.util.ArrayList;

/**
 * A partir de deux individus, selectionne un point de croisement
 * et retourne deux enfants :
 * Le premier est la copie du parent 1 jusque la césure, puis le restant du parent 2 qui n'est pas dans l'enfant
 * Le second est construit de la même manière, en inversant le parent 1 et 2
 */
public class Croisement implements base.Croisement {

    private Double propability;

    public Croisement(Double probability) {
        this.propability = probability;
    }

    public Croisement() {
        this.propability = 0.5;
    }

    @Override
    public ArrayList<Individu> croiser(Individu individu1, Individu individu2) {
        ArrayList<Individu> children = new ArrayList<>();
        int cesure = Probability.between(0, individu1.size() -1);

        Individu parent1;
        Individu parent2;

        for (int j = 0; j < 2; j++) {
            if(j == 0) {
                parent1 = individu1;
                parent2 = individu2;
            } else {
                parent1 = individu2;
                parent2 = individu1;
            }

            Individu child = new Chemin();
            // on copie jusqu'à la césure du parent 1 dans l'enfant
            for (int i = 0; i < cesure; i++) {
                child.add(i, parent1.get(i));
            }

            // on copie du parent 2 les villes qui ne sont pas encore dans l'enfant
            int k = 0;
            while(child.size() < parent1.size()) {
                Gene ville = parent2.get(k);
                if(!child.contains(ville)) {
                    child.add(ville);
                }
                k++;
            }

            children.add(child);
        }

        return children;
    }

    @Override
    public double getProbability() {
        return this.propability;
    }
}
