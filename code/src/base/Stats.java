package base;

import PVC.Chemin;
import PVC.Representation;

import java.io.*;

/**
 * Created by corentin on 18/05/16.
 */
public class Stats {

    private static Stats instance = null;
    private Writer outputStream;
    private Chemin lastIndiv = null;
    private Stats() {}

    public static Stats getInstance() {
        if(instance == null) instance = new Stats();
        return instance;
    }

    public void setOutputStream(OutputStream outputStream) throws UnsupportedEncodingException {
        this.outputStream = new OutputStreamWriter(outputStream, "UTF-8");
    }

    public void write(String msg) {
        try {
            outputStream.write(msg);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeln(String msg) {
        this.write(msg+"\n");
    }

    public void write(Integer msg) {
        try {
            outputStream.write(msg);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeln(Integer msg) {
        this.write(msg+"\n");
    }

    public void onNewGeneration(Population population, int generationNumber) {
        this.writeln(generationNumber + "; " + population.size() + "; " + population.getBest().getFitness() + ";");
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
    public void onStart() {
        this.writeln("\"generation\"; \"size\"; \"best fitness\";");
    }
    public void onFinish(Individu individu, int nbIterations) {

    }
    public void afterSelection(Population population) {}
    public void onChildrenGeneration(Population population) {}
}
