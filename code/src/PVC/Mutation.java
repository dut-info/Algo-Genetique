package PVC;

import base.Gene;
import base.Individu;
import base.Probability;

/**
 * Created by corentin on 18/05/16.
 */
public class Mutation implements base.Mutation {
    @Override
    public Individu muter(Individu child) {
        int individuSize = child.size() - 1;
        int a = Probability.between(0, individuSize);
        int b = Probability.between(0, individuSize);

        Gene tmp = child.get(a);
        child.set(a, child.get(b));
        child.set(b, tmp);

        return child;
    }

    @Override
    public double getProbability() {
        return 0.05;
    }
}
