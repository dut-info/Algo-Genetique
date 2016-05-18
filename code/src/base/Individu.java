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
        List<? extends Gene> copy = new ArrayList<>(genes);
        Individu individu = individuClass.newInstance();
        int nbGenes = copy.size();
        for (int i = 0; i < nbGenes; i++) {
            int random = Probability.between(0, copy.size()-1);
            individu.add(copy.remove(random));
        }
        return individu;
    }

    /**
     * Détermine le fitness de l'individu
     * @return
     */
    public abstract double getFitness();
}
