package PVC;

import base.Gene;
import base.Individu;
import base.Probability;

public class Mutation implements base.Mutation {

    private double propability;

    public Mutation(double propability) {
        this.propability = propability;
    }

    public Mutation() {
        this.propability = 0.1;
    }

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
        return this.propability;
    }
}
