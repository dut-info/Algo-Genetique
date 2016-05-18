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

    private Stats() {}

    public static Stats getInstance() {
        if(instance == null) instance = new Stats();
        return instance;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void onNewGeneration(Population population) {

        Representation.getInstance().drawChemin((Chemin) population.getBest());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void afterSelection(Population population) {}
    public void onChildrenGeneration(Population population) {}
}
