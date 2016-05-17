package base;

import java.util.ArrayList;

/**
 * Created by corentin on 05/05/16.
 */
public interface Croisement {
    ArrayList<Individu> croiser(Individu individu1, Individu individu2);
    double getProbability();
}
