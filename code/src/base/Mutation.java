package base;

import base.Individu;

/**
 * Created by corentin on 05/05/16.
 */
public interface Mutation {

    Individu muter(Individu child);
    double getProbability();
}
