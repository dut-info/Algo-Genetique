import javax.sound.sampled.Line;
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

    public Representation(ArrayList<Ville> villes)
    {
        this.villes = villes;
        JFrame jFrame = new JFrame();
        container = jFrame.getContentPane();

        this.setPreferredSize(new Dimension(width, height));
        container.add(this);

        jFrame.setTitle("Voyageur de commerce");
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int r = 100;
        for(Ville ville : villes) {
            g2.drawOval(map(ville.x - r),map(ville.y - r), r, r);
        }


        int villeSize = chemin.size();
        for (int i = 0; i < villeSize; i++) {
            Ville ville = (Ville) chemin.get(i);
            Ville nextVille = (Ville) chemin.get((i + 1) % villeSize);
            g.drawLine(map(ville.x), map(ville.y), map(nextVille.x), map(nextVille.y));
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
