package base;

import PVC.Chemin;
import PVC.Representation;

import java.io.OutputStream;

/**
 * Created by corentin on 18/05/16.
 */
public class Stats {

    private static Stats instance = null;
    private OutputStream outputStream;
    private Chemin lastIndiv = null;
    private Stats() {}

    public static Stats getInstance() {
        if(instance == null) instance = new Stats();
        return instance;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void onNewGeneration(Population population) {
//        System.out.println(population.getBest().getFitness()+", "+population.getAverageFitness());

    	System.out.println(population.size());
        Chemin indiv = (Chemin) population.getBest();
        if(lastIndiv != null && !lastIndiv.equals(indiv)) {
            Representation.getInstance().drawChemin(indiv);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
      lastIndiv = indiv;

    }
    public void onStart() {}
    public void onFinish(Individu individu, int nbIterations) {
        System.out.println(nbIterations);
    }
    public void afterSelection(Population population) {}
    public void onChildrenGeneration(Population population) {}
}
