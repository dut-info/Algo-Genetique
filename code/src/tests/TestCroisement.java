package tests;


import PVC.Chemin;
import PVC.Croisement;
import PVC.Ville;
import base.Individu;

import java.util.ArrayList;

/**
 * Created by corentin on 18/05/16.
 */
public class TestCroisement {
    public static void main(String [] arg) throws InstantiationException, IllegalAccessException {

        // Géneration de 10 villes aléatoires
        ArrayList<Ville> villes = new ArrayList<>();
        int nbVilles = 10;
        for (int i = 0; i < nbVilles; i++) {
            villes.add(new Ville());
        }


        Croisement croisement = new Croisement(0.6);
        Chemin chemin1 = (Chemin) Individu.generate(villes, Chemin.class);
        Chemin chemin2 = (Chemin) Individu.generate(villes, Chemin.class);

        System.out.println("Chemin 1 : " + chemin1);
        System.out.println("Chemin 2 : " + chemin2);

        ArrayList<Individu> children = croisement.croiser(chemin1, chemin2);

        System.out.println("Enfant 1 : " + children.get(0));
        System.out.println("Enfant 2 : " + children.get(1));

    }
}
