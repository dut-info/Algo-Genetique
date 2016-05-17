import base.AlgorithmeGenetique;
import base.Gene;
import base.Population;
import base.Probability;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by corentin on 05/05/16.
 */
public class Main {

    public static void main(String [] arg) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // Géneration de 10 villes aléatoires
        ArrayList<Ville> villes = new ArrayList<>();
        int nbVilles = 10;
        for (int i = 0; i < nbVilles; i++) {
            villes.add(new Ville());
        }

        Representation repre = new Representation(villes);


        //Chemins chemins = new Chemins(villes);

        AlgorithmeGenetique algo = new AlgorithmeGenetique();
        Chemin best = (Chemin) algo.run(villes);
        repre.drawChemin(best);
    }
}
