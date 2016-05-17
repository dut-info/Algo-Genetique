import base.Gene;
import base.Individu;

/**
 * Created by corentin on 17/04/16.
 */
public class Chemin extends Individu {

    private Double fitness = null;

    @Override
    public double getFitness() {
        if(this.fitness == null) {
            double distance = 0.0;
            int villeSize = size();
            for (int i = 0; i < villeSize; i++) {
                Ville ville = (Ville) get(i);
                Ville nextVille = (Ville) get((i + 1) % villeSize);
                distance += ville.distance(nextVille);
            }
            this.fitness = distance;
        }

        return this.fitness;
    }

    @Override
    public String toString()
    {
        String r = "";

        for(Gene gene : this) {
            r += gene + "; ";
        }

        return r;
    }
}
