package base;

import java.util.ArrayList;
import java.util.List;

public abstract class Individu extends ArrayList<Gene> implements Comparable<Individu>
{

    /**
     * Créer un Individu aléatoire
     *
     * @param genes
     * @param individuClass
     */
    public static Individu generate(List<? extends Gene> genes, Class<? extends Individu> individuClass) throws IllegalAccessException, InstantiationException {
        Individu individu = individuClass.newInstance();
        int nbGenes = genes.size();
        for (int i = 0; i < nbGenes; i++) {
            int random = Probability.between(0, genes.size()-1);
            individu.add(genes.remove(random));
        }
        return individu;
    }

    /**
     * Détermine le fitness de l'individu
     * @return
     */
    public abstract double getFitness();
}
