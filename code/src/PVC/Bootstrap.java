package PVC;

import base.AlgorithmeGenetique;
import base.Selection;
import base.Stats;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by corentin on 25/05/16.
 */
public class Bootstrap extends JFrame implements ChangeListener, MouseListener {
    private JPanel panel1;
    private JButton lancerButton;
    private JComboBox<Selection> methodeSelection;
    private JSlider tauxMutation;
    private JRadioButton aleatoireRadioButton;
    private JRadioButton enCercleRadioButton;
    private JLabel tauxMutationLabel;
    private JSpinner nbVilles;
    private JSpinner nbIndividus;
    private JSpinner nbIterations;
    private JSpinner showSteps;

    public Bootstrap() {
        super("Configuration");
        setContentPane(panel1);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        lancerButton.addMouseListener(this);
        tauxMutation.addChangeListener(this);
        nbVilles.setValue(20);
        nbIterations.setValue(500);
        nbIndividus.setValue(500);
    }

    private void createUIComponents() {
        Selection selections[] = {
                new SelectionRoulette(0.5),
                new SelectionElitisme(0.5),
                new SelectionTournoi(0.5)
        };
        methodeSelection = new JComboBox<>(selections);
    }

    private void launch() throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println(this.nbIterations.getValue());
        int nbVilles = (int) this.nbVilles.getValue();
        int nbIterations = (int) this.nbIterations.getValue();
        int nbIndiv = (int) this.nbIndividus.getValue();

        Stats stats = Stats.getInstance();
        stats.setOutputStream(System.out);
        System.out.println((Integer) this.showSteps.getValue());
        stats.showSteps((Integer) this.showSteps.getValue());

        ArrayList<Ville> villes = new ArrayList<>();

        if(this.aleatoireRadioButton.isSelected()) {
            // Géneration de 10 villes aléatoires
            for (int i = 0; i < nbVilles; i++) {
                villes.add(new Ville());
            }
        } else {
            // Géneration de 10 villes en cercle
            double teta = 2* Math.PI / nbVilles;
            double rayon = 30;
            for (int i = 0; i < nbVilles; i++) {
                double angle = i*teta;
                villes.add(new Ville(50+ (rayon * Math.cos(angle)), 50+(rayon * Math.sin(angle))));
            }
        }

        Representation repre = Representation.getInstance();
        repre.setVilles(villes);

        Selection selectionElitisme = (Selection) methodeSelection.getSelectedItem();
        base.Croisement croisement = new PVC.Croisement();
        Mutation mutation = new Mutation((double) tauxMutation.getValue()/100);

        AlgorithmeGenetique algo = new AlgorithmeGenetique(Chemins.class, selectionElitisme, croisement, mutation, nbIterations, nbIndiv);

        PVC.Chemin best = (PVC.Chemin) algo.run(villes);
        System.out.println(best);

        repre.drawChemin(best);
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        double tM = tauxMutation.getValue();
        tauxMutationLabel.setText(String.valueOf(tM/100));
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        try {
            this.launch();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    public static void main(String args[]) {
        new Bootstrap();
    }
}
