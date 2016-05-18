package PVC;

import base.Gene;
import base.Individu;
import base.Population;

import java.util.List;

/**
 * Created by corentin on 05/05/16.
 */
public class Chemins extends Population {

    public Chemins(List<Gene> genes, Integer nbIndiv) throws InstantiationException, IllegalAccessException {
        for (int i = 0; i < nbIndiv; i++) {
            this.add(Individu.generate(genes, Chemin.class));
        }
    }

    public Chemins() {
        super();
    }

    @Override
    public double getDiversity() {
        return 0;
    }

    //public PVC.Chemins(List<PVC.Ville> villes, ,) {

    //}
}
