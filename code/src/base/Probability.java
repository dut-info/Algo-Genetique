package base;

/**
 * Created by corentin on 05/05/16.
 */
public class Probability {

    /**
     * Retourne un nombre aléatoire entre min et max
     * avec une équiprobalbilité
     * @param min
     * @param max
     * @return
     */
    public static int between(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static double between(double min, double max) {
        return min + (Math.random() * (max - min));
    }

    /**
     * Retourne true avec une propabilité de 'rate'
     * sinon retourne false
     * @param rate
     * @return boolean
     */
    public static boolean rate(double rate) {
        if(rate < 0 || rate > 1) throw new IllegalArgumentException("Rate should be between 0 and 1");
        if(rate == 0) return false;
        else if(rate == 1) return true;
        else return 0 == (int)between(0, (1/rate)-1.0);
    }
}
