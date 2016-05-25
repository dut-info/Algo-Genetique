package tests;


import PVC.Chemin;
import PVC.Croisement;
import PVC.Ville;
import base.Individu;
import base.Probability;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by corentin on 18/05/16.
 */
public class TestCroisement {
    public static void main(String [] arg) throws InstantiationException, IllegalAccessException {
        int nb = 1000000000;
        double cumul = 0;
        for (int i = 0; i < nb; i++) {
            cumul += Probability.between(0,1);
        }

        System.out.println(cumul/nb);
    }
}
