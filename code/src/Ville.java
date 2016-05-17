import base.Gene;

/**
 * Created by corentin on 17/04/16.
 */
public class Ville implements Gene {
    private Integer num;
    public double x;
    public double y;
    private static int currentNumber = 0;

    public Ville(int num, double x, double y)
    {
        this.num = num;
        this.x = x;
        this.y = y;
    }

    public Ville(int num)
    {
        this(num, Math.random()*100, Math.random()*100);
    }

    public Ville()
    {
        this(++currentNumber);
    }

    /**
     * Calcule la distance entre deux villes
     * @param v
     * @return
     */
    public double distance(Ville v)
    {
        return Math.sqrt(Math.pow(this.x - v.x, 2) + Math.pow(this.y - v.y, 2));
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Ville) {
            Ville v = (Ville) o;
            return v.num == this.num;
        }
        return false;
    }

    public String toString()
    {
        return num.toString();
    }
}
