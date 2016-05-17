package base;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by corentin on 01/05/16.
 */
public class AlgorithmeGenetique {

    private Class<? extends Population> populationClass;
    private Population population;
    private Selection methodSelection;
    private Croisement methodCroisement;
    private Mutation methodMutation;
    private int nbIterations;
    private int nbIndividus;

    public AlgorithmeGenetique(Class<? extends Population> populationClass, Selection methodSelection, Croisement methodCroisement, Mutation methodMutation, int nbIterations, int nbIndividus)
    {
        this.populationClass = populationClass;
        this.methodSelection = methodSelection;
        this.methodCroisement = methodCroisement;
        this.methodMutation = methodMutation;
        this.nbIterations = nbIterations;
        this.nbIndividus = nbIndividus;
    }

    public Individu run(List<? extends Gene> genes) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Population population = populationClass.getDeclaredConstructor(List.class, Integer.class).newInstance(genes, nbIndividus);
        for (int i = 0; i < nbIterations; i++) {
            // Selection
            population = methodSelection.select(population);
            Population children = populationClass.newInstance();

            // Croisement
            for (int j = 0; j < population.size(); j++) {
                if(Probability.rate(methodCroisement.getProbability())) {

                    ArrayList<Individu> child = methodCroisement.croiser(null,null);

                    for (int k = 0; k < child.size(); k++) {
                        // Mutation
                        if (Probability.rate(methodMutation.getProbability())) {
                            children.set(k, methodMutation.muter(children.get(k)));
                        }
                        children.add(child.get(k));
                    }
                }
            }



            // Ajout de la nouvelle population
            population.addAll(children);
        }

        return population.getBest();
    }
}
