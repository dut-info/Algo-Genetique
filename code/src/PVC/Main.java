package PVC;

import base.*;
import base.Croisement;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by corentin on 05/05/16.
 */
public class Main {

    public static void main(String [] arg) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // Géneration de 10 villes aléatoires
        ArrayList<Ville> villes = new ArrayList<>();
        int nbVilles = 20;
        for (int i = 0; i < nbVilles; i++) {
            villes.add(new Ville());
        }

        Representation repre = Representation.getInstance();
        repre.setVilles(villes);

        //PVC.Chemins chemins = new PVC.Chemins(villes);

        Selection selectionElitisme = new SelectionElitisme();
        Croisement croisement = new PVC.Croisement();
        Mutation mutation = new Mutation();

        AlgorithmeGenetique algo = new AlgorithmeGenetique(Chemins.class, selectionElitisme, croisement, mutation, 500, 3000);
        PVC.Chemin best = (PVC.Chemin) algo.run(villes);
        System.out.println(best);
        repre.drawChemin(best);
    }
}
