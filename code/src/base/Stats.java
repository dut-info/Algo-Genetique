package base;

import PVC.Chemin;
import PVC.Representation;

import java.io.*;

/**
 * Created by corentin on 18/05/16.
 */
public class Stats {

    private static Stats instance = null;
    private static int column = 0;
    private Writer outputStream;
    private Chemin lastIndiv = null;
    private Object[][] data;
    private Integer showSteps = null;
    private Stats() {}

    public static Stats getInstance() {
        if(instance == null) {
            instance = new Stats();
            instance.data = new Object[501][51];
        }

        return instance;
    }

    public void showSteps(int ms) {
        this.showSteps = ms;
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

//        this.data[generationNumber][0] = generationNumber;
//        this.data[generationNumber][column] = population.getBest().getFitness();

        if(this.showSteps != null && this.showSteps != 0) {
            Chemin indiv = (Chemin) population.getBest();
            if(lastIndiv != null && !lastIndiv.equals(indiv)) {
                Representation.getInstance().drawChemin(indiv);
                try {
                    Thread.sleep(showSteps);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lastIndiv = indiv;
        }
    }
    public void onStart() {
        column++;
    }
    public void onFinish(Individu individu, int nbIterations) {
        System.out.print(nbIterations+";");
/*
        String[] columns = new String[51];
        columns[0] = "Generation num";
        for (int i = 1; i < 51; i++) {
            columns[i] = "Iteration " + i;
        }

        TableModel model = new DefaultTableModel(data, columns);

        final File file = new File("stats/stats.ods");
        try {
            SpreadSheet.createEmpty(model).saveAs(file);
            //OOUtils.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }
    public void afterSelection(Population population) {}
    public void onChildrenGeneration(Population population) {}
}
