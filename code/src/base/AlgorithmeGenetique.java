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

    public Individu run(List<? extends Gene> genes) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Stats stats = Stats.getInstance();
        stats.onStart();
        Population population;
        try {
            population = populationClass.getDeclaredConstructor(List.class, Integer.class).newInstance(genes, nbIndividus);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Class "+ populationClass +" should have a custructor with arguments : (List.class, Integer.class)");
        }

        int i =0;
        int sameSuccessif = 0;
        Individu savedBest = null;
        while(i < nbIterations) {
            stats.onNewGeneration(population);
            savedBest = population.getBest();
            // Selection
            population = methodSelection.select(population);
            stats.afterSelection(population);
            Population children = populationClass.newInstance();

            // PVC.Croisement et mutation
            for (int j = 0; j+1 < population.size(); j+=2) {
                if(Probability.rate(methodCroisement.getProbability())) {

                    ArrayList<Individu> child = methodCroisement.croiser(population.get(j), population.get(j+1));

                    for (int k = 0; k < child.size(); k++) {
                        // PVC.Mutation
                        if (Probability.rate(methodMutation.getProbability())) {
                            child.set(k, methodMutation.muter(child.get(k)));
                        }
                        children.add(child.get(k));
                    }
                }
            }
            stats.onChildrenGeneration(children);

            // Ajout de la nouvelle population
            population.addAll(children);
            if(savedBest.equals(population.getBest())) sameSuccessif++;
            else sameSuccessif = 0;
            i++;
        }
        Individu best = population.getBest();
        stats.onFinish(best, i);
        return best;
    }
}
