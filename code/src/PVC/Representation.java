package PVC;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by corentin on 15/05/16.
 */
public class Representation extends JComponent {

    private Container container;
    private ArrayList<Ville> villes;
    private Chemin chemin;
    private int width = 700;
    private int height = 700;

    private static Representation instance = null;

    private Representation()
    {
        JFrame jFrame = new JFrame();
        container = jFrame.getContentPane();

        this.setPreferredSize(new Dimension(width, height));
        container.add(this);

        jFrame.setTitle("Voyageur de commerce");
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
    }

    public static Representation getInstance() {
        if(instance == null) instance = new Representation();
        return instance;
    }

    public void setVilles(ArrayList<Ville> villes) {
        this.villes = villes;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(this.villes != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            int r = 7;
            for(Ville ville : villes) {
                g2.fillOval(map(ville.x)-r/2,map(ville.y)-r/2, r, r);
            }
        }

        if(chemin != null) {
            int villeSize = chemin.size();
            for (int i = 0; i < villeSize; i++) {
                Ville ville = (Ville) chemin.get(i);
                Ville nextVille = (Ville) chemin.get((i + 1) % villeSize);
                g.drawLine(map(ville.x), map(ville.y), map(nextVille.x), map(nextVille.y));
            }
        }
    }

    private int map(double a) {
        Dimension d = this.getSize();

        return (int) a * (int)d.getHeight()/100;
    }

    public void drawChemin(Chemin chemin) {
        this.chemin = chemin;
        repaint();
    }
}
